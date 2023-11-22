/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {Dispatch, SetStateAction} from 'react';
interface SidebarBodyProps {
	fectchedObjectDefinitions: ObjectDefinitionsRelationshipTree;
	navHistory: AddedObjectDefinition[][];
	schemaUIData: APISchemaUIData;
	searchKeyword: string;
	setNavHistory: Dispatch<SetStateAction<AddedObjectDefinition[][]>>;
	setSchemaUIData: Dispatch<SetStateAction<APISchemaUIData>>;
}
export default function SidebarBody({
	fectchedObjectDefinitions,
	navHistory,
	schemaUIData,
	searchKeyword,
	setNavHistory,
	setSchemaUIData,
}: SidebarBodyProps): React.JSX.Element;
export {};
