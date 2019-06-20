package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 银行卡列表
 *
 * @author xiaohong
 * @create 2017-11-24 19:07
 **/
@Data
@OpenApiMessage(service = "bankCardList", type = ApiMessageType.Request)
public class BankCardListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;
}
