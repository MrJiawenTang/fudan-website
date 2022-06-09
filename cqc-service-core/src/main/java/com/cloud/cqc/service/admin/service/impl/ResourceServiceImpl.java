package com.cloud.cqc.service.admin.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.core.utils.EntityTransform;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.admin.entity.Resource;
import com.cloud.cqc.service.admin.mapper.ResourceMapper;
import com.cloud.cqc.service.admin.searchable.ResoueceSearchable;
import com.cloud.cqc.service.admin.service.IResourceService;
import com.cloud.cqc.service.admin.vo.ResourceVO;

/**
 * <p>
 * 资源信息 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource, ResourceVO>
		implements IResourceService {
	@Override
	protected void getCondition(EntityWrapper<Resource> ew, Searchable searchable) {

		if (searchable instanceof ResoueceSearchable) {

			ResoueceSearchable search = (ResoueceSearchable) searchable;

			if (StringUtils.isNotBlank(search.getKey())) {
				ew.eq("resource_name", search.getKey()).or().eq("resource_description", search.getKey());
			}

			if (StringUtils.isNotBlank(search.getResourceType())) {
				ew.eq("resource_type", search.getResourceType());
			}

		}

	}

	@Override
	public List<ResourceVO> selectListByRoles(List<String> roles) {
		return EntityTransform.trans(baseMapper.selectListByRoles(roles), voClass);
	}

}
