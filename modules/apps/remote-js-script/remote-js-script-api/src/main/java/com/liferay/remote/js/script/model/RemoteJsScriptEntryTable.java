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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;RemoteJsScriptEntry&quot; database table.
 *
 * @author Bryce Osterhaus
 * @see RemoteJsScriptEntry
 * @generated
 */
public class RemoteJsScriptEntryTable extends BaseTable<RemoteJsScriptEntryTable> {

	public static final RemoteJsScriptEntryTable INSTANCE =
		new RemoteJsScriptEntryTable();

	public final Column<RemoteJsScriptEntryTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<RemoteJsScriptEntryTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, Long> remoteJsScriptEntryId =
		createColumn(
			"remoteJsScriptEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<RemoteJsScriptEntryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RemoteJsScriptEntryTable, String> url = createColumn(
		"url", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private RemoteJsScriptEntryTable() {
		super("RemoteJsScriptEntry", RemoteJsScriptEntryTable::new);
	}

}