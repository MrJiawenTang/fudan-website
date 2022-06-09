package com.cloud.cqc.client.cms.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.cms.entity.CmsContentAttribute;
import com.cloud.cqc.service.cms.searchable.CmsContentAttrSearchable;
import com.cloud.cqc.service.cms.service.ICmsContentAttributeService;
import com.cloud.cqc.service.cms.vo.CmsContentAttributeVO;

/**
 * <p>
 * CMS 内容扩展管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/content/attr")
public class CmsContentAttributeController extends
		CRUDController<CmsContentAttribute, CmsContentAttributeVO, CmsContentAttrSearchable, ICmsContentAttributeService> {

	/**
	 * 获取文章内
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/content/{id}")
	public Object getByContent(@PathVariable Long id) {
		return resultOk(this.baseService.selectByContentId(id));
	}

}
