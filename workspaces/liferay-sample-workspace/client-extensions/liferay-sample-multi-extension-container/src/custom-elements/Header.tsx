import React from 'react';
import ReactDOM from 'react-dom';
import { prefixLiferay } from 'shared-utils'
import { random } from 'underscore';

import './styles/header.scss'

function Header() {
	return <div className="cx-header">{prefixLiferay('Header: ') + random(10, 20)}</div>
}

class WebComponent extends HTMLElement {
	constructor() {
		super();

		const root = document.createElement('div');

		ReactDOM.render(
			<Header />,
			root
		);

		this.appendChild(root);
	}
}

const ELEMENT_ID = 'header-liferay-sample-multi-extension-container';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}