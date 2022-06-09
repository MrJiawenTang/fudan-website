package com.cloud.cqc.service.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsSiteParts;
import com.cloud.cqc.service.cms.mapper.CmsSitePartsMapper;
import com.cloud.cqc.service.cms.searchable.CmsSitePartsSearchable;
import com.cloud.cqc.service.cms.service.ICmsSitePartsService;
import com.cloud.cqc.service.cms.vo.CmsSitePartsVO;

/**
 * <p>
 * 网站部件 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsSitePartsServiceImpl extends BaseServiceImpl<CmsSitePartsMapper, CmsSiteParts, CmsSitePartsVO>
		implements ICmsSitePartsService {

	@Override
	protected void getCondition(EntityWrapper<CmsSiteParts> ew, Searchable searchable) {

		if (searchable instanceof CmsSitePartsSearchable) {
			CmsSitePartsSearchable search = (CmsSitePartsSearchable) searchable;

			if (search.getPosition() != null) {
				ew.eq("position", search.getPosition());
			}
			if (search.getDisabled() != null) {
				ew.eq("disabled", search.getDisabled());
			}
		}
	}

	@Override
	protected void getSearchKey(EntityWrapper<CmsSiteParts> ew, Searchable searchable) {

		ew.like("name", searchable.getKey());
	}
}
