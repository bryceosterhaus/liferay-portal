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

package com.liferay.remote.js.script.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RemoteJsScriptEntryLocalService}.
 *
 * @author Bryce Osterhaus
 * @see RemoteJsScriptEntryLocalService
 * @generated
 */
public class RemoteJsScriptEntryLocalServiceWrapper
	implements RemoteJsScriptEntryLocalService,
			   ServiceWrapper<RemoteJsScriptEntryLocalService> {

	public RemoteJsScriptEntryLocalServiceWrapper(
		RemoteJsScriptEntryLocalService remoteJsScriptEntryLocalService) {

		_remoteJsScriptEntryLocalService = remoteJsScriptEntryLocalService;
	}

	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry addRemoteJsScriptEntry(
			long userId, java.util.Map<java.util.Locale, String> nameMap,
			String url,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.addRemoteJsScriptEntry(
			userId, nameMap, url, serviceContext);
	}

	/**
	 * Adds the remote js script entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteJsScriptEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteJsScriptEntry the remote js script entry
	 * @return the remote js script entry that was added
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry addRemoteJsScriptEntry(
		com.liferay.remote.js.script.model.RemoteJsScriptEntry remoteJsScriptEntry) {

		return _remoteJsScriptEntryLocalService.addRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new remote js script entry with the primary key. Does not add the remote js script entry to the database.
	 *
	 * @param remoteJsScriptEntryId the primary key for the new remote js script entry
	 * @return the new remote js script entry
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry createRemoteJsScriptEntry(
		long remoteJsScriptEntryId) {

		return _remoteJsScriptEntryLocalService.createRemoteJsScriptEntry(
			remoteJsScriptEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the remote js script entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteJsScriptEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry that was removed
	 * @throws PortalException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry deleteRemoteJsScriptEntry(
			long remoteJsScriptEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.deleteRemoteJsScriptEntry(
			remoteJsScriptEntryId);
	}

	/**
	 * Deletes the remote js script entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteJsScriptEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteJsScriptEntry the remote js script entry
	 * @return the remote js script entry that was removed
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry deleteRemoteJsScriptEntry(
		com.liferay.remote.js.script.model.RemoteJsScriptEntry remoteJsScriptEntry) {

		return _remoteJsScriptEntryLocalService.deleteRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _remoteJsScriptEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _remoteJsScriptEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _remoteJsScriptEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _remoteJsScriptEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _remoteJsScriptEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _remoteJsScriptEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _remoteJsScriptEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _remoteJsScriptEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry fetchRemoteJsScriptEntry(
		long remoteJsScriptEntryId) {

		return _remoteJsScriptEntryLocalService.fetchRemoteJsScriptEntry(
			remoteJsScriptEntryId);
	}

	/**
	 * Returns the remote js script entry with the matching UUID and company.
	 *
	 * @param uuid the remote js script entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry
		fetchRemoteJsScriptEntryByUuidAndCompanyId(String uuid, long companyId) {

		return _remoteJsScriptEntryLocalService.
			fetchRemoteJsScriptEntryByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _remoteJsScriptEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _remoteJsScriptEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _remoteJsScriptEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _remoteJsScriptEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the remote js script entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @return the range of remote js script entries
	 */
	@Override
	public java.util.List<com.liferay.remote.js.script.model.RemoteJsScriptEntry>
		getRemoteJsScriptEntries(int start, int end) {

		return _remoteJsScriptEntryLocalService.getRemoteJsScriptEntries(start, end);
	}

	/**
	 * Returns the number of remote js script entries.
	 *
	 * @return the number of remote js script entries
	 */
	@Override
	public int getRemoteJsScriptEntriesCount() {
		return _remoteJsScriptEntryLocalService.getRemoteJsScriptEntriesCount();
	}

	/**
	 * Returns the remote js script entry with the primary key.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry
	 * @throws PortalException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry getRemoteJsScriptEntry(
			long remoteJsScriptEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.getRemoteJsScriptEntry(remoteJsScriptEntryId);
	}

	/**
	 * Returns the remote js script entry with the matching UUID and company.
	 *
	 * @param uuid the remote js script entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote js script entry
	 * @throws PortalException if a matching remote js script entry could not be found
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry
			getRemoteJsScriptEntryByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.getRemoteJsScriptEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public java.util.List<com.liferay.remote.js.script.model.RemoteJsScriptEntry>
			searchRemoteJsScriptEntries(
				long companyId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.searchRemoteJsScriptEntries(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchRemoteJsScriptEntriesCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.searchRemoteJsScriptEntriesCount(
			companyId, keywords);
	}

	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry updateRemoteJsScriptEntry(
			long remoteJsScriptEntryId,
			java.util.Map<java.util.Locale, String> nameMap, String url,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteJsScriptEntryLocalService.updateRemoteJsScriptEntry(
			remoteJsScriptEntryId, nameMap, url, serviceContext);
	}

	/**
	 * Updates the remote js script entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteJsScriptEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteJsScriptEntry the remote js script entry
	 * @return the remote js script entry that was updated
	 */
	@Override
	public com.liferay.remote.js.script.model.RemoteJsScriptEntry updateRemoteJsScriptEntry(
		com.liferay.remote.js.script.model.RemoteJsScriptEntry remoteJsScriptEntry) {

		return _remoteJsScriptEntryLocalService.updateRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	@Override
	public RemoteJsScriptEntryLocalService getWrappedService() {
		return _remoteJsScriptEntryLocalService;
	}

	@Override
	public void setWrappedService(
		RemoteJsScriptEntryLocalService remoteJsScriptEntryLocalService) {

		_remoteJsScriptEntryLocalService = remoteJsScriptEntryLocalService;
	}

	private RemoteJsScriptEntryLocalService _remoteJsScriptEntryLocalService;

}