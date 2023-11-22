/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FormError} from '@liferay/object-js-components-web';
import React from 'react';
import {NotificationTemplateError} from '../EditNotificationTemplate';
interface EmailNotificationSettingsProps {
	errors: FormError<NotificationTemplate & NotificationTemplateError>;
	selectedLocale: Locale;
	setValues: (values: Partial<NotificationTemplate>) => void;
	values: NotificationTemplate;
}
export declare function EmailNotificationSettings({
	errors,
	selectedLocale,
	setValues,
	values,
}: EmailNotificationSettingsProps): React.JSX.Element;
export {};
