package com.cloud.cqc.service.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsFriendlyLink;
import com.cloud.cqc.service.cms.mapper.CmsFriendlyLinkMapper;
import com.cloud.cqc.service.cms.service.ICmsFriendlyLinkService;
import com.cloud.cqc.service.cms.vo.CmsFriendlyLinkVO;

/**
 * <p>
 * 友情链接 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsFriendlyLinkServiceImpl extends
		BaseServiceImpl<CmsFriendlyLinkMapper, CmsFriendlyLink, CmsFriendlyLinkVO> implements ICmsFriendlyLinkService {

	@Override
	protected void getSearchKey(EntityWrapper<CmsFriendlyLink> ew, Searchable searchable) {

		ew.like("name", searchable.getKey());
	}

}
