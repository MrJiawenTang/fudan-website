package com.cloud.cqc.framework.persistence.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.cqc.framework.core.dto.DateRangeDto;
import com.cloud.cqc.framework.core.dto.PageDto;
import com.cloud.cqc.framework.core.utils.ConvertUtils;
import com.cloud.cqc.framework.core.utils.EntityTransform;
import com.cloud.cqc.framework.persistence.dto.Ordered;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import com.cloud.cqc.framework.persistence.entity.BasicSerializableEntity;
import com.cloud.cqc.framework.persistence.service.IBaseService;

/**
 *
 * Coustomers Base Service Impl
 *
 * @author joy.zhou
 * @date 2017年6月14日
 * @param <M>
 * @param <T>
 * @param <V>
 * @param <SE>
 */
@CacheConfig(cacheNames = "gobal")
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BasicEntity, V extends BasicSerializableEntity>
		extends ServiceImpl<M, T> implements IBaseService<T, V> {

	/**
	 * entity class
	 */
	protected Class<T> entityClass;
	/**
	 * vo class
	 */
	protected Class<V> voClass;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType genType = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] params = genType.getActualTypeArguments();
		this.entityClass = (Class<T>) params[1];
		this.voClass = (Class<V>) params[2];
	}

	@Override
	public boolean insert(V vo) {
		T entity = EntityTransform.trans(vo, entityClass);
		boolean flag = this.insert(entity);
		vo.setId(entity.getId());
		return flag;
	}

	@Override
	public boolean insert(List<V> list) {
		return this.insertBatch(EntityTransform.trans(list, entityClass));
	}

	@Override
	public boolean insert(List<V> list, int batchSize) {
		return this.insertBatch(EntityTransform.trans(list, entityClass), batchSize);
	}

	@Override
	public boolean update(V vo) {
		return this.updateById(EntityTransform.trans(vo, entityClass));
	}

	@Override
	public boolean update(List<V> list) {
		return this.updateBatchById(EntityTransform.trans(list, entityClass));
	}

	@Override
	public boolean update(List<V> list, int batchSize) {
		return this.updateBatchById(EntityTransform.trans(list, entityClass), batchSize);
	}

	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		return super.deleteByMap(columnMap);
	}

	@Override
	public boolean delete(Wrapper<T> wrapper) {
		return super.delete(wrapper);
	}

	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		return super.deleteBatchIds(idList);
	}

	@Override
	public List<V> selectList(Searchable searchable) {
		return EntityTransform.trans(this.selectList(getWrapperCondition(searchable)), voClass);
	}

	@Override
	@Cacheable
	public V select(Serializable id) {
		return EntityTransform.trans(this.selectById(id), voClass);
	}

	@Override
	public V selectOne(Searchable searchable) {
		return EntityTransform.trans(this.selectOne(getWrapperCondition(searchable)), voClass);
	}

	@Override
	public PageDto<V> selectPage(Page<T> page, Searchable searchable) {
		return EntityTransform.transPager(this.selectPage(page, getWrapperCondition(searchable)), voClass);
	}

	private EntityWrapper<T> getWrapperCondition(Searchable searchable) {
		if (searchable == null) {
			searchable = new Searchable();
		}
		EntityWrapper<T> ew = this.getDefaultCondition(searchable);
		this.getCondition(ew, searchable);
		if (StringUtils.isNotBlank(searchable.getKey())) {
			this.getSearchKey(ew, searchable);
		}
		if (searchable.getOrdereds() != null) {
			for (Ordered ordered : searchable.getOrdereds()) {
				ew.orderBy(ConvertUtils.humpToLine(ordered.getFieldName()), Ordered.ASC.equals(ordered.getSortType()));
			}
		}
		return ew;
	}

	private EntityWrapper<T> getDefaultCondition(Searchable searchable) {
		EntityWrapper<T> ew = new EntityWrapper<>();
		if (searchable == null) {
			return ew;
		}
		DateRangeDto range = searchable.getCreateDate();
		if (range != null) {
			if (StringUtils.isNoneBlank(range.getStart())) {
				ew.ge("create_time", range.getStart());
			}
			if (StringUtils.isNoneBlank(range.getEnd())) {
				ew.le("create_time", range.getEnd());
			}
		}
		return ew;
	}

	/**
	 * 关键字查询
	 *
	 * @param ew
	 * @param searchable
	 */
	protected void getSearchKey(EntityWrapper<T> ew, Searchable searchable) {
	}

	/**
	 * to override the search condition
	 *
	 * @param searchable
	 * @return
	 */
	protected void getCondition(EntityWrapper<T> ew, Searchable searchable) {

	}

	/**
	 * to override the search order by
	 *
	 * @param searchable
	 * @return
	 */
	protected void getOrderBy(EntityWrapper<T> ew, Searchable searchable) {
	}

}
