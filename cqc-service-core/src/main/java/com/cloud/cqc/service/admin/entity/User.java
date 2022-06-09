package com.cloud.cqc.service.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@TableName("t_user")
public class User extends BasicEntity {

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

}
