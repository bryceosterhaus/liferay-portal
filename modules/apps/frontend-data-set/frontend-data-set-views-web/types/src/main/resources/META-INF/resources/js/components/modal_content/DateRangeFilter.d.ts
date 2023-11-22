/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
declare function Header(): React.JSX.Element;
interface IBodyProps {
	from: string;
	isValidDateRange: boolean;
	namespace: string;
	onFromChange: (val: string) => void;
	onToChange: (val: string) => void;
	onValidDateChange: (val: boolean) => void;
	to: string;
}
declare function Body({
	from,
	isValidDateRange,
	namespace,
	onFromChange,
	onToChange,
	onValidDateChange,
	to,
}: IBodyProps): React.JSX.Element;
declare const _default: {
	Body: typeof Body;
	Header: typeof Header;
};
export default _default;
