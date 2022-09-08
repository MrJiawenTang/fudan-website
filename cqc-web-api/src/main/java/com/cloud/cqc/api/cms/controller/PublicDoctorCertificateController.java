package com.cloud.cqc.api.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.framework.mvc.http.RestEntity;
import com.cloud.cqc.service.certificate.entity.TumourDoctorCertificate;
import com.cloud.cqc.service.certificate.searchable.TumourDoctorCertificateSearchable;
import com.cloud.cqc.service.certificate.service.ITumourDoctorCertificateService;
import com.cloud.cqc.service.certificate.vo.TumourDoctorCertificateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MrTang
 * @Description
 * @createTime 2022年08月12日 15:42:00
 */
@Slf4j
@RestController
@RequestMapping("/certificate/certificateList")
public class PublicDoctorCertificateController
        extends CRUDController<TumourDoctorCertificate, TumourDoctorCertificateVO, TumourDoctorCertificateSearchable, ITumourDoctorCertificateService> {

    @Resource
    private ITumourDoctorCertificateService tumourDoctorCertificateService;

    //根据excel文件导入证书数据到系统
    @PostMapping(value = "upload/data/excel")
    public Object uploadByExcel(@RequestParam("file") MultipartFile file) {

        String result = tumourDoctorCertificateService.uploadCertificateInfoByExcel(file);

        RestEntity r;

        if ("success".equals(result)) {

            r = resultOk().appendMessage(result);
        } else {
            r = resultError().appendMessage(result);
        }

        log.info("根据excel文件导入培训证书数据到系统返回报文:{}", JSONObject.toJSONString(r));

        return r;
    }

    //下载证书录入excel模版
    @GetMapping(value = "/get/model")
    public void upModelExcelFile(HttpServletResponse response) {

        tumourDoctorCertificateService.upModelExcelFile(response);

    }

    /**
     * 获取单个证书下载信息
     */
    @PostMapping(value = "/one/certificate")
    public Object getSlideList(@RequestBody @Validated TumourDoctorCertificateSearchable searchable) {

        log.info("获取单个证书下载信息请求报文:{}", JSONObject.toJSONString(searchable));

        return resultOk(tumourDoctorCertificateService.findOneCertificate(searchable));

    }

    /**
     * 更新单个证书
     */
    @PutMapping(value = "/update")
    public Object getSlideList(@RequestBody TumourDoctorCertificate certificate) {

        log.info("更新单个证书请求报文:{}", JSONObject.toJSONString(certificate));

        tumourDoctorCertificateService.updateEntity(certificate);

        return resultOk();
    }

    /**
     * 新增单个证书
     */
    @PostMapping(value = "/add")
    public Object getSlideList(@RequestBody TumourDoctorCertificateVO vo) {

        log.info("新增单个证书请求报文:{}", JSONObject.toJSONString(vo));

        tumourDoctorCertificateService.addEntity(vo);

        return resultOk();
    }

    /**
     * 获取内容列表
     *
     * @param size
     * @param current
     * @param searchable
     * @return
     */
/*    @GetMapping(value = "/list/{current}_{size}")
    public Object list(@PathVariable Integer size, @PathVariable Integer current,
                       @ModelAttribute TumourDoctorCertificateSearchable searchable) {

        if (searchable == null) {
            searchable = new TumourDoctorCertificateSearchable();
        }

        log.info("获取证书内容列表searchable:{},size:{},current:{}", JSONObject.toJSONString(searchable), size, current);

        return resultOk(tumourDoctorCertificateService.selectPage(new Page<>(current, size), searchable));
    }*/

}
