package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游一级审核
 *
 * @author xiaohong
 * @create 2018-05-22 11:45
 **/
@Data
@OpenApiMessage(service = "guideLevelOne", type = ApiMessageType.Request)
public class GuideLevelOneRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "导游资格证", constraint = "导游资格证")
    private String guideCertificateImg;
}
