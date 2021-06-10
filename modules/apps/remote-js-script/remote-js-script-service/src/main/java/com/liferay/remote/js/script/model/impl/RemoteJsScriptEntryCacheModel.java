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

package com.liferay.remote.js.script.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RemoteJsScriptEntry in entity cache.
 *
 * @author Bryce Osterhaus
 * @generated
 */
public class RemoteJsScriptEntryCacheModel
	implements CacheModel<RemoteJsScriptEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RemoteJsScriptEntryCacheModel)) {
			return false;
		}

		RemoteJsScriptEntryCacheModel remoteJsScriptEntryCacheModel =
			(RemoteJsScriptEntryCacheModel)object;

		if ((remoteJsScriptEntryId == remoteJsScriptEntryCacheModel.remoteJsScriptEntryId) &&
			(mvccVersion == remoteJsScriptEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, remoteJsScriptEntryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", remoteJsScriptEntryId=");
		sb.append(remoteJsScriptEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", url=");
		sb.append(url);
		sb.append(", customElementName=");
		sb.append(customElementName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RemoteJsScriptEntry toEntityModel() {
		RemoteJsScriptEntryImpl remoteJsScriptEntryImpl = new RemoteJsScriptEntryImpl();

		remoteJsScriptEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			remoteJsScriptEntryImpl.setUuid("");
		}
		else {
			remoteJsScriptEntryImpl.setUuid(uuid);
		}

		remoteJsScriptEntryImpl.setRemoteJsScriptEntryId(remoteJsScriptEntryId);
		remoteJsScriptEntryImpl.setCompanyId(companyId);
		remoteJsScriptEntryImpl.setUserId(userId);

		if (userName == null) {
			remoteJsScriptEntryImpl.setUserName("");
		}
		else {
			remoteJsScriptEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			remoteJsScriptEntryImpl.setCreateDate(null);
		}
		else {
			remoteJsScriptEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			remoteJsScriptEntryImpl.setModifiedDate(null);
		}
		else {
			remoteJsScriptEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			remoteJsScriptEntryImpl.setName("");
		}
		else {
			remoteJsScriptEntryImpl.setName(name);
		}

		if (url == null) {
			remoteJsScriptEntryImpl.setUrl("");
		}
		else {
			remoteJsScriptEntryImpl.setUrl(url);
		}

		if (customElementName == null) {
			remoteJsScriptEntryImpl.setCustomElementName("");
		}
		else {
			remoteJsScriptEntryImpl.setCustomElementName(customElementName);
		}

		remoteJsScriptEntryImpl.resetOriginalValues();

		return remoteJsScriptEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		remoteJsScriptEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		customElementName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(remoteJsScriptEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (customElementName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(customElementName);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long remoteJsScriptEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String customElementName;

}