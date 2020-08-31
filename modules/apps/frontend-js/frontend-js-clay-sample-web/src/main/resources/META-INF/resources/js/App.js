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
import ClayChart, {bb} from '@clayui/charts';
import React from 'react';

import '../css/main.scss';

export default () => {
	console.log('VERSION: ', bb.version);

	return (
		<div>
			<ClayAlert title="Info">
				This widget is used to test out Clay components. Simply add
				whatever JS you want to App.js and redeploy.
			</ClayAlert>

			<ClayChart
				axis={{
					x: {
						type: 'timeseries',
					},
				}}
				data={{
					columns: [
						[
							'x',
							'2018-01-01',
							'2018-02-01',
							'2018-03-01',
							'2018-04-01',
							'2018-05-01',
							'2018-06-01',
							'2018-07-01',
							'2018-08-01',
							'2018-09-01',
							'2018-10-01',
							'2018-11-01',
							'2018-12-01',
						],
						[
							'product1',
							130,
							340,
							200,
							100,
							40,
							300,
							{high: 240, low: 140, mid: 180},
							{high: 380, low: 300, mid: 350},
							{high: 480, low: 320, mid: 400},
							{high: 260, low: 100, mid: 200},
							{high: 140, low: 100, mid: 120},
							{high: 180, low: 80, mid: 100},
						],
						[
							'product2',
							210,
							180,
							30,
							90,
							40,
							120,
							{high: 260, low: 180, mid: 240},
							{high: 460, low: 360, mid: 420},
							{high: 180, low: 80, mid: 120},
							{high: 120, low: 60, mid: 80},
							{high: 80, low: 10, mid: 20},
							{high: 100, low: 20, mid: 60},
						],
					],
					type: 'predictive',
					types: {
						product1: 'area-line-range',
						product2: 'area-spline-range',
					},
					x: 'x',
				}}
				predictionDate="2018-06-01"
			/>
		</div>
	);
};
