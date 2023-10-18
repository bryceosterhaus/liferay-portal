/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
interface HandleCreateInModal {
	apiApplicationsURLPath: string;
	basePath: string;
	closeModal: voidReturn;
	editURL: string;
	loadData: voidReturn;
	portletId: string;
}
export declare function CreateAPIApplicationModalContent({
	apiApplicationsURLPath,
	basePath,
	closeModal,
	editURL,
	loadData,
	portletId,
}: HandleCreateInModal): React.JSX.Element;
export {};
