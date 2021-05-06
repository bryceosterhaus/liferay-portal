/**
 * SPDX-FileCopyrightText: © 2019 Liferay, Inc. <https://liferay.com>
 * SPDX-License-Identifier: BSD-3-Clause
 */

import React from 'react';
declare const ReactPortal: React.FunctionComponent<
	React.HTMLAttributes<HTMLDivElement> & {

		/**
		 * Element to render portal into.
		 */
		container?: Element;

		/**
		 * Ref of element to render nested portals into.
		 */
		subPortalRef?: React.RefObject<Element>;
	}
>;
export default ReactPortal;
