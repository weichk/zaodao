package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.BusinessType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 会员企业实名信息
 */
@Getter
@Setter
public class CustomerEnterpriseDto implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3115425517706324279L;

    @OpenApiField(desc = "企业类型", constraint = "企业类型")
    private BusinessType businessType;

    @OpenApiField(desc = "企业名称", constraint = "企业名称")
    private String comName;

    @OpenApiField(desc = "营业执照编号", constraint = "营业执照编号")
    private String licenceNo;

    @OpenApiField(desc = "营业执照图片", constraint = "营业执照图片")
    private String licence;

    @OpenApiField(desc = "法人姓名", constraint = "法人姓名")
    private String legalPersonName;

    @OpenApiField(desc = "法人证件类型", constraint = "法人证件类型")
    private String legalPersonCertType;

    @OpenApiField(desc = "法人证件号码", constraint = "法人证件号码")
    private String legalPersonCertNo;

    @OpenApiField(desc = "常用地址", constraint = "常用地址")
    private String address;

    @OpenApiField(desc = "联系电话", constraint = "联系电话")
    private String phone;

}
