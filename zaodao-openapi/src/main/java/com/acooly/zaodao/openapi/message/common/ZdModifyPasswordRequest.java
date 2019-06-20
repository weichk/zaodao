/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 11:01 创建
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
@OpenApiMessage(service = "zdModifyPassword", type = ApiMessageType.Request)
@Data
public class ZdModifyPasswordRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "原密码", constraint = "原密码", demo = "12345678")
    private String oldPassword;

    @NotBlank
    @OpenApiField(desc = "新密码", constraint = "新密码", demo = "12345678")
    private String newPassword;

    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;
}
