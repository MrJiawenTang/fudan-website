package com.cloud.cqc.service.certificate.service.impl;

import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.cqc.exception.TumourException;
import com.cloud.cqc.framework.persistence.dto.Searchable;
import com.cloud.cqc.framework.persistence.service.impl.BaseServiceImpl;
import com.cloud.cqc.service.certificate.entity.TumourDoctorCertificate;
import com.cloud.cqc.service.certificate.mapper.TumourDoctorCertificateMapper;
import com.cloud.cqc.service.certificate.searchable.TumourDoctorCertificateSearchable;
import com.cloud.cqc.service.certificate.service.ITumourDoctorCertificateService;
import com.cloud.cqc.service.certificate.vo.TumourDoctorCertificateVO;
import com.cloud.cqc.util.AESUtil;
import com.cloud.cqc.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author MrTang
 * @Description
 * @createTime 2022年08月11日 17:04:00
 */
@Slf4j
@Service
public class TumourDoctorCertificateServiceImpl extends BaseServiceImpl<TumourDoctorCertificateMapper, TumourDoctorCertificate, TumourDoctorCertificateVO>
        implements ITumourDoctorCertificateService {

    @Resource
    private TumourDoctorCertificateMapper tumourDoctorCertificateMapper;

    @Override
    protected void getCondition(EntityWrapper<TumourDoctorCertificate> ew, Searchable searchable) {

        if (searchable instanceof TumourDoctorCertificateSearchable) {

            TumourDoctorCertificateSearchable search = (TumourDoctorCertificateSearchable) searchable;

            if (StringUtils.isNotBlank(search.getName())) {
                ew.eq("name_aes", search.getName());
            }

            if (StringUtils.isNotBlank(search.getIdCard())) {
                ew.eq("id_card_aes", search.getIdCard());
            }

            if (StringUtils.isNotBlank(search.getMobilePhone())) {
                ew.eq("mobile_phone_aes", search.getMobilePhone());
            }

            if (StringUtils.isNotBlank(search.getVerifyCode())) {

                ew.eq("verify_code", search.getVerifyCode());
            }

            if (search.getDeleted() != null) {
                ew.eq("deleted", search.getDeleted());
            }

            if (search.getStartTime() != null) {

                ew.ge("create_time", search.getStartTime());
            }

            if (search.getEndTime() != null) {

                ew.le("create_time", search.getEndTime());
            }
        }
    }

    @Override
    public TumourDoctorCertificateVO findOneCertificate(TumourDoctorCertificateSearchable searchable) {

/*        EntityWrapper<TumourDoctorCertificate> ew = new EntityWrapper<>();

        ew.eq("deleted", 0);

        ew.eq("id_card_aes", AESUtil.AESEncode(searchable.getIdCard()));

        ew.eq("mobile_phone_aes", AESUtil.AESEncode(searchable.getMobilePhone()));*/

        return super.selectOne(searchable);

    }

    @Override
    public void upModelExcelFile(HttpServletResponse response) {

        log.info("下载培训证书模版文件");

        try {
            String userDir = System.getProperties().getProperty("user.dir");

            userDir = userDir.substring(0, userDir.length() - 19);

            log.info("userDir:{}", userDir);

            File file = new File(userDir + "/cqc-service-core/src/main/resources/培训信息录入模板.xlsx");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("培训信息录入模板.xlsx", "UTF-8"));
            FileInputStream fileInputStream = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            OutputStream outputStream = response.getOutputStream();
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            log.error("下载培训证书模版文件Error:{}", e.getMessage());
        }
    }

    @Override
    public void updateEntity(TumourDoctorCertificate tumourDoctorCertificate) {

        if (tumourDoctorCertificate.getId() == null) {

            TumourException.fail("ID不允许为空");

        }

        TumourDoctorCertificate findOne = tumourDoctorCertificateMapper.selectById(tumourDoctorCertificate.getId());

        if (findOne == null) {

            throw new RuntimeException("没有找到此ID的证书,Id:{}" + tumourDoctorCertificate.getId());
        }

        super.insertOrUpdate(tumourDoctorCertificate);
    }

    @Override
    public void addEntity(TumourDoctorCertificateVO tumourDoctorCertificateVO) {

        super.insert(tumourDoctorCertificateVO);
    }

    /**
     * @author MrTang
     * @time 2022-08-11 17:42
     * @description 根据excel文件导入医生考核信息
     */
    @Override
    public String uploadCertificateInfoByExcel(MultipartFile file) {

        String originName = file.getOriginalFilename();

        if (!StringUtils.containsIgnoreCase(originName, "xlsx")) {

            TumourException.fail("文件类型不符合规定,需要xlsx文件");
        }

        //从第二行开始读
        Sheet sheet = new Sheet(1, 1);

        List<Object> objects = ExcelUtil.readLessThan1000RowBySheetMultipartFile(file, sheet);

        log.info("objects:{}", JSONObject.toJSONString(objects));

        StringBuilder errSbr = new StringBuilder("导入失败的医生，可能这些医生之前已经导入过:");

        long errorNumber = 0L;

        for (Object ob : objects) {

            String line = ob.toString();

            line = line.replaceAll("[\\[\\]]", "");

            String[] arr = line.split(",");

            if (StringUtils.contains(arr[0], "null")
                    || StringUtils.contains(arr[1], "null")
                    || StringUtils.contains(arr[2], "null")) {
                log.info("该行数据为空:{}", JSONObject.toJSONString(arr));
                continue;
            }

            if (arr.length == 10) {

                EntityWrapper<TumourDoctorCertificate> ew = new EntityWrapper<>();

                ew.eq("deleted", 0);

                //首先判断当前登记的医生信息是否已经存在于库里
                ew.eq("id_card_aes", AESUtil.AESEncode(trimByStr(arr[2])));

                if (super.selectCount(ew) > 0) {

                    errSbr.append(trimByStr(arr[0])).append("|").append(trimByStr(arr[2])).append("&");

                    errorNumber++;

                    continue;
                }

                //姓名 手机号 身份证号 培训会开始结束时间 培训会年度 证书编号 有效期 颁发时间 验证码 所属医院
                TumourDoctorCertificate doctorCertificate = TumourDoctorCertificate.builder()
                        .trainingTimeSpan(trimByStr(arr[3]))
                        .trainingDate(trimByStr(arr[4]))
                        .certificateNumber(trimByStr(arr[5]))
                        .termSpan(trimByStr(arr[6]))
                        .issuedTime(trimByStr(arr[7]))
                        .verifyCode(trimByStr(arr[8]))
                        .hospital(trimByStr(arr[9]))
                        .build();

                //随机生成五位英文加数字的验证码
                //String code = RandomStringUtils.randomAlphanumeric(5);

                //doctorCertificate.setVerifyCode(code);

                doctorCertificate.setName(trimByStr(arr[0]));

                doctorCertificate.setMobilePhone(trimByStr(arr[1]));

                doctorCertificate.setIdCard(trimByStr(arr[2]));

                doctorCertificate.setDeleted(0);

                try {

                    tumourDoctorCertificateMapper.insert(doctorCertificate);

                    log.info("医生考核信息导入成功:{}", JSONObject.toJSONString(doctorCertificate));

                } catch (Exception e) {

                    log.info("医生考核信息导入失败:{},err:{}", JSONObject.toJSONString(doctorCertificate), e.getMessage());

                }
            } else {

                log.info("长度不足8,arrLength:{},或者为空数据行", arr.length);
            }
        }

        if (errorNumber == 0) {
            return "success";
        }

        return errSbr.toString();
    }

    //去除空格
    private String trimByStr(String str) {
        return str.trim();
    }
}
