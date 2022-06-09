package com.cloud.cqc.client.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.cloud.cqc.service.admin.service.IUserService;
import com.cloud.cqc.service.admin.vo.UserVO;
import com.cloud.cqc.client.configure.security.JwtUserFactory;

/**
 * 
 * @author joy.zhou
 * @date 2017年7月17日
 * @version 1.0
 */
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	/**
	 * 获取用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException(username);
		}

		UserVO user = userService.selectByUA(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		Set<String> roles = userService.selectUserRoles(username);

		if (!roles.isEmpty()) {
			user.setRoles(roles);
		}

		return JwtUserFactory.create(user);

	}

}
