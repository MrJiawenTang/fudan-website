package com.cloud.cqc.service.admin.vo;

import java.util.ArrayList;
import java.util.List;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-09-01
 */
public class RoleVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/** 角色编号 */
	private String roleCode;
	/** 角色名称 */
	private String roleName;
	/** 角色描述 */
	private String roleDescription;
	/** 资源ID */
	private List<Long> resourceIds = new ArrayList<>(0);

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<Long> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}

}
