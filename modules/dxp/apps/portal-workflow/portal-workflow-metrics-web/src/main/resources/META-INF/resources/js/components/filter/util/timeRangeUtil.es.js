/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {dateUtils} from 'frontend-js-web';

import {
	defaultDateFormat,
	formatDate,
	getLocaleDateFormat,
	isValidDate,
} from '../../../shared/util/date.es';

const convertQueryDate = (date = '', format = 'P') => {
	return dateUtils.format(
		new Date(decodeURIComponent(date)),
		format,
		'en-US'
	);
};

const parseDateMoment = (date, format = 'P') => {
	return dateUtils.parse(date, format, 'en-US');
};

const formatDateTime = (date, format, isEndDate) => {
	let dateTime = parseDateMoment(date, format || 'P');

	dateTime = isEndDate ? dateTime.endOf('day') : dateTime.startOf('day');

	return dateTime.format(defaultDateFormat);
};

const formatDescriptionDate = (date) => {
	return formatDate(
		decodeURIComponent(date),
		getLocaleDateFormat('ll'),
		defaultDateFormat
	);
};

const getFormatPattern = (dateEnd, dateStart, isAmPm) => {
	let dateStartPattern = 'MMM dd, yyyy';

	const daysDiff = dateEnd.getDate() - dateStart.getDate();

	if (daysDiff <= 1) {
		if (isAmPm) {
			dateStartPattern = 'MMM dd, hh:mm a';
		}
		else {
			dateStartPattern = 'MMM dd, HH:mm';
		}
	}
	else if (dateEnd.getFullYear() - dateStart.getFullYear() < 1) {
		dateStartPattern = 'MMM dd';
	}

	let dateEndPattern = dateStartPattern;

	if (daysDiff > 90) {
		dateEndPattern = 'MMM dd, yyyy';
	}

	return {
		dateEndPattern,
		dateStartPattern,
	};
};

const formatTimeRange = (timeRange, isAmPm) => {
	const {dateEnd, dateStart} = timeRange;

	if (!dateEnd && !dateStart) {
		return null;
	}

	const {dateEndPattern, dateStartPattern} = getFormatPattern(
		dateEnd,
		dateStart,
		isAmPm
	);

	return `${dateUtils.format(
		dateStart,
		dateStartPattern
	)} - ${dateUtils.format(dateEnd, dateEndPattern)}`;
};

const getCustomTimeRange = (dateEnd, dateStart) => {
	const customTimeRange = {
		active: false,
		dateEnd: dateEnd ? decodeURIComponent(dateEnd) : undefined,
		dateStart: dateStart ? decodeURIComponent(dateStart) : undefined,
		dividerAfter: true,
		id: 'custom',
		name: Liferay.Language.get('custom-range'),
	};

	customTimeRange.resultName = `${formatDescriptionDate(
		dateStart
	)} - ${formatDescriptionDate(dateEnd)}`;

	return customTimeRange;
};

const getTimeRangeParams = (dateStartEncoded = '', dateEndEncoded = '') => {
	let params = {};

	const dateEnd = decodeURIComponent(dateEndEncoded);
	const dateStart = decodeURIComponent(dateStartEncoded);

	if (
		isValidDate(dateEnd, defaultDateFormat) &&
		isValidDate(dateStart, defaultDateFormat)
	) {
		params = {
			dateEnd,
			dateStart,
		};
	}

	return params;
};

const parseDateItems = (isAmPm) => (items) => {
	return items.map((item) => {
		const parsedItem = {
			...item,
			dateEnd: item.dateEnd,
			dateStart: item.dateStart,
			key: item.key,
		};

		if (parsedItem.key !== 'custom') {
			parsedItem.description = formatTimeRange(item, isAmPm);
		}

		return parsedItem;
	});
};

export {
	convertQueryDate,
	formatDateTime,
	formatDescriptionDate,
	formatTimeRange,
	getCustomTimeRange,
	getTimeRangeParams,
	isValidDate,
	parseDateMoment,
	parseDateItems,
};
