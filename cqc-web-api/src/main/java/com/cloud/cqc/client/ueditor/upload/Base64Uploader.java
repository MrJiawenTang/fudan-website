package com.cloud.cqc.client.ueditor.upload;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.codec.Base64;

import com.cloud.cqc.client.ueditor.constant.AppInfo;
import com.cloud.cqc.client.ueditor.constant.FileType;
import com.cloud.cqc.client.ueditor.entity.ResultState;
import com.cloud.cqc.client.ueditor.util.PathFormat;

public class Base64Uploader {

	/**
	 * 
	 * @param request
	 * @param conf
	 * @return
	 */
	public static ResultState save(HttpServletRequest request, Map<String, Object> conf) {
		String filedName = (String) conf.get("fieldName");
		String fileName = request.getParameter(filedName);
		byte[] data = decode(fileName);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new ResultState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));

		savePath = savePath + suffix;
		String rootPath = getRootPath(request, conf);
		String physicalPath = rootPath + savePath;

		ResultState storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.setUrl(conf.get("domain") + PathFormat.format(savePath));
			storageState.setType(suffix);
			storageState.setOriginal("");
		}

		return storageState;
	}

	private static byte[] decode(String content) {

		return Base64.decode(content.getBytes());
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

	public static String getRootPath(HttpServletRequest request, Map<String, Object> conf) {
		Object rootPath = request.getAttribute("rootPath");
		if (rootPath != null) {
			return rootPath + "" + File.separatorChar;
		} else {
			return conf.get("rootPath") + "";
		}
	}
}
