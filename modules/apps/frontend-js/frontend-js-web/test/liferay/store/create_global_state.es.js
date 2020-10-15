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

'use strict';

import createGlobalState from '../../../src/main/resources/META-INF/resources/liferay/store/create_global_state.es';

describe('Liferay.State', () => {
	let Liferay;

	beforeEach(() => {
		Liferay = {State: createGlobalState()};
	});

	it('registers a store', () => {
		const id = 'my-apps-store';

		Liferay.State.register(id, {});

		expect(Liferay.State.getState()).toMatchObject({
			'my-apps-store': {},
		});
	});

	it('updates store via setState', () => {
		const id = 'my-apps-store';

		Liferay.State.register(id, {foo: 'bar'});

		expect(Liferay.State.getState()).toMatchObject({
			'my-apps-store': {
				foo: 'bar',
			},
		});

		Liferay.State.setState(id, {foo: 'baz'});

		expect(Liferay.State.getState()).toMatchObject({
			'my-apps-store': {
				foo: 'baz',
			},
		});
	});

	it('registers a selector', () => {
		const id = 'my-apps-selector';

		Liferay.State.register(
			id,
			() => {
				return 'foo bar';
			},
			[]
		);

		expect(Liferay.State.getState()).toMatchObject({});
		expect(Liferay.State.getState(id)).toEqual('foo bar');
	});

	it('registers a store with initial data', () => {
		const id = 'my-apps-store';
		const val = {hello: 'world'};

		Liferay.State.register(id, val);

		expect(Liferay.State.getState(id)).toMatchObject(val);
	});

	it('updates selector when store dependency updates', () => {
		const storeID = 'my-apps-store';
		const selectorID = 'my-apps-selector';

		const val = {name: 'joe Bloggs'};

		Liferay.State.register(storeID, val);
		Liferay.State.register(
			selectorID,
			(data) => data[storeID].name.toUpperCase(),
			[storeID]
		);

		expect(Liferay.State.getState(selectorID)).toEqual('JOE BLOGGS');

		Liferay.State.setState(storeID, {name: 'Test Test'});

		expect(Liferay.State.getState(selectorID)).toEqual('TEST TEST');
	});

	it('subscribes to store changes', () => {
		const storeID = 'my-apps-store';
		const val = {name: 'joe Bloggs'};

		const subscriber = jest.fn();

		Liferay.State.register(storeID, val);
		Liferay.State.subscribe(storeID, subscriber);

		expect(subscriber).not.toHaveBeenCalled();

		Liferay.State.setState(storeID, {name: 'Test Test'});

		expect(subscriber).toHaveBeenCalled();
	});

	it('subscribes to selector changes', () => {
		const storeID = 'my-apps-store';
		const selectorID = 'my-apps-selector';

		const val = {name: 'joe Bloggs'};

		Liferay.State.register(storeID, val);
		Liferay.State.register(
			selectorID,
			(data) => data[storeID].name.toUpperCase(),
			[storeID]
		);

		const subscriber = jest.fn();

		Liferay.State.subscribe(selectorID, subscriber);

		expect(subscriber).not.toHaveBeenCalled();

		Liferay.State.setState(storeID, {name: 'Test Test'});

		expect(subscriber).toHaveBeenCalled();
	});

	it("does not fire subscription to selector if store dependency isn't updated", () => {
		const storeID = 'my-apps-store';
		const selectorID = 'my-apps-selector';

		const val = {name: 'joe Bloggs'};

		Liferay.State.register(storeID, val);
		Liferay.State.register(selectorID, () => 'test', []);

		const subscriber = jest.fn();

		Liferay.State.subscribe(selectorID, subscriber);

		expect(subscriber).not.toHaveBeenCalled();

		Liferay.State.setState(storeID, {name: 'Test Test'});

		expect(subscriber).not.toHaveBeenCalled();
	});
});
