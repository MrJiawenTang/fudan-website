package com.cloud.cqc.client.ueditor.entity;

import com.cloud.cqc.client.ueditor.constant.AppInfo;

public class ResultState {
	/** 状态 */
	private Boolean success;
	/** 消息 */
	private String state;
	/** 名称 */
	private String title;
	/** 文件大小 */
	private Long size;
	/** url地址 */
	private String url;
	/** 原地址 */
	private String source;
	/** 文件类型 */
	private String type;
	/** 文件名 */
	private String original;

	public ResultState() {
	}

	public ResultState(boolean success, int infoCode) {
		this.success = success;
		this.state = success ? AppInfo.getStateInfo(AppInfo.SUCCESS) : AppInfo.getStateInfo(infoCode);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

}
