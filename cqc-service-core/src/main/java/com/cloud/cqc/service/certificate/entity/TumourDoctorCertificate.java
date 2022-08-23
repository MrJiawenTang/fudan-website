package com.cloud.cqc.service.certificate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cloud.cqc.framework.persistence.entity.BasicEntity;
import com.cloud.cqc.util.AESUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MrTang
 * @Description
 * @createTime 2022年08月11日 16:27:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tumour_doctor_certificate")
public class TumourDoctorCertificate extends BasicEntity {

    private static final long serialVersionUID = 1L;

    //姓名
    @TableField(exist = false)
    private String name;

    @JsonIgnore
    private String nameAes;

    //手机号
    @TableField(exist = false)
    private String mobilePhone;

    @JsonIgnore
    private String mobilePhoneAes;

    //身份证号
    @TableField(exist = false)
    private String idCard;

    @JsonIgnore
    private String idCardAes;

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

    public String getName() {
        if (StringUtils.isNotBlank(this.nameAes)) {
            return AESUtil.AESDecode(this.nameAes);
        }
        return null;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.setNameAes(AESUtil.AESEncode(name));
        }
        this.name = name;
    }


    public String getMobilePhone() {
        if (StringUtils.isNotBlank(this.mobilePhoneAes)) {
            return AESUtil.AESDecode(this.mobilePhoneAes);
        }
        return null;
    }

    public void setMobilePhone(String mobilePhone) {
        if (StringUtils.isNotBlank(mobilePhone)) {
            this.mobilePhoneAes = AESUtil.AESEncode(mobilePhone);
        }
        this.mobilePhone = mobilePhone;
    }

    public String getIdCard() {
        if (StringUtils.isNotBlank(this.idCardAes)) {
            return AESUtil.AESDecode(this.idCardAes);
        }
        return null;
    }

    public void setIdCard(String idCard) {
        if (StringUtils.isNotBlank(idCard)) {
            this.idCardAes = AESUtil.AESEncode(idCard);
        }
        this.idCard = idCard;
    }

    public String getNameAes() {
        return nameAes;
    }

    public void setNameAes(String nameAes) {
        this.nameAes = nameAes;
    }

    public String getMobilePhoneAes() {
        return mobilePhoneAes;
    }

    public void setMobilePhoneAes(String mobilePhoneAes) {
        this.mobilePhoneAes = mobilePhoneAes;
    }

    public String getIdCardAes() {
        return idCardAes;
    }

    public void setIdCardAes(String idCardAes) {
        this.idCardAes = idCardAes;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getTrainingTimeSpan() {
        return trainingTimeSpan;
    }

    public void setTrainingTimeSpan(String trainingTimeSpan) {
        this.trainingTimeSpan = trainingTimeSpan;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getTermSpan() {
        return termSpan;
    }

    public void setTermSpan(String termSpan) {
        this.termSpan = termSpan;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }
}
