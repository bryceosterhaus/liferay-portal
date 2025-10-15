/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import fs from 'fs';
import resolve from 'resolve';
import ts from 'typescript';

import projectScopeRequire from './projectScopeRequire.mjs';

export default function loadSymbols(moduleName) {
	const requirePath = resolve.sync(moduleName, {basedir: '.'});

	let symbols;

	if (requirePath.endsWith('.ts') || requirePath.endsWith('.tsx')) {
		symbols = getRuntimeSymbols(requirePath);
	}
	else {
		const module = projectScopeRequire(moduleName);

		symbols = Object.keys(module).reduce((acc, key) => {
			acc[key] = true;

			return acc;
		}, {});

		if (module.__esModule) {
			symbols.__esModule = true;
		}
	}

	return symbols;
}

function getRuntimeSymbols(fileName) {
	const source = ts.createSourceFile(
		fileName,
		fs.readFileSync(fileName, 'utf8'),
		ts.ScriptTarget.ESNext,
		true
	);

	const symbols = {};

	ts.forEachChild(source, (node) => {
		if (
			(ts.isVariableStatement(node) ||
				ts.isFunctionDeclaration(node) ||
				ts.isClassDeclaration(node)) &&
			node.modifiers?.some((m) => m.kind === ts.SyntaxKind.ExportKeyword)
		) {
			if (ts.isVariableStatement(node)) {
				node.declarationList.declarations.forEach((d) => {
					symbols[d.name.getText()] = true;
				});
			}
			else if (node.name) {
				symbols[node.name.getText()] = true;
			}
		}

		if (ts.isExportDeclaration(node) && node.exportClause?.elements) {
			node.exportClause.elements.forEach((element) => {
				symbols[element.name.text] = true;
			});
		}

		if (ts.isExportAssignment(node)) {
			symbols['default'] = true;
		}
	});

	return symbols;
}
