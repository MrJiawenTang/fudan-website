package com.cloud.cqc.service.cms.searchable;

import com.cloud.cqc.framework.persistence.dto.DefaultSortSearchable;

public class CmsSitePartsSearchable extends DefaultSortSearchable {

	private static final long serialVersionUID = 1L;

	/** 位置 */
	private Integer position;
	/** 是否禁用 */
	private Boolean disabled;

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

}
