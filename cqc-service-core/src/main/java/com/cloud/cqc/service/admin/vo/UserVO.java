package com.cloud.cqc.service.admin.vo;

import java.util.HashSet;
import java.util.Set;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-09-01
 */
public class UserVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 盐噪声
	 */
	private String salt;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 0 女 1男 99 未知
	 */
	private Integer sex;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 备注
	 */
	private String remark;

	/** 角色列表 */
	private Set<String> roles = new HashSet<>(0);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
