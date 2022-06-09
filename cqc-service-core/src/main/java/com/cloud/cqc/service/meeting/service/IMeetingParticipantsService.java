package com.cloud.cqc.service.meeting.service;

import com.cloud.cqc.service.meeting.entity.MeetingParticipants;
import com.cloud.cqc.service.meeting.vo.MeetingParticipantsVO;
import com.cloud.cqc.framework.persistence.service.IBaseService;

import java.util.List;

/**
 * <p>
 * 会议人员表 服务类
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
public interface IMeetingParticipantsService extends IBaseService<MeetingParticipants, MeetingParticipantsVO> {

	/**
	 * 
	 * @param meetingId
	 * @return
	 */
	List<MeetingParticipantsVO> selectByMeetingId(Long meetingId);
}
