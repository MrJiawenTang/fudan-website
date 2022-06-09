package com.cloud.cqc.client.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cloud.cqc.service.admin.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public class SecurityUser extends User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@JsonIgnore
	@Override
	public String getSalt() {
		return super.getSalt();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

}
