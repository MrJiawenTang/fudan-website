package com.cloud.cqc.client.ueditor.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.ueditor.configure.UEditorProperties;
import com.cloud.cqc.client.ueditor.constant.ActionMap;
import com.cloud.cqc.client.ueditor.constant.AppInfo;
import com.cloud.cqc.client.ueditor.entity.ResultState;
import com.cloud.cqc.client.ueditor.upload.ImageHunter;
import com.cloud.cqc.client.ueditor.upload.Uploader;

/**
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@EnableConfigurationProperties(UEditorProperties.class)
public class UEditorUploadController {

	@Autowired
	private UEditorProperties ueditorProperties;
	/** 文件上传服务域名 */
	@Value("${app.upload.domain}")
	private String domain;
	/** 文件上传服务根路径 */
	@Value("${app.upload.root}")
	private String rootPath;

	/**
	 * ueditor配置
	 * 
	 * @param action
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload/ueditor")
	public Object config(String action, HttpServletRequest request) {

		if (action == null || !ActionMap.mapping.containsKey(action)) {
			return new ResultState(false, AppInfo.INVALID_ACTION);
		}

		ResultState state = null;

		int actionCode = ActionMap.getType(action);

		Map<String, Object> conf = null;

		switch (actionCode) {

		case ActionMap.CONFIG:
			return ueditorProperties;
		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = this.getConfig(actionCode);
			state = new Uploader(request, conf).doExec();
			break;
		case ActionMap.CATCH_IMAGE:
			conf = this.getConfig(actionCode);
			String[] list = request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;
		}

		return state;

	}

	public Map<String, Object> getConfig(int type) {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;

		switch (type) {

		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", ueditorProperties.getFileMaxSize());
			conf.put("allowFiles", ueditorProperties.getFileAllowFiles());
			conf.put("fieldName", ueditorProperties.getFileFieldName());
			savePath = ueditorProperties.getFilePathFormat();
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", ueditorProperties.getImageMaxSize());
			conf.put("allowFiles", ueditorProperties.getImageAllowFiles());
			conf.put("fieldName", ueditorProperties.getImageFieldName());
			savePath = ueditorProperties.getImagePathFormat();
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", ueditorProperties.getVideoMaxSize());
			conf.put("allowFiles", ueditorProperties.getVideoAllowFiles());
			conf.put("fieldName", ueditorProperties.getVideoFieldName());
			savePath = ueditorProperties.getVideoPathFormat();
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("isBase64", "true");
			conf.put("filename", "scrawl");
			conf.put("maxSize", ueditorProperties.getScrawlMaxSize());
			conf.put("fieldName", ueditorProperties.getScrawlFieldName());
			savePath = ueditorProperties.getScrawlPathFormat();
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", "remote");
			conf.put("filter", ueditorProperties.getCatcherLocalDomain());
			conf.put("maxSize", ueditorProperties.getCatcherMaxSize());
			conf.put("allowFiles", ueditorProperties.getCatcherAllowFiles());
			conf.put("fieldName", ueditorProperties.getCatcherFieldName() + "[]");
			savePath = ueditorProperties.getCatcherPathFormat();
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", ueditorProperties.getImageManagerAllowFiles());
			conf.put("dir", ueditorProperties.getImageManagerListPath());
			conf.put("count", ueditorProperties.getImageManagerListSize());
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", ueditorProperties.getFileManagerAllowFiles());
			conf.put("dir", ueditorProperties.getFileManagerListPath());
			conf.put("count", ueditorProperties.getFileManagerListSize());
			break;

		}

		conf.put("savePath", savePath);
		conf.put("domain", this.domain);
		conf.put("rootPath", this.rootPath);
		return conf;

	}

}
