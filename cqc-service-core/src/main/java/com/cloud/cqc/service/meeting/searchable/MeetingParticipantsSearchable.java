package com.cloud.cqc.service.meeting.searchable;

import com.cloud.cqc.framework.persistence.dto.DefaultSortSearchable;

public class MeetingParticipantsSearchable extends DefaultSortSearchable {

	private static final long serialVersionUID = 1L;
	/** 会议ID */
	private Long meetingId;

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

}
