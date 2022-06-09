package com.cloud.cqc.service.cms.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicSortEntity;

/**
 * <p>
 * 网站部件
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@TableName("t_cms_site_parts")
public class CmsSiteParts extends BasicSortEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 内容(html)
	 */
	private String content;
	/**
	 * 位置(1-上,2-下)
	 */
	private Integer position;
	/**
	 * 是否禁用
	 */
	private Boolean disabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
