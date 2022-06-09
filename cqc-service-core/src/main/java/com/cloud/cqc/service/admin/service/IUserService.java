package com.cloud.cqc.service.admin.service;

import java.util.Set;

import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.admin.entity.User;
import com.cloud.cqc.service.admin.vo.UserVO;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
public interface IUserService extends IBaseService<User, UserVO> {

	/**
	 * 获取用户角色
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	Set<String> selectUserRoles(String username);

	/**
	 * 获取用户信息
	 * 
	 * @param ua
	 *            用户唯一标识
	 * @return
	 */
	UserVO selectByUA(String ua);

}
