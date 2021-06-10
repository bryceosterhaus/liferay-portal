<%--
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
--%>

<%@ include file="/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

RemoteJsScriptEntry remoteJsScriptEntry = (RemoteJsScriptEntry)request.getAttribute(RemoteJsScriptAdminWebKeys.REMOTE_JS_SCRIPT_ENTRY);

long remoteJsScriptEntryId = BeanParamUtil.getLong(remoteJsScriptEntry, request, "remoteJsScriptEntryId");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((remoteJsScriptEntry == null) ? LanguageUtil.get(request, "new-remote-js-script") : remoteJsScriptEntry.getName(locale));
%>

<portlet:actionURL name="/remote_js_script_admin/edit_remote_js_script_entry" var="editRemoteJsScriptEntryURL" />

<clay:container-fluid>
	<aui:form action="<%= editRemoteJsScriptEntryURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "saveRemoteJsScriptEntry();" %>'>
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="remoteJsScriptEntryId" type="hidden" value="<%= remoteJsScriptEntryId %>" />

		<liferay-ui:error exception="<%= DuplicateRemoteJsScriptEntryURLException.class %>" message="please-enter-a-unique-remote-js-script-url" />

		<aui:model-context bean="<%= remoteJsScriptEntry %>" model="<%= RemoteJsScriptEntry.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:field-wrapper label="name">
					<liferay-ui:input-localized
						autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
						name="name"
						xml='<%= BeanPropertiesUtil.getString(remoteJsScriptEntry, "name") %>'
					/>
				</aui:field-wrapper>

				<aui:input name="url">
					<aui:validator name="url" />
				</aui:input>

				<aui:input name="customElementName">
					<aui:validator name="customElementName" />
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</clay:container-fluid>

<aui:script>
	function <portlet:namespace />saveRemoteJsScriptEntry() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value =
			'<%= (remoteJsScriptEntry == null) ? Constants.ADD : Constants.UPDATE %>';

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>