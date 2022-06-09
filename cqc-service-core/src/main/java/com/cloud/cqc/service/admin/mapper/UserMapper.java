package com.cloud.cqc.service.admin.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cloud.cqc.service.admin.entity.User;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 插入用户角色
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCode
	 *            角色编号
	 */
	@Insert("insert into t_user_role_mapping(user_name,role_code) values(#{username},#{roleCode})")
	void insertUserRole(@Param(value = "username") String username, @Param(value = "roleCode") String roleCode);

	/**
	 * 获取用户角色
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	@Select("select role_code from t_user_role_mapping where user_name = #{username}")
	Set<String> selectUserRoles(@Param(value = "username") String username);

	/**
	 * 删除用户角色列表
	 * 
	 * @param userName
	 *            用户名
	 * @param roleCode
	 *            角色编号
	 */
	@Delete("delete from t_user_role_mapping where user_name=#{username} and role_code=#{roleCode}")
	void removeUserRoles(@Param(value = "username") String username, @Param(value = "roleCode") String roleCode);

	/**
	 * 删除用户角色列表
	 * 
	 * @param userName
	 *            用户名
	 */
	@Delete("delete from t_user_role_mapping where user_name=#{username}")
	void removeAllUserRoles(@Param(value = "username") String username);

}
