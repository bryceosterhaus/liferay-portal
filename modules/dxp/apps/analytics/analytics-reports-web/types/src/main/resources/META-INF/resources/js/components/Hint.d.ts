/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
export declare type Position =
	| 'top'
	| 'top-left'
	| 'top-right'
	| 'bottom'
	| 'bottom-left'
	| 'bottom-right'
	| 'left'
	| 'left-top'
	| 'left-bottom'
	| 'right'
	| 'right-top'
	| 'right-bottom';
interface Props {
	message: string;
	position?: Position;
	secondary?: boolean;
	title: string;
}
export declare function Hint({
	message,
	position,
	secondary,
	title,
}: Props): React.JSX.Element;
export default Hint;
