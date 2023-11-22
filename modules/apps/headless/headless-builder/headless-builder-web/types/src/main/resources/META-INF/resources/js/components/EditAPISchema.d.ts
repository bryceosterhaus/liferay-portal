/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {Dispatch, SetStateAction} from 'react';
import '../../css/main.scss';
interface EditAPISchemaProps {
	apiURLPaths: APIURLPaths;
	currentAPIApplicationId: string;
	schemaId: number;
	setMainSchemaNav: Dispatch<SetStateAction<MainNav>>;
	setManagementButtonsProps: Dispatch<SetStateAction<ManagementButtonsProps>>;
	setStatus: Dispatch<SetStateAction<ApplicationStatusKeys>>;
	setTitle: Dispatch<SetStateAction<string>>;
}
export default function EditAPISchema({
	apiURLPaths,
	currentAPIApplicationId,
	schemaId,
	setMainSchemaNav,
	setManagementButtonsProps,
	setStatus,
	setTitle,
}: EditAPISchemaProps): React.JSX.Element;
export {};
