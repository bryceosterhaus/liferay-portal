/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.spa.web.internal;

import com.liferay.frontend.js.spa.web.internal.configuration.SPAConfiguration;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.frontend.spa.FrontendSPA;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import jakarta.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(
	configurationPid = "com.liferay.frontend.js.spa.web.internal.configuration.SPAConfiguration",
	service = FrontendSPA.class
)
public class FrontendSPAImpl implements FrontendSPA {

	@Override
	public boolean enabled(HttpServletRequest httpServletRequest) {
		SPAConfiguration spaConfiguration = _getSPAConfiguration(
			httpServletRequest);

		return spaConfiguration.enabled();
	}

	@Override
	public boolean enabled(long siteGroupId) {
		SPAConfiguration spaConfiguration = _getSPAConfiguration(siteGroupId);

		return spaConfiguration.enabled();
	}

	private SPAConfiguration _getSPAConfiguration(
		HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _getSPAConfiguration(themeDisplay.getSiteGroupId());
	}

	private SPAConfiguration _getSPAConfiguration(long siteGroupId) {
		try {
			return _configurationProvider.getGroupConfiguration(
				SPAConfiguration.class, siteGroupId);
		}
		catch (ConfigurationException configurationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to get SPA configuration", configurationException);
			}

			return _SPA_CONFIGURATION;
		}
	}

	private static final SPAConfiguration _SPA_CONFIGURATION =
		new SPAConfiguration() {

			@Override
			public long cacheExpirationTime() {
				return -1;
			}

			@Override
			public String[] customExcludedPaths() {
				return new String[0];
			}

			@Override
			public boolean enabled() {
				return PropsValues.JAVASCRIPT_SINGLE_PAGE_APPLICATION_ENABLED;
			}

			@Override
			public String[] navigationExceptionSelectors() {
				return new String[] {
					":not([target=\"_blank\"])", ":not([data-senna-off])",
					":not([data-resource-href])"
				};
			}

			@Override
			public boolean preloadCSS() {
				return false;
			}

			@Override
			public int requestTimeout() {
				return 0;
			}

			@Override
			public int userNotificationTimeout() {
				return 30000;
			}

		};

	private static final Log _log = LogFactoryUtil.getLog(
		FrontendSPAImpl.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

}