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

package com.liferay.remote.js.script.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.remote.js.script.exception.NoSuchEntryException;
import com.liferay.remote.js.script.model.RemoteJsScriptEntry;
import com.liferay.remote.js.script.model.RemoteJsScriptEntryTable;
import com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryImpl;
import com.liferay.remote.js.script.model.impl.RemoteJsScriptEntryModelImpl;
import com.liferay.remote.js.script.service.persistence.RemoteJsScriptEntryPersistence;
import com.liferay.remote.js.script.service.persistence.impl.constants.RemoteJsScriptPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the remote js script entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bryce Osterhaus
 * @generated
 */
@Component(service = {RemoteJsScriptEntryPersistence.class, BasePersistence.class})
public class RemoteJsScriptEntryPersistenceImpl
	extends BasePersistenceImpl<RemoteJsScriptEntry>
	implements RemoteJsScriptEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RemoteJsScriptEntryUtil</code> to access the remote js script entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RemoteJsScriptEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the remote js script entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching remote js script entries
	 */
	@Override
	public List<RemoteJsScriptEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<RemoteJsScriptEntry> list = null;

		if (useFinderCache) {
			list = (List<RemoteJsScriptEntry>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (RemoteJsScriptEntry remoteJsScriptEntry : list) {
					if (!uuid.equals(remoteJsScriptEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_REMOTEAPPENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RemoteJsScriptEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<RemoteJsScriptEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByUuid_First(
			String uuid, OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByUuid_First(
			uuid, orderByComparator);

		if (remoteJsScriptEntry != null) {
			return remoteJsScriptEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByUuid_First(
		String uuid, OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		List<RemoteJsScriptEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByUuid_Last(
			String uuid, OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (remoteJsScriptEntry != null) {
			return remoteJsScriptEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByUuid_Last(
		String uuid, OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RemoteJsScriptEntry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the remote js script entries before and after the current remote js script entry in the ordered set where uuid = &#63;.
	 *
	 * @param remoteJsScriptEntryId the primary key of the current remote js script entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry[] findByUuid_PrevAndNext(
			long remoteJsScriptEntryId, String uuid,
			OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		uuid = Objects.toString(uuid, "");

		RemoteJsScriptEntry remoteJsScriptEntry = findByPrimaryKey(remoteJsScriptEntryId);

		Session session = null;

		try {
			session = openSession();

			RemoteJsScriptEntry[] array = new RemoteJsScriptEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, remoteJsScriptEntry, uuid, orderByComparator, true);

			array[1] = remoteJsScriptEntry;

			array[2] = getByUuid_PrevAndNext(
				session, remoteJsScriptEntry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RemoteJsScriptEntry getByUuid_PrevAndNext(
		Session session, RemoteJsScriptEntry remoteJsScriptEntry, String uuid,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_REMOTEAPPENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(RemoteJsScriptEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						remoteJsScriptEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RemoteJsScriptEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the remote js script entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RemoteJsScriptEntry remoteJsScriptEntry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(remoteJsScriptEntry);
		}
	}

	/**
	 * Returns the number of remote js script entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching remote js script entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REMOTEAPPENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"remoteJsScriptEntry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(remoteJsScriptEntry.uuid IS NULL OR remoteJsScriptEntry.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching remote js script entries
	 */
	@Override
	public List<RemoteJsScriptEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<RemoteJsScriptEntry> list = null;

		if (useFinderCache) {
			list = (List<RemoteJsScriptEntry>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (RemoteJsScriptEntry remoteJsScriptEntry : list) {
					if (!uuid.equals(remoteJsScriptEntry.getUuid()) ||
						(companyId != remoteJsScriptEntry.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_REMOTEAPPENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RemoteJsScriptEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<RemoteJsScriptEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (remoteJsScriptEntry != null) {
			return remoteJsScriptEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the first remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		List<RemoteJsScriptEntry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (remoteJsScriptEntry != null) {
			return remoteJsScriptEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntryException(sb.toString());
	}

	/**
	 * Returns the last remote js script entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<RemoteJsScriptEntry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public RemoteJsScriptEntry[] findByUuid_C_PrevAndNext(
			long remoteJsScriptEntryId, String uuid, long companyId,
			OrderByComparator<RemoteJsScriptEntry> orderByComparator)
		throws NoSuchEntryException {

		uuid = Objects.toString(uuid, "");

		RemoteJsScriptEntry remoteJsScriptEntry = findByPrimaryKey(remoteJsScriptEntryId);

		Session session = null;

		try {
			session = openSession();

			RemoteJsScriptEntry[] array = new RemoteJsScriptEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, remoteJsScriptEntry, uuid, companyId, orderByComparator,
				true);

			array[1] = remoteJsScriptEntry;

			array[2] = getByUuid_C_PrevAndNext(
				session, remoteJsScriptEntry, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RemoteJsScriptEntry getByUuid_C_PrevAndNext(
		Session session, RemoteJsScriptEntry remoteJsScriptEntry, String uuid,
		long companyId, OrderByComparator<RemoteJsScriptEntry> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_REMOTEAPPENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(RemoteJsScriptEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						remoteJsScriptEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RemoteJsScriptEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the remote js script entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (RemoteJsScriptEntry remoteJsScriptEntry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(remoteJsScriptEntry);
		}
	}

	/**
	 * Returns the number of remote js script entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching remote js script entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_REMOTEAPPENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"remoteJsScriptEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(remoteJsScriptEntry.uuid IS NULL OR remoteJsScriptEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"remoteJsScriptEntry.companyId = ?";

	private FinderPath _finderPathFetchByC_U;
	private FinderPath _finderPathCountByC_U;

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching remote js script entry
	 * @throws NoSuchEntryException if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByC_U(long companyId, String url)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByC_U(companyId, url);

		if (remoteJsScriptEntry == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", url=");
			sb.append(url);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntryException(sb.toString());
		}

		return remoteJsScriptEntry;
	}

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByC_U(long companyId, String url) {
		return fetchByC_U(companyId, url, true);
	}

	/**
	 * Returns the remote js script entry where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching remote js script entry, or <code>null</code> if a matching remote js script entry could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByC_U(
		long companyId, String url, boolean useFinderCache) {

		url = Objects.toString(url, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, url};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(_finderPathFetchByC_U, finderArgs);
		}

		if (result instanceof RemoteJsScriptEntry) {
			RemoteJsScriptEntry remoteJsScriptEntry = (RemoteJsScriptEntry)result;

			if ((companyId != remoteJsScriptEntry.getCompanyId()) ||
				!Objects.equals(url, remoteJsScriptEntry.getUrl())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_REMOTEAPPENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			boolean bindUrl = false;

			if (url.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_U_URL_3);
			}
			else {
				bindUrl = true;

				sb.append(_FINDER_COLUMN_C_U_URL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindUrl) {
					queryPos.add(url);
				}

				List<RemoteJsScriptEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_U, finderArgs, list);
					}
				}
				else {
					RemoteJsScriptEntry remoteJsScriptEntry = list.get(0);

					result = remoteJsScriptEntry;

					cacheResult(remoteJsScriptEntry);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (RemoteJsScriptEntry)result;
		}
	}

	/**
	 * Removes the remote js script entry where companyId = &#63; and url = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the remote js script entry that was removed
	 */
	@Override
	public RemoteJsScriptEntry removeByC_U(long companyId, String url)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = findByC_U(companyId, url);

		return remove(remoteJsScriptEntry);
	}

	/**
	 * Returns the number of remote js script entries where companyId = &#63; and url = &#63;.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the number of matching remote js script entries
	 */
	@Override
	public int countByC_U(long companyId, String url) {
		url = Objects.toString(url, "");

		FinderPath finderPath = _finderPathCountByC_U;

		Object[] finderArgs = new Object[] {companyId, url};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_REMOTEAPPENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			boolean bindUrl = false;

			if (url.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_U_URL_3);
			}
			else {
				bindUrl = true;

				sb.append(_FINDER_COLUMN_C_U_URL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindUrl) {
					queryPos.add(url);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 =
		"remoteJsScriptEntry.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_U_URL_2 =
		"remoteJsScriptEntry.url = ?";

	private static final String _FINDER_COLUMN_C_U_URL_3 =
		"(remoteJsScriptEntry.url IS NULL OR remoteJsScriptEntry.url = '')";

	public RemoteJsScriptEntryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(RemoteJsScriptEntry.class);

		setModelImplClass(RemoteJsScriptEntryImpl.class);
		setModelPKClass(long.class);

		setTable(RemoteJsScriptEntryTable.INSTANCE);
	}

	/**
	 * Caches the remote js script entry in the entity cache if it is enabled.
	 *
	 * @param remoteJsScriptEntry the remote js script entry
	 */
	@Override
	public void cacheResult(RemoteJsScriptEntry remoteJsScriptEntry) {
		entityCache.putResult(
			RemoteJsScriptEntryImpl.class, remoteJsScriptEntry.getPrimaryKey(),
			remoteJsScriptEntry);

		finderCache.putResult(
			_finderPathFetchByC_U,
			new Object[] {
				remoteJsScriptEntry.getCompanyId(), remoteJsScriptEntry.getUrl()
			},
			remoteJsScriptEntry);
	}

	/**
	 * Caches the remote js script entries in the entity cache if it is enabled.
	 *
	 * @param remoteJsScriptEntries the remote js script entries
	 */
	@Override
	public void cacheResult(List<RemoteJsScriptEntry> remoteJsScriptEntries) {
		for (RemoteJsScriptEntry remoteJsScriptEntry : remoteJsScriptEntries) {
			if (entityCache.getResult(
					RemoteJsScriptEntryImpl.class, remoteJsScriptEntry.getPrimaryKey()) ==
						null) {

				cacheResult(remoteJsScriptEntry);
			}
		}
	}

	/**
	 * Clears the cache for all remote js script entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RemoteJsScriptEntryImpl.class);

		finderCache.clearCache(RemoteJsScriptEntryImpl.class);
	}

	/**
	 * Clears the cache for the remote js script entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RemoteJsScriptEntry remoteJsScriptEntry) {
		entityCache.removeResult(RemoteJsScriptEntryImpl.class, remoteJsScriptEntry);
	}

	@Override
	public void clearCache(List<RemoteJsScriptEntry> remoteJsScriptEntries) {
		for (RemoteJsScriptEntry remoteJsScriptEntry : remoteJsScriptEntries) {
			entityCache.removeResult(RemoteJsScriptEntryImpl.class, remoteJsScriptEntry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(RemoteJsScriptEntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(RemoteJsScriptEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		RemoteJsScriptEntryModelImpl remoteJsScriptEntryModelImpl) {

		Object[] args = new Object[] {
			remoteJsScriptEntryModelImpl.getCompanyId(),
			remoteJsScriptEntryModelImpl.getUrl()
		};

		finderCache.putResult(_finderPathCountByC_U, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByC_U, args, remoteJsScriptEntryModelImpl);
	}

	/**
	 * Creates a new remote js script entry with the primary key. Does not add the remote js script entry to the database.
	 *
	 * @param remoteJsScriptEntryId the primary key for the new remote js script entry
	 * @return the new remote js script entry
	 */
	@Override
	public RemoteJsScriptEntry create(long remoteJsScriptEntryId) {
		RemoteJsScriptEntry remoteJsScriptEntry = new RemoteJsScriptEntryImpl();

		remoteJsScriptEntry.setNew(true);
		remoteJsScriptEntry.setPrimaryKey(remoteJsScriptEntryId);

		String uuid = PortalUUIDUtil.generate();

		remoteJsScriptEntry.setUuid(uuid);

		remoteJsScriptEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return remoteJsScriptEntry;
	}

	/**
	 * Removes the remote js script entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry that was removed
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry remove(long remoteJsScriptEntryId)
		throws NoSuchEntryException {

		return remove((Serializable)remoteJsScriptEntryId);
	}

	/**
	 * Removes the remote js script entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the remote js script entry
	 * @return the remote js script entry that was removed
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry remove(Serializable primaryKey)
		throws NoSuchEntryException {

		Session session = null;

		try {
			session = openSession();

			RemoteJsScriptEntry remoteJsScriptEntry = (RemoteJsScriptEntry)session.get(
				RemoteJsScriptEntryImpl.class, primaryKey);

			if (remoteJsScriptEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(remoteJsScriptEntry);
		}
		catch (NoSuchEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected RemoteJsScriptEntry removeImpl(RemoteJsScriptEntry remoteJsScriptEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(remoteJsScriptEntry)) {
				remoteJsScriptEntry = (RemoteJsScriptEntry)session.get(
					RemoteJsScriptEntryImpl.class,
					remoteJsScriptEntry.getPrimaryKeyObj());
			}

			if (remoteJsScriptEntry != null) {
				session.delete(remoteJsScriptEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (remoteJsScriptEntry != null) {
			clearCache(remoteJsScriptEntry);
		}

		return remoteJsScriptEntry;
	}

	@Override
	public RemoteJsScriptEntry updateImpl(RemoteJsScriptEntry remoteJsScriptEntry) {
		boolean isNew = remoteJsScriptEntry.isNew();

		if (!(remoteJsScriptEntry instanceof RemoteJsScriptEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(remoteJsScriptEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					remoteJsScriptEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in remoteJsScriptEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RemoteJsScriptEntry implementation " +
					remoteJsScriptEntry.getClass());
		}

		RemoteJsScriptEntryModelImpl remoteJsScriptEntryModelImpl =
			(RemoteJsScriptEntryModelImpl)remoteJsScriptEntry;

		if (Validator.isNull(remoteJsScriptEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			remoteJsScriptEntry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (remoteJsScriptEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				remoteJsScriptEntry.setCreateDate(date);
			}
			else {
				remoteJsScriptEntry.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!remoteJsScriptEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				remoteJsScriptEntry.setModifiedDate(date);
			}
			else {
				remoteJsScriptEntry.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(remoteJsScriptEntry);
			}
			else {
				remoteJsScriptEntry = (RemoteJsScriptEntry)session.merge(remoteJsScriptEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			RemoteJsScriptEntryImpl.class, remoteJsScriptEntryModelImpl, false, true);

		cacheUniqueFindersCache(remoteJsScriptEntryModelImpl);

		if (isNew) {
			remoteJsScriptEntry.setNew(false);
		}

		remoteJsScriptEntry.resetOriginalValues();

		return remoteJsScriptEntry;
	}

	/**
	 * Returns the remote js script entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the remote js script entry
	 * @return the remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {

		RemoteJsScriptEntry remoteJsScriptEntry = fetchByPrimaryKey(primaryKey);

		if (remoteJsScriptEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return remoteJsScriptEntry;
	}

	/**
	 * Returns the remote js script entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry
	 * @throws NoSuchEntryException if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry findByPrimaryKey(long remoteJsScriptEntryId)
		throws NoSuchEntryException {

		return findByPrimaryKey((Serializable)remoteJsScriptEntryId);
	}

	/**
	 * Returns the remote js script entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param remoteJsScriptEntryId the primary key of the remote js script entry
	 * @return the remote js script entry, or <code>null</code> if a remote js script entry with the primary key could not be found
	 */
	@Override
	public RemoteJsScriptEntry fetchByPrimaryKey(long remoteJsScriptEntryId) {
		return fetchByPrimaryKey((Serializable)remoteJsScriptEntryId);
	}

	/**
	 * Returns all the remote js script entries.
	 *
	 * @return the remote js script entries
	 */
	@Override
	public List<RemoteJsScriptEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findAll(
		int start, int end,
		OrderByComparator<RemoteJsScriptEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<RemoteJsScriptEntry> findAll(
		int start, int end, OrderByComparator<RemoteJsScriptEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<RemoteJsScriptEntry> list = null;

		if (useFinderCache) {
			list = (List<RemoteJsScriptEntry>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REMOTEAPPENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REMOTEAPPENTRY;

				sql = sql.concat(RemoteJsScriptEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RemoteJsScriptEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the remote js script entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RemoteJsScriptEntry remoteJsScriptEntry : findAll()) {
			remove(remoteJsScriptEntry);
		}
	}

	/**
	 * Returns the number of remote js script entries.
	 *
	 * @return the number of remote js script entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REMOTEAPPENTRY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "remoteJsScriptEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REMOTEAPPENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RemoteJsScriptEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the remote js script entry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new RemoteJsScriptEntryModelArgumentsResolver(),
			new HashMapDictionary<>());

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathFetchByC_U = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_U",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "url"}, true);

		_finderPathCountByC_U = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "url"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(RemoteJsScriptEntryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();
	}

	@Override
	@Reference(
		target = RemoteJsScriptPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = RemoteJsScriptPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = RemoteJsScriptPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REMOTEAPPENTRY =
		"SELECT remoteJsScriptEntry FROM RemoteJsScriptEntry remoteJsScriptEntry";

	private static final String _SQL_SELECT_REMOTEAPPENTRY_WHERE =
		"SELECT remoteJsScriptEntry FROM RemoteJsScriptEntry remoteJsScriptEntry WHERE ";

	private static final String _SQL_COUNT_REMOTEAPPENTRY =
		"SELECT COUNT(remoteJsScriptEntry) FROM RemoteJsScriptEntry remoteJsScriptEntry";

	private static final String _SQL_COUNT_REMOTEAPPENTRY_WHERE =
		"SELECT COUNT(remoteJsScriptEntry) FROM RemoteJsScriptEntry remoteJsScriptEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "remoteJsScriptEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RemoteJsScriptEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RemoteJsScriptEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteJsScriptEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class RemoteJsScriptEntryModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			RemoteJsScriptEntryModelImpl remoteJsScriptEntryModelImpl =
				(RemoteJsScriptEntryModelImpl)baseModel;

			long columnBitmask = remoteJsScriptEntryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					remoteJsScriptEntryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						remoteJsScriptEntryModelImpl.getColumnBitmask(columnName);
				}

				if (finderPath.isBaseModelResult() &&
					(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

					finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					remoteJsScriptEntryModelImpl, columnNames, original);
			}

			return null;
		}

		@Override
		public String getClassName() {
			return RemoteJsScriptEntryImpl.class.getName();
		}

		@Override
		public String getTableName() {
			return RemoteJsScriptEntryTable.INSTANCE.getTableName();
		}

		private static Object[] _getValue(
			RemoteJsScriptEntryModelImpl remoteJsScriptEntryModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						remoteJsScriptEntryModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = remoteJsScriptEntryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

		private static final long _ORDER_BY_COLUMNS_BITMASK;

		static {
			long orderByColumnsBitmask = 0;

			orderByColumnsBitmask |= RemoteJsScriptEntryModelImpl.getColumnBitmask(
				"name");

			_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
		}

	}

}