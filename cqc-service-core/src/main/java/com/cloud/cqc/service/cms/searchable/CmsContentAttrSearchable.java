package com.cloud.cqc.service.cms.searchable;

import com.cloud.cqc.framework.persistence.dto.Searchable;

public class CmsContentAttrSearchable extends Searchable {

	private static final long serialVersionUID = 1L;
	/** 内容ID */
	private Long contentId;
	/** 内容来源 */
	private String source;

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
