/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import resolve from 'resolve';
import ts from 'typescript';

import projectScopeRequire from './projectScopeRequire.mjs';

export default function loadSymbols(moduleName) {
	const requirePath = resolve.sync(moduleName, {basedir: '.'});

	let symbols;

	if (requirePath.endsWith('.ts') || requirePath.endsWith('.tsx')) {
		symbols = getSymbolsFromTS(requirePath);
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

function getSymbolsFromTS(fileName) {
	const program = ts.createProgram([fileName], {});

	const checker = program.getTypeChecker();
	const source = program.getSourceFile(fileName);

	const moduleSymbol = checker.getSymbolAtLocation(source);

	if (!moduleSymbol) {
		throw new Error('No module symbols found');
	}

	return checker
		.getExportsOfModule(moduleSymbol)
		.reduce((acc, symbol) => ({...acc, [symbol.escapedName]: true}), {});
}
