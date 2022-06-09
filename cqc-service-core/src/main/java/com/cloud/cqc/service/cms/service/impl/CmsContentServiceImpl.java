package com.cloud.cqc.service.cms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsContent;
import com.cloud.cqc.service.cms.mapper.CmsContentMapper;
import com.cloud.cqc.service.cms.searchable.CmsContentSearchable;
import com.cloud.cqc.service.cms.service.ICmsContentService;
import com.cloud.cqc.service.cms.vo.CmsContentVO;

/**
 * <p>
 * 文章内容 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsContentServiceImpl extends BaseServiceImpl<CmsContentMapper, CmsContent, CmsContentVO>
		implements ICmsContentService {

	@Override
	protected void getCondition(EntityWrapper<CmsContent> ew, Searchable searchable) {

		if (searchable instanceof CmsContentSearchable) {
			CmsContentSearchable search = (CmsContentSearchable) searchable;

			if (StringUtils.isNotBlank(search.getCategoryCode())) {
				ew.eq("category_code", search.getCategoryCode());
			}
			if (search.getStatus() != null) {
				ew.eq("status", search.getStatus());
			}
			if (StringUtils.isNotBlank(search.getLkAuthor())) {
				ew.like("author", search.getLkAuthor());
			}
			if (StringUtils.isNotBlank(search.getLkEditor())) {
				ew.like("editor", search.getLkEditor());
			}
		}
	}

	@Override
	protected void getSearchKey(EntityWrapper<CmsContent> ew, Searchable searchable) {
		ew.like("title", searchable.getKey()).or().like("description", searchable.getKey());
	}

}
