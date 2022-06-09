package com.cloud.cqc.client.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.service.admin.entity.Resource;
import com.cloud.cqc.service.admin.searchable.ResoueceSearchable;
import com.cloud.cqc.service.admin.service.IResourceService;
import com.cloud.cqc.service.admin.vo.ResourceVO;
import com.cloud.cqc.client.common.controller.CRUDController;

/**
 * <p>
 * 资源管理controller
 * </p>
 * 
 * @author Joy.zhou
 *
 */
@RestController
@RequestMapping("/admin/resource")
public class ResourceController extends CRUDController<Resource, ResourceVO, ResoueceSearchable, IResourceService> {

}
