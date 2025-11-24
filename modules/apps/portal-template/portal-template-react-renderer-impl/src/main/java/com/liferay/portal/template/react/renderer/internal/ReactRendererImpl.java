/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.react.renderer.internal;

import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.frontend.js.loader.modules.extender.esm.ESImportUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolvedPackageNameUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.taglib.aui.ESImport;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.react.renderer.ComponentDescriptor;
import com.liferay.portal.template.react.renderer.ReactRenderer;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilderFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Writer;

import java.net.HttpURLConnection;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(service = ReactRenderer.class)
public class ReactRendererImpl implements ReactRenderer {

	@Override
	public void renderReact(
			ComponentDescriptor componentDescriptor, Map<String, Object> data,
			HttpServletRequest httpServletRequest, Writer writer)
		throws IOException {

		renderReact(
			componentDescriptor, data, httpServletRequest, writer, false);
	}

	@Override
	public void renderReact(
			ComponentDescriptor componentDescriptor, Map<String, Object> data,
			HttpServletRequest httpServletRequest, Writer writer, boolean ssr)
		throws IOException {

		String placeholderId = StringUtil.randomId();

		_renderPlaceholder(writer, placeholderId);

		if (ESImportUtil.isESImport(componentDescriptor.getModule())) {
			AbsolutePortalURLBuilder absolutePortalURLBuilder =
				_absolutePortalURLBuilderFactory.getAbsolutePortalURLBuilder(
					httpServletRequest);

			if (ssr) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)httpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				String cdnBaseURL = themeDisplay.getCDNBaseURL();

				ESImport esImport = ESImportUtil.getESImport(
					absolutePortalURLBuilder, componentDescriptor.getModule());

				String namedImport = esImport.getAlias();

				if ((namedImport == null) || (namedImport == "")) {
					namedImport = esImport.getSymbol();
				}

				Map<String, Object> body = HashMapBuilder.<String, Object>put(
					"component", namedImport
				).put(
					"props", _prepareProps(componentDescriptor, data, httpServletRequest)
				).put(
					"url", cdnBaseURL + esImport.getModule()
				).build();

				JSONSerializer jsonSerializer = _jsonFactory.createJSONSerializer();

				try {
					Http.Options options = new Http.Options();
					options.setLocation("http://localhost:3030/render");
					options.setPost(true);
					options.addHeader("Content-Type", "application/json");
					options.setBody(jsonSerializer.serializeDeep(body), "application/json", "UTF-8");

					String html = _http.URLtoString(options);

					Http.Response response = options.getResponse();

					if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
						writer.append(html);
					}
					else {
						System.out.println(
							"Server Side Rendering failed: '" +
								componentDescriptor.getModule() +
									"' fails 'ReactDOMServer.renderToString(...)'.");
					}
				} catch (Exception e) {
				}
			}

			ReactRendererUtil.renderEcmaScript(
				_absolutePortalURLBuilderFactory.getAbsolutePortalURLBuilder(
					httpServletRequest),
				componentDescriptor, httpServletRequest, _jsonFactory,
				placeholderId, _portal,
				_prepareProps(componentDescriptor, data, httpServletRequest),
				writer);
		}
		else {
			ReactRendererUtil.renderJavaScript(
				componentDescriptor,
				_prepareProps(componentDescriptor, data, httpServletRequest),
				httpServletRequest, _jsonFactory,
				NPMResolvedPackageNameUtil.get(_servletContext), placeholderId,
				_portal, writer);
		}
	}

	private Map<String, Object> _prepareProps(
		ComponentDescriptor componentDescriptor, Map<String, Object> props,
		HttpServletRequest httpServletRequest) {

		Map<String, Object> modifiedProps = null;

		if (!props.containsKey("componentId")) {
			if (modifiedProps == null) {
				modifiedProps = new HashMap<>(props);
			}

			modifiedProps.put(
				"componentId", componentDescriptor.getComponentId());
		}

		if (!props.containsKey("locale")) {
			if (modifiedProps == null) {
				modifiedProps = new HashMap<>(props);
			}

			modifiedProps.put(
				"locale",
				LocaleUtil.toJSONString(LocaleUtil.getMostRelevantLocale()));
		}

		String portletId = (String)props.get("portletId");

		if (portletId == null) {
			if (modifiedProps == null) {
				modifiedProps = new HashMap<>(props);
			}

			portletId = _portal.getPortletId(httpServletRequest);

			modifiedProps.put("portletId", portletId);
		}

		if ((portletId != null) && !props.containsKey("portletNamespace")) {
			if (modifiedProps == null) {
				modifiedProps = new HashMap<>(props);
			}

			modifiedProps.put(
				"portletNamespace", _portal.getPortletNamespace(portletId));
		}

		if (modifiedProps == null) {
			return props;
		}

		return modifiedProps;
	}

	private void _renderPlaceholder(Writer writer, String placeholderId)
		throws IOException {

		writer.append("<div id=\"");
		writer.append(placeholderId);
		writer.append("\"></div>");
	}

	@Reference
	private AbsolutePortalURLBuilderFactory _absolutePortalURLBuilderFactory;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.template.react.renderer.impl)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}