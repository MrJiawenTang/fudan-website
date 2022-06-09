package com.cloud.cqc.service.admin.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloud.cqc.framework.core.utils.EntityTransform;
import com.cloud.cqc.framework.core.utils.EntityTransform.MergeDto;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.admin.entity.Role;
import com.cloud.cqc.service.admin.mapper.RoleMapper;
import com.cloud.cqc.service.admin.service.IRoleService;
import com.cloud.cqc.service.admin.vo.RoleVO;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleVO> implements IRoleService {

	@Override
	public boolean insert(RoleVO vo) {
		super.insert(vo);
		// 分配资源
		insertRoleRecourse(vo.getRoleCode(), vo.getResourceIds());
		return true;
	}

	@Override
	public boolean update(RoleVO vo) {
		super.update(vo);
		if (StringUtils.isNotBlank(vo.getRoleCode())) {
			List<Long> sourcrs = baseMapper.selectRoleResource(vo.getRoleCode());
			MergeDto<Long> merge = EntityTransform.merge(sourcrs, vo.getResourceIds());
			insertRoleRecourse(vo.getRoleCode(), merge.getSaveData());
			removeRoleRecourse(vo.getRoleCode(), merge.getDeleteData());
		}
		return true;
	}

	@Override
	public boolean deleteById(Serializable id) {
		Role role = baseMapper.selectById(id);
		baseMapper.removeAllRoleResource(role.getRoleCode());
		super.deleteById(id);
		return true;
	}

	private void insertRoleRecourse(String roleCode, Collection<Long> recourseIds) {
		if (StringUtils.isBlank(roleCode)) {
			return;
		}
		if (recourseIds == null || recourseIds.isEmpty()) {
			return;
		}
		for (Long recourseId : recourseIds) {
			baseMapper.insertRoleResource(roleCode, recourseId);
		}
	}

	private void removeRoleRecourse(String roleCode, Collection<Long> recourseIds) {
		if (StringUtils.isBlank(roleCode)) {
			return;
		}
		if (recourseIds == null || recourseIds.isEmpty()) {
			return;
		}
		for (Long recourseId : recourseIds) {
			baseMapper.removeRoleResource(roleCode, recourseId);
		}
	}

	@Override
	@Cacheable
	public List<Long> selectRoleResource(String roleCode) {
		return baseMapper.selectRoleResource(roleCode);
	}

	@Override
	@Cacheable
	public List<Map<String, Object>> selectRoleMenu() {
		return baseMapper.selectRoleMenu();
	}

}
