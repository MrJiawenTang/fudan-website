package com.cloud.cqc.service.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsSlide;
import com.cloud.cqc.service.cms.mapper.CmsSlideMapper;
import com.cloud.cqc.service.cms.service.ICmsSlideService;
import com.cloud.cqc.service.cms.vo.CmsSlideVO;

/**
 * <p>
 * 幻灯片 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsSlideServiceImpl extends BaseServiceImpl<CmsSlideMapper, CmsSlide, CmsSlideVO>
		implements ICmsSlideService {
	@Override
	protected void getSearchKey(EntityWrapper<CmsSlide> ew, Searchable searchable) {

		ew.like("name", searchable.getKey()).or().like("title", searchable.getKey());
	}
}
