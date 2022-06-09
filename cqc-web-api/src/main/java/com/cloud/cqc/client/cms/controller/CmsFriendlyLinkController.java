package com.cloud.cqc.client.cms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.cms.entity.CmsFriendlyLink;
import com.cloud.cqc.service.cms.searchable.CmsFriendlyLinkSearchable;
import com.cloud.cqc.service.cms.service.ICmsFriendlyLinkService;
import com.cloud.cqc.service.cms.vo.CmsFriendlyLinkVO;

/**
 * <p>
 * CMS 友情链接管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/friendlyLink")
public class CmsFriendlyLinkController
		extends CRUDController<CmsFriendlyLink, CmsFriendlyLinkVO, CmsFriendlyLinkSearchable, ICmsFriendlyLinkService> {

}
