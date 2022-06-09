package com.cloud.cqc.service.admin.service;

import java.util.List;
import java.util.Map;

import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.admin.entity.Role;
import com.cloud.cqc.service.admin.vo.RoleVO;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
public interface IRoleService extends IBaseService<Role, RoleVO> {

	/**
	 * 获取角色资源
	 * 
	 * @param roleCode
	 *            角色编号
	 * @return
	 */
	List<Long> selectRoleResource(String roleCode);

	/**
	 * 获取所有角色菜单
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectRoleMenu();
}
