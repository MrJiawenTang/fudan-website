package com.cloud.cqc.service.cms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.cms.entity.CmsContentAttribute;
import com.cloud.cqc.service.cms.mapper.CmsContentAttributeMapper;
import com.cloud.cqc.service.cms.searchable.CmsContentAttrSearchable;
import com.cloud.cqc.service.cms.service.ICmsContentAttributeService;
import com.cloud.cqc.service.cms.vo.CmsContentAttributeVO;

/**
 * <p>
 * 文章内容扩展 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
@Service
public class CmsContentAttributeServiceImpl
		extends BaseServiceImpl<CmsContentAttributeMapper, CmsContentAttribute, CmsContentAttributeVO>
		implements ICmsContentAttributeService {

	@Override
	protected void getCondition(EntityWrapper<CmsContentAttribute> ew, Searchable searchable) {

		if (searchable instanceof CmsContentAttrSearchable) {
			CmsContentAttrSearchable search = (CmsContentAttrSearchable) searchable;

			if (search.getContentId() != null) {
				ew.eq("content_id", search.getContentId());
			}
			if (StringUtils.isNotBlank(search.getSource())) {
				ew.eq("source", search.getSource());
			}
		}
	}

	@Override
	public CmsContentAttributeVO selectByContentId(Long id) {
		CmsContentAttrSearchable searchable = new CmsContentAttrSearchable();
		searchable.setContentId(id);
		return this.selectOne(searchable);
	}
}
