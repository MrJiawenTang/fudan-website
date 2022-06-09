package com.cloud.cqc.client.file.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloud.cqc.framework.core.utils.MD5Encoder;
import com.cloud.cqc.framework.mvc.BaseRestController;

/**
 * 上传管理
 * 
 * @author joy.zhou
 * @date 2017年12月14日
 * @version 1.0
 */
@RestController
public class UploadController extends BaseRestController {

	/** 文件上传服务域名 */
	@Value("${app.upload.domain}")
	private String domain;
	/** 文件上传服务根路径 */
	@Value("${app.upload.root}")
	private String rootPath;

	/**
	 * 
	 * 文件上传
	 * 
	 * @param files
	 *            上传的文件
	 * @param path
	 *            上传目录
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload/file", method = RequestMethod.POST)
	public Object common(String path, String action, MultipartHttpServletRequest request) throws IOException {

		Map<String, MultipartFile> fileMaps = request.getFileMap();

		List<FileEntity> result = new ArrayList<>();

		if (fileMaps != null && !fileMaps.isEmpty()) {

			MultipartFile[] files = fileMaps.values().toArray(new MultipartFile[0]);

			for (MultipartFile file : files) {

				result.add(copyFile(path, file));

			}

		}
		return resultOk(result);
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private FileEntity copyFile(String path, MultipartFile file) throws IOException {

		String fileName = MD5Encoder.encode(file.getBytes())
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		String filePath = getFilePath(path, fileName);

		File outFile = new File(rootPath + filePath);

		if (!outFile.exists()) {
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(outFile));
		}

		String url = domain + filePath;

		return new FileEntity(fileName, url);
	}

	private String getFilePath(String path, String fileName) {
		StringBuilder sb = new StringBuilder("/");
		if (StringUtils.isNotBlank(path)) {
			sb.append(path).append("/");
		}
		sb.append(fileName);
		return sb.toString();
	}

	static class FileEntity {
		private String name;
		private String url;

		public FileEntity() {
		}

		public FileEntity(String name, String url) {
			this.name = name;
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

}
