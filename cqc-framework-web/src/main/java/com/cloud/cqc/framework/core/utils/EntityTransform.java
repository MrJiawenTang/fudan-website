package com.cloud.cqc.framework.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.core.dto.PageDto;

/**
 * 
 * 公共数据处理器
 *
 * @author joy.zhou
 * @date 2014年12月29日
 * @version 1.0
 *
 */
public class EntityTransform {

	/**
	 * 实体转换
	 * 
	 * @param source
	 *            源对象
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T, K> K trans(T source, Class<K> clazz) {

		if (source == null) {
			return null;
		}

		K result = null;
		try {
			result = clazz.newInstance();
			BeanUtils.copyProperties(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 实体转换
	 * 
	 * @param sources
	 *            源对象
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T, K> List<K> trans(List<T> sources, Class<K> clazz) {

		if (sources == null) {
			return new ArrayList<>();
		}

		return sources.stream().map(item -> trans(item, clazz)).collect(Collectors.toList());

	}

	/**
	 * 实体转换
	 * 
	 * @param sources
	 *            源对象
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	public static <T, K> Set<K> trans(Set<T> sources, Class<K> clazz) {

		if (sources == null) {
			return new HashSet<>();
		}

		return sources.stream().map(item -> trans(item, clazz)).collect(Collectors.toSet());

	}

	/**
	 * 
	 * 实体转为map
	 * 
	 * @param source
	 *            实体对象
	 * @return
	 */
	public static <T> Map<String, Object> transMap(final T source) {

		final Map<String, Object> map = new HashMap<String, Object>();

		ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

				ReflectionUtils.makeAccessible(field);
				map.put(field.getName(), field.get(source));

			}
		}, ReflectionUtils.COPYABLE_FIELDS);

		return map;
	}

	/**
	 * 转为MultiValueMap
	 * 
	 * @param source
	 *            实体对象
	 * @return
	 */
	public static <T> MultiValueMap<String, String> transMutilMapIfNotNll(final T source) {

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				Object value = field.get(source);
				if (value != null) {
					map.add(field.getName(), value.toString());
				}
			}
		}, ReflectionUtils.COPYABLE_FIELDS);

		return map;

	}

	/**
	 * 转换api pager
	 * 
	 * @param pager
	 * @return
	 */
	public static <T> PageDto<T> transPager(Page<T> pager) {

		PageDto<T> target = new PageDto<T>();
		if (pager == null) {
			return target;
		}
		target.setTotal(pager.getTotal());
		target.setSize(pager.getSize());
		target.setRecords(pager.getRecords());
		return target;
	}

	/**
	 * 转换api pager
	 * 
	 * @param pager
	 * @param cls
	 * @return
	 */
	public static <T, K> PageDto<K> transPager(Page<T> pager, Class<K> cls) {

		PageDto<K> target = new PageDto<K>();
		if (pager == null) {
			return target;
		}
		target.setTotal(pager.getTotal());
		target.setSize(pager.getSize());
		target.setRecords(trans(pager.getRecords(), cls));
		return target;
	}

	/**
	 * 
	 * 求出需要删除与新增的数据
	 * 
	 * (使用contains方法比较实体,按需求重写equals方法)
	 * 
	 * @param source
	 *            源数据列表
	 * @param target
	 *            目标数据列表
	 * @return
	 */
	public static <T> MergeDto<T> merge(final Collection<T> sources, final Collection<T> targets) {

		MergeDto<T> dto = new MergeDto<T>();

		if (sources == null && targets == null) {
			return dto;
		}

		if (sources == null && targets != null) {
			dto.getSaveData().addAll(targets);
			return dto;
		}

		if (sources != null && targets == null) {
			dto.getDeleteData().addAll(sources);
			return dto;
		}

		// 需要删除的数据
		for (T source : sources) {
			if (!targets.contains(source)) {
				dto.getDeleteData().add(source);
			}
		}
		// 需要新增的时候
		for (T target : targets) {
			if (!sources.contains(target)) {
				dto.getSaveData().add(target);
			}
		}

		return dto;
	}

	public static class MergeDto<T> {

		/** 需要保存的数据 */
		private Collection<T> saveData = new ArrayList<T>(0);
		/** 需要删除的数据 */
		private Collection<T> deleteData = new ArrayList<T>(0);

		public Collection<T> getSaveData() {
			return saveData;
		}

		public void setSaveData(Collection<T> saveData) {
			this.saveData = saveData;
		}

		public Collection<T> getDeleteData() {
			return deleteData;
		}

		public void setDeleteData(Collection<T> deleteData) {
			this.deleteData = deleteData;
		}
	}

}
