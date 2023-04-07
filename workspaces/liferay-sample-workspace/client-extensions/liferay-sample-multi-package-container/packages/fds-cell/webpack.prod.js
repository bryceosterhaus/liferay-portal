import { merge } from 'webpack-merge';
import common from './webpack.common.js';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';

export default () =>
	merge(common, {
		mode: 'production',
		plugins: [
			new MiniCssExtractPlugin()
		],
		externals: {
			// npm Dependency

			"underscore": "/o/liferay-sample-multi-package-container/underscore.js",

			// Internally Shared

			"shared-utils": "/o/liferay-sample-multi-package-container/shared-utils/index.js",

			// From Liferay

			"react": '/o/frontend-js-react-web/__liferay__/exports/react.js',
			"react-dom": '/o/frontend-js-react-web/__liferay__/exports/react-dom.js'
		},
		module: {
			rules: [
				{
					test: /\.css$/i,
					exclude: /node_modules/,
					use: [MiniCssExtractPlugin.loader, 'css-loader'],
					sideEffects: true,
				},
				{
					test: /\.s[ac]ss$/i,
					exclude: /node_modules/,
					use: [MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader'],
					sideEffects: true,
				},
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
		},
		output: {
			filename: 'FDSCell.js',
		},
		experiments: {
			outputModule: true,
		},
	});
