/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import './Attachments.scss';
interface AttachmentsProps {
	objectDefinitions: ObjectDefinition[];
	setValues: (values: Partial<NotificationTemplate>) => void;
	values: Partial<NotificationTemplate>;
}
export declare function Attachments({
	objectDefinitions,
	setValues,
	values,
}: AttachmentsProps): React.JSX.Element;
export {};
