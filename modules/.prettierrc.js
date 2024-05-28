const liferayPlugin = require('@liferay/prettier-plugin');

module.exports = {
	bracketSpacing: false,
	endOfLine: 'lf',
	jsxSingleQuote: false,
	plugins: [liferayPlugin],
	quoteProps: 'consistent',
	singleQuote: true,
	tabWidth: 4,
	trailingComma: 'es5',
	useTabs: true,
};
