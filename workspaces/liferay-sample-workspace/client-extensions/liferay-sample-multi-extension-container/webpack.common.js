import CopyPlugin from "copy-webpack-plugin"
import path from 'path';
import fs from 'fs';
import { fileURLToPath } from 'url';
import {createRequire} from 'module'
import MiniCssExtractPlugin from 'mini-css-extract-plugin';

const require = createRequire(import.meta.url);

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export default {
	entry: {
		"Header": "./src/custom-elements/Header",
		"Sidebar": "./src/custom-elements/Sidebar",
		"FDSIconCell": "./src/fds-cells/FDSIconCell",
		"shared-utils": "./src/shared-utils/index"
	},
	plugins: [
		new CopyPlugin({
			patterns: [
				{
					from: "src/icons/**/*.svg",
					to: "spritemap.svg",
					transformAll(svgs) {		
						return generateSpritemap(svgs);
					},
				},
			],
		}),
		new MiniCssExtractPlugin()
	],
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
				test: /\.js$/,
				exclude: /node_modules/,
				use: {
					loader: 'babel-loader',
				},
			}
		],
	},
	resolve: {
		alias: {
			'shared-utils': path.resolve(__dirname, 'src/shared-utils/index.ts')
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

const HEADER_REGEXP = /<!--(.*)-->/s;

function generateSpritemap(svgAssets) {
	let spritemapContent =
		'<?xml version="1.0" encoding="UTF-8"?>' +
		'<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">' +
		'<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">';

	const claySpritemapPath = require.resolve(
		'@clayui/css/lib/images/icons/icons.svg'
	);

	const claySpritemapContent = fs.readFileSync(claySpritemapPath, 'utf8');

	spritemapContent = claySpritemapContent
		.replace('</svg>', '')
		.replace(/\n/gm, '')
		.replace(/\t/gm, '');

	const iconsReplaced = [];

	for (const svgAsset of svgAssets) {
		const {data, sourceFilename} = svgAsset;

		const content = data.toString();

		const fileName = path.basename(sourceFilename, '.svg');

		// Remove existing Clay icons that duplicate our new icon names

		const existingSymbolRegex = new RegExp(
			`<symbol id="${fileName}".*?</symbol>`,
			'gm'
		);

		if (existingSymbolRegex.test(spritemapContent)) {
			spritemapContent = spritemapContent.replace(existingSymbolRegex, '');

			iconsReplaced.push(fileName);
		}


		const svgAttributesExec = /<svg\s+([^>]+)>/gm.exec(content);

		let svgAttributes = svgAttributesExec ? svgAttributesExec[1] : '';

		svgAttributes = svgAttributes
			.replace(/id=".*"?/, '')
			.replace(/xmlns="http:\/\/www\.w3\.org\/2000\/svg"/gm, ``);

		spritemapContent += content
			.replace(HEADER_REGEXP, '')
			.replace(/<svg.*?>/gm, `<symbol id="${fileName}" ${svgAttributes}>`)
			.replace(/<\/svg/gm, '</symbol')
			.replace(/\n/gm, '')
			.replace(/\t/gm, '');
	}

	spritemapContent += '</svg>';

	return spritemapContent;
}