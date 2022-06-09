package com.cloud.cqc.service.meeting.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.cloud.cqc.service.meeting.entity.Meeting;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 会议表 Mapper 接口
 * </p>
 *
 * @author eric.wang
 * @since 2018-11-02
 */
@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {

}