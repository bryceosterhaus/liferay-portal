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

import {useIsMounted} from '@liferay/frontend-js-react-web';
import React, {useEffect, useState} from 'react';

import debounce from '../../../utilities/debounce';
import {getData} from '../../../utilities/index';
import {showErrorNotification} from '../../../utilities/notifications';
import InfiniteScroller from '../../infinite_scroller/InfiniteScroller';

function ListView({apiUrl, customView: CustomView, pageSize = 10, query}) {
	const [items, setItems] = useState(null);
	const [loading, setLoading] = useState(false);
	const [totalCount, setTotalCount] = useState(null);
	const [lastPage, setLastPage] = useState(null);
	const [page, setPage] = useState(1);
	const isMounted = useIsMounted();

	const fetchData = debounce((queryVal) => {
		if (queryVal && isMounted()) {
			setLoading(true);

			getData(apiUrl, queryVal, page, pageSize)
				.then((jsonResponse) => {
					setItems((prevItems) => {
						if (prevItems?.length && page > 1) {
							return [...prevItems, ...jsonResponse.items];
						}

						return jsonResponse.items;
					});

					setTotalCount(jsonResponse.totalCount);
					setLastPage(jsonResponse.lastPage);
					setLoading(false);
				})
				.catch(() => {
					showErrorNotification();
					setLoading(false);
				});
		}
	}, 200);

	useEffect(() => {
		fetchData(query);
	}, [fetchData, query]);

	return (
		<InfiniteScroller
			onBottomTouched={() => {
				if (!loading) {
					if (page !== lastPage) {
						setPage((currentPage) => currentPage + 1);

						fetchData(query);
					}
				}
			}}
			scrollCompleted={!items || items.length >= totalCount}
		>
			<CustomView items={items} loading={loading} />
		</InfiniteScroller>
	);
}

export default ListView;
