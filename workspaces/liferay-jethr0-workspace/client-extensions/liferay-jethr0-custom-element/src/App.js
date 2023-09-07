/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {HashRouter, Route, Routes} from 'react-router-dom';

import HomePage from './pages/HomePage/HomePage';
import NotFoundPage from './pages/NotFoundPage/NotFoundPage';
import ProjectPage from './pages/ProjectPage/ProjectPage';

import './App.css';

function App() {
	return (
		<HashRouter>
			<Routes>
				<Route element={<HomePage />} path="/" />
				<Route path="/projects">
					<Route element={<ProjectPage />} path=":id" />
				</Route>
				<Route element={<NotFoundPage />} path="*" />
			</Routes>
		</HashRouter>
	);
}

export default App;
