package com.cloud.cqc.service.admin.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.framework.core.utils.EntityTransform;
import com.cloud.cqc.framework.core.utils.EntityTransform.MergeDto;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.admin.entity.User;
import com.cloud.cqc.service.admin.mapper.UserMapper;
import com.cloud.cqc.service.admin.searchable.UserSearchable;
import com.cloud.cqc.service.admin.service.IUserService;
import com.cloud.cqc.service.admin.vo.UserVO;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-08-31
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserVO> implements IUserService {

	@Override
	public boolean insert(UserVO vo) {
		super.insert(vo);
		insertUserRoles(vo.getUsername(), vo.getRoles());
		return true;
	}

	@Override
	public boolean update(UserVO vo) {
		super.update(vo);
		if (StringUtils.isNotBlank(vo.getUsername())) {
			Set<String> sourcrs = baseMapper.selectUserRoles(vo.getUsername());
			MergeDto<String> merge = EntityTransform.merge(sourcrs, vo.getRoles());
			insertUserRoles(vo.getUsername(), merge.getSaveData());
			removeUserRoles(vo.getUsername(), merge.getDeleteData());
		}
		return true;
	}

	@Override
	public boolean deleteById(Serializable id) {
		User user = baseMapper.selectById(id);
		if (user != null) {
			baseMapper.removeAllUserRoles(user.getUsername());
			super.deleteById(id);
		}
		return true;
	}

	private void insertUserRoles(String username, Collection<String> roleCodes) {
		if (StringUtils.isBlank(username)) {
			return;
		}
		if (roleCodes == null || roleCodes.isEmpty()) {
			return;
		}
		for (String roleCode : roleCodes) {
			baseMapper.insertUserRole(username, roleCode);
		}
	}

	private void removeUserRoles(String username, Collection<String> roleCodes) {
		if (StringUtils.isBlank(username)) {
			return;
		}
		if (roleCodes == null || roleCodes.isEmpty()) {
			return;
		}
		for (String roleCode : roleCodes) {
			baseMapper.removeUserRoles(username, roleCode);
		}
	}

	@Override
	public UserVO selectByUA(String ua) {
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		// username or email?
		wrapper.eq("username", ua).or().eq("email", ua);
		return EntityTransform.trans(this.selectOne(wrapper), voClass);
	}

	@Override
	protected void getCondition(EntityWrapper<User> ew, Searchable searchable) {

		if (searchable instanceof UserSearchable) {

			UserSearchable search = (UserSearchable) searchable;

			if (StringUtils.isNotBlank(search.getKey())) {
				ew.like("username", search.getKey()).or().or().like("realname", search.getKey()).or().like("mobile",
						search.getKey());
			}
			if (StringUtils.isNotBlank(search.getUsername())) {
				ew.eq("username", search.getUsername());
			}
			if (StringUtils.isNotBlank(search.getStaffNo())) {
				ew.eq("staff_no", search.getStaffNo());
			}
			if (StringUtils.isNotBlank(search.getDepartmentCode())) {
				ew.eq("department_code", search.getDepartmentCode());
			}
			if (search.getDepartmentCodes() != null && search.getDepartmentCodes().length > 0) {
				ew.in("department_code", Arrays.asList(search.getDepartmentCodes()));
			}
			if (StringUtils.isNotBlank(search.getPositionCode())) {
				ew.eq("position_code", search.getPositionCode());
			}
			if (StringUtils.isNotBlank(search.getMobile())) {
				ew.eq("mobile", search.getMobile());
			}
			if (search.getSex() != null) {
				ew.eq("sex", search.getSex());
			}
		}

	}

	@Override
	public Set<String> selectUserRoles(String username) {
		return baseMapper.selectUserRoles(username);
	}

}
