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

package com.liferay.remote.js.script.admin.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.remote.js.script.admin.web.internal.RemoteJsScriptPortletRegistrar;
import com.liferay.remote.js.script.admin.web.internal.constants.RemoteJsScriptAdminPortletKeys;
import com.liferay.remote.js.script.exception.DuplicateRemoteJsScriptEntryURLException;
import com.liferay.remote.js.script.exception.NoSuchEntryException;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.service.RemoteJsScriptEntryLocalService;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + RemoteJsScriptAdminPortletKeys.REMOTE_JS_SCRIPT_ADMIN,
		"mvc.command.name=/remote_js_script_admin/edit_remote_js_script_entry"
	},
	service = MVCActionCommand.class
)
public class EditRemoteJsScriptEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");
		String customElementName = ParamUtil.getString(actionRequest, "customElementName");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			RemoteJsScriptEntry.class.getName(), actionRequest);

		try {
			if (cmd.equals(Constants.ADD)) {
				RemoteJsScriptEntry remoteJsScriptEntry =
					_remoteJsScriptEntryLocalService.addRemoteJsScriptEntry(
						serviceContext.getUserId(), nameMap, url, customElementName,
						serviceContext);

				_remoteJsScriptPortletRegistrar.registerPortlet(remoteJsScriptEntry);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				long remoteJsScriptEntryId = ParamUtil.getLong(
					actionRequest, "remoteJsScriptEntryId");

				RemoteJsScriptEntry remoteJsScriptEntry =
					_remoteJsScriptEntryLocalService.updateRemoteJsScriptEntry(
						remoteJsScriptEntryId, nameMap, url, serviceContext);

				_remoteJsScriptPortletRegistrar.unregisterPortlet(remoteJsScriptEntry);

				_remoteJsScriptPortletRegistrar.registerPortlet(remoteJsScriptEntry);
			}

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchEntryException ||
				exception instanceof PrincipalException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcPath", "/admin/error.jsp");
			}
			else if (exception instanceof DuplicateRemoteJsScriptEntryURLException) {
				SessionErrors.add(actionRequest, exception.getClass());
			}
			else {
				throw exception;
			}
		}
	}

	@Reference
	private RemoteJsScriptEntryLocalService _remoteJsScriptEntryLocalService;

	@Reference
	private RemoteJsScriptPortletRegistrar _remoteJsScriptPortletRegistrar;

}