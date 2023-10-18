/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {SegmentExperience} from '@liferay/layout-js-components-web';
import React from 'react';
import SegmentEntry from '../../types/SegmentEntry';
interface Props {
	deactivateSimulationURL: string;
	namespace: string;
	portletNamespace: string;
	segmentationEnabled: boolean;
	segmentsCompanyConfigurationURL: string;
	segmentsEntries: SegmentEntry[];
	segmentsExperiences: SegmentExperience[];
	simulateSegmentsEntriesURL: string;
}
declare function PageContentSelectors({
	deactivateSimulationURL,
	namespace,
	segmentationEnabled,
	segmentsCompanyConfigurationURL,
	segmentsEntries,
	segmentsExperiences,
	simulateSegmentsEntriesURL,
}: Props): React.JSX.Element;
export default PageContentSelectors;
