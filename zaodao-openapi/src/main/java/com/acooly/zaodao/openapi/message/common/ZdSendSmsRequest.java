/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:47 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.common.enums.SendSmsType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "zdSendSms", type = ApiMessageType.Request)
public class ZdSendSmsRequest extends ApiRequest{
    @NotEmpty(message = "电话号码不能为空")
    @OpenApiField(desc = "电话号码", constraint = "电话号码", demo = "18695765276")
    private String mobileNo;

    @NotNull
    @OpenApiField(desc = "短信类型", constraint = "发送短信类型", demo = "registerTemplet")
    private SendSmsType sendSmsType;

    @OpenApiField(desc = "提现金额(提现使用)", constraint = "提现金额")
    private Money amount;
}
