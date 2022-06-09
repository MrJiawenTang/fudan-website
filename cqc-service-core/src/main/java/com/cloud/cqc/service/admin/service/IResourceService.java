package com.cloud.cqc.service.admin.service;

import java.util.List;

import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.admin.entity.Resource;
import com.cloud.cqc.service.admin.vo.ResourceVO;

/**
 * <p>
 * 资源信息 服务类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
public interface IResourceService extends IBaseService<Resource, ResourceVO> {
	/**
	 * 获取资源列表
	 * 
	 * @param roles
	 *            角色编号
	 * @return
	 */
	List<ResourceVO> selectListByRoles(List<String> roles);
}
