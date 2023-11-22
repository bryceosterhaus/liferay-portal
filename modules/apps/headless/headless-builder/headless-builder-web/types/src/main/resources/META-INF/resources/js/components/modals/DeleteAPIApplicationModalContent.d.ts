/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
interface DeleteAPIApplicationModal {
	closeModal: voidReturn;
	itemData: APIApplicationItem;
	loadData: voidReturn;
}
export declare function DeleteAPIApplicationModalContent({
	closeModal,
	itemData,
	loadData,
}: DeleteAPIApplicationModal): React.JSX.Element;
export {};
