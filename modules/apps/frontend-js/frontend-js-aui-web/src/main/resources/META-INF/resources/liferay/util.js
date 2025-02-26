/* eslint-disable @liferay/aui/no-one */

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function (A) {
	A.use('aui-base-lang');

	const Lang = A.Lang;

	const EVENT_CLICK = 'click';

	const SRC_HIDE_LINK = {
		src: 'hideLink',
	};

	const STR_RIGHT_SQUARE_BRACKET = ']';

	Liferay.Util = Liferay.Util ?? {};

	Object.assign(Liferay.Util, {
		_getEditableInstance(title) {
			let editable = Liferay.Util._EDITABLE;

			if (!editable) {
				editable = new A.Editable({
					after: {
						contentTextChange(event) {
							const instance = this;

							if (!event.initial) {
								const title = instance.get('node');

								const portletTitleEditOptions = title.getData(
									'portletTitleEditOptions'
								);

								Liferay.Util.savePortletTitle({
									doAsUserId:
										portletTitleEditOptions.doAsUserId,
									plid: portletTitleEditOptions.plid,
									portletId:
										portletTitleEditOptions.portletId,
									title: event.newVal,
								});
							}
						},
						startEditing() {
							const instance = this;

							const Layout = Liferay.Layout;

							if (Layout) {
								instance._dragListener =
									Layout.getLayoutHandler().on(
										'drag:start',
										() => {
											instance.fire('save');
										}
									);
							}

							const title = instance.get('node');

							instance._titleListener = title.on(
								'mouseupoutside',
								(event) => {
									const editable =
										Liferay.Util._getEditableInstance(
											title
										);

									if (
										!editable
											.get('boundingBox')
											.contains(event.target)
									) {
										editable.save();
									}
								}
							);
						},
						stopEditing() {
							const instance = this;

							if (instance._dragListener) {
								instance._dragListener.detach();
							}

							if (instance._titleListener) {
								instance._titleListener.detach();
							}
						},
					},
					cssClass: 'lfr-portlet-title-editable',
					node: title,
				});

				editable.get('cancelButton').icon = 'times';
				editable.get('saveButton').icon = 'check';

				Liferay.Util._EDITABLE = editable;
			}

			return editable;
		},

		Window: {
			_map: {},

			getById(id) {
				const instance = this;

				return instance._map[id];
			},
		},

		checkAll(form, name, allBox, selectClassName) {
			if (form) {
				form = Liferay.Util.getDOM(form);

				if (typeof form === 'string') {
					form = document.querySelector(form);
				}

				allBox = Liferay.Util.getDOM(allBox);

				if (typeof allBox === 'string') {
					allBox = document.querySelector(allBox);
				}

				let selector;

				if (Array.isArray(name)) {
					selector =
						'input[name=' +
						name.join('], input[name=') +
						STR_RIGHT_SQUARE_BRACKET;
				}
				else {
					selector = 'input[name=' + name + STR_RIGHT_SQUARE_BRACKET;
				}

				const allBoxChecked = allBox.checked;

				const uploadedItems = Array.from(
					form.querySelectorAll(selector)
				);

				uploadedItems.forEach((item) => {
					if (!item.disabled) {
						item.checked = allBoxChecked;
					}
				});

				if (selectClassName) {
					const selectItem = form.querySelector(selectClassName);

					if (allBoxChecked) {
						selectItem.classList.add('info');
					}
					else {
						selectItem.classList.remove('info');
					}
				}
			}
		},

		checkAllBox(form, name, allBox) {
			let totalOn = 0;

			if (form) {
				form = Liferay.Util.getDOM(form);

				if (typeof form === 'string') {
					form = document.querySelector(form);
				}

				allBox = Liferay.Util.getDOM(allBox);

				if (typeof allBox === 'string') {
					allBox =
						document.querySelector(allBox) ||
						form.querySelector(`input[name="${allBox}"]`);
				}

				const inputs = Array.from(
					form.querySelectorAll('input[type=checkbox]')
				);

				if (!Array.isArray(name)) {
					name = [name];
				}

				let totalBoxes = 0;

				inputs.forEach((input) => {
					if (
						input.id !== allBox.id ||
						(input.id !== allBox.name &&
							name.indexOf(input.name) > -1)
					) {
						totalBoxes++;

						if (input.checked) {
							totalOn++;
						}
					}
				});

				allBox.checked = totalBoxes === totalOn;
			}

			return totalOn;
		},

		/**
		 * @deprecated As of Cavanaugh (7.4.x), replaced by `Liferay.Util.openWindow()`
		 */
		openInDialog(event, config) {
			event.preventDefault();

			const currentTarget = Liferay.Util.getElement(event.currentTarget);

			// eslint-disable-next-line prefer-object-spread
			config = Object.assign(
				{},

				// eslint-disable-next-line prefer-object-spread
				Object.assign({}, currentTarget.dataset),
				config
			);

			if (!config.uri) {
				config.uri =
					currentTarget.dataset.href ||
					currentTarget.getAttribute('href');
			}

			if (!config.title) {
				config.title = currentTarget.getAttribute('title');
			}

			Liferay.Util.openWindow(config);
		},

		/**
		 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
		 */
		rowCheckerCheckAllBox(
			ancestorTable,
			ancestorRow,
			checkboxesIds,
			checkboxAllIds,
			cssClass
		) {
			Liferay.Util.checkAllBox(
				ancestorTable,
				checkboxesIds,
				checkboxAllIds
			);

			if (ancestorRow) {
				ancestorRow.toggleClass(cssClass);
			}
		},

		/**
		 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
		 */
		savePortletTitle(params) {
			params = {
				doAsUserId: 0,
				plid: 0,
				portletId: 0,
				title: '',
				url:
					themeDisplay.getPathMain() + '/portal/update_portlet_title',
				...params,
			};

			const data = {
				doAsUserId: params.doAsUserId,
				p_auth: Liferay.authToken,
				p_l_id: params.plid,
				portletId: params.portletId,
				title: params.title,
			};

			Liferay.Util.fetch(params.url, {
				body: Liferay.Util.objectToFormData(data),
				method: 'POST',
			});
		},

		submitCountdown: 0,
	});

	Liferay.provide(
		Liferay.Util,
		'afterIframeLoaded',
		(event) => {

			// eslint-disable-next-line @liferay/aui/no-node
			const nodeInstances = A.Node._instances;

			const docEl = event.doc;

			const docUID = docEl._yuid;

			if (docUID in nodeInstances) {
				delete nodeInstances[docUID];
			}

			const iframeDocument = A.one(docEl);

			const iframeBody = iframeDocument.one('body');

			const dialog = event.dialog;

			const lfrFormContent = iframeBody.one('.lfr-form-content');

			iframeBody.addClass('dialog-iframe-popup');

			if (
				lfrFormContent &&
				iframeBody.one('.button-holder.dialog-footer')
			) {
				iframeBody.addClass('dialog-with-footer');

				const stagingAlert = iframeBody.one(
					'.portlet-body > .lfr-portlet-message-staging-alert'
				);

				if (stagingAlert) {
					stagingAlert.remove();

					lfrFormContent.prepend(stagingAlert);
				}
			}

			iframeBody.addClass(dialog.iframeConfig.bodyCssClass);

			event.win.focus();

			const iframeWindow = event.win;

			if (iframeWindow.Liferay.SPA) {
				const beforeScreenFlipHandler = iframeWindow.Liferay.on(
					'beforeScreenFlip',
					() => {
						iframeWindow.document.body.classList.add(
							'dialog-iframe-popup'
						);
					}
				);

				iframeWindow.onunload = () => {
					if (beforeScreenFlipHandler) {
						iframeWindow.Liferay.detach(beforeScreenFlipHandler);
					}
				};
			}

			const cancelEventHandler = iframeBody.delegate(
				EVENT_CLICK,
				(event) => {
					dialog.set(
						'visible',
						false,
						event.currentTarget.hasClass('lfr-hide-dialog')
							? SRC_HIDE_LINK
							: null
					);

					cancelEventHandler.detach();

					iframeDocument.purge(true);
				},
				'.btn-cancel,.lfr-hide-dialog'
			);

			Liferay.fire('modalIframeLoaded', {
				src: event.dialog.iframe.node.getAttribute('src'),
			});
		},
		['aui-base']
	);

	Liferay.provide(
		Liferay.Util,
		'portletTitleEdit',
		(options) => {
			const object = options.obj;

			A.Event.defineOutside('mouseup');

			if (object) {
				const title = object.one('.portlet-title-text');

				if (title && !title.hasClass('not-editable')) {
					title.addClass('portlet-title-editable');

					title.on(EVENT_CLICK, (event) => {
						const editable =
							Liferay.Util._getEditableInstance(title);

						const rendered = editable.get('rendered');

						if (rendered) {
							editable.fire('stopEditing');
						}

						editable.set('node', event.currentTarget);

						if (rendered) {
							editable.syncUI();
						}

						editable._startEditing(event);

						if (!rendered) {
							const defaultIconsTpl =
								A.ToolbarRenderer.prototype.TEMPLATES.icon;

							A.ToolbarRenderer.prototype.TEMPLATES.icon =
								Liferay.Util.getLexiconIconTpl('{cssClass}');

							editable._comboBox.icons.destroy();
							editable._comboBox._renderIcons();

							A.ToolbarRenderer.prototype.TEMPLATES.icon =
								defaultIconsTpl;
						}
					});

					title.setData('portletTitleEditOptions', options);
				}
			}
		},
		['aui-editable-deprecated', 'event-outside']
	);

	/**
	 * Used in `modules/apps/frontend-js/frontend-js-web/src/main/resources/META-INF/resources/liferay/util/open_window.js`
	 * which will need to be migrated over to `openModal`.
	 */
	Liferay.provide(
		Liferay.Util,
		'_openWindowProvider',
		(config, callback) => {
			const dialog = Liferay.Util.Window.getWindow(config);

			if (Lang.isFunction(callback)) {
				callback(dialog);
			}
		},
		['liferay-util-window']
	);

	// 0-200: Theme Developer
	// 200-400: Portlet Developer
	// 400+: Liferay

})(AUI());
