package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * App关键字
 *
 * @author xiaohong
 * @create 2018-06-14 11:06
 **/
@Getter
@Setter
public class ApplicationKeyDto implements Serializable {

    @OpenApiField(desc = "隐藏关键字", constraint = "隐藏关键字", demo = "{true,false}")
    private Boolean isHide;

    @OpenApiField(desc = "充值", constraint = "充值")
    private String key0;

    @OpenApiField(desc = "提现", constraint = "提现")
    private String key1;

    @OpenApiField(desc = "交易", constraint = "交易")
    private String key2;

    @OpenApiField(desc = "钙片交易", constraint = "钙片交易")
    private String key3;

    @OpenApiField(desc = "打赏", constraint = "打赏")
    private String key4;

    @OpenApiField(desc = "兑换", constraint = "兑换")
    private String key5;

    @OpenApiField(desc = "绑卡", constraint = "绑卡")
    private String key6;

    @OpenApiField(desc = "我的银行卡", constraint = "我的银行卡")
    private String key7;

    @OpenApiField(desc = "账户余额", constraint = "账户余额")
    private String key8;

    @OpenApiField(desc = "可用积分", constraint = "可用积分")
    private String key9;
}
