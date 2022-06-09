package com.cloud.cqc.framework.supports.oath2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Oath2用户信息
 * 
 * @author joy.zhou
 * @date 2017年6月5日
 */
public class SecurityUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	/** 密码 */
	private String password;
	/** 用户名 */
	private String username;
	/** 角色列表 */
	private Set<SecurityRole> roles = new HashSet<>(0);

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	public void addRoles(String... roles) {
		for (String role : roles) {
			this.roles.add(new SecurityRole(role));
		}
	}

	@Override
	public Collection<SecurityRole> getAuthorities() {
		return roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
