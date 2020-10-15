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

import React from 'react';
import useLiferayState from 'frontend-js-react-web/js/hooks/useLiferayState.es';

import '../css/main.scss';

export default () => {
	const [user, setUser] = useLiferayState(
		'user',
		{nickname: Liferay.themeDisplay.getUserName()},
		true
	);

	return (
		<div>
			<h2>Nickname Updater:</h2>

			<div>
				<input
					value={user.nickname}
					onChange={(e) => {
						setUser({
							...user,
							nickname: e.target.value,
						});
					}}
				/>
			</div>
		</div>
	);
};
