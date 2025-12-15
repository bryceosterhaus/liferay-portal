/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import fs from 'fs';
import glob from 'glob';
import path from 'path';
import ts from 'typescript';

const ROOT = path.resolve('./clay');

function getExports(entry) {
	const program = ts.createProgram([entry], {
		target: ts.ScriptTarget.ESNext,
		module: ts.ModuleKind.ESNext,
	});

	const checker = program.getTypeChecker();
	const source = program.getSourceFile(entry);
	if (!source) {
		return [];
	}

	const moduleSymbol = checker.getSymbolAtLocation(source);
	if (!moduleSymbol) {
		return [];
	}

	return checker.getExportsOfModule(moduleSymbol).map((s) => s.getName());
}

const result = {};

for (const dir of fs.readdirSync(ROOT)) {
	const pkgDir = path.join(ROOT, dir);
	const pkgJsonPath = path.join(pkgDir, 'package.json');
	if (!fs.existsSync(pkgJsonPath)) {
		continue;
	}

	const pkg = JSON.parse(fs.readFileSync(pkgJsonPath, 'utf8'));
	if (!pkg['main']) {
		continue;
	}

	const entry = path.resolve(pkgDir, pkg['main']);
	const exports = getExports(entry);

	result[pkg.name] = exports.includes('default') ? exports : [...exports];
}

console.log(result);
