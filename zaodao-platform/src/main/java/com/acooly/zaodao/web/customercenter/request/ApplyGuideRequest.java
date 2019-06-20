/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 00:12 创建
 *
 */
package com.acooly.zaodao.web.customercenter.request;

import com.acooly.zaodao.common.RequestInfoBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ApplyGuideRequest extends RequestInfoBase implements Serializable {
    private Integer sex;

    private Integer age;

    @NotBlank
    private String guideNo;

    @NotBlank
    private String guideCertificateNo;

    private String[] language;

    private String city;

    private Integer workAge;

    private String familiarLine;

    private String introduce;

    private String speciality;

    private Long pricePerDay;

    private String realName;

    @NotBlank(message = "请上传全国导游之家app的电子导游证")
    private String guideCertificateImg;
}
