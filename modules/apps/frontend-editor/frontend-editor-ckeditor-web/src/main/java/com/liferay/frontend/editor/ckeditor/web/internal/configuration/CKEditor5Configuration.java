/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.ckeditor.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.frontend.editor.ckeditor.web.internal.constants.CKEditorConstants;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Marko Cikos
 */
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.frontend.editor.ckeditor.web.internal.configuration.CKEditor5Configuration"
)
public interface CKEditor5Configuration {

	@Meta.AD(
		deflt = CKEditorConstants.OPEN_SOURCE_LICENSE_KEY, required = false
	)
	public String licenseKey();

}