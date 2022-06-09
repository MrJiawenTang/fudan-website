package com.cloud.cqc.service.cms.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 友情链接
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsFriendlyLinkVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    /** 名称 */
	private String name;
    /** 链接 */
	private String link;
    /** 排序 */
	private Float sort;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}
}
