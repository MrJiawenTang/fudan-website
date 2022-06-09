package com.cloud.cqc.service.meeting.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 会议表
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
public class MeetingVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    /** 名称 */
	private String meetingName;
    /** 名称 */
	private String meetingContent;
    /** 名称 */
	private String meetingTime;
    /** 名称 */
	private String meetingAddress;
    /** 排序 */
	private Float sort;
    /** 0 未发  1 已发 */
	private Integer smsStatus;
    /** 0 未发  1 已发 */
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

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
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
