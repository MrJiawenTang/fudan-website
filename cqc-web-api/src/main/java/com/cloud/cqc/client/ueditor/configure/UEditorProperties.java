package com.cloud.cqc.client.ueditor.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * UEditor 配置
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@ConfigurationProperties(prefix = "ueditor")
public class UEditorProperties {

	/****** 上传图片配置项 ********/
	/** 执行上传图片的action名称 */
	private String imageActionName = "uploadimage";
	/** 提交的图片表单名称 */
	private String imageFieldName = "upfile";
	/** 上传大小限制，单位B */
	private Long imageMaxSize = 2048000L;
	/** 上传图片格式显示 */
	private String[] imageAllowFiles = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };
	/** 是否压缩图片,默认是true */
	private Boolean imageCompressEnable = true;
	/** 图片压缩最长边限制 */
	private Integer imageCompressBorder = 1600;
	/** 插入的图片浮动方式 */
	private String imageInsertAlign = "none";
	/** 图片访问路径前缀 */
	private String imageUrlPrefix = "";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String imagePathFormat = "/image/{yyyy}{mm}{dd}/{time}{rand=6}";

	/********** 涂鸦图片上传配置项 ********/
	/** 执行上传涂鸦的action名称 */
	private String scrawlActionName = "uploadscrawl";
	/* 提交的图片表单名称 */
	private String scrawlFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String scrawlPathFormat = "/image/{yyyy}{mm}{dd}/{time}{rand=6}";
	/** 上传大小限制，单位B */
	private Long scrawlMaxSize = 2048000l;
	/** 图片访问路径前缀 */
	private String scrawlUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String scrawlInsertAlign = "none";

	/*********************** 截图工具上传 ********************/
	/** 执行上传截图的action名称 */
	private String snapscreenActionName = "uploadimage";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String snapscreenPathFormat = "/image/{yyyy}{mm}{dd}/{time}{rand=6}";
	/** 图片访问路径前缀 */
	private String snapscreenUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String snapscreenInsertAlign = "none";

	/********************* 抓取远程图片配置 ******************/
	private String[] catcherLocalDomain = { "img.baidu.com" };
	/** 执行抓取远程图片的action名称 */
	private String catcherActionName = "catchimage";
	/** 提交的图片列表表单名称 */
	private String catcherFieldName = "source";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String catcherPathFormat = "/image/{yyyy}{mm}{dd}/{time}{rand=6}";
	/** 图片访问路径前缀 */
	private String catcherUrlPrefix = "";
	/** 上传大小限制，单位B */
	private Long catcherMaxSize = 2048000l;
	/** 抓取图片格式显示 */
	private String[] catcherAllowFiles = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };

	/**************** 上传视频配置 ***********************/
	/** 执行上传视频的action名称 */
	private String videoActionName = "uploadvideo";
	/** 提交的视频表单名称 */
	private String videoFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String videoPathFormat = "/video/{yyyy}{mm}{dd}/{time}{rand=6}";
	/** 视频访问路径前缀 */
	private String videoUrlPrefix = "";
	/** 上传大小限制，单位B，默认100MB */
	private Long videoMaxSize = 102400000l;
	/** 上传视频格式显示 */
	private String[] videoAllowFiles = { ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg",
			".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid" };

	/******************** 上传文件配置 *********************/
	/** controller里,执行上传视频的action名称 */
	private String fileActionName = "uploadfile";
	/** 提交的文件表单名称 */
	private String fileFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String filePathFormat = "/file/{yyyy}{mm}{dd}/{time}{rand=6}";
	/** 文件访问路径前缀 */
	private String fileUrlPrefix = "";
	/** 上传大小限制，单位B，默认50MB */
	private Long fileMaxSize = 51200000l;
	/** 上传文件格式显示 */
	private String[] fileAllowFiles = { ".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm",
			".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar",
			".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
			".pdf", ".txt", ".md", ".xml" };

	/***************** 列出指定目录下的图片 ************/
	/** 执行图片管理的action名称 */
	private String imageManagerActionName = "listimage";
	/** 指定要列出图片的目录 */
	private String imageManagerListPath = "/image/";
	/** 每次列出文件数量 */
	private Integer imageManagerListSize = 20;
	/** 图片访问路径前缀 */
	private String imageManagerUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String imageManagerInsertAlign = "none";
	/** 列出的文件类型 */
	private String[] imageManagerAllowFiles = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };

	/************ 列出指定目录下的文件 ***************/
	/** 执行文件管理的action名称 */
	private String fileManagerActionName = "listfile";
	/** 指定要列出文件的目录 */
	private String fileManagerListPath = "/file/";
	/** 文件访问路径前缀 */
	private String fileManagerUrlPrefix = "";
	/** 每次列出文件数量 */
	private Integer fileManagerListSize = 20;
	/** 列出的文件类型 */
	private String[] fileManagerAllowFiles = { ".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi",
			".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
			".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt",
			".pptx", ".pdf", ".txt", ".md", ".xml" };

	public String getImageActionName() {
		return imageActionName;
	}

	public void setImageActionName(String imageActionName) {
		this.imageActionName = imageActionName;
	}

	public String getImageFieldName() {
		return imageFieldName;
	}

	public void setImageFieldName(String imageFieldName) {
		this.imageFieldName = imageFieldName;
	}

	public Long getImageMaxSize() {
		return imageMaxSize;
	}

	public void setImageMaxSize(Long imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}

	public String[] getImageAllowFiles() {
		return imageAllowFiles;
	}

	public void setImageAllowFiles(String[] imageAllowFiles) {
		this.imageAllowFiles = imageAllowFiles;
	}

	public Boolean getImageCompressEnable() {
		return imageCompressEnable;
	}

	public void setImageCompressEnable(Boolean imageCompressEnable) {
		this.imageCompressEnable = imageCompressEnable;
	}

	public Integer getImageCompressBorder() {
		return imageCompressBorder;
	}

	public void setImageCompressBorder(Integer imageCompressBorder) {
		this.imageCompressBorder = imageCompressBorder;
	}

	public String getImageInsertAlign() {
		return imageInsertAlign;
	}

	public void setImageInsertAlign(String imageInsertAlign) {
		this.imageInsertAlign = imageInsertAlign;
	}

	public String getImageUrlPrefix() {
		return imageUrlPrefix;
	}

	public void setImageUrlPrefix(String imageUrlPrefix) {
		this.imageUrlPrefix = imageUrlPrefix;
	}

	public String getImagePathFormat() {
		return imagePathFormat;
	}

	public void setImagePathFormat(String imagePathFormat) {
		this.imagePathFormat = imagePathFormat;
	}

	public String getScrawlActionName() {
		return scrawlActionName;
	}

	public void setScrawlActionName(String scrawlActionName) {
		this.scrawlActionName = scrawlActionName;
	}

	public String getScrawlFieldName() {
		return scrawlFieldName;
	}

	public void setScrawlFieldName(String scrawlFieldName) {
		this.scrawlFieldName = scrawlFieldName;
	}

	public String getScrawlPathFormat() {
		return scrawlPathFormat;
	}

	public void setScrawlPathFormat(String scrawlPathFormat) {
		this.scrawlPathFormat = scrawlPathFormat;
	}

	public Long getScrawlMaxSize() {
		return scrawlMaxSize;
	}

	public void setScrawlMaxSize(Long scrawlMaxSize) {
		this.scrawlMaxSize = scrawlMaxSize;
	}

	public String getScrawlUrlPrefix() {
		return scrawlUrlPrefix;
	}

	public void setScrawlUrlPrefix(String scrawlUrlPrefix) {
		this.scrawlUrlPrefix = scrawlUrlPrefix;
	}

	public String getScrawlInsertAlign() {
		return scrawlInsertAlign;
	}

	public void setScrawlInsertAlign(String scrawlInsertAlign) {
		this.scrawlInsertAlign = scrawlInsertAlign;
	}

	public String getSnapscreenActionName() {
		return snapscreenActionName;
	}

	public void setSnapscreenActionName(String snapscreenActionName) {
		this.snapscreenActionName = snapscreenActionName;
	}

	public String getSnapscreenPathFormat() {
		return snapscreenPathFormat;
	}

	public void setSnapscreenPathFormat(String snapscreenPathFormat) {
		this.snapscreenPathFormat = snapscreenPathFormat;
	}

	public String getSnapscreenUrlPrefix() {
		return snapscreenUrlPrefix;
	}

	public void setSnapscreenUrlPrefix(String snapscreenUrlPrefix) {
		this.snapscreenUrlPrefix = snapscreenUrlPrefix;
	}

	public String getSnapscreenInsertAlign() {
		return snapscreenInsertAlign;
	}

	public void setSnapscreenInsertAlign(String snapscreenInsertAlign) {
		this.snapscreenInsertAlign = snapscreenInsertAlign;
	}

	public String[] getCatcherLocalDomain() {
		return catcherLocalDomain;
	}

	public void setCatcherLocalDomain(String[] catcherLocalDomain) {
		this.catcherLocalDomain = catcherLocalDomain;
	}

	public String getCatcherActionName() {
		return catcherActionName;
	}

	public void setCatcherActionName(String catcherActionName) {
		this.catcherActionName = catcherActionName;
	}

	public String getCatcherFieldName() {
		return catcherFieldName;
	}

	public void setCatcherFieldName(String catcherFieldName) {
		this.catcherFieldName = catcherFieldName;
	}

	public String getCatcherPathFormat() {
		return catcherPathFormat;
	}

	public void setCatcherPathFormat(String catcherPathFormat) {
		this.catcherPathFormat = catcherPathFormat;
	}

	public String getCatcherUrlPrefix() {
		return catcherUrlPrefix;
	}

	public void setCatcherUrlPrefix(String catcherUrlPrefix) {
		this.catcherUrlPrefix = catcherUrlPrefix;
	}

	public Long getCatcherMaxSize() {
		return catcherMaxSize;
	}

	public void setCatcherMaxSize(Long catcherMaxSize) {
		this.catcherMaxSize = catcherMaxSize;
	}

	public String[] getCatcherAllowFiles() {
		return catcherAllowFiles;
	}

	public void setCatcherAllowFiles(String[] catcherAllowFiles) {
		this.catcherAllowFiles = catcherAllowFiles;
	}

	public String getVideoActionName() {
		return videoActionName;
	}

	public void setVideoActionName(String videoActionName) {
		this.videoActionName = videoActionName;
	}

	public String getVideoFieldName() {
		return videoFieldName;
	}

	public void setVideoFieldName(String videoFieldName) {
		this.videoFieldName = videoFieldName;
	}

	public String getVideoPathFormat() {
		return videoPathFormat;
	}

	public void setVideoPathFormat(String videoPathFormat) {
		this.videoPathFormat = videoPathFormat;
	}

	public String getVideoUrlPrefix() {
		return videoUrlPrefix;
	}

	public void setVideoUrlPrefix(String videoUrlPrefix) {
		this.videoUrlPrefix = videoUrlPrefix;
	}

	public Long getVideoMaxSize() {
		return videoMaxSize;
	}

	public void setVideoMaxSize(Long videoMaxSize) {
		this.videoMaxSize = videoMaxSize;
	}

	public String[] getVideoAllowFiles() {
		return videoAllowFiles;
	}

	public void setVideoAllowFiles(String[] videoAllowFiles) {
		this.videoAllowFiles = videoAllowFiles;
	}

	public String getFileActionName() {
		return fileActionName;
	}

	public void setFileActionName(String fileActionName) {
		this.fileActionName = fileActionName;
	}

	public String getFileFieldName() {
		return fileFieldName;
	}

	public void setFileFieldName(String fileFieldName) {
		this.fileFieldName = fileFieldName;
	}

	public String getFilePathFormat() {
		return filePathFormat;
	}

	public void setFilePathFormat(String filePathFormat) {
		this.filePathFormat = filePathFormat;
	}

	public String getFileUrlPrefix() {
		return fileUrlPrefix;
	}

	public void setFileUrlPrefix(String fileUrlPrefix) {
		this.fileUrlPrefix = fileUrlPrefix;
	}

	public Long getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(Long fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	public String[] getFileAllowFiles() {
		return fileAllowFiles;
	}

	public void setFileAllowFiles(String[] fileAllowFiles) {
		this.fileAllowFiles = fileAllowFiles;
	}

	public String getImageManagerActionName() {
		return imageManagerActionName;
	}

	public void setImageManagerActionName(String imageManagerActionName) {
		this.imageManagerActionName = imageManagerActionName;
	}

	public String getImageManagerListPath() {
		return imageManagerListPath;
	}

	public void setImageManagerListPath(String imageManagerListPath) {
		this.imageManagerListPath = imageManagerListPath;
	}

	public Integer getImageManagerListSize() {
		return imageManagerListSize;
	}

	public void setImageManagerListSize(Integer imageManagerListSize) {
		this.imageManagerListSize = imageManagerListSize;
	}

	public String getImageManagerUrlPrefix() {
		return imageManagerUrlPrefix;
	}

	public void setImageManagerUrlPrefix(String imageManagerUrlPrefix) {
		this.imageManagerUrlPrefix = imageManagerUrlPrefix;
	}

	public String getImageManagerInsertAlign() {
		return imageManagerInsertAlign;
	}

	public void setImageManagerInsertAlign(String imageManagerInsertAlign) {
		this.imageManagerInsertAlign = imageManagerInsertAlign;
	}

	public String[] getImageManagerAllowFiles() {
		return imageManagerAllowFiles;
	}

	public void setImageManagerAllowFiles(String[] imageManagerAllowFiles) {
		this.imageManagerAllowFiles = imageManagerAllowFiles;
	}

	public String getFileManagerActionName() {
		return fileManagerActionName;
	}

	public void setFileManagerActionName(String fileManagerActionName) {
		this.fileManagerActionName = fileManagerActionName;
	}

	public String getFileManagerListPath() {
		return fileManagerListPath;
	}

	public void setFileManagerListPath(String fileManagerListPath) {
		this.fileManagerListPath = fileManagerListPath;
	}

	public String getFileManagerUrlPrefix() {
		return fileManagerUrlPrefix;
	}

	public void setFileManagerUrlPrefix(String fileManagerUrlPrefix) {
		this.fileManagerUrlPrefix = fileManagerUrlPrefix;
	}

	public Integer getFileManagerListSize() {
		return fileManagerListSize;
	}

	public void setFileManagerListSize(Integer fileManagerListSize) {
		this.fileManagerListSize = fileManagerListSize;
	}

	public String[] getFileManagerAllowFiles() {
		return fileManagerAllowFiles;
	}

	public void setFileManagerAllowFiles(String[] fileManagerAllowFiles) {
		this.fileManagerAllowFiles = fileManagerAllowFiles;
	}

}
