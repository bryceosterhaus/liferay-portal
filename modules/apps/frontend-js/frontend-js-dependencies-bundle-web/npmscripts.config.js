/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

module.exports = {
	build: {
		exports: [
			"clipboard",
			{
				includeInternalPaths: [
					'/addon/display/placeholder',
					'/addon/display/autorefresh',
					'/addon/edit/closebrackets',
					'/addon/edit/closetag',
					'/addon/edit/matchbrackets',
					'/addon/fold/brace-fold',
					'/addon/fold/comment-fold',
					'/addon/fold/foldcode',
					'/addon/fold/foldgutter.css',
					'/addon/fold/foldgutter',
					'/addon/fold/indent-fold',
					'/addon/fold/xml-fold',
					'/addon/hint/css-hint',
					'/addon/hint/html-hint',
					'/addon/hint/javascript-hint',
					'/addon/hint/show-hint.css',
					'/addon/hint/show-hint',
					'/addon/hint/xml-hint',
					'/mode/groovy/groovy',
					'/lib/codemirror.css',
					'/mode/css/css',
					'/mode/htmlmixed/htmlmixed',
					'/mode/javascript/javascript',
					'/mode/xml/xml',
				],
				name: "codemirror"
			},
			"dagre",
			"date-fns",
			{
				format: 'esm',
				name: "dom-align",
				symbols: [
					'alignElement',
					'alignPoint'
				]
			},
			"fuzzy",
			"moment",
			"qrcode",
			'react-router-dom',
			"react-flow-renderer",
			"react-transition-group",
			"text-mask-core",
			{name: "uuid", symbols: 'auto'}
		],
		main: 'src/main/resources/META-INF/resources/js/index.ts',
	},
};
