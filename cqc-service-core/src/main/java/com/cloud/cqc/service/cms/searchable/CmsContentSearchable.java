package com.cloud.cqc.service.cms.searchable;

import com.cloud.cqc.framework.persistence.dto.DefaultSortSearchable;

public class CmsContentSearchable extends DefaultSortSearchable {

	private static final long serialVersionUID = 1L;

	/** 分类 */
	private String categoryCode;
	/** 状态 */
	private Integer status;
	/** 作者 */
	private String lkAuthor;
	/** 编辑 */
	private String lkEditor;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLkAuthor() {
		return lkAuthor;
	}

	public void setLkAuthor(String lkAuthor) {
		this.lkAuthor = lkAuthor;
	}

	public String getLkEditor() {
		return lkEditor;
	}

	public void setLkEditor(String lkEditor) {
		this.lkEditor = lkEditor;
	}

}
