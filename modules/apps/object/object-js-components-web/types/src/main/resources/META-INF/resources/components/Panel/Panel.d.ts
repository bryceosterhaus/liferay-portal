/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import './Panel.scss';
interface PanelProps extends React.HTMLAttributes<HTMLElement> {
	children: React.ReactNode;
}
export declare function Panel({
	children,
	className,
	...otherProps
}: PanelProps): React.JSX.Element;
export {};
