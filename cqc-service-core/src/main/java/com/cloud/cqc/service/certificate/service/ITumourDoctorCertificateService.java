package com.cloud.cqc.service.certificate.service;

import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.certificate.entity.TumourDoctorCertificate;
import com.cloud.cqc.service.certificate.searchable.TumourDoctorCertificateSearchable;
import com.cloud.cqc.service.certificate.vo.TumourDoctorCertificateVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author MrTang
 * @Description
 * @createTime 2022年08月11日 17:03:00
 */
public interface ITumourDoctorCertificateService extends IBaseService<TumourDoctorCertificate, TumourDoctorCertificateVO> {

    String uploadCertificateInfoByExcel(MultipartFile file);

    TumourDoctorCertificateVO findOneCertificate(TumourDoctorCertificateSearchable searchable);

    void upModelExcelFile(HttpServletResponse response);

    void updateEntity(TumourDoctorCertificate tumourDoctorCertificate);
}
