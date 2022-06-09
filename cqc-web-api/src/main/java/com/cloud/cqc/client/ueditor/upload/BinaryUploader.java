package com.cloud.cqc.client.ueditor.upload;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloud.cqc.client.ueditor.constant.AppInfo;
import com.cloud.cqc.client.ueditor.constant.FileType;
import com.cloud.cqc.client.ueditor.entity.ResultState;
import com.cloud.cqc.client.ueditor.util.PathFormat;

public class BinaryUploader {

	public static final ResultState save(HttpServletRequest request, Map<String, Object> conf) {

		if (!(request instanceof MultipartHttpServletRequest)) {
			return new ResultState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		MultipartHttpServletRequest multipar = (MultipartHttpServletRequest) request;

		String filedName = (String) conf.get("fieldName");

		MultipartFile multipartFile = multipar.getFile(filedName);

		if (multipartFile == null) {
			return new ResultState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
		}

		try {

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new ResultState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			// modified by Ternence
			String rootPath = getRootPath(request, conf);
			String physicalPath = rootPath + savePath;

			ResultState storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),
					physicalPath, maxSize);

			if (storageState.isSuccess()) {
				storageState.setUrl(conf.get("domain") + PathFormat.format(savePath));
				storageState.setType(suffix);
				storageState.setOriginal(originFileName + suffix);
			}

			return storageState;
		} catch (IOException e) {
		}
		return new ResultState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
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
