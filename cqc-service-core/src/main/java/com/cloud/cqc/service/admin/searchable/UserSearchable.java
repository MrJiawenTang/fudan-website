package com.cloud.cqc.service.admin.searchable;

import com.cloud.cqc.framework.persistence.dto.Searchable;

/**
 * <p>
 * 用户查询参数
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public class UserSearchable extends Searchable {

	private static final long serialVersionUID = 1L;
	/** 关键字 */
	private String key;
	/** 用户名 */
	private String username;
	/** 员工号 */
	private String staffNo;
	/** 部门编号 */
	private String departmentCode;
	/** 岗位编号 */
	private String positionCode;
	/** 性别 */
	private Integer sex;
	/** 手机 */
	private String mobile;
	/** 排除员工号 */
	private String notStaffNo;
	/** 任务月份 */
	private String taskMonth;
	/** 部门编号列表 */
	private String[] departmentCodes;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTaskMonth() {
		return taskMonth;
	}

	public void setTaskMonth(String taskMonth) {
		this.taskMonth = taskMonth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
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

	public String getNotStaffNo() {
		return notStaffNo;
	}

	public void setNotStaffNo(String notStaffNo) {
		this.notStaffNo = notStaffNo;
	}

	public String[] getDepartmentCodes() {
		return departmentCodes;
	}

	public void setDepartmentCodes(String[] departmentCodes) {
		this.departmentCodes = departmentCodes;
	}

}
