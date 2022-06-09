package com.cloud.cqc.framework.persistence.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.core.dto.PageDto;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import com.cloud.cqc.framework.persistence.entity.BasicSerializableEntity;

/**
 * 
 * Coustomer Base Service
 * 
 * @author joy.zhou
 * @date 2017年6月14日
 * @param <T>
 *            实体对象
 * @param <S>
 *            查询参数
 */
public interface IBaseService<T extends BasicEntity, V extends BasicSerializableEntity> {

	/**
	 * 插入一条记录（选择字段，策略插入）
	 * 
	 * @param vo
	 * @return
	 */
	boolean insert(V vo);

	/**
	 * 批量插入
	 * 
	 * @param list
	 * @return
	 */
	boolean insert(List<V> list);

	/**
	 * 批量插入
	 * 
	 * @param list
	 * @param batchSize
	 *            批次数(多少条数据刷新缓冲)
	 * @return
	 */
	boolean insert(List<V> list, int batchSize);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteById(Serializable id);

	/**
	 * 批量删除
	 * 
	 * @param idList
	 * @return
	 */
	boolean deleteBatchIds(List<? extends Serializable> idList);

	/**
	 * 更新
	 * 
	 * @param vo
	 * @return
	 */
	boolean update(V vo);

	/**
	 * 批量更新
	 * 
	 * @param list
	 * @return
	 */
	boolean update(List<V> list);

	/**
	 * 批量更新
	 * 
	 * @param list
	 * @param batchSize
	 *            批次数(多少条数据刷新缓冲)
	 * @return
	 */
	boolean update(List<V> list, int batchSize);

	/**
	 * 获取
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	V select(Serializable id);

	/**
	 * 获取列表
	 * 
	 * @param searchable
	 *            查询参数
	 * @return
	 */
	List<V> selectList(Searchable searchable);

	/**
	 * 获取对象
	 * 
	 * @param searchable
	 * @return
	 */
	V selectOne(Searchable searchable);

	/**
	 * 获取分页列表
	 * 
	 * @param page
	 *            分页参数
	 * @param searchable
	 *            查询参数
	 * @return
	 */
	PageDto<V> selectPage(Page<T> page, Searchable searchable);
}
