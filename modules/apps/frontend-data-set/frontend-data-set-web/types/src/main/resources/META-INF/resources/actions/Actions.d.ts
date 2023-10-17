/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {IItemsActions} from '..';
import React from 'react';
declare function Actions({
	actions,
	itemData,
	itemId,
	menuActive,
	onMenuActiveChange,
}: {
	actions: Array<IItemsActions>;
	itemData: any;
	itemId: string | number;
	menuActive: boolean;
	onMenuActiveChange: Function;
}): React.JSX.Element;
export default Actions;
