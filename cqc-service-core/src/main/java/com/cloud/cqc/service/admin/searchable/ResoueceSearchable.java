package com.cloud.cqc.service.admin.searchable;

import java.util.List;

import com.cloud.cqc.framework.persistence.dto.Searchable;

/**
 * <p>
 * 资源查询参数
 * </p>
 * 
 * @author Joy.zhou
 *
 */
public class ResoueceSearchable extends Searchable {

	private static final long serialVersionUID = 1L;
	/** 关键字 */
	private String key;
	/** 资源类型 */
	private String resourceType;
	/** 角色列表 */
	private List<String> roles;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
