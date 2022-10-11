package com.cloud.cqc.service.certificate.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MrTang
 * @Description
 * @createTime 2022年08月11日 17:06:00
 */
@Data
public class TumourDoctorCertificateVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    //姓名
    private String name;

    //手机号
    private String mobilePhone;

    //身份证号
    private String idCard;

    //证书下载验证码
    private String verifyCode;

    //培训会开始-结束时间
    private String trainingTimeSpan;

    //培训会年度
    private String trainingDate;

    //证书编号
    private String certificateNumber;

    //有效期
    private String termSpan;

    //颁发时间
    private String issuedTime;

    private Integer deleted = 0;

    public String getTrainingDate() {

        if (StringUtils.isNotEmpty(this.trainingDate) && !this.trainingDate.contains("上海市抗肿瘤药物规范化使用培训会")) {

            return this.trainingDate + "上海市抗肿瘤药物规范化使用培训会";
        }
        return this.trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }
}
