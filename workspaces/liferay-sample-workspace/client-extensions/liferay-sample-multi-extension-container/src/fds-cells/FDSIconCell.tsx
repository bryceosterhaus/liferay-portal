import type { FDSCellRenderer } from '@liferay/js-api/data-set';

import React from 'react';
import ReactDOM from 'react-dom';
import { prefixLiferay } from 'shared-utils'
import { random } from 'underscore';

const fdsCellRenderer: FDSCellRenderer = ({ value }) => {
	const element = document.createElement('div');

	ReactDOM.render(
		<div>
			{random(20, 30) + prefixLiferay(value.toString())}
		</div>,
		element
	);

	return element;
};

export default fdsCellRenderer;
