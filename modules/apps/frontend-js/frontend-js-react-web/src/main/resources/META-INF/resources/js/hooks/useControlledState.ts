/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useState} from 'react';

export default function useControlledState<T>({
	defaultValue,
	onChange,
	value: valueProp,
}: {
	defaultValue: T;
	onChange?: (value: T) => void;
	value?: T;
}) {
	const [internalState, setInternalState] = useState(defaultValue);

	const isControlled = valueProp !== undefined;

	const value = isControlled ? valueProp : internalState;

	const setValue = (newValue: T) => {
		if (!isControlled) {
			setInternalState(newValue);
		}

		if (onChange) {
			onChange(newValue);
		}
	};

	return [value, setValue] as const;
}
