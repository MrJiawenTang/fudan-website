package com.cloud.cqc.api.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.mvc.BaseRestController;
import com.cloud.cqc.service.cms.entity.CmsCategory;
import com.cloud.cqc.service.cms.searchable.CmsCategorySearchable;
import com.cloud.cqc.service.cms.searchable.CmsFriendlyLinkSearchable;
import com.cloud.cqc.service.cms.searchable.CmsSlideSearchable;
import com.cloud.cqc.service.cms.service.ICmsCategoryService;
import com.cloud.cqc.service.cms.service.ICmsFriendlyLinkService;
import com.cloud.cqc.service.cms.service.ICmsSlideService;
import com.cloud.cqc.service.cms.vo.CmsCategoryVO;

/**
 * 
 * @author joy.zhou
 * @date 2017年12月18日
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class PublicCmsIndexController extends BaseRestController {

	@Autowired
	private ICmsCategoryService cmsCategoryService;
	@Autowired
	private ICmsSlideService cmsSlideService;
	@Autowired
	private ICmsFriendlyLinkService cmsFriendlyLinkService;

	/**
	 * 获取导航列表
	 * 
	 * @param size
	 *            数量
	 */
	@RequestMapping(value = "/navs", method = RequestMethod.GET)
	public Object getNavList(@RequestParam Integer size) {

		CmsCategorySearchable searchable = new CmsCategorySearchable();
		searchable.setDisabled(false);
		List<CmsCategoryVO> list = cmsCategoryService.selectPage(new Page<CmsCategory>(0, size), searchable)
				.getRecords();

		return resultOk(list);
	}

	/**
	 * 获取幻灯列表
	 */
	@RequestMapping(value = "/slides", method = RequestMethod.GET)
	public Object getSlideList() {
		return resultOk(cmsSlideService.selectList(new CmsSlideSearchable()));
	}

	/**
	 * 获取友情链接列表
	 * 
	 */
	@RequestMapping(value = "/friendLinks", method = RequestMethod.GET)
	public Object getFriendLinkList() {

		return resultOk(cmsFriendlyLinkService.selectList(new CmsFriendlyLinkSearchable()));
	}

}
