/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import CodeMirror from 'codemirror';
import React, {RefObject} from 'react';
import './Sidebar.scss';
interface SidebarElement {
	content: string;
	helpText?: string;
	label: string;
	tooltip?: string;
}
export interface SidebarCategory {
	items: SidebarElement[];
	label: string;
}
interface IProps {
	CustomSidebarContent?: React.ReactNode;
	editorRef: RefObject<CodeMirror.Editor>;
	elements: SidebarCategory[];
	elementsDisabled?: boolean;
	otherProps?: unknown;
}
export declare function Sidebar({
	CustomSidebarContent,
	editorRef,
	elements,
	elementsDisabled,
}: IProps): React.JSX.Element;
export {};
