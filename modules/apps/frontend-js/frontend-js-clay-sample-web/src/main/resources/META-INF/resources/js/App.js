/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayAlert from '@clayui/alert';
import React from 'react';

import '../css/main.scss';

export default () => {
	const URL = 'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_fragment_web_portlet_FragmentPortlet&p_p_lifecycle=1&p_p_state=pop_up&p_p_mode=view&_com_liferay_fragment_web_portlet_FragmentPortlet_mvcRenderCommandName=%2Ffragment%2Fselect_fragment_collection&p_auth=5y9XdDWx&p_p_auth=iN0ElrVI&_com_liferay_fragment_web_portlet_FragmentPortlet_bodyCssClass=dialog-iframe-popup';
	
	return (
		<div>
			<button
				onClick={() => {
					Liferay.Util.openWindow({
						title: 'IFrame',
						uri: URL,
					});
				}}
			>
				IFrame
			</button>

			<button
				onClick={() => {
					Liferay.Util.openModal({
						title: 'Modal',
						url: URL
					});
				}}
			>
				Modal
			</button>
		</div>
	);
};
