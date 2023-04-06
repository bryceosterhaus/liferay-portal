import React from 'react';
import ReactDOM from 'react-dom';
import { prefixLiferay } from 'shared-utils'
import { random } from 'underscore';

import './styles/sidebar.scss'

function Sidebar() {
	return <div className="cx-sidebar">{prefixLiferay('Sidebar: ') + random(0, 10)}</div>
}

class WebComponent extends HTMLElement {
	constructor() {
		super();

		const root = document.createElement('div');

		ReactDOM.render(
			<Sidebar />,
			root
		);

		this.appendChild(root);
	}
}

const ELEMENT_ID = 'sidebar-liferay-sample-multi-extension-container';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}