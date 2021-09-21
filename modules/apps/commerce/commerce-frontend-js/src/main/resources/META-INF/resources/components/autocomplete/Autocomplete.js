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
import {Autocomplete as BaseAutocomplete} from 'frontend-js-components-web';
import React, {useState} from 'react';

import debounce from '../../utilities/debounce';
import {getData, getValueFromItem} from '../../utilities/index';
import {showErrorNotification} from '../../utilities/notifications';
import InfiniteScroller from '../infinite_scroller/InfiniteScroller';

function Autocomplete({
	apiUrl,
	customView: CustomView,
	disabled = false,
	inputName,
	inputPlaceholder = Liferay.Language.get('type-here'),
	valueKey,
	labelKey,
	pageSize = 10,
}) {
	const [inputValue, setInputValue] = useState('');
	const [selectedItem, setSelectedItem] = useState(null);
	const [items, setItems] = useState(null);
	const [loading, setLoading] = useState(false);
	const [totalCount, setTotalCount] = useState(null);
	const [lastPage, setLastPage] = useState(null);
	const [page, setPage] = useState(1);
	const [internalPageSize, setInternalPageSize] = useState(pageSize);
	const isMounted = useIsMounted();

	const fetchData = debounce((query) => {
		if (query && isMounted() && !disabled) {
			setLoading(true);

			getData(apiUrl, query, page, internalPageSize)
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

					if (!query) {
						return;
					}

					const found = jsonResponse.items.find(
						(item) => getValueFromItem(item, labelKey) === query
					);

					if (found) {
						setSelectedItem(found);
					}
				})
				.catch(() => {
					showErrorNotification();
					setLoading(false);
				});
		}
	}, 200);

	const itemsWrapperRenderer = ({items, labelKey, onItemClick, valueKey}) => (
		<InfiniteScroller
			onBottomTouched={() => {
				if (!loading) {
					if (page !== lastPage) {
						setPage((currentPage) => currentPage + 1);

						fetchData(inputValue);
					}
				}
			}}
			scrollCompleted={!items || items.length >= totalCount}
		>
			{CustomView ? (
				<CustomView
					items={items}
					lastPage={lastPage}
					loading={loading}
					page={page}
					pageSize={internalPageSize}
					setPage={setPage}
					setPageSize={setInternalPageSize}
					setSelectedItem={onItemClick}
					totalCount={totalCount}
				/>
			) : (
				<BaseAutocomplete.DefaultItemsWrapperRenderer
					items={items}
					labelKey={labelKey}
					onItemClick={onItemClick}
					valueKey={valueKey}
				/>
			)}
		</InfiniteScroller>
	);

	return (
		<>
			<BaseAutocomplete
				disabled={disabled}
				inputName={inputName}
				inputPlaceholder={inputPlaceholder}
				inputValue={inputValue}
				items={items}
				itemsWrapperRenderer={itemsWrapperRenderer}
				labelKey={labelKey}
				loading={loading}
				onInputChange={(val) => {
					setSelectedItem(null);
					setPage(1);
					setInputValue(val);
					fetchData(val);
				}}
				onSelectedItemChange={setSelectedItem}
				selectedItem={selectedItem}
				valueKey={valueKey}
			/>
		</>
	);
}

export default Autocomplete;
