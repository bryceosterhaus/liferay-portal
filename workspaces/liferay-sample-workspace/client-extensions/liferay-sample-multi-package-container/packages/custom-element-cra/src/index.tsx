import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

import './index.css';

class WebComponent extends HTMLElement {
  constructor() {
    super();

    const root = document.createElement('div');

    ReactDOM.render(
      <App />,
      root
    );

    this.appendChild(root);
  }
}

const ELEMENT_ID = 'custom-element-cra';

if (!customElements.get(ELEMENT_ID)) {
  customElements.define(ELEMENT_ID, WebComponent);
}