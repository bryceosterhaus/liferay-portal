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

// disregard this util, I'm sure you'll need something more robust to get search params
const getSearchParam = (key) => {
	const urlParams = new URLSearchParams(window.location.search);

	return urlParams.get(key);
};

export default () => {
	const [page, setPage] = React.useState(getSearchParam('page'));
	const [delta, setDelta] = React.useState(getSearchParam('delta'));

	const handlePage = () => {
		const newPage = Number(page) + 1;

		setPage(newPage);

		const newPath =
			window.location.pathname + `?page=${newPage}&delta=${delta}`;

		window.history.pushState(
			{senna: true, path: newPath},
			document.title,
			newPath
		);
	};

	const handleDelta = () => {
		const newDelta = Number(delta) + 1;

		setDelta(newDelta);

		const newPath =
			window.location.pathname + `?page=${page}&delta=${newDelta}`;

		window.history.pushState(
			{senna: true, path: newPath},
			document.title,
			newPath
		);
	};

	React.useEffect(() => {
		const listener = () => {
			setPage(getSearchParam('page'));
			setDelta(getSearchParam('delta'));
		};

		window.addEventListener('popstate', listener);

		return () => window.removeEventListener('popstate', listener);
	}, []);

	return (
		<div>
			<ClayAlert title="Info">
				This widget is used to test out Clay components. Simply add
				whatever JS you want to App.js and redeploy.
			</ClayAlert>

			<button onClick={handlePage}>Update page to {page}</button>
			<button onClick={handleDelta}>Update delta to {delta}</button>
		</div>
	);
};
