import path from 'path';
import { fileURLToPath } from 'url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export default {
	entry: './src/index.tsx',
	resolve: {
		alias: {
			'shared-utils': path.resolve(__dirname, '../shared-utils/src/index.ts')
		},
		extensions: ['.tsx', '.ts', '.js'],
	},
	experiments: {
		outputModule: true,
	},
	output: {
		filename: '[name].js',
		environment: {
			dynamicImport: true,
			module: true,
		},
		library: {
			type: 'module',
		},
		path: path.resolve(__dirname, 'dist'),
		module: true,
	},
};