package com.cloud.cqc.service.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cloud.cqc.service.admin.entity.Resource;

/**
 * <p>
 * 资源信息 Mapper 接口
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

	/**
	 * 获取角色权限列表
	 * 
	 * @param roles
	 *            角色列表
	 * @return
	 */
	List<Resource> selectListByRoles(@Param(value = "roles") List<String> roles);

}