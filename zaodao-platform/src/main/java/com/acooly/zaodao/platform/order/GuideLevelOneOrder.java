package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游一级审核
 *
 * @author xiaohong
 * @create 2018-05-22 11:51
 **/
@Data
public class GuideLevelOneOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "导游资格证", constraint = "导游资格证")
    private String guideCertificateImg;
}
