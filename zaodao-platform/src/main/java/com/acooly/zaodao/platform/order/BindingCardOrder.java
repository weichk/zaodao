package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 绑卡
 *
 * @author xiaohong
 * @create 2017-11-23 13:42
 **/
@Data
public class BindingCardOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "真实姓名", constraint = "真实姓名", demo = "张三")
    private String realName;

    @NotBlank
    @OpenApiField(desc = "银行卡简称", constraint = "银行简称", demo = "ICBC")
    private String bankCode;

    @NotBlank
    @OpenApiField(desc = "银行卡卡号", constraint = "银行卡卡号", demo = "6227003760019988000")
    private String bankCardNo;

    @NotBlank
    @OpenApiField(desc = "银行预留手机号", constraint = "银行预留手机号", demo = "18696725337")
    private String mobileNo;

    @NotBlank
    @OpenApiField(desc = "身份证号", constraint = "身份证号", demo = "500221198709190011")
    private String idNumber;
}
