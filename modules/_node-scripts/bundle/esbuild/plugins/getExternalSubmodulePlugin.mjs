/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import path from 'path';

export default function getSubmoduleExternalPlugin(
	submodules,
	projectWebContextPath
) {
	return {
		name: 'external-submodule-plugin',

		setup(build) {
			Object.keys(submodules).forEach((submoduleName) => {
				const submodulePath = submodules[submoduleName];

				const regexp = new RegExp(submoduleName);

				build.onResolve({filter: regexp}, (args) => {
					if (args.kind === 'import-statement') {
						const importPath = path.join(
							args.resolveDir,
							args.path.endsWith('.js')
								? args.path
								: `${args.path}.js`
						);

						if (importPath.endsWith(submodulePath)) {
							return {
								external: true,
								path: `../..${projectWebContextPath}/__liferay__/${submoduleName}.js`,
							};
						}
					}
				});
			});
		},
	};
}
