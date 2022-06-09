package com.cloud.cqc.client.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.cms.entity.CmsCategory;
import com.cloud.cqc.service.cms.searchable.CmsCategorySearchable;
import com.cloud.cqc.service.cms.service.ICmsCategoryService;
import com.cloud.cqc.service.cms.vo.CmsCategoryVO;

/**
 * <p>
 * CMS 分类管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/category")
public class CmsCategoryController
		extends CRUDController<CmsCategory, CmsCategoryVO, CmsCategorySearchable, ICmsCategoryService> {

}
