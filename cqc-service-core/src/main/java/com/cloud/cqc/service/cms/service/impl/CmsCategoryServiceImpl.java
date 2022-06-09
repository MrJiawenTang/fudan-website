package com.cloud.cqc.service.cms.service.impl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsCategory;
import com.cloud.cqc.service.cms.mapper.CmsCategoryMapper;
import com.cloud.cqc.service.cms.searchable.CmsCategorySearchable;
import com.cloud.cqc.service.cms.service.ICmsCategoryService;
import com.cloud.cqc.service.cms.vo.CmsCategoryVO;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory, CmsCategoryVO>
		implements ICmsCategoryService {

	@Override
	protected void getCondition(EntityWrapper<CmsCategory> ew, Searchable searchable) {

		if (searchable instanceof CmsCategorySearchable) {
			CmsCategorySearchable search = (CmsCategorySearchable) searchable;

			if (search.getType() != null) {
				ew.eq("type", search.getType());
			}
			if (search.getDisabled() != null) {
				ew.eq("disabled", search.getDisabled());
			}
			if (StringUtils.isNotBlank(search.getCode())) {
				ew.eq("code", search.getCode());
			}
			if (search.getCodes() != null && search.getCodes().length > 0) {
				ew.in("code", Arrays.asList(search.getCodes()));
			}
		}
	}

	@Override
	public CmsCategoryVO selectByCode(String code) {
		CmsCategorySearchable searchable = new CmsCategorySearchable();
		searchable.setCode(code);
		return this.selectOne(searchable);
	}

}
