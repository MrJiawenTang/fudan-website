package com.cloud.cqc.client.configure.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * Web服务配置
 * 
 * @author joy.zhou
 * @date 2017年9月14日
 * @version 1.0
 */
@ConfigurationProperties(prefix = "spring.http.cors")
public class CorsProperties {

	/** 是否允许跨域 */
	private Boolean allowed = true;
	/** 映射路径 */
	private String mapping = "/**";
	/** 允许域名 */
	private String allowedOrigins = "*";
	/** 是否发送cookie */
	private Boolean allowCredentials = true;
	/** 允许方法 */
	private String[] allowedMethods = { "GET", "POST", "OPTIONS", "DELETE", "PUT" };
	/** 缓存时间 */
	private long maxAge = 1800;

	public Boolean getAllowed() {
		return allowed;
	}

	public void setAllowed(Boolean allowed) {
		this.allowed = allowed;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public Boolean getAllowCredentials() {
		return allowCredentials;
	}

	public void setAllowCredentials(Boolean allowCredentials) {
		this.allowCredentials = allowCredentials;
	}

	public String[] getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(String[] allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}

}
