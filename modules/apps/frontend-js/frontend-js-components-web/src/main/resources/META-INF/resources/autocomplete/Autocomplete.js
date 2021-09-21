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
import ClayDropDown from '@clayui/drop-down';
import {FocusScope} from '@clayui/shared';
import React, {useEffect, useRef, useState} from 'react';

const DefaultItemsWrapperRenderer = ({
	items,
	labelKey = 'label',
	onItemClick,
	valueKey = 'value',
}) => {
	return (
		<ClayDropDown.ItemList className="mb-0">
			{items && items.length === 0 && (
				<ClayDropDown.Item className="disabled">
					{Liferay.Language.get('no-items-were-found')}
				</ClayDropDown.Item>
			)}
			{items &&
				items.length > 0 &&
				items.map((item) => (
					<ClayAutocomplete.Item
						key={String(item[valueKey])}
						onClick={() => onItemClick(item)}
						value={String(item[labelKey])}
					/>
				))}
		</ClayDropDown.ItemList>
	);
};

function Autocomplete({
	disabled = false,
	id,
	inputClass,
	inputId,
	inputName,
	inputPlaceholder = Liferay.Language.get('type-here'),
	inputValue,
	items,
	itemsWrapperRenderer = DefaultItemsWrapperRenderer,
	labelKey = 'label',
	loading,
	name,
	onFocus = () => {},
	onInputChange = () => {},
	onSelectedItemChange = () => {},
	required,
	selectedItem,
	valueKey = 'value',
}) {
	const [active, setActive] = useState(false);

	const autocompleteRef = useRef(null);
	const dropdownRef = useRef(null);
	const inputRef = useRef(null);

	const currentValue = selectedItem ? selectedItem[valueKey] : null;
	const currentLabel = selectedItem ? selectedItem[labelKey] : null;

	useEffect(() => {
		function handleClick(event) {
			if (
				autocompleteRef.current.contains(event.target) ||
				(dropdownRef.current &&
					dropdownRef.current.contains(event.target))
			) {
				return;
			}

			setActive(false);
		}
		if (active) {
			document.addEventListener('mousedown', handleClick);
		}

		return () => {
			document.removeEventListener('mousedown', handleClick);
		};
	}, [active]);

	return (
		<>
			<FocusScope>
				<ClayAutocomplete className={inputClass} ref={autocompleteRef}>
					<input
						id={inputId || inputName}
						name={inputName}
						type="hidden"
						value={currentValue || ''}
					/>
					<ClayAutocomplete.Input
						id={id}
						name={name}
						onChange={(event) => {
							onSelectedItemChange(null);
							onInputChange(event.target.value);
							setActive(true);
						}}
						onFocus={() => {
							if (items?.length) {
								setActive(true);
							}

							onFocus();
						}}
						onKeyUp={(event) => {
							if (event.key === 'Escape') {
								setActive(false);
							}
						}}
						placeholder={inputPlaceholder}
						ref={inputRef}
						required={required || false}
						value={selectedItem ? currentLabel : inputValue}
					/>
					{!disabled && (
						<ClayAutocomplete.DropDown active={active}>
							<div
								className="autocomplete-items"
								ref={dropdownRef}
							>
								{itemsWrapperRenderer({
									items,
									labelKey,
									onItemClick: (item) => {
										setActive(false);
										onSelectedItemChange(item);
									},
									valueKey,
								})}
							</div>
						</ClayAutocomplete.DropDown>
					)}
					{loading && <ClayAutocomplete.LoadingIndicator />}
				</ClayAutocomplete>
			</FocusScope>
		</>
	);
}

Autocomplete.DefaultItemsWrapperRenderer = DefaultItemsWrapperRenderer;

export default Autocomplete;
