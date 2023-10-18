/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import {IFDSTableProps} from '../../utils/fds';
interface FieldsProps extends IFDSTableProps {
	baseResourceURL: string;
}
export default function Fields({
	apiURL,
	baseResourceURL,
	creationMenu,
	formName,
	id,
	items,
	objectDefinitionExternalReferenceCode,
	style,
	url,
}: FieldsProps): React.JSX.Element;
export {};
