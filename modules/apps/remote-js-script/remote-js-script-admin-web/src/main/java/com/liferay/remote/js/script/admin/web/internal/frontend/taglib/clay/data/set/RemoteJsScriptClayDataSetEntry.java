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

package com.liferay.remote.js.script.admin.web.internal.frontend.taglib.clay.data.set;

import com.liferay.remote.js.script.model.RemoteJsScriptEntry;

import java.util.Locale;

/**
 * @author Bryce Osterhaus
 */
public class RemoteJsScriptClayDataSetEntry {

	public RemoteJsScriptClayDataSetEntry(
		RemoteJsScriptEntry remoteJsScriptEntry, Locale locale) {

		_remoteJsScriptEntry = remoteJsScriptEntry;
		_locale = locale;
	}

	public String getName() {
		return _remoteJsScriptEntry.getName(_locale);
	}

	public long getRemoteJsScriptEntryId() {
		return _remoteJsScriptEntry.getRemoteJsScriptEntryId();
	}

	public String getURL() {
		return _remoteJsScriptEntry.getUrl();
	}

	public String getCustomElementName() {
		return _remoteJsScriptEntry.getCustomElementName();
	}

	private final Locale _locale;
	private final RemoteJsScriptEntry _remoteJsScriptEntry;

}