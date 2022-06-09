package com.cloud.cqc.api.cms.controller;

import com.cloud.cqc.framework.core.dto.DateRangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.mvc.BaseRestController;
import com.cloud.cqc.service.cms.constant.CmsContentStatus;
import com.cloud.cqc.service.cms.entity.CmsContent;
import com.cloud.cqc.service.cms.searchable.CmsContentSearchable;
import com.cloud.cqc.service.cms.service.ICmsContentAttributeService;
import com.cloud.cqc.service.cms.service.ICmsContentService;

/**
 * @author joy.zhou
 * @version 1.0
 * @date 2017年12月18日
 */
@RestController
@RequestMapping("/api/content")
public class PublicCmsContentController extends BaseRestController {
    @Autowired
    private ICmsContentService cmsContentService;
    @Autowired
    private ICmsContentAttributeService cmsContentAttributeService;

    /**
     * 获取内容列表
     *
     * @param size
     * @param current
     * @param searchable
     * @return
     */
    @RequestMapping(value = "/list/{current}_{size}", method = RequestMethod.GET)
    public Object list(@PathVariable Integer size, @PathVariable Integer current,
                       @ModelAttribute CmsContentSearchable searchable) {

        if (searchable == null) {
            searchable = new CmsContentSearchable();
        }
        searchable.setStatus(CmsContentStatus.PUBLISHED.getValue());

        return resultOk(cmsContentService.selectPage(new Page<CmsContent>(current, size), searchable));
    }

    @RequestMapping(value = "/last/list/{current}_{size}", method = RequestMethod.GET)
    public Object lastList(@PathVariable Integer size, @PathVariable Integer current,
                           @ModelAttribute CmsContentSearchable searchable) {

        if (searchable == null) {
            searchable = new CmsContentSearchable();
        }
        DateRangeDto dateRangeDto = new DateRangeDto();

        dateRangeDto.setStart("2022-01-01");

        searchable.setCreateDate(dateRangeDto);

        searchable.setStatus(CmsContentStatus.PUBLISHED.getValue());

        return resultOk(cmsContentService.selectPage(new Page<CmsContent>(current, size), searchable));
    }

    /**
     * 获取内容属性详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getContent(@PathVariable Long id) {

        return resultOk(cmsContentService.select(id));
    }

    /**
     * 获取内容属性详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/attr", method = RequestMethod.GET)
    public Object getContentAttr(@PathVariable Long id) {

        return resultOk(cmsContentAttributeService.selectByContentId(id));
    }
}
