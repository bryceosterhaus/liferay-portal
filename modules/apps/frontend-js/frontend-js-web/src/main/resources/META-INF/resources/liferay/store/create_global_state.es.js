function emitError(val) {
	throw new Error('Liferay.State: ' + val);
}

function getStateFromStorage(storeID, initialValue) {
	try {
		const item = window.localStorage.getItem(storeID);

		return item ? JSON.parse(item) : initialValue;
	} catch (error) {
		return initialValue;
	}
}

function setStateToStorage(storeID, value) {
	try {
		window.localStorage.setItem(storeID, JSON.stringify(value));
	} catch (error) {}
}

const STORAGE_KEY = 'LIFERAY-GLOBAL-STATE';

export default function createGlobalState() {
	/**
	 * Keeps track of dependencies for a selector
	 */
	let _selectorsDependencyMap = new Map();

	/**
	 * Keeps track of each selectorID and its callback function
	 */
	let _selectors = new Map();

	/**
	 * Global State Object
	 */
	let _stores = getStateFromStorage(STORAGE_KEY, {});

	/**
	 * Keeps track of subscribers for a given store or selector
	 */
	let _subscribers = {};

	/**
	 * Iterates subscriberIDs so that each subscriber is unique
	 */
	let _subscriberID = 0;

	/**
	 * Keeps track of stores that should be persisted via localStorage
	 */
	let _persistedStores = new Set(Object.keys(_stores));

	function runSubscribers(storeID, data) {
		if (_subscribers[storeID]) {
			for (const subscriber of _subscribers[storeID].values()) {
				subscriber(data);
			}
		}
	}

	function registerState(storeID, data, persistInStorage) {
		_stores[storeID] = {..._stores[storeID], ...data};
		_selectorsDependencyMap.set(storeID, []);

		if (persistInStorage && !_persistedStores.has(storeID)) {
			_persistedStores.add(storeID);
		}

		return function unregister() {
			delete _stores[storeID];
			delete _selectorsDependencyMap.delete(storeID);
		};
	}

	function registerSelector(selectorID, selector, dependencies) {
		dependencies.forEach((storeID) => {
			const selectorIDs = _selectorsDependencyMap.get(storeID);

			_selectorsDependencyMap.set(storeID, [...selectorIDs, selectorID]);
		});

		if (!_selectors.has(selectorID)) {
			_selectors.set(selectorID, selector);
		}

		return function unregisterSelector() {
			dependencies.forEach((storeID) => {
				const filteredSelectors = _selectorsDependencyMap
					.get(storeID)
					.filter((item) => item !== selectorID);

				_selectorsDependencyMap.set(storeID, filteredSelectors);
			});

			_selectors.delete(selectorID);
		};
	}

	function collectDataForSelector(selectorID) {
		let data = {};

		for (const storeID of _selectorsDependencyMap.keys()) {
			if (_selectorsDependencyMap.get(storeID).includes(selectorID)) {
				data[storeID] = {..._stores[storeID]};
			}
		}

		return data;
	}

	const store = {
		/**
		 * Registers data store or selector function
		 * @param {String} id - Unique identifier of store or selector
		 * @param {Object|Function} storeOrSelector - Store data or selector function
		 * @param {String[]|Boolean} dependenciesOrPersist - Array of IDs that the selector is dependent on or a boolean for using localStorage
		 * @return {Function} Callback to unregister the store or selector
		 */
		register(id, storeOrSelector, dependenciesOrPersist) {
			if (!id || typeof id !== 'string') {
				emitError('`id` must be a non-empty string');
			} else if (_selectors.has(id) || _stores[id]) {
				emitError(`'${id}' is already a registered id`);
			}

			if (!dependenciesOrPersist || dependenciesOrPersist === true) {
				return registerState(
					id,
					storeOrSelector,
					dependenciesOrPersist
				);
			} else {
				return registerSelector(
					id,
					storeOrSelector,
					dependenciesOrPersist
				);
			}
		},

		/**
		 * Subscribes to changes from a store or a selector
		 * @param {String} id - Unique identifier of data or selector
		 * @param {Function} subscriber - Callback for when data changes
		 * @return {Function} Callback to unsibscribe from the data or selector
		 */
		subscribe(id, subscriber) {
			if (!_subscribers[id]) {
				_subscribers[id] = new Map();
			}

			const nextSubscriberID = ++_subscriberID;

			_subscribers[id].set(nextSubscriberID, subscriber);

			return function unsubscribe() {
				_subscribers[id].delete(nextSubscriberID);
			};
		},

		/**
		 * Returns the value of a store or computed selector
		 * @param {String} id - Unique identifier of data or selector
		 * @return {any} Value of store or selector
		 */
		getState(id) {
			if (!id) {
				return _stores;
			} else if (_stores[id]) {
				return _stores[id];
			} else if (_selectors.get(id)) {
				const selector = _selectors.get(id);

				const data = collectDataForSelector(id);

				return selector(data);
			} else {
				emitError(`No data available for '${id}'`);
			}
		},

		/**
		 * Sets the value of the data store on the given id
		 * @param {String} storeID - Unique identifier of data store
		 * @param {any} data - Value to store
		 */
		setState(storeID, data) {
			if (typeof _stores[storeID] === 'undefined') {
				emitError(`'${storeID}' is not registered`);
			}

			_stores[storeID] = data;

			const selectorIDs = _selectorsDependencyMap.get(storeID);

			if (selectorIDs && selectorIDs.length) {
				selectorIDs.forEach((selectorID) => {
					const selector = _selectors.get(selectorID);

					const data = collectDataForSelector(selectorID);

					runSubscribers(selectorID, selector(data));
				});
			}

			runSubscribers(storeID, data);

			if (_persistedStores.has(storeID)) {
				const currentStorage = getStateFromStorage(STORAGE_KEY, {});

				const newStorage = {...currentStorage, [storeID]: data};

				setStateToStorage(STORAGE_KEY, newStorage);
			}
		},
	};

	return store;
}
