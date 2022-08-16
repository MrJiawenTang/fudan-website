package com.cloud.cqc.service.certificate.searchable;

import com.cloud.cqc.framework.persistence.dto.DefaultSortSearchable;
import com.cloud.cqc.util.AESUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

public class TumourDoctorCertificateSearchable extends DefaultSortSearchable {

    private static final long serialVersionUID = 1L;
    /**
     * 是否禁用
     */
    private Boolean disabled;

    @NotBlank(message = "姓名不允许为空")
    private String name;

    private String mobilePhone;

    @NotBlank(message = "身份证号码不允许为空")
    private String idCard;

    //证书下载验证码
    @NotBlank(message = "证书下载验证码不允许为空")
    private String verifyCode;

    private Integer deleted;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 删除标记
     */
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        if (StringUtils.isNotEmpty(this.name)) {
            return AESUtil.AESEncode(this.name);
        }
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        if (StringUtils.isNotEmpty(this.mobilePhone)) {
            return AESUtil.AESEncode(this.mobilePhone);
        }
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdCard() {
        if (StringUtils.isNotEmpty(this.idCard)) {
            return AESUtil.AESEncode(this.idCard);
        }
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
