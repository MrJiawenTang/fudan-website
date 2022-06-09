package com.cloud.cqc.client.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.cms.entity.CmsSiteParts;
import com.cloud.cqc.service.cms.searchable.CmsSitePartsSearchable;
import com.cloud.cqc.service.cms.service.ICmsSitePartsService;
import com.cloud.cqc.service.cms.vo.CmsSitePartsVO;

/**
 * <p>
 * CMS 网站部件管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/siteParts")
public class CmsSitePartsController
		extends CRUDController<CmsSiteParts, CmsSitePartsVO, CmsSitePartsSearchable, ICmsSitePartsService> {

}
