/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import './ScriptManagementContainer.scss';
export declare type GroovyScriptUseItem = {
	companyWebId: string;
	sourceName: string;
	sourceURL: string;
};
interface ScriptManagementContainerProps {
	allowScriptContentToBeExecutedOrIncluded: boolean;
	baseResourceURL: string;
	scriptManagementConfigurationDefined: boolean;
}
export default function ScriptManagementContainer({
	allowScriptContentToBeExecutedOrIncluded,
	baseResourceURL,
	scriptManagementConfigurationDefined,
}: ScriptManagementContainerProps): React.JSX.Element;
export {};
