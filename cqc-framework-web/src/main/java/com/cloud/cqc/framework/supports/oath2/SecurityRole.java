package com.cloud.cqc.framework.supports.oath2;

import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义角色
 * 
 * @author joy.zhou
 * @date 2017年6月5日
 */
public class SecurityRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	/** 角色名 */
	public String authority;

	public SecurityRole() {
	}

	public SecurityRole(String authority) {
		this.authority = authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
