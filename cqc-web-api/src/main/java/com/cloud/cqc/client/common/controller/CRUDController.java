package com.cloud.cqc.client.common.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.mvc.http.RestEntity;
import com.cloud.cqc.framework.mvc.model.BatchEntity;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import com.cloud.cqc.framework.persistence.entity.BasicSerializableEntity;
import com.cloud.cqc.framework.persistence.service.IBaseService;

/**
 * <p>
 * 增/删/改/查controller
 * </p>
 * 
 * @author Joy.zhou
 *
 */
public class CRUDController<T extends BasicEntity, V extends BasicSerializableEntity, SE extends Searchable, Service extends IBaseService<T, V>>
		extends SecurityController {

	@Autowired
	protected Service baseService;

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public RestEntity<?> save(@Valid @RequestBody V vo) {
		baseService.insert(vo);
		return resultOk(vo);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestEntity<?> remove(@PathVariable Long id) {
		baseService.deleteById(id);
		return resultOk();
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/removeBatch", method = RequestMethod.POST)
	public RestEntity<?> remove(@RequestBody BatchEntity batch) {
		baseService.deleteBatchIds(batch.getIds());
		return resultOk();
	}

	/**
	 * 修改
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RestEntity<?> update(@PathVariable Long id, @RequestBody V vo) {
		vo.setId(id);
		baseService.update(vo);
		return resultOk();
	}

	/**
	 * 查询
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestEntity<?> get(@PathVariable Long id) {
		return resultOk(baseService.select(id));
	}

	/**
	 * 查询
	 * 
	 * @param searchable
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public RestEntity<?> get(@ModelAttribute SE searchable) {
		return resultOk(baseService.selectOne(searchable));
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RestEntity<?> listPost(@RequestBody SE searchable) {
		return resultOk(this.baseService.selectList(searchable));
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public RestEntity<?> listGet(@ModelAttribute SE searchable) {
		return resultOk(this.baseService.selectList(searchable));
	}

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list/{size}_{current}", method = RequestMethod.POST)
	public RestEntity<?> page(@PathVariable Integer size, @PathVariable Integer current,
			@RequestBody(required = false) SE s) {
		return resultOk(baseService.selectPage(new Page<T>(current, size), s));
	}

}
