package com.cloud.cqc.service.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.meeting.entity.MeetingParticipants;
import com.cloud.cqc.service.meeting.mapper.MeetingParticipantsMapper;
import com.cloud.cqc.service.meeting.searchable.MeetingParticipantsSearchable;
import com.cloud.cqc.service.meeting.service.IMeetingParticipantsService;
import com.cloud.cqc.service.meeting.vo.MeetingParticipantsVO;

/**
 * <p>
 * 会议人员表 服务实现类
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@Service
@Transactional
public class MeetingParticipantsServiceImpl
		extends BaseServiceImpl<MeetingParticipantsMapper, MeetingParticipants, MeetingParticipantsVO>
		implements IMeetingParticipantsService {
	@Autowired
	MeetingParticipantsMapper meetingParticipantsMapper;

	@Override
	protected void getCondition(EntityWrapper<MeetingParticipants> ew, Searchable searchable) {

		if (searchable instanceof MeetingParticipantsSearchable) {
			MeetingParticipantsSearchable search = (MeetingParticipantsSearchable) searchable;

			if (search.getMeetingId() != null) {
				ew.eq("meeting_id", search.getMeetingId());
			}
		}
	}

	@Override
	public List<MeetingParticipantsVO> selectByMeetingId(Long meetingId) {
		MeetingParticipantsSearchable searchable = new MeetingParticipantsSearchable();
		searchable.setMeetingId(meetingId);
		return this.selectList(searchable);
	}

}
