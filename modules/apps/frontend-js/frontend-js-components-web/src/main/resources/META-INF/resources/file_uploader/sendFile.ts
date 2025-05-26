/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const noop = () => {};

function parse(req: XMLHttpRequest) {
	let result;

	try {
		result = JSON.parse(req.responseText);
	}
	catch (error) {
		result = req.responseText;
	}

	return result;
}

type SendFileOptions = {
	file: File;
	fileFieldName: string;
	onError?: (error: any) => void;
	onProgress?: (progress: number | null) => void;
	onSuccess?: (response: any) => void;
	url: string;
};

export default function sendFile({
	file,
	onError = noop,
	onProgress = noop,
	onSuccess = noop,
	url,
}: SendFileOptions): XMLHttpRequest {
	const formData = new FormData();
	const request = new XMLHttpRequest();

	request.upload.addEventListener('progress', (event: ProgressEvent) => {
		if (event.lengthComputable) {
			onProgress(Math.round((event.loaded * 100) / event.total));
		}
	});

	request.addEventListener('readystatechange', () => {
		if (request.readyState === XMLHttpRequest.DONE) {
			const response = parse(request);

			onProgress(null);

			if (request.status >= 200 && request.status < 300) {
				onSuccess(response);
			}
			else {
				onError(response);
			}
		}
	});

	formData.append('file', file);
	request.open('POST', url);
	request.send(formData);

	return request;
}
