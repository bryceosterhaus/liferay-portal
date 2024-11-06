/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../../../fixtures/featureFlagsTest';
import {isolatedSiteTest} from '../../../../fixtures/isolatedSiteTest';
import {loginTest} from '../../../../fixtures/loginTest';
import {ckeditorSamplePageTest} from '../../fixtures/ckeditorSamplePageTest';

export const test = mergeTests(
	apiHelpersTest,
	ckeditorSamplePageTest,
	featureFlagsTest({
		'LPS-178052': true,
	}),
	loginTest(),
	isolatedSiteTest
);

test.beforeEach(async ({ckeditorSamplePage, site}) => {
	await ckeditorSamplePage.createAndGotoSitePage({site});

	await ckeditorSamplePage.selectTab('CKEditor 4');
	await ckeditorSamplePage.selectTab('Classic');
});

test(
	'Dropdown and context menus are visible when maximized',
	{tag: ['@LPD-33712', '@LPD-38600']},
	async ({page}) => {
		await test.step('Select Maximized toolbar control', async () => {
			await page.getByRole('button', {name: 'Maximize'}).click();
		});

		await test.step('Assert "Styles" dropdown is visible', async () => {
			await page.getByRole('button', {name: 'Styles'}).click();

			const stylesComboZIndex = await page.evaluate(() => {
				const stylesComboElement = document.querySelector(
					'.cke_panel.cke_combopanel.lfr-maximized'
				);

				const stylesComboElementStyles =
					window.getComputedStyle(stylesComboElement);

				return stylesComboElementStyles.getPropertyValue('z-index');
			});

			expect(stylesComboZIndex).toEqual('10000');
		});

		await test.step('Assert context menu is visible', async () => {
			const ckeditorEditorBody = page
				.frameLocator('iframe[title="editor"]')
				.getByRole('heading', {name: 'Classic Editor'});

			await ckeditorEditorBody.click({button: 'right'});

			const contextMenuZIndex = await page.evaluate(() => {
				const stylesComboElement = document.querySelector(
					'.cke_panel.cke_menu_panel'
				);

				const contextMenuElementStyles =
					window.getComputedStyle(stylesComboElement);

				return contextMenuElementStyles.getPropertyValue('z-index');
			});

			expect(contextMenuZIndex).toEqual('10001');
		});
	}
);
