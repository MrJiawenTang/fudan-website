package com.cloud.cqc.service.meeting.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicSortEntity;

/**
 * <p>
 * 会议表
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@TableName("t_meeting")
public class Meeting extends BasicSortEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String meetingName;
	/**
	 * 内容
	 */
	private String meetingContent;
	/**
	 * 时间
	 */
	private String meetingTime;
	/**
	 * 地址
	 */
	private String meetingAddress;
	/**
	 * 0 未发 1 已发
	 */
	private Integer smsStatus;
	/**
	 * 0 未发 1 已发
	 */
	private Integer mailStatus;

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingAddress() {
		return meetingAddress;
	}

	public void setMeetingAddress(String meetingAddress) {
		this.meetingAddress = meetingAddress;
	}

	public Integer getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(Integer smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Integer getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}

}
