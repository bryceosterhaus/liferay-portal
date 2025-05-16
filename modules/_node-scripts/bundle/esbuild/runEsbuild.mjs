/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import esbuild from 'esbuild';
import fs from 'fs/promises';
import path from 'path';

function formatBytes(byteString) {
	const bytes = parseFloat(byteString);

	if (bytes < 1024) {
		return `${bytes} B`;
	}
	else if (bytes < 1024 * 1024) {
		return `${(bytes / 1024).toFixed(2)} KB`;
	}
	else {
		return `${(bytes / (1024 * 1024)).toFixed(2)} MB`;
	}
}

export default async function runEsbuild(esbuildConfig, configName, emitStats) {
	await Promise.all([
		doRunEsbuild(esbuildConfig, configName, emitStats),
		writeDebugEsbuildConfig(esbuildConfig, configName),
	]);
}

async function doRunEsbuild(
	esbuildesbuildConfig,
	configName,
	emitStats = false
) {
	const start = performance.now();
	let buildResult;

	try {
		buildResult = await esbuild.build({
			define: {

				// Flag to use React 16 instead of React 18. See render.tsx in frontend-js-react-web.

				'process.env.USE_REACT_16': process.env.USE_REACT_16
					? 'true'
					: 'false',
			},
			metafile: emitStats,
			minify: process.env.NODE_ENV === 'production',
			...esbuildesbuildConfig,
		});
	}
	catch (error) {
		throw new Error(`Esbuild command failed: ${error}`);
	}

	const lapse = performance.now() - start;

	console.log(
		`⌛ Esbuild for ${configName} took: ${(lapse / 1000).toFixed(3)}s`
	);

	if (emitStats && buildResult?.metafile?.outputs) {
		console.table(
			Object.entries(buildResult.metafile.outputs).reduce(
				(acc, [inputPath, data]) => {
					if (!inputPath.endsWith('.map')) {
						acc[inputPath] = formatBytes(data.bytes);
					}

					return acc;
				},
				{}
			)
		);
	}
}

async function writeDebugEsbuildConfig(esbuildConfig, configName) {
	const configFilePath = path.join(
		'build',
		'node-build',
		`${configName}.esbuild.config.json`
	);

	await fs.mkdir(path.dirname(configFilePath), {recursive: true});
	await fs.writeFile(
		configFilePath,
		JSON.stringify(esbuildConfig, null, '\t'),
		'utf-8'
	);
}
