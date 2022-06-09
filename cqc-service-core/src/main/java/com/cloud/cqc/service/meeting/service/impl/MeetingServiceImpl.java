package com.cloud.cqc.service.meeting.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.cqc.framework.core.exception.AppException;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.meeting.entity.Meeting;
import com.cloud.cqc.service.meeting.mapper.MeetingMapper;
import com.cloud.cqc.service.meeting.mapper.MeetingParticipantsMapper;
import com.cloud.cqc.service.meeting.service.IMeetingParticipantsService;
import com.cloud.cqc.service.meeting.service.IMeetingService;
import com.cloud.cqc.service.meeting.vo.MeetingParticipantsVO;
import com.cloud.cqc.service.meeting.vo.MeetingVO;
import com.cloud.cqc.util.SmsUtils;

/**
 * <p>
 * 会议表 服务实现类
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@Service
@Transactional
public class MeetingServiceImpl extends BaseServiceImpl<MeetingMapper, Meeting, MeetingVO> implements IMeetingService {
	@Autowired
	private IMeetingParticipantsService meetingParticipantsService;
	@Autowired
	private MeetingParticipantsMapper meetingParticipantsMapper;

	private static final String SMS_TEMPLATE_CODE_MEETING = "SMS_150171647";
	private static final String SMS_OUTID_MEETING = "meeting";

	@Override
	public void notifyMeeting(Long meetingId, Long userId) {
		Meeting meeting = this.selectById(meetingId);
		Map<String, Object> param = new HashMap<>();
		param.put("meetingId", meetingId);
		StringBuilder json = new StringBuilder();
		json.append("{").append("\"meeting_name\":").append("\"").append(meeting.getMeetingName()).append("\",")
				.append("\"meeting_time\":").append("\"").append(meeting.getMeetingTime()).append("\",")
				.append("\"meeting_address\":").append("\"").append(meeting.getMeetingAddress()).append("\"")
				.append("}");
		List<MeetingParticipantsVO> participantsList = this.meetingParticipantsService.selectByMeetingId(meetingId);
		try {
			for (MeetingParticipantsVO participant : participantsList) {
				SmsUtils.sendSms(participant.getMobile(), SMS_TEMPLATE_CODE_MEETING, json.toString(),
						SMS_OUTID_MEETING);
			}
		} catch (ClientException e) {
			throw new AppException("sms error", e);
		}
		meeting.setSmsStatus(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		meeting.setLastUpdateTime(sdf.format(new Date()));
		meeting.setLastUpdateBy(String.valueOf(userId));
		this.baseMapper.updateById(meeting);
	}

	@Override
	public boolean deleteById(Serializable id) {
		this.meetingParticipantsMapper.deleteByMeetingId(id);
		return super.deleteById(id);
	}

}
