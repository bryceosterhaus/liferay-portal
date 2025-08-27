/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useCallback, useState} from 'react';

export type InternalDispatch<Value> =
	| ((value: Value) => void)
	| ((value?: Value) => void)
	| ((value: Value | (() => Value)) => void)
	| ((value?: Value | (() => Value)) => void)
	| React.Dispatch<React.SetStateAction<Value>>;

type Props<Value> = {
	defaultValue?: Value | (() => Value);
	onChange?: InternalDispatch<Value>;
	value?: Value;
};

export default function useControlledState<Value>({
	defaultValue,
	onChange,
	value,
}: Props<Value>) {
	const [stateValue, setStateValue] = useState(
		defaultValue === undefined ? value : defaultValue
	);

	const isControlled = onChange !== undefined && value !== undefined;

	const setValue = useCallback(
		(value: Value) => {
			if (isControlled) {
				onChange(value);
			}
			else {
				setStateValue(value);
			}
		},
		[isControlled, onChange]
	);

	return [isControlled ? value : stateValue, setValue, isControlled] as [
		Value,
		InternalDispatch<Value>,
		boolean,
	];
}
