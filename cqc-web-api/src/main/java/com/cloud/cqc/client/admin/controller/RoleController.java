package com.cloud.cqc.client.admin.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.service.admin.entity.Role;
import com.cloud.cqc.service.admin.service.IRoleService;
import com.cloud.cqc.service.admin.vo.RoleVO;
import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.framework.mvc.http.RestEntity;
import com.cloud.cqc.framework.persistence.dto.Searchable;

/**
 * <p>
 * 角色管理controller
 * </p>
 * 
 * @author Joy.zhou
 *
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController extends CRUDController<Role, RoleVO, Searchable, IRoleService> {

	/**
	 * 根据角色获取权限
	 * 
	 * @param roleCode
	 *            角色编号
	 */
	@RequestMapping(value = "/resource/{roleCode}", method = RequestMethod.GET)
	public RestEntity<?> getRoles(@PathVariable String roleCode) {
		return resultOk(baseService.selectRoleResource(roleCode));
	}

	/**
	 * 查询所有菜单角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listMenuRoles", method = RequestMethod.GET)
	public RestEntity<?> listMenuRoles() {
		return resultOk(baseService.selectRoleMenu());
	}
}
