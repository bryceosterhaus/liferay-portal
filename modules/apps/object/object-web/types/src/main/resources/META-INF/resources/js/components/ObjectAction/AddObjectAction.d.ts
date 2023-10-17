/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {SidebarCategory} from '@liferay/object-js-components-web';
import React from 'react';
interface AddObjectActionProps {
	allowScriptContentToBeExecutedOrIncluded: boolean;
	apiURL: string;
	objectActionCodeEditorElements: SidebarCategory[];
	objectActionExecutors: ObjectActionTriggerExecutorItem[];
	objectActionTriggers: ObjectActionTriggerExecutorItem[];
	objectDefinitionExternalReferenceCode: string;
	objectDefinitionId: number;
	objectDefinitionsRelationshipsURL: string;
	scriptManagementConfigurationPortletURL: string;
	systemObject: boolean;
	validateExpressionURL: string;
}
export default function AddObjectAction({
	allowScriptContentToBeExecutedOrIncluded,
	apiURL,
	objectActionCodeEditorElements,
	objectActionExecutors,
	objectActionTriggers,
	objectDefinitionExternalReferenceCode,
	objectDefinitionId,
	objectDefinitionsRelationshipsURL,
	scriptManagementConfigurationPortletURL,
	systemObject,
	validateExpressionURL,
}: AddObjectActionProps): React.JSX.Element;
export {};
