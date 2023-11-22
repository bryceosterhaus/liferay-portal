/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
interface IProps {
	ariaLabel: string;
	companyId: number;
	disabled: boolean;
	enabled: boolean;
	featureFlagKey: string;
	inputName: string;
	onItemsChange: (value: Array<any>) => void;
}
declare const FeatureFlagToggle: ({
	ariaLabel,
	companyId,
	disabled,
	enabled,
	featureFlagKey,
	inputName,
	onItemsChange,
}: IProps) => React.JSX.Element;
export default FeatureFlagToggle;
