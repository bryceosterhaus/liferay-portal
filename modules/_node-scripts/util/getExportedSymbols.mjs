/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import loadSymbols from './loadSymbols.mjs';

export default function getExportedSymbols(
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

				Object.keys(loadSymbols(moduleName)).forEach((symbol) => {
					symbols[symbol] = true;
				});
			}
		}
		else {
			symbols = loadSymbols(moduleName);
		}
	}
	catch (error) {
		throw new Error(
			`Cannot infer exported symbols for ${moduleName}: ${error}`
		);
	}

	return symbols;
}
