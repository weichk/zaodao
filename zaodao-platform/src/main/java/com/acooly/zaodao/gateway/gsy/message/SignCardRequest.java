/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:38 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.enums.CardTypeEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.PublicTagEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.Purpose;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class SignCardRequest extends RequestBase{
    /** 短信验证码 */
    @NotNull(message = "短信验证码不能为空")
    @OpenApiField(desc = "验证码", constraint = "验证码", demo = "123456")
    private String captcha;

    /** 用户ID */
    @Length(max = 64)
    @NotEmpty(message = "用户ID不能为空")
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "2016070809542815088")
    private String userId;

    /** 手机号码 */
    @Length(max = 32)
    @NotEmpty(message = "手机号码不能为空")
    @OpenApiField(desc = "手机号码", constraint = "手机号码", demo = "18052365874")
    private String mobile;

    /** 银行卡号 */
    @Length(max = 64)
    @NotEmpty(message = "卡号不能为空")
    @OpenApiField(desc = "银行卡号", constraint = "银行卡号", demo = "6251522358963215")
    private String bankCardNo;

    /** 绑卡用途 */
    @NotNull(message = "绑卡用途不能为空")
    @OpenApiField(desc = "绑卡用途", constraint = "绑卡用途", demo = "WITHDRAW")
    private Purpose purpose;

    /** 银行卡账户类型 */
    @NotNull(message = "银行卡账户类型不能为空")
    @OpenApiField(desc = "银行卡账户类型", constraint = "银行卡账户类型", demo = "N")
    private PublicTagEnum publicTag;

    /** 银行简称 */
    @Length(max = 16)
    @OpenApiField(desc = "银行简称", constraint = "银行简称,对公卡银行简称不能为空", demo = "ICBC")
    private String bankCode;

    /** 银行名称 */
    @Length(max = 32)
    @OpenApiField(desc = "银行名称", constraint = "银行名称,对公卡银行名称不能为空", demo = "工商银行")
    private String bankName;

    /** 开户省 */
    @Length(max = 32)
    @OpenApiField(desc = "开户省", constraint = "开户省,对公卡开户省不能为空", demo = "重庆")
    private String province;

    /** 开户市 */
    @Length(max = 32)
    @OpenApiField(desc = "开户市", constraint = "开户市,对公卡开户市不能为空", demo = "重庆")
    private String city;

    /** 卡种 */
    @OpenApiField(desc = "卡种", constraint = "卡种,默认为借记卡", demo = "DEBIT_CARD")
    private CardTypeEnum bankCardType= CardTypeEnum.DEBIT;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 银行开户证件号
     */
    private String certNo;
}
