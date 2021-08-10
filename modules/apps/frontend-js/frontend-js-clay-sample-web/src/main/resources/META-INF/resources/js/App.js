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

import ClayIcon from '@clayui/icon';
import React from 'react';

import '../css/main.scss';

/**
 * Liferay.Icons
 *
 * This would be a util that maps to any available "icons" module available in DXP by a friendly name such as "Clay"
 *
 * Liferay.Icons.Clay => returns the publically available spitemap such as "/o/frontend-icons-clay/images/icons.svg"
 * Liferay.Icons.Clay['add-cell'] => returns the publically available svg such as "/o/frontend-icons-clay/images/add-cell.svg"
 */

export default () => {
	return (
		<div>
			<ClayIcon
				spritemap={Liferay.Icons.Github} // This points to the github-icons module's "main" file that is served by the backend
				symbol="octocat"
			/>

			<ClayIcon
				spritemap={Liferay.Icons.Clay} // This points to the frontend-icons-clay module's "main" file that is served by the backend
				symbol="octocat"
			/>
		</div>
	);
};
