package com.cloud.cqc.service.meeting.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 会议人员表
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@TableName("t_meeting_participants")
public class MeetingParticipants extends BasicEntity {

    private static final long serialVersionUID = 1L;

	private Long meetingId;
    /**
     * 名称
     */
	private String name;
	private String title;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 0 女 1 男
     */
	private Integer sex;
    /**
     * 邮箱
     */
	private String mail;
    /**
     * 排序
     */
	private Float sort;
	private String smsTime;
	private String mailTime;
    /**
     * 0 未签到 1 已签到
     */
	private Integer signInStatus;
	private String signInTime;


	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

	public String getSmsTime() {
		return smsTime;
	}

	public void setSmsTime(String smsTime) {
		this.smsTime = smsTime;
	}

	public String getMailTime() {
		return mailTime;
	}

	public void setMailTime(String mailTime) {
		this.mailTime = mailTime;
	}

	public Integer getSignInStatus() {
		return signInStatus;
	}

	public void setSignInStatus(Integer signInStatus) {
		this.signInStatus = signInStatus;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

}
