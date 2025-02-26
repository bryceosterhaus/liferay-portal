/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {$} from 'execa';
import fs from 'fs/promises';
import path from 'path';

import getYarnWorkspaceProjects from '../util/getYarnWorkspaceProjects.mjs';
import runConcurrentTasks from '../util/runConcurrentTasks.mjs';

export default async function all() {
	const projects = await getYarnWorkspaceProjects();

	const testableProjects = [];

	/**
	 * Filter out projects that do not have `node-scripts test`
	 */
	for (const projectPath of projects) {
		const packageJson = path.join(projectPath, 'package.json');
		const pkgJsonContents = await fs.readFile(packageJson, 'utf8');

		if (
			pkgJsonContents.includes('node-scripts test') &&
			!pkgJsonContents.includes('node-scripts test:all')
		) {
			testableProjects.push(projectPath);
		}
	}

	console.log(`ℹ️ Testing ${testableProjects.length} projects.`);

	const tasks = testableProjects.map((project) => async () => {
		console.log(`🧪 Testing ${project}`);

		const {failed} = await $({
			cwd: project,
			env: {
				...process.env,
				NODE_ENV: 'test',
			},
			reject: false,
			stdio: 'pipe',
		})`yarn run test`;

		console.log(
			`${!failed ? '✅ PASSED' : '❌ FAILED'} ${path.basename(project)}`
		);

		return all;
	});

	await runConcurrentTasks(tasks);
}
