package com.cloud.cqc.service.cms.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicSortEntity;

/**
 * <p>
 * 友情链接
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@TableName("t_cms_friendly_link")
public class CmsFriendlyLink extends BasicSortEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 链接
	 */
	private String link;

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

}
