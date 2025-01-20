/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const SECONDS_IN_MINUTE = 60;
const SECONDS_IN_HOUR = 3600;
const SECONDS_IN_DAY = 86400;
const SECONDS_IN_WEEK = 604800;
const SECONDS_IN_MONTH = 2592000;
const SECONDS_IN_YEAR = 31536000;

const FIRST_DAY_OF_WEEK_MAP = {
	'ar-AE': 6,
	'ar-BH': 6,
	'ar-EG': 6,
	'ar-IQ': 6,
	'ar-JO': 6,
	'ar-KW': 6,
	'ar-LB': 6,
	'ar-OM': 6,
	'ar-QA': 6,
	'ar-SA': 1,
	'ar-SY': 6,
	'ar-YE': 6,
	'au-AU': 0,
	'cn-HK': 0,
	'cs-CZ': 1,
	'da-DK': 1,
	'de-DE': 1,
	'el-GR': 1,
	'en-CA': 0,
	'en-GB': 1,
	'en-US': 0,
	'es-ES': 1,
	'et-EE': 1,
	'fi-FI': 1,
	'fr-FR': 1,
	'hu-HU': 1,
	'id-ID': 1,
	'it-IT': 1,
	'jp-JP': 0,
	'ko-KR': 1,
	'lt-LT': 1,
	'lv-LV': 1,
	'ms-MY': 1,
	'my-MM': 0,
	'nl-NL': 1,
	'no-NO': 1,
	'ph-PH': 0,
	'pl-PL': 1,
	'pt-PT': 1,
	'ro-RO': 1,
	'ru-RU': 1,
	'sg-SG': 0,
	'sk-SK': 1,
	'sl-SI': 1,
	'sv-SE': 1,
	'th-TH': 1,
	'tr-TR': 1,
	'tw-TW': 0,
	'vi-VN': 1,
	'zh-CN': 1,
} as const;

function getFirstDayOfWeek(
	locale = Liferay.ThemeDisplay.getBCP47LanguageId() as keyof typeof FIRST_DAY_OF_WEEK_MAP
): number {
	return FIRST_DAY_OF_WEEK_MAP[locale] ?? 0;
}

function getWeekdaysShort(locale = Liferay.ThemeDisplay.getBCP47LanguageId()) {
	const weekdaysShort = Array.from({length: 7}, (_, i) => {
		const date = new Date(2025, 0, i + 5); // 2025-01-05 is a Sunday

		return date.toLocaleDateString(locale, {weekday: 'short'});
	});

	return weekdaysShort;
}

function getMonthsLong(locale = Liferay.ThemeDisplay.getBCP47LanguageId()) {
	const weekdaysShort = Array.from({length: 12}, (_, i) => {
		const date = new Date(2025, i); // 2025-01-05 is a Sunday

		return date.toLocaleDateString(locale, {month: 'long'});
	});

	return weekdaysShort;
}

function fromNow(
	date: Date,
	locale = Liferay.ThemeDisplay.getBCP47LanguageId()
) {
	const now = new Date();
	const diffInSeconds = Math.floor((now.getTime() - date.getTime()) / 1000);

	const relative = new Intl.RelativeTimeFormat(locale, {numeric: 'auto'});

	let value;
	let type: Intl.RelativeTimeFormatUnit;

	if (diffInSeconds < SECONDS_IN_MINUTE) {
		value = -diffInSeconds;
		type = 'second';
	}
	else if (diffInSeconds < SECONDS_IN_HOUR) {
		value = -Math.floor(diffInSeconds / SECONDS_IN_MINUTE);
		type = 'minute';
	}
	else if (diffInSeconds < SECONDS_IN_DAY) {
		value = -Math.floor(diffInSeconds / SECONDS_IN_HOUR);
		type = 'hour';
	}
	else if (diffInSeconds < SECONDS_IN_WEEK) {
		value = -Math.floor(diffInSeconds / SECONDS_IN_DAY);
		type = 'day';
	}
	else if (diffInSeconds < SECONDS_IN_MONTH) {
		value = -Math.floor(diffInSeconds / SECONDS_IN_WEEK);
		type = 'week';
	}
	else if (diffInSeconds < SECONDS_IN_YEAR) {
		value = -Math.floor(diffInSeconds / SECONDS_IN_MONTH);
		type = 'month';
	}
	else {
		value = -Math.floor(diffInSeconds / SECONDS_IN_YEAR);
		type = 'year';
	}

	return relative.format(value, type);
}

function subDays(date: Date, days: number) {
	date.setDate(date.getDate() - days);

	return date;
}

function subMonths(date: Date, months: number) {
	date.setMonth(date.getMonth() - months);

	return date;
}

function isValid(date: any) {
	return !isNaN((date instanceof Date ? date : new Date(date)).getTime());
}

const getDateParts = (date: Date, locale: string) => {
	const intl = new Intl.DateTimeFormat(locale, {
		day: '2-digit',
		month: '2-digit',
		year: 'numeric',
	});

	const parts = intl.formatToParts(date);

	const year = parts.find((part) => part.type === 'year')?.value;
	const month = parts.find((part) => part.type === 'month')?.value;
	const day = parts.find((part) => part.type === 'day')?.value;

	return {
		day,
		month,
		year,
	};
};

const FORMATTER_MAP = {
	'MMM dd, yyyy': (date: Date, locale: string) => {
		const intl = new Intl.DateTimeFormat(locale, {
			day: '2-digit',
			month: 'short',
			year: 'numeric',
		});

		const parts = intl.formatToParts(date);

		const day = parts.find((part) => part.type === 'day')?.value;
		const month = parts.find((part) => part.type === 'month')?.value;
		const year = parts.find((part) => part.type === 'year')?.value;

		return `${month} ${day}, ${year}`;
	},
	'MMM dd, hh:mm a': (date: Date, locale: string) => {
		const intl = new Intl.DateTimeFormat(locale, {
			day: '2-digit', // Two-digit day (e.g., "17")
			hour: '2-digit', // Two-digit hour (e.g., "01")
			hour12: true, // Use 12-hour clock with AM/PM
			minute: '2-digit', // Two-digit minute (e.g., "30")
			month: 'short', // Abbreviated month name (e.g., "Jan")
		});

		return intl.format(date);
	},
	'MMM dd, HH:mm': (date: Date, locale: string) => {
		const intl = new Intl.DateTimeFormat(locale, {
			day: '2-digit', // Two-digit day (e.g., "17")
			hour: '2-digit', // Two-digit hour (e.g., "01")
			hour12: false, // Use 12-hour clock with AM/PM
			minute: '2-digit', // Two-digit minute (e.g., "30")
			month: 'short', // Abbreviated month name (e.g., "Jan")
		});

		return intl.format(date);
	},
	'MMM dd': (date: Date, locale: string) => {
		const intl = new Intl.DateTimeFormat(locale, {
			day: '2-digit',
			month: 'short',
		});

		const parts = intl.formatToParts(date);

		const day = parts.find((part) => part.type === 'day')?.value;
		const month = parts.find((part) => part.type === 'month')?.value;

		return `${month} ${day}`;
	},
	'MMM D, h A': (date: Date) => {
		return new Intl.DateTimeFormat('en-US', {
			day: 'numeric',
			hour: 'numeric',
			hour12: true,
			minute: 'numeric',
			month: 'short',
		}).format(date);
	},

	// eslint-disable-next-line sort-keys
	'MM/dd/yyyy': (date: Date, locale: string) => {
		const {day, month, year} = getDateParts(date, locale);

		return `${month}/${day}/${year}`;
	},
	'MM-dd-yyyy': (date: Date, locale: string) => {
		const {day, month, year} = getDateParts(date, locale);

		return `${month}-${day}-${year}`;
	},
	'P': (date: Date, locale: string) => {
		return new Intl.DateTimeFormat(locale, {
			day: '2-digit',
			month: '2-digit',
			year: 'numeric',
		}).format(date);
	},
	'P p': (date: Date, locale: string) => {
		return new Intl.DateTimeFormat(locale, {
			day: '2-digit',
			hour: '2-digit',
			minute: '2-digit',
			month: '2-digit',
			second: '2-digit',
			year: 'numeric',
		}).format(date);
	},

	// same as moment's lll

	'PP p': (date: Date, locale: string) => {
		return new Intl.DateTimeFormat(locale, {
			day: 'numeric',
			hour: 'numeric',
			minute: 'numeric',
			month: 'short',
			year: 'numeric',
		}).format(date);
	},
	'p': (date: Date, locale: string) => {
		return new Intl.DateTimeFormat(locale, {
			hour: 'numeric',
			minute: 'numeric',
		}).format(date);
	},

	// eslint-disable-next-line sort-keys
	'YYYY MMM D': (date: Date) => {
		return new Intl.DateTimeFormat('en-US', {
			day: 'numeric',
			month: 'short',
			year: 'numeric',
		}).format(date);
	},
	'yyyy-MM-ddTHH:00': (date: Date) => {
		const year = date.getFullYear();
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 01-12
		const day = date.getDate().toString().padStart(2, '0'); // 01-31
		const hours = date.getHours().toString().padStart(2, '0'); // 00-23

		return `${year}-${month}-${day}T${hours}:00`;
	},
	'yyyy-MM-dd HH:mm': (date: Date) => {
		const year = date.getFullYear();
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 01-12
		const day = date.getDate().toString().padStart(2, '0'); // 01-31
		const hours = date.getHours().toString().padStart(2, '0'); // 00-23
		const minutes = date.getMinutes().toString().padStart(2, '0'); // 00-23

		return `${year}-${month}-${day} ${hours}:${minutes}`;
	},
	'YYYYMMDDHHMMSS': (date: Date) => {
		const year = date.getFullYear();
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 01-12
		const day = date.getDate().toString().padStart(2, '0'); // 01-31
		const hours = date.getHours().toString().padStart(2, '0'); // 00-23
		const minutes = date.getMinutes().toString().padStart(2, '0'); // 00-59
		const seconds = date.getSeconds().toString().padStart(2, '0'); // 00-59

		return `${year}${month}${day}${hours}${minutes}${seconds}`;
	},
	'yyyy/MM/dd': (date: Date, locale: string) => {
		const {day, month, year} = getDateParts(date, locale);

		return `${year}/${month}/${day}`;
	},
	'yyyy-MM-dd': (date: Date, locale: string) => {
		const {day, month, year} = getDateParts(date, locale);

		return `${year}-${month}-${day}`;
	},
	'yyyyMMdd': (date: Date, locale: string) => {
		const {day, month, year} = getDateParts(date, locale);

		return `${year}${month}${day}`;
	},
};

function normalizeFormat(dateString: string) {
	return dateString.replaceAll('Y', 'y').replaceAll('D', 'd');
}

function format(
	date: Date,
	format: keyof typeof FORMATTER_MAP,
	locale = Liferay.ThemeDisplay.getBCP47LanguageId()
) {
	const formatter =
		FORMATTER_MAP[normalizeFormat(format) as keyof typeof FORMATTER_MAP];

	if (!formatter) {

		// eslint-disable-next-line no-console
		console.log(`No formatter found for ${format}`);
	}

	if (!(date instanceof Date)) {
		date = new Date(date);
	}

	return formatter(date, locale);
}

const PARSER_MAP = {
	'YYYYMMDDHHmmss': (dateString: string) => {
		const year = dateString.substring(0, 4);
		const month = dateString.substring(4, 6);
		const day = dateString.substring(6, 8);
		const hours = dateString.substring(8, 10);
		const minutes = dateString.substring(10, 12);
		const seconds = dateString.substring(12, 14);

		return new Date(
			Number(year),
			Number(month) - 1,
			Number(day),
			Number(hours),
			Number(minutes),
			Number(seconds)
		);
	},
	'MM/dd/yyyy': (dateString: string) => {
		const [month, day, year] = dateString.split('/');

		return new Date(Number(year), Number(month) - 1, Number(day));
	},
	'MM-dd-yyyy': (dateString: string) => {
		const [month, day, year] = dateString.split('-');

		return new Date(Number(year), Number(month) - 1, Number(day));
	},
	'P': (dateString: string, locale: string) => {
		const dateFormat = new Intl.DateTimeFormat(locale, {
			day: '2-digit',
			month: '2-digit',
			year: 'numeric',
		})
			.format(new Date(2000, 0, 2))
			.replace('2000', 'yyyy')
			.replace('01', 'MM')
			.replace('02', 'dd');

		const day = dateString.substring(
			dateFormat.indexOf('d'),
			dateFormat.lastIndexOf('d') + 1
		);
		const month = dateString.substring(
			dateFormat.indexOf('M'),
			dateFormat.lastIndexOf('M') + 1
		);
		const year = dateString.substring(
			dateFormat.indexOf('y'),
			dateFormat.lastIndexOf('y') + 1
		);

		return new Date(Number(year), Number(month) - 1, Number(day));
	},
	'yyyy/MM/dd': (dateString: string) => {
		const [year, month, day] = dateString.split('/');

		return new Date(Number(year), Number(month) - 1, Number(day));
	},
	'yyyy-MM-dd': (dateString: string) => {
		const [year, month, day] = dateString.split('-');

		return new Date(Number(year), Number(month) - 1, Number(day));
	},
};

function parse(
	date: string,
	format: keyof typeof PARSER_MAP,
	locale = Liferay.ThemeDisplay.getBCP47LanguageId()
) {
	if (!date) {
		return;
	}

	const parser = PARSER_MAP[format];

	if (!parser) {

		// eslint-disable-next-line no-console
		console.log(`No parser found for ${format}`);
	}

	return parser(date, locale);
}

export default {
	getWeekdaysShort,
	getFirstDayOfWeek,
	getMonthsLong,
	format,
	fromNow,
	isValid,
	parse,
	subDays,
	subMonths,
};
