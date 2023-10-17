/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import {DropDownItems} from '../types';
import './ObjectDefinitionNodeHeader.scss';
interface ObjectDefinitionNodeHeaderProps {
	dbTableName: string | undefined;
	dropDownItems: DropDownItems[];
	handleSelectObjectDefinitionNode: () => void;
	isLinkedObjectDefinition: boolean;
	objectDefinitionLabel: string;
	status: {
		code: number;
		label: string;
		label_i18n: string;
	};
	system: boolean;
}
export default function ObjectDefinitionNodeHeader({
	dbTableName,
	dropDownItems,
	handleSelectObjectDefinitionNode,
	isLinkedObjectDefinition,
	objectDefinitionLabel,
	status,
	system,
}: ObjectDefinitionNodeHeaderProps): React.JSX.Element;
export {};
