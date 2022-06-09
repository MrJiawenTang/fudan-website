package com.cloud.cqc.client.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.cms.entity.CmsSlide;
import com.cloud.cqc.service.cms.searchable.CmsSlideSearchable;
import com.cloud.cqc.service.cms.service.ICmsSlideService;
import com.cloud.cqc.service.cms.vo.CmsSlideVO;

/**
 * <p>
 * CMS 幻灯片管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/slide")
public class CmsSlideController extends CRUDController<CmsSlide, CmsSlideVO, CmsSlideSearchable, ICmsSlideService> {

}
