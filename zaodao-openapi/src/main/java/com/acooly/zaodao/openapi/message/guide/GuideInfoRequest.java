/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 18:13 创建
 *
 */
package com.acooly.zaodao.openapi.message.guide;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "guideInfo", type = ApiMessageType.Request)
public class GuideInfoRequest extends ApiRequest{
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID(是否关注该导游)", demo = "O00117052610240611600001")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID", demo = "O00117052610240611600000")
    private String guideUserId;

}
