/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.frontend.spa;

import com.liferay.portal.kernel.module.util.SystemBundleUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Bryce Osterhaus
 */
public class FrontendSPAUtil {

	public static boolean isEnabled(HttpServletRequest httpServletRequest) {
		return _frontendSPA.enabled(httpServletRequest);
	}

	public static boolean isEnabled(long siteGroupId) {
		return _frontendSPA.enabled(siteGroupId);
	}

	private static volatile FrontendSPA _frontendSPA;
	private static final ServiceTracker<FrontendSPA, FrontendSPA>
		_serviceTracker;

	static {
		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		_serviceTracker = new ServiceTracker<>(
			bundleContext, FrontendSPA.class,
			new ServiceTrackerCustomizer<FrontendSPA, FrontendSPA>() {

				@Override
				public FrontendSPA addingService(
					ServiceReference<FrontendSPA> serviceReference) {

					_frontendSPA = bundleContext.getService(serviceReference);

					return _frontendSPA;
				}

				@Override
				public void modifiedService(
					ServiceReference<FrontendSPA> serviceReference,
					FrontendSPA frontendSPA) {
				}

				@Override
				public void removedService(
					ServiceReference<FrontendSPA> serviceReference,
					FrontendSPA frontendSPA) {

					_frontendSPA = null;

					bundleContext.ungetService(serviceReference);
				}

			});

		_serviceTracker.open();
	}

}