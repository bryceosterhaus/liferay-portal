/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {Ref} from 'react';
interface RuleBuilderItemProps {
	children: React.ReactNode;
	onDeleteButtonClick: () => void;
	showDeleteButton: boolean;
	type: 'action' | 'condition';
	wrapperRef?: Ref<HTMLDivElement>;
}
export default function RuleBuilderItem({
	children,
	onDeleteButtonClick,
	showDeleteButton,
	type,
	wrapperRef,
}: RuleBuilderItemProps): React.JSX.Element;
export {};
