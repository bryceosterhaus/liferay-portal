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
import ClayButton from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import React from 'react';

import '../css/main.scss';

export default () => {
	const [value, setValue] = React.useState('');

	const items = [
		{
			label: 'clickable',
			onClick: () => {
				alert('you clicked!');
			},
		},
		{
			type: 'divider',
		},
		{
			items: [
				{
					label: 'one',
					type: 'radio',
					value: 'one',
				},
				{
					label: 'two',
					type: 'radio',
					value: 'two',
				},
			],
			label: 'radio',
			name: 'radio',
			onChange: (value) => alert(`New Radio checked ${value}`),
			type: 'radiogroup',
		},
		{
			items: [
				{
					checked: true,
					label: 'checkbox',
					onChange: () => alert('checkbox changed'),
					type: 'checkbox',
				},
				{
					checked: true,
					label: 'checkbox 1',
					onChange: () => alert('checkbox changed'),
					type: 'checkbox',
				},
			],
			label: 'checkbox',
			type: 'group',
		},
		{
			href: '#',
			label: 'linkable',
		},
	];

	return (
		<div>
			<ClayAlert title="Info">
				This widget is used to test out Clay components. Simply add
				whatever JS you want to App.js and redeploy.
			</ClayAlert>

			<div className="clay-test-class">
				<ClayDropDownWithItems
					footerContent={
						<>
							<ClayButton displayType="secondary">
								{'Cancel'}
							</ClayButton>
							<ClayButton>{'Done'}</ClayButton>
						</>
					}
					helpText="You can customize this menu or see all you have by pressing 'more'."
					items={items}
					onSearchValueChange={setValue}
					searchable={true}
					searchProps={{
						formProps: {
							onSubmit: (e) => {
								e.preventDefault();
								alert('Submitted!');
							},
						},
					}}
					searchValue={value}
					trigger={<ClayButton>{'Click Me'}</ClayButton>}
				/>
			</div>
		</div>
	);
};
