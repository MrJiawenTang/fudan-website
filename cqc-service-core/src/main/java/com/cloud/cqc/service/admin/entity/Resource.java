package com.cloud.cqc.service.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 资源信息
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@TableName("t_resource")
public class Resource extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 父节点
	 */
	private Long parentId;
	/**
	 * 资源名称
	 */
	private String resourceName;
	/**
	 * 资源描述
	 */
	private String resourceDescription;
	/**
	 * 资源类型
	 */
	private Integer resourceType;
	/**
	 * 资源URL
	 */
	private String resourceUrl;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

}
