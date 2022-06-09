package com.cloud.cqc.service.cms.searchable;

import com.cloud.cqc.framework.persistence.dto.DefaultSortSearchable;

public class CmsCategorySearchable extends DefaultSortSearchable {

	private static final long serialVersionUID = 1L;
	/**
	 * 编码(别名)
	 */
	private String code;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 是否禁用
	 */
	private Boolean disabled;
	/** 编码列表 */
	private String[] codes;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

}
