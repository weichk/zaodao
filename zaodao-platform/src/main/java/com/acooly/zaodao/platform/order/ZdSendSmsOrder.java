package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.common.enums.SendSmsType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 发送短信
 *
 * @author xiaohong
 * @create 2017-11-23 11:18
 **/
@Data
public class ZdSendSmsOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "电话号码", constraint = "电话号码", demo = "18695765276")
    private String mobileNo;

    @NotNull
    @OpenApiField(desc = "短信类型", constraint = "发送短信类型", demo = "registerTemplet")
    private SendSmsType sendSmsType;

    @OpenApiField(desc = "提现金额", constraint = "提现金额")
    private Money amount;
}
