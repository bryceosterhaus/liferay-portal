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

package com.liferay.remote.js.script.admin.web.internal.frontend.taglib.clay.data.set.provider;

import com.liferay.frontend.taglib.clay.data.Filter;
import com.liferay.frontend.taglib.clay.data.Pagination;
import com.liferay.frontend.taglib.clay.data.set.provider.ClayDataSetDataProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.remote.js.script.admin.web.internal.constants.RemoteJsScriptAdminConstants;
import com.liferay.remote.js.script.admin.web.internal.frontend.taglib.clay.data.set.RemoteJsScriptClayDataSetEntry;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.service.RemoteJsScriptEntryLocalService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(
	immediate = true,
	property = "clay.data.provider.key=" + RemoteJsScriptAdminConstants.REMOTE_JS_SCRIPT_ENTRY_DATA_SET_DISPLAY,
	service = ClayDataSetDataProvider.class
)
public class RemoteJsScriptEntryClayDataSetDataProvider
	implements ClayDataSetDataProvider<RemoteJsScriptClayDataSetEntry> {

	@Override
	public List<RemoteJsScriptClayDataSetEntry> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<RemoteJsScriptEntry> remoteJsScriptEntries =
			_remoteJsScriptEntryLocalService.searchRemoteJsScriptEntries(
				themeDisplay.getCompanyId(), filter.getKeywords(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				sort);

		Stream<RemoteJsScriptEntry> stream = remoteJsScriptEntries.stream();

		return stream.map(
			remoteJsScriptEntry -> new RemoteJsScriptClayDataSetEntry(
				remoteJsScriptEntry, themeDisplay.getLocale())
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public int getItemsCount(
			HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _remoteJsScriptEntryLocalService.searchRemoteJsScriptEntriesCount(
			themeDisplay.getCompanyId(), filter.getKeywords());
	}

	@Reference
	private RemoteJsScriptEntryLocalService _remoteJsScriptEntryLocalService;

}