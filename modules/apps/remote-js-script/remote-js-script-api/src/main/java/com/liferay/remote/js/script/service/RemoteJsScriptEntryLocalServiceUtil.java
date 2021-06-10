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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for RemoteJsScriptEntry. This utility wraps
 * <code>com.liferay.remote.js.script.service.impl.RemoteJsScriptEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Bryce Osterhaus
 * @see RemoteJsScriptEntryLocalService
 * @generated
 */
public class RemoteJsScriptEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.remote.js.script.service.impl.RemoteJsScriptEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static RemoteJsScriptEntry addRemoteJsScriptEntry(
			long userId, Map<java.util.Locale, String> nameMap, String url, String customElementName,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addRemoteJsScriptEntry(
			userId, nameMap, url, customElementName, serviceContext);
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
	public static RemoteJsScriptEntry addRemoteJsScriptEntry(
		RemoteJsScriptEntry remoteJsScriptEntry) {

		return getService().addRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new remote js script entry with the primary key. Does not add the remote js script entry to the database.
	 *
	 * @param remoteJsScriptEntryId the primary key for the new remote js script entry
	 * @return the new remote js script entry
	 */
	public static RemoteJsScriptEntry createRemoteJsScriptEntry(long remoteJsScriptEntryId) {
		return getService().createRemoteJsScriptEntry(remoteJsScriptEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static RemoteJsScriptEntry deleteRemoteJsScriptEntry(long remoteJsScriptEntryId)
		throws PortalException {

		return getService().deleteRemoteJsScriptEntry(remoteJsScriptEntryId);
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
	public static RemoteJsScriptEntry deleteRemoteJsScriptEntry(
		RemoteJsScriptEntry remoteJsScriptEntry) {

		return getService().deleteRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static RemoteJsScriptEntry fetchRemoteJsScriptEntry(long remoteJsScriptEntryId) {
		return getService().fetchRemoteJsScriptEntry(remoteJsScriptEntryId);
	}

	/**
	 * Returns the remote js script entry with the matching UUID and company.
	 *
	 * @param uuid the remote js script entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public static RemoteJsScriptEntry fetchRemoteJsScriptEntryByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchRemoteJsScriptEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static List<RemoteJsScriptEntry> getRemoteJsScriptEntries(int start, int end) {
		return getService().getRemoteJsScriptEntries(start, end);
	}

	/**
	 * Returns the number of remote js script entries.
	 *
	 * @return the number of remote js script entries
	 */
	public static int getRemoteJsScriptEntriesCount() {
		return getService().getRemoteJsScriptEntriesCount();
	}

	/**
	 * Returns the remote js script entry with the primary key.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry
	 * @throws PortalException if a remote js script entry with the primary key could not be found
	 */
	public static RemoteJsScriptEntry getRemoteJsScriptEntry(long remoteJsScriptEntryId)
		throws PortalException {

		return getService().getRemoteJsScriptEntry(remoteJsScriptEntryId);
	}

	/**
	 * Returns the remote js script entry with the matching UUID and company.
	 *
	 * @param uuid the remote js script entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote js script entry
	 * @throws PortalException if a matching remote js script entry could not be found
	 */
	public static RemoteJsScriptEntry getRemoteJsScriptEntryByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getRemoteJsScriptEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static List<RemoteJsScriptEntry> searchRemoteJsScriptEntries(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().searchRemoteJsScriptEntries(
			companyId, keywords, start, end, sort);
	}

	public static int searchRemoteJsScriptEntriesCount(
			long companyId, String keywords)
		throws PortalException {

		return getService().searchRemoteJsScriptEntriesCount(companyId, keywords);
	}

	public static RemoteJsScriptEntry updateRemoteJsScriptEntry(
			long remoteJsScriptEntryId, Map<java.util.Locale, String> nameMap,
			String url,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateRemoteJsScriptEntry(
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
	public static RemoteJsScriptEntry updateRemoteJsScriptEntry(
		RemoteJsScriptEntry remoteJsScriptEntry) {

		return getService().updateRemoteJsScriptEntry(remoteJsScriptEntry);
	}

	public static RemoteJsScriptEntryLocalService getService() {
		return _service;
	}

	private static volatile RemoteJsScriptEntryLocalService _service;

}