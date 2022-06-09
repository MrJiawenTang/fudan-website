package com.cloud.cqc.service.meeting.service;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.meeting.entity.Meeting;
import com.cloud.cqc.service.meeting.vo.MeetingVO;

/**
 * <p>
 * 会议表 服务类
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
public interface IMeetingService extends IBaseService<Meeting, MeetingVO> {

	/**
	 * 会议通知
	 * 
	 * @param meetingId
	 * 
	 * @param userId
	 * @throws ClientException
	 */
	void notifyMeeting(Long meetingId, Long userId);

}
