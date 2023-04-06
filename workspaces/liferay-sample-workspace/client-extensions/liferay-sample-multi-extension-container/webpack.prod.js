import { merge } from 'webpack-merge';
import common from './webpack.common.js';

export default () =>
	merge(common, {
		mode: 'production',
		externals: {
			// npm Dependency

			"underscore": "/o/liferay-sample-multi-extension-container/underscore.js",

			// Internally Shared

			"shared-utils": "/o/liferay-sample-multi-extension-container/shared-utils.js",

			// From Liferay

			"react": '/o/frontend-js-react-web/__liferay__/exports/react.js',
			"react-dom": '/o/frontend-js-react-web/__liferay__/exports/react-dom.js'
		},
		module: {
			rules: [
				{
					test: /\.tsx?$/,
					exclude: /node_modules/,
					use: [
						{
							loader: 'ts-loader'
						},
					],
				},
			],
		}
	});
