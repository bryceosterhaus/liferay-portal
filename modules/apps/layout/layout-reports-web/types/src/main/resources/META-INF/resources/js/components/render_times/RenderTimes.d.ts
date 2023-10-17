/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {SegmentExperience} from '@liferay/layout-js-components-web';
import React from 'react';
export default function RenderTimes({
	segmentsExperiences,
	selectedSegmentsExperience,
	url,
}: {
	segmentsExperiences: SegmentExperience[];
	selectedSegmentsExperience: SegmentExperience;
	url: string;
}): React.JSX.Element;
