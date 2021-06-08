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

package com.liferay.remote.js.script.admin.web.internal;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.remote.js.script.admin.web.internal.portlet.RemoteJsScriptPortlet;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.service.RemoteJsScriptEntryLocalService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(immediate = true, service = RemoteJsScriptPortletRegistrar.class)
public class RemoteJsScriptPortletRegistrar {

	public void registerPortlet(RemoteJsScriptEntry remoteJsScriptEntry) {
		_registerPortlet(remoteJsScriptEntry);
	}

	public void unregisterPortlet(RemoteJsScriptEntry remoteJsScriptEntry) {
		_unregisterPortlet(remoteJsScriptEntry.getRemoteJsScriptEntryId());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		if (_log.isInfoEnabled()) {
			_log.info("Starting remote js script entries");
		}

		for (RemoteJsScriptEntry remoteJsScriptEntry :
				remoteJsScriptEntryLocalService.getRemoteJsScriptEntries(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			registerPortlet(remoteJsScriptEntry);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_log.isInfoEnabled()) {
			_log.info("Stopping remote js script entries");
		}

		for (long remoteJsScriptEntryId : _remoteJsScriptPortlets.keySet()) {
			_unregisterPortlet(remoteJsScriptEntryId);
		}
	}

	@Reference
	protected RemoteJsScriptEntryLocalService remoteJsScriptEntryLocalService;

	private void _registerPortlet(RemoteJsScriptEntry remoteJsScriptEntry) {
		RemoteJsScriptPortlet remoteJsScriptPortlet = new RemoteJsScriptPortlet(
			remoteJsScriptEntry);

		long remoteJsScriptEntryId = remoteJsScriptEntry.getRemoteJsScriptEntryId();

		RemoteJsScriptPortlet existingRemoteJsScriptPortlet =
			_remoteJsScriptPortlets.putIfAbsent(remoteJsScriptEntryId, remoteJsScriptPortlet);

		if (existingRemoteJsScriptPortlet != null) {
			throw new IllegalStateException(
				"Remote app entry " + remoteJsScriptEntryId +
					" is already registered");
		}

		remoteJsScriptPortlet.register(_bundleContext);

		if (_log.isInfoEnabled()) {
			_log.info("Started remote js script entry " + remoteJsScriptPortlet.getName());
		}
	}

	private void _unregisterPortlet(long remoteJsScriptEntryId) {
		RemoteJsScriptPortlet remoteJsScriptPortlet = _remoteJsScriptPortlets.remove(
			remoteJsScriptEntryId);

		if (remoteJsScriptPortlet != null) {
			remoteJsScriptPortlet.unregister();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Stopped remote js script entry " + remoteJsScriptPortlet.getName());
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteJsScriptPortletRegistrar.class);

	private BundleContext _bundleContext;
	private final ConcurrentMap<Long, RemoteJsScriptPortlet> _remoteJsScriptPortlets =
		new ConcurrentHashMap<>();

}