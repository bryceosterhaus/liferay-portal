import { merge } from 'webpack-merge';
import common from './webpack.common.js';

const port = 3000;

export default merge(common, {
	mode: 'development',
	devtool: 'inline-source-map',
	devServer: {
		allowedHosts:'all',
		port: port,
		hot: true,
		headers: {
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Methods': '*',
			'Access-Control-Allow-Headers':
				'X-Requested-With, content-type, Authorization',
		},
		liveReload: true,
		watchFiles: [
			'src/**/*.tsx',
			'src/**/*.ts',
			'src/**/*.scss',
			'src/**/*.css',
			'src/**/*.js',
			'src/**/*.jsx',
		],
		open: false,
	},
	module: {
		rules: [
			{
				test: /\.tsx?$/,
				exclude: [/node_modules/],
				use: {
					loader: 'ts-loader'
				},
			},
		],
	},
	output: {
		publicPath: `http://localhost:${port}/`,
	},
});
