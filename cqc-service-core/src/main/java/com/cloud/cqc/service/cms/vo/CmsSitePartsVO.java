package com.cloud.cqc.service.cms.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 网站部件
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsSitePartsVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    /** 名称 */
	private String name;
    /** 排序 */
	private Float sort;
    /** 内容(html) */
	private String content;
    /** 位置(1-上,2-下) */
	private Integer position;
    /** 是否禁用 */
	private Boolean disabled;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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
