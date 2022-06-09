package com.cloud.cqc.client.configure.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cloud.cqc.service.admin.vo.UserVO;
import com.cloud.cqc.client.security.model.SecurityUser;

/**
 * 
 * Jwt User Factory
 * 
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static SecurityUser create(UserVO user) {

		SecurityUser securityUser = new SecurityUser();

		BeanUtils.copyProperties(user, securityUser);

		securityUser.setAuthorities(mapToGrantedAuthorities(user.getRoles()));

		return securityUser;
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(Set<String> authorities) {

		if (authorities == null || authorities.isEmpty()) {
			return new ArrayList<>(0);
		}

		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
