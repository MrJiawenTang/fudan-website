package com.cloud.cqc.client.ueditor.upload;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cloud.cqc.client.ueditor.constant.AppInfo;
import com.cloud.cqc.client.ueditor.constant.MIMEType;
import com.cloud.cqc.client.ueditor.entity.MultiResultState;
import com.cloud.cqc.client.ueditor.entity.ResultState;
import com.cloud.cqc.client.ueditor.util.PathFormat;

/**
 * 图片抓取
 *
 */
public class ImageHunter {
	private String filename = null;
	private String savePath = null;
	private String rootPath = null;
	private String domain = null;
	private List<String> allowTypes = null;
	private long maxSize = -1;

	private List<String> filters = null;

	public ImageHunter(Map<String, Object> conf) {

		this.filename = (String) conf.get("filename");
		this.savePath = (String) conf.get("savePath");
		this.rootPath = (String) conf.get("rootPath");
		this.maxSize = (Long) conf.get("maxSize");
		this.domain = (String) conf.get("domain");
		this.allowTypes = Arrays.asList((String[]) conf.get("allowFiles"));
		this.filters = Arrays.asList((String[]) conf.get("filter"));

	}

	public ResultState capture(String[] list) {

		MultiResultState state = new MultiResultState(true, AppInfo.SUCCESS);

		if (list != null && list.length > 0) {
			for (String source : list) {
				state.addState(captureRemoteData(source));
			}
		}

		return state;

	}

	public ResultState captureRemoteData(String urlStr) {

		HttpURLConnection connection = null;
		URL url = null;
		String suffix = null;

		try {
			url = new URL(urlStr);

			if (!validHost(url.getHost())) {
				return new ResultState(false, AppInfo.PREVENT_HOST);
			}

			connection = (HttpURLConnection) url.openConnection();

			connection.setInstanceFollowRedirects(true);
			connection.setUseCaches(true);

			if (!validContentState(connection.getResponseCode())) {
				return new ResultState(false, AppInfo.CONNECTION_ERROR);
			}

			suffix = MIMEType.getSuffix(connection.getContentType());

			if (!validFileType(suffix)) {
				return new ResultState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			if (!validFileSize(connection.getContentLength())) {
				return new ResultState(false, AppInfo.MAX_SIZE);
			}

			String savePath = this.getPath(this.savePath, this.filename, suffix);
			String physicalPath = this.rootPath + savePath;

			ResultState state = StorageManager.saveFileByInputStream(connection.getInputStream(), physicalPath);

			if (state.isSuccess()) {
				state.setUrl(this.domain + PathFormat.format(savePath));
				state.setSource(urlStr);
			}

			return state;

		} catch (Exception e) {
			return new ResultState(false, AppInfo.REMOTE_FAIL);
		}

	}

	private String getPath(String savePath, String filename, String suffix) {

		return PathFormat.parse(savePath + suffix, filename);

	}

	private boolean validHost(String hostname) {

		return !filters.contains(hostname);

	}

	private boolean validContentState(int code) {

		return HttpURLConnection.HTTP_OK == code;

	}

	private boolean validFileType(String type) {

		return this.allowTypes.contains(type);

	}

	private boolean validFileSize(int size) {
		return size < this.maxSize;
	}
}
