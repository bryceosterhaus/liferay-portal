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
import {FieldBase} from 'dynamic-data-mapping-form-field-type/FieldBase/ReactFieldBase.es';
import {Autocomplete as BaseAutocomplete} from 'frontend-js-components-web';
import {debounce, fetch} from 'frontend-js-web';
import React, {useState} from 'react';

function getData(apiUrl, inputValue, page, pageSize) {
	const url = new URL(apiUrl, themeDisplay.getPortalURL());

	if (inputValue) {
		url.searchParams.set('search', inputValue);
	}

	if (page) {
		url.searchParams.set('page', page);
	}

	if (pageSize) {
		url.searchParams.set('pageSize', pageSize);
	}

	return fetch(url, {
		headers: new Headers({
			Accept: 'application/json',
			'Accept-Language': themeDisplay.getBCP47LanguageId(),
			'Content-Type': 'application/json',
		}),
	}).then((data) => data.json());
}

export function ObjectRelationship({
	apiURL,
	initialLabel,
	initialValue,
	inputName,
	labelKey,
	name,
	value,
	valueKey,
	...otherProps
}) {
	const [inputValue, setInputValue] = useState(initialLabel || '');
	const [selectedItem, setSelectedItem] = useState(initialValue || value);
	const [items, setItems] = useState(null);
	const [loading, setLoading] = useState(false);
	const isMounted = useIsMounted();

	const fetchData = debounce((query) => {
		if (query && isMounted()) {
			setLoading(true);

			getData(apiURL, query, 1, 10)
				.then((jsonResponse) => {
					setItems(jsonResponse.items);

					setLoading(false);

					if (!query) {
						return;
					}

					const found = jsonResponse.items.find(
						(item) => item[labelKey] === query
					);

					if (found) {
						setSelectedItem(found);
					}
				})
				.catch(() => {
					setLoading(false);
				});
		}
	}, 500);

	return (
		<FieldBase name={name} {...otherProps}>
			<BaseAutocomplete
				inputName={inputName}
				inputValue={inputValue}
				items={items}
				labelKey={labelKey}
				loading={loading}
				name={name}
				onInputChange={(val) => {
					setInputValue(val);
					setSelectedItem(null);
					fetchData(val);
				}}
				onSelectedItemChange={setSelectedItem}
				selectedItem={selectedItem}
				valueKey={valueKey}
			/>
		</FieldBase>
	);
}

export default ObjectRelationship;
