package com.cloud.cqc.client.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cloud.cqc.client.ueditor.entity.ResultState;

/**
 *
 * @version 1.0
 */
public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final ResultState doExec() {

		ResultState state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request, this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf);
		}

		return state;
	}
}
