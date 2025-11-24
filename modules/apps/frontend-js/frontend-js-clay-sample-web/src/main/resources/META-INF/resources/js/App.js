/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayCard, {ClayCardWithInfo} from '@clayui/card';
import {Provider as ClayIconProvider} from '@clayui/core';
import {ClayInput} from '@clayui/form';
import ClayLayout from '@clayui/layout';
import ClayManagementToolbar from '@clayui/management-toolbar';
import React, {useState} from 'react';

const dogNames = [
	['Bailey', 1],
	['Max', 2],
	['Charlie', 3],
	['Buddy', 4],
	['Rocky', 5],
	['Jake', 6],
	['Jack', 7],
	['Sadie', 8],
	['Toby', 9],
	['Chloe', 10],
	['Cody', 11],
	['Buster', 12],
	['Buddy', 13],
	['Rocky', 14],
	['Jake', 15],
	['Jack', 16],
	['Sadie', 17],
	['Toby', 18],
	['Chloe', 19],
	['Cody', 20],
];

export function App(props) {
	const {user} = props;
	const [value, setValue] = useState('');

	return (
		<ClayIconProvider
			spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/clay/icons.svg`}
		>
			<div className="m-4 p-4">
				{user.firstName && (
					<h2>
						{user.greeting} your email is {user.emailAddress}
					</h2>
				)}

				<ClayManagementToolbar>
					<ClayManagementToolbar.Search onlySearch>
						<ClayInput.Group>
							<ClayInput.GroupItem>
								<ClayInput
									aria-label="Search"
									className="form-control input-group-inset input-group-inset-after"
									onChange={(event) =>
										setValue(event.target.value)
									}
									placeholder="Search..."
									type="text"
									value={value}
								/>

								<ClayInput.GroupInsetItem after tag="span">
									<ClayButtonWithIcon
										displayType="unstyled"
										symbol="search"
										type="submit"
									/>
								</ClayInput.GroupInsetItem>
							</ClayInput.GroupItem>
						</ClayInput.Group>
					</ClayManagementToolbar.Search>
				</ClayManagementToolbar>

				<ClayLayout.ContainerFluid view>
					<ClayCard.Group label="Good Boys">
						{dogNames
							.filter(([name]) =>
								name.toLowerCase().match(value.toLowerCase())
							)
							.map(([doggoName, doggoId]) => (
								<ClayCardWithInfo
									description="the goodest boy"
									href={`https://placedog.net/1000?id=${doggoId}`}
									imgProps={{
										src: ``,
									}}
									key={doggoId}
									title={`${doggoName}.jpg`}
								/>
							))}
					</ClayCard.Group>
				</ClayLayout.ContainerFluid>
			</div>
		</ClayIconProvider>
	);
}
