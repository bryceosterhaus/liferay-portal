/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {parse as parseTS} from '@typescript-eslint/typescript-estree';
import {parse as parseJS} from 'acorn';
import estraverse from 'estraverse';
import fs from 'fs/promises';
import path from 'path';
import resolve from 'resolve';

import projectScopeRequire from './projectScopeRequire.mjs';

export default async function getExportedSymbols(
	overridenPackageSymbols,
	moduleName
) {
	let symbols;

	try {
		if (overridenPackageSymbols[moduleName]) {
			symbols = {};

			overridenPackageSymbols[moduleName].forEach((symbol) => {
				symbols[symbol] = true;
			});

			if (symbols['*']) {
				delete symbols['*'];

				const loadedSymbols = await loadSymbols(moduleName);

				Object.keys(loadedSymbols).forEach((symbol) => {
					symbols[symbol] = true;
				});
			}
		}
		else {
			symbols = await loadSymbols(moduleName);
		}
	}
	catch (error) {
		console.log('fire-', overridenPackageSymbols[moduleName]);
		throw new Error(
			`Cannot infer exported symbols for ${moduleName}: ${error}`
		);
	}

	return symbols;
}

async function loadSymbols(moduleName) {
	let module;

	try {
		module = projectScopeRequire(moduleName);
	}
	catch (error) {
		module = await parseESMExports(moduleName);
	}

	const symbols = Object.keys(module).reduce((symbols, key) => {
		symbols[key] = true;

		return symbols;
	}, {});

	// Some modules config __esModule as non-enumerable, so we explicitly check for it

	if (module.__esModule) {
		symbols.__esModule = true;
	}

	return symbols;
}

async function parseESMExports(moduleName, projectDir = '.') {
	const modulePath = resolve.sync(moduleName, {basedir: projectDir});
	const code = await fs.readFile(modulePath, 'utf8');
	const ext = path.extname(modulePath);

	const ast =
		ext === '.ts' || ext === '.tsx'
			? parseTS(code, {
					ecmaVersion: 2022,
					jsx: ext === '.tsx',
					sourceType: 'module',
				})
			: parseJS(code, {
					ecmaVersion: 2022,
					sourceType: 'module',
				});

	const symbols = {};

	estraverse.traverse(ast, {
		enter(node) {
			switch (node.type) {
				case 'ExportAllDeclaration':
					throw new Error('Cannot infer symbols if export * is used');

				case 'ExportDefaultDeclaration':
					symbols.default = true;
					break;

				case 'ExportNamedDeclaration':
					for (const specifier of node.specifiers ?? []) {
						symbols[specifier.exported.name] = true;
					}
					break;
				default:
					break;
			}
		},
		fallback: 'iteration',
	});

	symbols.__esModule = true;

	return symbols;
}
