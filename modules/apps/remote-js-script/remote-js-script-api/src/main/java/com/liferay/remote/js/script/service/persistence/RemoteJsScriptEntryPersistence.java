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

package com.liferay.remote.js.script.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.remote.js.script.exception.NoSuchEntryException;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the remote js script entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bryce Osterhaus
 * @see RemoteJsScriptEntryUtil
 * @generated
 */
@ProviderType
public interface RemoteJsScriptEntryPersistence
	extends BasePersistence<RemoteJsScriptEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RemoteJsScriptEntryUtil} to access the remote js script entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the remote js script entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid(String uuid);

	/**
	 * Returns a range of all the remote js script entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @return the range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the remote js script entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the remote js script entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns the remote js script entries before and after the current remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param remoteJsScriptEntryId the primary key of the current remote js script entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	public RemoteJsScriptEntry[] findByUuid_PrevAndNext(
			long remoteJsScriptEntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the remote js script entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of remote js script entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching remote js script entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @return the range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns the remote js script entries before and after the current remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param remoteJsScriptEntryId the primary key of the current remote js script entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	public RemoteJsScriptEntry[] findByUuid_C_PrevAndNext(
			long remoteJsScriptEntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the remote js script entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching remote js script entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry findByC_U(long companyId, String url)
		throws NoSuchEntryException;

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByC_U(long companyId, String url);

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	public RemoteJsScriptEntry fetchByC_U(
		long companyId, String url, boolean useFinderCache);

	/**
	 * Removes the remote js script entry where companyId = &#63; and url = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the remote js script entry that was removed
	 */
	public RemoteJsScriptEntry removeByC_U(long companyId, String url)
		throws NoSuchEntryException;

	/**
	 * Returns the number of remote js script entries where companyId = &#63; and url = &#63;.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the number of matching remote js script entries
	 */
	public int countByC_U(long companyId, String url);

	/**
	 * Caches the remote js script entry in the entity cache if it is enabled.
	 *
	 * @param remoteJsScriptEntry the remote js script entry
	 */
	public void cacheResult(RemoteJsScriptEntry remoteJsScriptEntry);

	/**
	 * Caches the remote js script entries in the entity cache if it is enabled.
	 *
	 * @param remoteJsScriptEntries the remote js script entries
	 */
	public void cacheResult(java.util.List<RemoteJsScriptEntry> remoteJsScriptEntries);

	/**
	 * Creates a new remote js script entry with the primary key. Does not add the remote js script entry to the database.
	 *
	 * @param remoteJsScriptEntryId the primary key for the new remote js script entry
	 * @return the new remote js script entry
	 */
	public RemoteJsScriptEntry create(long remoteJsScriptEntryId);

	/**
	 * Removes the remote js script entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry that was removed
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	public RemoteJsScriptEntry remove(long remoteJsScriptEntryId)
		throws NoSuchEntryException;

	public RemoteJsScriptEntry updateImpl(RemoteJsScriptEntry remoteJsScriptEntry);

	/**
	 * Returns the remote js script entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	public RemoteJsScriptEntry findByPrimaryKey(long remoteJsScriptEntryId)
		throws NoSuchEntryException;

	/**
	 * Returns the remote js script entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry, or <code>null</code> if a remote js script entry with the primary key could not be found
	 */
	public RemoteJsScriptEntry fetchByPrimaryKey(long remoteJsScriptEntryId);

	/**
	 * Returns all the remote js script entries.
	 *
	 * @return the remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findAll();

	/**
	 * Returns a range of all the remote js script entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @return the range of remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the remote js script entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the remote js script entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemoteJsScriptEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remote js script entries
	 * @param end the upper bound of the range of remote js script entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of remote js script entries
	 */
	public java.util.List<RemoteJsScriptEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemoteJsScriptEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the remote js script entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of remote js script entries.
	 *
	 * @return the number of remote js script entries
	 */
	public int countAll();

}