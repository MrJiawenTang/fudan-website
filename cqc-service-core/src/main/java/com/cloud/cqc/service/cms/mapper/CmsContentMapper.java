package com.cloud.cqc.service.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.cloud.cqc.service.cms.entity.CmsContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 文章内容 Mapper 接口
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Mapper
public interface CmsContentMapper extends BaseMapper<CmsContent> {

}