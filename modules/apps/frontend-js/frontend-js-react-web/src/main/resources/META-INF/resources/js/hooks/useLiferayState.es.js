import React from 'react';

export default function useLiferayState(
	name,
	initState = {},
	dependenciesOrPersist
) {
	let initialValue = Liferay.State.getState(name);

	if (!initialValue) {
		Liferay.State.register(name, initState, dependenciesOrPersist);

		initialValue = Liferay.State.getState(name);
	}

	const [value, setValue] = React.useState(initialValue);

	React.useEffect(() => {
		const unsub = Liferay.State.subscribe(name, (newData) =>
			setValue(newData)
		);

		return unsub;
	}, [name]);

	const setLiferayValue = React.useCallback(
		(val) => Liferay.State.setState(name, val),
		[name]
	);

	return [value, setLiferayValue];
}
