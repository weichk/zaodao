/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 22:24 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public enum GsyServiceEnum implements Messageable {
    orderCreate("orderCreate", "创建支付订单"),
    aliScanQRCodePay("aliScanQRCodePay", "支付宝支付(主扫)"),
    wechatScanQRCodePay("wechatScanQRCodePay", "微信支付(主扫)"),
    sendCaptchaSms("sendCaptchaSms", "发送验证码短信"),
    withdraw("withdraw", "提现"),
    customerQuery("customerQuery", "会员信息查询"),
    customerRegister("customerRegister", "会员注册"),
    signCard("bankCardBind", "实名绑卡"),
    unSignCard("bankCardUnBind", "银行卡解绑"),
    orderProfit("orderProfit", "交易清分"),
    balancePay("balancePay", "余额支付"),
    withdrawCard("withdrawCard", "提现到卡"),
    fundQuery("fundQuery", "查询充提订单(单笔)")
    ;

    private final String code;
    private final String message;

    private GsyServiceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap();
        GsyServiceEnum[] var4;
        int var3 = (var4 = values()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            GsyServiceEnum type = var4[var2];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static GsyServiceEnum find(String code) {
        GsyServiceEnum[] var4;
        int var3 = (var4 = values()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            GsyServiceEnum status = var4[var2];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        return null;
    }

    public static List<GsyServiceEnum> getAll() {
        List<GsyServiceEnum> list = new ArrayList();
        GsyServiceEnum[] var4;
        int var3 = (var4 = values()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            GsyServiceEnum status = var4[var2];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        GsyServiceEnum[] var4;
        int var3 = (var4 = values()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            GsyServiceEnum status = var4[var2];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}
