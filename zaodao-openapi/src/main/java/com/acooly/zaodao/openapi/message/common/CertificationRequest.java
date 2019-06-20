/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 18:08 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "certification", type = ApiMessageType.Request)
public class CertificationRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "真实姓名", constraint = "真实姓名", demo = "张三")
    private String realName;

    @NotBlank
    @OpenApiField(desc = "身份证号", constraint = "身份证号", demo = "50022196519172312")
    private String idNo;

    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;
}
