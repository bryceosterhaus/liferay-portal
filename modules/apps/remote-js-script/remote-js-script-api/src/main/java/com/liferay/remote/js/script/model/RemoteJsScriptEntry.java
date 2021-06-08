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

package com.liferay.remote.js.script.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the RemoteJsScriptEntry service. Represents a row in the &quot;RemoteJsScriptEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Bryce Osterhaus
 * @see RemoteJsScriptEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryImpl")
@ProviderType
public interface RemoteJsScriptEntry extends PersistedModel, RemoteJsScriptEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<RemoteJsScriptEntry, Long>
		REMOTE_JS_SCRIPT_ENTRY_ID_ACCESSOR = new Accessor<RemoteJsScriptEntry, Long>() {

			@Override
			public Long get(RemoteJsScriptEntry remoteJsScriptEntry) {
				return remoteJsScriptEntry.getRemoteJsScriptEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<RemoteJsScriptEntry> getTypeClass() {
				return RemoteJsScriptEntry.class;
			}

		};

}