package com.cloud.cqc.client.configure.security;

import java.io.Serializable;

/**
 * 
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	/** token */
	private String token;
	/** 随机数 */
	private String key;

	public JwtAuthenticationResponse(String token, String key) {
		this.token = token;
		this.key = key;
	}

	public String getToken() {
		return this.token;
	}

	public String getKey() {
		return key;
	}

}
