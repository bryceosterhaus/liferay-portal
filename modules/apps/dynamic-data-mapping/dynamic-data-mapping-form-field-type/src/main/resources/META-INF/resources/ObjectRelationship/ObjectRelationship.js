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

import ClayAutocomplete from '@clayui/autocomplete';
import {useResource} from '@clayui/data-provider';
import ClayDropDown from '@clayui/drop-down';
import {useDebounce} from '@clayui/shared';
import {FieldBase} from 'dynamic-data-mapping-form-field-type/FieldBase/ReactFieldBase.es';
import {fetch} from 'frontend-js-web';
import React from 'react';

const LoadingWithDebounce = ({loading, networkStatus, render}) => {
	const debouncedLoadingChange = useDebounce(loading, 500);

	if (networkStatus === 1 || debouncedLoadingChange) {
		return (
			<ClayDropDown.Item className="disabled">
				{Liferay.Language.get('loading')}
			</ClayDropDown.Item>
		);
	}

	return render;
};

export function ObjectRelationship({
	apiURL,
	initialLabel = '',
	initialValue = '',
	inputName,
	labelKey = 'label',
	name,
	valueKey = 'value',
	...otherProps
}) {
	const [selectedValue, setSelectedValue] = React.useState(initialValue);
	const [inputValue, setInputValue] = React.useState(initialLabel);
	const [networkStatus, setNetworkStatus] = React.useState(1);

	const {resource} = useResource({
		fetch,
		fetchPolicy: 'cache-first',
		link: apiURL,
		onNetworkStatusChange: setNetworkStatus,
		variables: {
			page: 1,
			pageSize: 10,
			search: inputValue,
		},
	});

	const initialLoading = networkStatus === 1;
	const loading = networkStatus < 4;

	return (
		<FieldBase name={name} {...otherProps}>
			<input
				id={inputName}
				name={inputName}
				type="hidden"
				value={selectedValue}
			/>

			<ClayAutocomplete>
				<ClayAutocomplete.Input
					onChange={(event) => {
						setSelectedValue('');
						setInputValue(event.target.value);
					}}
					placeholder={Liferay.Language.get('search')}
					value={inputValue}
				/>

				<ClayAutocomplete.DropDown
					active={
						selectedValue
							? false
							: (!!resource && !!inputValue) || initialLoading
					}
				>
					<ClayDropDown.ItemList>
						<LoadingWithDebounce
							loading={loading}
							networkStatus={networkStatus}
							render={
								<>
									{resource?.items?.length === 0 && (
										<ClayDropDown.Item className="disabled">
											{Liferay.Language.get(
												'no-results-found'
											)}
										</ClayDropDown.Item>
									)}
									{resource?.items?.map((item) => (
										<ClayAutocomplete.Item
											key={item.id}
											match={String(inputValue)}
											onClick={() => {
												setSelectedValue(
													item[valueKey]
												);
												setInputValue(item[labelKey]);
											}}
											value={String(item[labelKey])}
										/>
									))}
								</>
							}
						/>
					</ClayDropDown.ItemList>
				</ClayAutocomplete.DropDown>
				{loading && <ClayAutocomplete.LoadingIndicator />}
			</ClayAutocomplete>
		</FieldBase>
	);
}

export default ObjectRelationship;
