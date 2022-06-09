package com.cloud.cqc.client.configure.security;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * JWT Authentication请求
 * 
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 用户名 */
	@NotBlank
	private String username;
	/** 密码 */
	@NotBlank
	private String password;

	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
