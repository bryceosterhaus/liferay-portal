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

package com.liferay.remote.js.script.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.remote.js.script.exception.DuplicateRemoteJsScriptEntryURLException;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.service.base.RemoteJsScriptEntryLocalServiceBaseImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryce Osterhaus
 */
@Component(
	property = "model.class.name=com.liferay.remote.js.script.model.RemoteJsScriptEntry",
	service = AopService.class
)
public class RemoteJsScriptEntryLocalServiceImpl
	extends RemoteJsScriptEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RemoteJsScriptEntry addRemoteJsScriptEntry(
			long userId, Map<Locale, String> nameMap, String url, String customElementName,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long companyId = user.getCompanyId();

		validate(companyId, 0, url);

		long remoteJsScriptEntryId = counterLocalService.increment();

		RemoteJsScriptEntry remoteJsScriptEntry = remoteJsScriptEntryPersistence.create(
			remoteJsScriptEntryId);

		remoteJsScriptEntry.setUuid(serviceContext.getUuid());
		remoteJsScriptEntry.setCompanyId(companyId);
		remoteJsScriptEntry.setUserId(user.getUserId());
		remoteJsScriptEntry.setUserName(user.getFullName());
		remoteJsScriptEntry.setNameMap(nameMap);
		remoteJsScriptEntry.setUrl(url);
		remoteJsScriptEntry.setCustomElementName(customElementName);

		return remoteJsScriptEntryPersistence.update(remoteJsScriptEntry);
	}

	@Override
	public List<RemoteJsScriptEntry> searchRemoteJsScriptEntries(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, keywords, start, end, sort);

		return searchRemoteJsScriptEntries(searchContext);
	}

	@Override
	public int searchRemoteJsScriptEntriesCount(long companyId, String keywords)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return searchRemoteJsScriptEntriesCount(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RemoteJsScriptEntry updateRemoteJsScriptEntry(
			long remoteJsScriptEntryId, Map<Locale, String> nameMap, String url,
			ServiceContext serviceContext)
		throws PortalException {

		validate(serviceContext.getCompanyId(), remoteJsScriptEntryId, url);

		RemoteJsScriptEntry remoteJsScriptEntry =
			remoteJsScriptEntryPersistence.findByPrimaryKey(remoteJsScriptEntryId);

		remoteJsScriptEntry.setNameMap(nameMap);
		remoteJsScriptEntry.setUrl(url);

		return remoteJsScriptEntryPersistence.update(remoteJsScriptEntry);
	}

	protected SearchContext buildSearchContext(
		long companyId, String keywords, int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setAttributes(
			HashMapBuilder.<String, Serializable>put(
				Field.NAME, keywords
			).put(
				Field.URL, keywords
			).build());
		searchContext.setCompanyId(companyId);
		searchContext.setEnd(end);
		searchContext.setKeywords(keywords);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		return searchContext;
	}

	protected List<RemoteJsScriptEntry> getRemoteJsScriptEntries(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<RemoteJsScriptEntry> remoteJsScriptEntries = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long remoteJsScriptEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			RemoteJsScriptEntry remoteJsScriptEntry =
				remoteJsScriptEntryPersistence.fetchByPrimaryKey(remoteJsScriptEntryId);

			if (remoteJsScriptEntry == null) {
				remoteJsScriptEntries = null;

				Indexer<RemoteJsScriptEntry> indexer =
					IndexerRegistryUtil.getIndexer(RemoteJsScriptEntry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else {
				remoteJsScriptEntries.add(remoteJsScriptEntry);
			}
		}

		return remoteJsScriptEntries;
	}

	protected List<RemoteJsScriptEntry> searchRemoteJsScriptEntries(
			SearchContext searchContext)
		throws PortalException {

		Indexer<RemoteJsScriptEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(RemoteJsScriptEntry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext);

			List<RemoteJsScriptEntry> remoteJsScriptEntries = getRemoteJsScriptEntries(hits);

			if (remoteJsScriptEntries != null) {
				return remoteJsScriptEntries;
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected int searchRemoteJsScriptEntriesCount(SearchContext searchContext)
		throws PortalException {

		Indexer<RemoteJsScriptEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(RemoteJsScriptEntry.class);

		return GetterUtil.getInteger(indexer.searchCount(searchContext));
	}

	protected void validate(long companyId, long remoteJsScriptEntryId, String url)
		throws PortalException {

		RemoteJsScriptEntry remoteJsScriptEntry = remoteJsScriptEntryPersistence.fetchByC_U(
			companyId, StringUtil.trim(url));

		if ((remoteJsScriptEntry != null) &&
			(remoteJsScriptEntry.getRemoteJsScriptEntryId() != remoteJsScriptEntryId)) {

			throw new DuplicateRemoteJsScriptEntryURLException(
				"{remoteJsScriptEntryId=" + remoteJsScriptEntryId + "}");
		}
	}

}