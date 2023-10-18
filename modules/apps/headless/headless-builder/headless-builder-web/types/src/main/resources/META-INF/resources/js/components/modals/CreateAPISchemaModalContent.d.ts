/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {Dispatch, SetStateAction} from 'react';
interface CreateAPISchemaModalProps {
	apiSchemasURLPath: string;
	closeModal: voidReturn;
	currentAPIApplicationId: string | null;
	loadData: voidReturn;
	setMainSchemaNav: Dispatch<SetStateAction<MainNav>>;
}
export declare function CreateAPISchemaModalContent({
	apiSchemasURLPath,
	closeModal,
	currentAPIApplicationId,
	loadData,
	setMainSchemaNav,
}: CreateAPISchemaModalProps): React.JSX.Element;
export {};
