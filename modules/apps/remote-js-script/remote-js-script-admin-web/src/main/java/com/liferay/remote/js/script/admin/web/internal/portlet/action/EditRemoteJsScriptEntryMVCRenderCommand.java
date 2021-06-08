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

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.remote.js.script.admin.web.internal.constants.RemoteJsScriptAdminPortletKeys;
import com.liferay.remote.js.script.admin.web.internal.constants.RemoteJsScriptAdminWebKeys;
import com.liferay.remote.js.script.admin.web.internal.display.context.RemoteJsScriptAdminDisplayContext;
import com.liferay.remote.js.script.exception.NoSuchEntryException;
import com.liferay.remote.js.script.service.RemoteJsScriptEntryLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
	service = MVCRenderCommand.class
)
public class EditRemoteJsScriptEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		renderRequest.setAttribute(
			RemoteJsScriptAdminWebKeys.REMOTE_JS_SCRIPT_ADMIN_DISPLAY_CONTEXT,
			new RemoteJsScriptAdminDisplayContext(
				renderRequest, renderResponse, _remoteJsScriptEntryLocalService));

		try {
			long remoteJsScriptEntryId = ParamUtil.getLong(
				renderRequest, "remoteJsScriptEntryId");

			if (remoteJsScriptEntryId > 0) {
				renderRequest.setAttribute(
					RemoteJsScriptAdminWebKeys.REMOTE_JS_SCRIPT_ENTRY,
					_remoteJsScriptEntryLocalService.getRemoteJsScriptEntry(
						remoteJsScriptEntryId));
			}
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchEntryException) {
				SessionErrors.add(renderRequest, exception.getClass());

				return "/admin/error.jsp";
			}

			throw new PortletException(exception);
		}

		return "/admin/edit.jsp";
	}

	@Reference
	private RemoteJsScriptEntryLocalService _remoteJsScriptEntryLocalService;

}