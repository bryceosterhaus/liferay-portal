/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert from '@clayui/alert';
import {ClayButtonWithIcon} from '@clayui/button';
import ClayCard from '@clayui/card';
import {ClayCheckbox} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import ClayProgressBar from '@clayui/progress-bar';
import ClaySticker from '@clayui/sticker';
import classNames from 'classnames';
import {sub} from 'frontend-js-web';
import React, {useEffect} from 'react';
import {DndProvider, useDrop} from 'react-dnd';
import {HTML5Backend, NativeTypes} from 'react-dnd-html5-backend';
import {v4 as uuidv4} from 'uuid';

import sendFile from './sendFile';

function updateMetaDataContainer({
	containerId,
	explanationContainerId,
	files,
	selectedFiles,
}: {
	containerId: string;
	explanationContainerId: string;
	files: Array<File & {temp?: boolean; title?: string}>;
	selectedFiles: Array<string>;
}) {
	const metadataContainer = document.querySelector(containerId);
	const metadataExplanationContainer = document.querySelector(
		explanationContainerId
	);

	if (metadataContainer && metadataExplanationContainer) {
		const totalFilesCount = files.length;

		const selectedFilesCount = selectedFiles.length;

		const hasSelectedFiles = selectedFilesCount > 0;

		if (metadataContainer) {
			metadataContainer.classList.toggle('hide', !hasSelectedFiles);

			let selectedFilesText = Liferay.Language.get('no-files-selected');

			if (hasSelectedFiles) {
				if (selectedFilesCount === 1) {
					const selectedFile = files.find(
						(file) => file.name === selectedFiles[0]
					);

					selectedFilesText = (selectedFile?.title ||
						selectedFile?.name) as string;
				}
				else if (selectedFilesCount === totalFilesCount) {
					selectedFilesText =
						Liferay.Language.get('all-files-selected');
				}
				else if (selectedFilesCount > 1) {
					selectedFilesText = sub(
						Liferay.Language.get('x-files-selected'),
						[selectedFilesCount]
					);
				}
			}

			const selectedFilesCountContainer = metadataContainer.querySelector(
				'.selected-files-count'
			);

			if (selectedFilesCountContainer) {
				selectedFilesCountContainer.innerHTML = selectedFilesText;

				selectedFilesCountContainer.setAttribute(
					'title',
					selectedFilesText
				);
			}
		}

		if (metadataExplanationContainer) {
			metadataExplanationContainer.classList.toggle(
				'hide',
				hasSelectedFiles && totalFilesCount > 0
			);
		}
	}
}

interface FileUploaderProps {
	containerId: string;
	deleteFileURL?: string;
	dropText?: string;
	explanationContainerId: string;
	fetchTempFiles?: () => Promise<string[]>;
	fileTypes: string[];
	maxFileSize?: number;
	multiple?: boolean;
	portletNamespace?: string;
	tempRandomSuffix: string;
	uploadURL?: string;
}

function FileUploader({
	containerId,
	deleteFileURL,
	dropText = Liferay.Language.get('drop-files-here'),
	explanationContainerId,
	fetchTempFiles,
	fileTypes = [NativeTypes.FILE],
	maxFileSize,
	multiple = false,
	portletNamespace,
	tempRandomSuffix,
	uploadURL,
}: FileUploaderProps) {
	const [hasTempFiles, setHasTempFiles] = React.useState(false);
	const [files, setFiles] = React.useState<any[]>([]);
	const [selectedFiles, setSelectedFiles] = React.useState<any[]>([]);
	const inputRef = React.useRef<HTMLInputElement>(null);
	const [uploadingFiles, setUploadingFiles] = React.useState<string[]>([]);

	updateMetaDataContainer({
		containerId,
		explanationContainerId,
		files,
		selectedFiles,
	});

	useEffect(() => {
		!hasTempFiles &&
			fetchTempFiles &&
			fetchTempFiles().then((tempFiles) => {
				if (tempFiles.length) {
					setHasTempFiles(true);

					setFiles(
						tempFiles.map((file) => {
							let tempTitle = file;

							const lastIndexOfPeriod =
								tempTitle.lastIndexOf('.');
							const posTempRandomSuffix =
								tempTitle.indexOf(tempRandomSuffix);

							if (posTempRandomSuffix !== -1) {
								tempTitle = file.substr(0, posTempRandomSuffix);

								if (lastIndexOfPeriod > 0) {
									tempTitle += file.substr(lastIndexOfPeriod);
								}
							}

							return {
								id: uuidv4(),
								name: file,
								temp: true,
								title: tempTitle,
							};
						})
					);
				}
			});
	}, [hasTempFiles, fetchTempFiles, tempRandomSuffix]);

	const [{isOver}, drop] = useDrop({
		accept: fileTypes,
		canDrop() {
			return true;
		},
		collect: (monitor) => {
			return {
				canDrop: monitor.canDrop(),
				isOver: monitor.isOver(),
			};
		},
		drop: (item: any) => {
			setFiles(item.files as any[]);
		},
	});

	useEffect(() => {
		document.documentElement.classList.toggle(
			'upload-drop-active',
			!!isOver
		);
	}, [isOver]);

	const allChecked = !!files.length && selectedFiles.length === files.length;

	return (
		<div className="lfr-upload-container">
			<div className="upload-target" ref={drop}>
				<span className="drop-file-text">
					{dropText}

					<span className="small">{Liferay.Language.get('or')}</span>
				</span>

				<span className="select-files-container">
					<button
						className="btn btn-secondary"
						onClick={() => inputRef.current?.click()}
						type="button"
					>
						{multiple
							? Liferay.Language.get('select-files')
							: Liferay.Language.get('select-file')}
					</button>
				</span>

				<input
					className="d-none"
					multiple={multiple}
					onChange={(event) => {
						const selectedFiles = event.target.files;

						if (selectedFiles && !!selectedFiles.length) {
							const newFiles = Array.from(selectedFiles);

							setFiles((prevFiles) => [
								...prevFiles,
								...newFiles,
							]);
						}
					}}
					ref={inputRef}
					type="file"
				/>
			</div>

			{!!files.length && (
				<>
					{!!uploadingFiles.length && (
						<div className="upload-list-info">
							<div className="h4">
								{sub(
									Liferay.Language.get('uploading-x-files'),
									[uploadingFiles.length]
								)}
							</div>
						</div>
					)}

					{hasTempFiles && (
						<ClayAlert
							displayType="warning"
							title={Liferay.Language.get('warning')}
						>
							{Liferay.Language.get(
								'these-files-have-been-previously-uploaded-but-not-actually-saved.-please-save-or-delete-them-before-they-are-removed'
							)}
						</ClayAlert>
					)}

					<ClayLayout.ContentRow>
						<ClayLayout.ContentCol expand>
							<ClayCheckbox
								checked={allChecked}
								className="form-check select-files"
								label={Liferay.Language.get('select-all')}
								onClick={() => {
									if (allChecked) {
										setSelectedFiles([]);
									}
									else {
										setSelectedFiles(
											files.map((file) => file.name)
										);
									}
								}}
							/>
						</ClayLayout.ContentCol>
					</ClayLayout.ContentRow>

					<div className="upload-list">
						{files.map((file) => {
							return (
								<FileItem
									deleteFileURL={deleteFileURL}
									file={file}
									key={file.name}
									maxFileSize={maxFileSize}
									onRemoveItem={() => {
										setFiles(
											files.filter(
												(selectedFile) =>
													selectedFile !== file
											)
										);
									}}
									onSelectChange={(fileName: string) => {
										if (selectedFiles.includes(fileName)) {
											setSelectedFiles(
												selectedFiles.filter(
													(selectedFile) =>
														selectedFile !==
														fileName
												)
											);
										}
										else {
											setSelectedFiles([
												...selectedFiles,
												fileName,
											]);
										}
									}}
									onUploadProgress={(
										fileName: string,
										uploadStatus: string
									) => {
										if (uploadStatus === 'uploadComplete') {
											setSelectedFiles([
												...selectedFiles,
												fileName,
											]);

											setUploadingFiles((prevFiles) => {
												return prevFiles.filter(
													(file) => file !== fileName
												);
											});
										}
										else {
											setUploadingFiles([
												...uploadingFiles,
												fileName,
											]);
										}
									}}
									portletNamespace={portletNamespace}
									selected={selectedFiles.includes(file.name)}
									uploadURL={uploadURL}
								/>
							);
						})}
					</div>
				</>
			)}
		</div>
	);
}

function FileItem({
	deleteFileURL,
	disabled,
	file,
	href,
	maxFileSize,
	onRemoveItem,
	onSelectChange,
	onUploadProgress,
	portletNamespace,
	selected = false,
	uploadURL,
}: any) {
	const [error, setError] = React.useState('');
	const [progress, setProgress] = React.useState(file.temp ? 100 : null);
	const [success, setSuccess] = React.useState(file.temp ? file : null);

	React.useEffect(
		() => () => {
			Liferay.Util.fetch(deleteFileURL, {
				body: Liferay.Util.objectToFormData({
					[`${portletNamespace}fileName`]: file.name,
				}),
				method: 'POST',
			}).then(() => {
				Liferay.Util.openToast({
					message: Liferay.Language.get(
						'your-request-completed-successfully'
					),
					type: 'success',
				});
			});
		},
		[deleteFileURL, file.name, portletNamespace]
	);

	React.useEffect(() => {
		if (!file.temp) {
			if (file.size > maxFileSize) {
				setError(
					sub(
						Liferay.Language.get(
							'please-enter-a-file-with-a-valid-file-size-no-larger-than-x'
						),
						[maxFileSize]
					)
				);
			}
			else {
				onUploadProgress(file.name, 'uploadStart');

				sendFile({
					file,
					fileFieldName: file.name,
					onError: setError,
					onProgress: setProgress,
					onSuccess: (result) => {
						onUploadProgress(file.name, 'uploadComplete');
						setSuccess(result);
					},
					url: uploadURL,
				});
			}
		}
	}, [file, uploadURL, maxFileSize, onUploadProgress]);

	return (
		<div
			className={classNames(
				'card-type-directory form-check form-check-card form-check-middle-left file-uploading'
			)}
		>
			<ClayCheckbox
				checked={selected}
				data-fileName={file.name}
				data-title={file.title}
				disabled={!!error}
				name={`${portletNamespace}selectUploadedFile`}
				onChange={() => onSelectChange(file.name)}
				value={file.name}
			>
				<div className="card card-horizontal">
					<ClayCard.Body>
						<ClayCard.Row>
							<ClayLayout.ContentCol>
								<ClaySticker inline>
									<ClayIcon
										spritemap={Liferay.Icons.spritemap}
										symbol="document"
									/>
								</ClaySticker>
							</ClayLayout.ContentCol>

							<ClayLayout.ContentCol expand gutters>
								<ClayCard.Description
									disabled={disabled}
									displayType="title"
									href={href}
									truncate={true}
								>
									{file.title || file.name}
								</ClayCard.Description>
							</ClayLayout.ContentCol>

							<ClayLayout.ContentCol>
								<ClayButtonWithIcon
									className="component-action"
									disabled={disabled}
									displayType="unstyled"
									onClick={onRemoveItem}
									spritemap={Liferay.Icons.spritemap}
									symbol="times"
								/>
							</ClayLayout.ContentCol>
						</ClayCard.Row>

						{error && (
							<ClayCard.Row>
								<ClayAlert
									displayType="danger"
									spritemap={Liferay.Icons.spritemap}
									title={Liferay.Language.get('error')}
								>
									{Liferay.Language.get(
										'an-unexpected-error-occurred-while-uploading-your-file'
									)}
								</ClayAlert>
							</ClayCard.Row>
						)}

						{!success && (
							<ClayCard.Row>
								<div className="autofit-col autofit-col-expand">
									<ClayProgressBar
										spritemap={Liferay.Icons.spritemap}
										value={success ? 100 : progress ?? 0}
									/>
								</div>
							</ClayCard.Row>
						)}
					</ClayCard.Body>
				</div>
			</ClayCheckbox>
		</div>
	);
}

export default function FileUploaderWrapper(props: FileUploaderProps) {
	return (
		<>
			{

				// @ts-ignore

				<DndProvider backend={HTML5Backend}>
					<FileUploader {...props} />
				</DndProvider>
			}
		</>
	);
}
