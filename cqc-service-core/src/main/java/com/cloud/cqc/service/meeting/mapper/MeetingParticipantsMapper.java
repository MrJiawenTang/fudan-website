package com.cloud.cqc.service.meeting.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cloud.cqc.service.meeting.entity.MeetingParticipants;

/**
 * <p>
 * 会议人员表 Mapper 接口
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@Mapper
public interface MeetingParticipantsMapper extends BaseMapper<MeetingParticipants> {

	/**
	 * 根据会议ID删除
	 * 
	 * @param id
	 */
	@Delete("delete from t_meeting_participants where meeting_id = #{id}")
	void deleteByMeetingId(@Param("id") Serializable id);
}