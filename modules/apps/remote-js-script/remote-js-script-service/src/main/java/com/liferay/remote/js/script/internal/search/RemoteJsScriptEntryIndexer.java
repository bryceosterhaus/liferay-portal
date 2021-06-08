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

package com.liferay.remote.js.script.internal.search;

import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.service.RemoteJsScriptEntryLocalService;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(immediate = true, service = Indexer.class)
public class RemoteJsScriptEntryIndexer extends BaseIndexer<RemoteJsScriptEntry> {

	public static final String CLASS_NAME = RemoteJsScriptEntry.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchTerm(searchQuery, searchContext, Field.URL, true);
	}

	@Override
	protected void doDelete(RemoteJsScriptEntry remoteJsScriptEntry) throws Exception {
		deleteDocument(
			remoteJsScriptEntry.getCompanyId(),
			remoteJsScriptEntry.getRemoteJsScriptEntryId());
	}

	@Override
	protected Document doGetDocument(RemoteJsScriptEntry remoteJsScriptEntry)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing remote js script entry " + remoteJsScriptEntry);
		}

		Document document = getBaseModelDocument(CLASS_NAME, remoteJsScriptEntry);

		Localization localization = getLocalization();

		String[] nameAvailableLanguageIds =
			localization.getAvailableLanguageIds(remoteJsScriptEntry.getName());

		String nameDefaultLanguageId = LocalizationUtil.getDefaultLanguageId(
			remoteJsScriptEntry.getName());

		for (String nameAvailableLanguageId : nameAvailableLanguageIds) {
			String name = remoteJsScriptEntry.getName(nameAvailableLanguageId);

			if (nameDefaultLanguageId.equals(nameAvailableLanguageId)) {
				document.addText(Field.NAME, name);
			}

			document.addText(
				localization.getLocalizedName(
					Field.NAME, nameAvailableLanguageId),
				name);
		}

		document.addText(Field.URL, remoteJsScriptEntry.getUrl());

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + remoteJsScriptEntry + " indexed successfully");
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(document, Field.NAME, Field.URL);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(RemoteJsScriptEntry remoteJsScriptEntry) throws Exception {
		_indexWriterHelper.updateDocument(
			getSearchEngineId(), remoteJsScriptEntry.getCompanyId(),
			getDocument(remoteJsScriptEntry), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(_remoteJsScriptEntryLocalService.getRemoteJsScriptEntry(classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexRemoteJsScriptEntries(companyId);
	}

	protected Localization getLocalization() {

		// See LPS-72507

		if (_localization != null) {
			return _localization;
		}

		return LocalizationUtil.getLocalization();
	}

	protected void reindexRemoteJsScriptEntries(long companyId)
		throws PortalException {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_remoteJsScriptEntryLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(RemoteJsScriptEntry remoteJsScriptEntry) -> {
				try {
					indexableActionableDynamicQuery.addDocuments(
						getDocument(remoteJsScriptEntry));
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index remote js script entry " +
								remoteJsScriptEntry.getRemoteJsScriptEntryId(),
							portalException);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteJsScriptEntryIndexer.class);

	@Reference
	private IndexWriterHelper _indexWriterHelper;

	private Localization _localization;

	@Reference
	private RemoteJsScriptEntryLocalService _remoteJsScriptEntryLocalService;

}