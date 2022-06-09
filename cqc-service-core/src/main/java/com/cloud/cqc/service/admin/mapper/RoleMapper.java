package com.cloud.cqc.service.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cloud.cqc.service.admin.entity.Role;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 插入用户角色
	 * 
	 * @param roleCode
	 *            角色编号
	 * @param recourseId
	 *            资源ID
	 */
	@Insert("insert into t_role_resource_mapping(role_code,resource_id) values(#{roleCode},#{recourseId})")
	void insertRoleResource(@Param(value = "roleCode") String roleCode, @Param(value = "recourseId") Long recourseId);

	/**
	 * 删除角色资源列表
	 * 
	 * @param roleCode
	 *            角色编号
	 * @param recourseId
	 *            资源ID
	 */
	@Delete("delete from t_role_resource_mapping where role_code=#{roleCode} and resource_id=#{recourseId}")
	void removeRoleResource(@Param(value = "roleCode") String roleCode, @Param(value = "recourseId") Long recourseId);

	/**
	 * 删除角色资源列表
	 * 
	 * @param roleCode
	 *            角色编号
	 * @param recourseId
	 *            资源ID
	 */
	@Delete("delete from t_role_resource_mapping where role_code=#{roleCode}")
	void removeAllRoleResource(@Param(value = "roleCode") String roleCode);

	/**
	 * 获取角色资源列表
	 * 
	 * @param roleCode
	 *            角色编号
	 * @return
	 */
	@Select("select resource_id from t_role_resource_mapping where role_code = #{roleCode}")
	List<Long> selectRoleResource(@Param(value = "roleCode") String roleCode);

	/**
	 * 获取角色菜单配置
	 * 
	 * @return
	 */
	@Select("select rrm.role_code as roleCode,r.resource_url as resourceUrl from t_role_resource_mapping rrm left join t_resource r on rrm.resource_id = r.id where resource_type = '1'")
	List<Map<String, Object>> selectRoleMenu();
}