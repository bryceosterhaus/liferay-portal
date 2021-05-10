<%--
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
--%>

<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %>

<div class="row">
	<div class="col">
		<react:component
			module="js/App"
		/>
	</div>

	<div class="col">
		<button id="test-button">Increment</button>

		<h3>Counter<span id="test-counter">0</span></h3>

		<h3>Name: <span id="test-name">Initial Name</span></h3>

		<aui:script require="@liferay/frontend-js-state-web@1.0.3/index as StateModule">
			const buttonElement = document.getElementById('test-button');
			const counterElement = document.getElementById('test-counter');
			const nameElement = document.getElementById('test-name');

			const State = StateModule.State;

			const counterAtom = State.atom('test-counter', 0);

			State.subscribe(counterAtom, function(newVal) {
				counterElement.innerText = newVal;
			});

			State.subscribe('clay-sample-atom', function(event) {
				nameElement.innerText = event.name;
			});

			buttonElement.addEventListener('click', function() {
				State.write(counterAtom, State.read(counterAtom) + 1);
			});
		</aui:script>
	</div>
</div>