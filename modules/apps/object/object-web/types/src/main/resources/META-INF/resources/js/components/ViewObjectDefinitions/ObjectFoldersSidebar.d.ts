/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {SetStateAction} from 'react';
interface ObjectFoldersSidebarProps {
	objectFolders: ObjectFolder[];
	selectedObjectFolder: ObjectFolder;
	setSelectedObjectFolder: (
		value: SetStateAction<Partial<ObjectFolder>>
	) => void;
	setShowModal: (value: SetStateAction<ViewObjectDefinitionsModals>) => void;
}
export default function ObjectFoldersSideBar({
	objectFolders,
	selectedObjectFolder,
	setSelectedObjectFolder,
	setShowModal,
}: ObjectFoldersSidebarProps): React.JSX.Element;
export {};
