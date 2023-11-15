/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import {ClayModalProvider} from '@clayui/modal';
import {
	ConfigProvider,
	FormProvider,
	KeyboardDNDContextProvider,
	parseProps,
} from 'data-engine-js-components-web';
import {
	dragAndDropReducer,
	fieldEditableReducer,
	languageReducer,
	pagesStructureReducer,
} from 'data-engine-js-components-web/js/core/reducers/index.es';
import {
	objectFieldsReducer,
	pageReducer,
} from 'data-engine-js-components-web/js/custom/form/reducers/index.es';
import React, {Suspense, lazy, useMemo} from 'react';
import {DndProvider} from 'react-dnd';
import {HTML5Backend} from 'react-dnd-html5-backend';
import {HashRouter as Router, Route, Switch} from 'react-router-dom';

import {NavigationBar} from './components/NavigationBar.es';
import {INITIAL_CONFIG_STATE} from './config/initialConfigState.es';
import {BUILDER_INITIAL_STATE, initState} from './config/initialState.es';
import AutoSaveProvider from './hooks/useAutoSave.es';
import {ToastProvider} from './hooks/useToast.es';
import {
	elementSetReducer,
	formInfoReducer,
	rulesReducer,
	sidebarReducer,
} from './reducers/index.es';

/**
 * Exporting default application to Forms Admin. Only Providers and
 * routing must be defined.
 */
export default function App({
	autosaveInterval,
	autosaveURL,
	mainRequire,
	...otherProps
}) {
	const {config, state} = parseProps(otherProps);
	const {defaultLanguageId} = state;

	const FormBuilder = useMemo(
		() =>
			lazy(
				() =>
					new Promise((resolve, reject) => {

						// @ts-ignore

						Liferay.Loader.require(
							[`${mainRequire}/admin/js/pages/FormBuilder.es`],

							// @ts-ignore

							(Component) => resolve(Component),

							// @ts-ignore

							(error) => reject(error)
						);
					})
			),
		[mainRequire]
	);

	const RuleBuilder = useMemo(
		() =>
			lazy(
				() =>
					new Promise((resolve, reject) => {

						// @ts-ignore

						Liferay.Loader.require(
							[`${mainRequire}/admin/js/pages/RuleBuilder.es`],

							// @ts-ignore

							(Component) => resolve(Component),

							// @ts-ignore

							(error) => reject(error)
						);
					})
			),
		[mainRequire]
	);

	const Report = useMemo(
		() =>
			lazy(
				() =>
					new Promise((resolve, reject) => {

						// @ts-ignore

						Liferay.Loader.require(
							[`${mainRequire}/admin/js/pages/Report`],

							// @ts-ignore

							(Component) => resolve(Component),

							// @ts-ignore

							(error) => reject(error)
						);
					})
			),
		[mainRequire]
	);

	return (
		<DndProvider backend={HTML5Backend} context={window}>
			<ConfigProvider
				config={config}
				initialConfig={INITIAL_CONFIG_STATE}
			>
				<ClayModalProvider>
					<FormProvider
						init={initState}
						initialState={{
							...BUILDER_INITIAL_STATE,
							defaultLanguageId,
							editingLanguageId: defaultLanguageId,
						}}
						reducers={[
							dragAndDropReducer,
							elementSetReducer,
							fieldEditableReducer,
							formInfoReducer,
							languageReducer,
							objectFieldsReducer,
							pageReducer,
							pagesStructureReducer,
							rulesReducer,
							sidebarReducer,
						]}
						value={state}
					>
						<KeyboardDNDContextProvider>
							<ToastProvider>
								<Router>
									<AutoSaveProvider
										interval={autosaveInterval}
										url={autosaveURL}
									>
										<Route
											component={NavigationBar}
											path="/"
										/>

										<Suspense
											fallback={<ClayLoadingIndicator />}
										>
											<Switch>
												<Route
													component={FormBuilder}
													exact
													path="/"
												/>

												<Route
													component={RuleBuilder}
													path="/rules"
												/>

												<Route
													component={Report}
													path="/report"
												/>
											</Switch>
										</Suspense>
									</AutoSaveProvider>
								</Router>
							</ToastProvider>
						</KeyboardDNDContextProvider>
					</FormProvider>
				</ClayModalProvider>
			</ConfigProvider>
		</DndProvider>
	);
}

App.displayName = 'App';
