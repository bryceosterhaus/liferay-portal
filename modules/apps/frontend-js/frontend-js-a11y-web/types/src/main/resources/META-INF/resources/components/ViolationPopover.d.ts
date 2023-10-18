/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './ViolationPopover.scss';
import React from 'react';
import type {RuleRaw} from '../hooks/useA11y';
declare type ViolationProps = {
	onClick: (target: string, id: string) => void;
	rules: Record<string, RuleRaw>;
	target: string;
	violations: Array<string>;
};
export declare function ViolationPopover({
	onClick,
	rules,
	target,
	violations,
}: ViolationProps): React.JSX.Element | null;
export {};
