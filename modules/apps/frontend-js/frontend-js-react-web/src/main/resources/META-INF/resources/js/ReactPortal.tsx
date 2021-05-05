/**
 * SPDX-FileCopyrightText: © 2019 Liferay, Inc. <https://liferay.com>
 * SPDX-License-Identifier: BSD-3-Clause
 */

import React from 'react';
import {createPortal} from 'react-dom';

const ReactPortalContext = React.createContext<React.RefObject<Element | null> | null>(
	null
);

ReactPortalContext.displayName = 'ReactPortalContext';

const rootPortalElement = document.createElement('div');

rootPortalElement.classList.add('lfr-tooltip-scope');

const ReactPortal: React.FunctionComponent<
	React.HTMLAttributes<HTMLDivElement> & {

		/**
		 * Ref of element to render portal into.
		 */
		containerRef?: React.RefObject<Element>;

		/**
		 * Ref of element to render nested portals into.
		 */
		subPortalRef?: React.RefObject<Element>;
	}
> = ({children, containerRef, subPortalRef}) => {
	const parentPortalRef = React.useContext(ReactPortalContext);
	const portalRef = React.useRef(
		typeof document !== 'undefined' ? rootPortalElement : null
	);

	React.useEffect(() => {
		const closestParent =
			parentPortalRef && parentPortalRef.current
				? parentPortalRef.current
				: document.body;

		const elToMountTo =
			containerRef && containerRef.current
				? containerRef.current
				: closestParent;

		if (elToMountTo && portalRef.current) {
			elToMountTo.appendChild(portalRef.current);
		}

		return () => {
			if (portalRef.current) {
				if (typeof portalRef.current.remove === 'function') {
					portalRef.current.remove();
				}
				else if (elToMountTo) {
					elToMountTo.removeChild(portalRef.current);
				}
			}
		};
	}, [containerRef, parentPortalRef]);

	const content = (
		<ReactPortalContext.Provider
			value={subPortalRef ? subPortalRef : portalRef}
		>
			{children}
		</ReactPortalContext.Provider>
	);

	return portalRef.current
		? createPortal(content, portalRef.current)
		: content;
};

export default ReactPortal;
