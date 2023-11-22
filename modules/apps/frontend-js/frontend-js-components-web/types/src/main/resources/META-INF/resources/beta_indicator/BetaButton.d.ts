/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ALIGN_POSITIONS} from '@clayui/popover';
import React from 'react';
export default function BetaButton({
	containerClassName,
	learnResourceContext,
	tooltipAlign,
}: {
	containerClassName?: string;
	learnResourceContext: object;
	tooltipAlign: typeof ALIGN_POSITIONS[number];
}): React.JSX.Element;
