package com.cloud.cqc.client.common.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cloud.cqc.client.security.model.SecurityUser;
import com.cloud.cqc.framework.mvc.BaseRestController;

/**
 * <p>
 * Security Controller
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年9月19日
 * @version 1.0
 */
public class SecurityController extends BaseRestController {

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	protected SecurityUser getCurrentUser() {
		return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
