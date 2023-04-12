package com.cloud.cqc.api.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.service.certificate.entity.TumourDoctorCertificate;
import com.cloud.cqc.service.certificate.searchable.TumourDoctorCertificateSearchable;
import com.cloud.cqc.service.certificate.service.ITumourDoctorCertificateService;
import com.cloud.cqc.service.certificate.vo.TumourDoctorCertificateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MrTang
 * @Description
 * @createTime 2023年04月12日 12:04:00
 */
@Slf4j
@RestController
@RequestMapping("/api/certificate")
public class PublicDoctorCertNewController extends
        CRUDController<TumourDoctorCertificate, TumourDoctorCertificateVO, TumourDoctorCertificateSearchable, ITumourDoctorCertificateService> {

    @Resource
    private ITumourDoctorCertificateService tumourDoctorCertificateService;

    /**
     * 获取单个证书下载信息
     */
    @PostMapping(value = "/one/certificate")
    public Object getSlideList(@RequestBody @Validated TumourDoctorCertificateSearchable searchable) {

        log.info("获取单个证书下载信息请求报文:{}", JSONObject.toJSONString(searchable));

        return resultOk(tumourDoctorCertificateService.findOneCertificate(searchable));

    }

}
