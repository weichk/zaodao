package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 评价列表
 *
 * @author xiaohong
 * @create 2017-12-12 9:33
 **/
@Data
@OpenApiMessage(service = "platOrderEvalList", type = ApiMessageType.Request)
public class PlatOrderEvalListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "导游用户ID", constraint = "导游用户ID", demo = "O00117052610240611600000")
    private String userId;
}
