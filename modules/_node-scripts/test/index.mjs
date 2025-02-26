/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import runJest from './jest/runJest.mjs';

export default async function () {
	await runJest({
		cliFlags: process.argv.slice(3),
		cwd: process.cwd(),
		execaConfig: {
			stdio: 'inherit',
		},
	});
}
