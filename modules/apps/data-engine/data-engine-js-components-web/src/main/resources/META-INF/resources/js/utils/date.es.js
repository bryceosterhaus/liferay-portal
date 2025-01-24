/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {dateUtils} from 'frontend-js-web';

export function formatDate(date, locale) {
	return dateUtils.format(date, 'P', locale);
}

export function parseDate(locale, value) {
	return dateUtils.parse(value, 'P', locale);
}
