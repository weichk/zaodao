/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 14:00 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public enum TradeTypeEnum {
    DEDUCT_DEPOSIT("DEDUCT_DEPOSIT", "代扣充值"),
    NET_DEPOSIT("NET_DEPOSIT", "网银充值"),
    OFFLINE_DEPOSIT("OFFLINE_DEPOSIT", "离线充值"),
    WITHDRAW("WITHDRAW", "提现"),
    BALANCE_PAY("BALANCE_PAY", "余额支付"),
    DEDUCT_PAY("DEDUCT_PAY", "代扣支付"),
    NET_DEPOSIT_PAY("NET_DEPOSIT_PAY", "网银支付"),
    POS_ONLINE_PAY("POS_ONLINE_PAY", "pos在线支付"),
    POS_OFFLINE_PAY("POS_OFFLINE_PAY", "pos离线支付"),
    TRADE_REFUND("TRADE_REFUND", "交易退款"),
    WECHAT_PUBLIC_PAY("WECHAT_PUBLIC_PAY", "微信公众号支付"),
    WECHAT_APP_PAY("WECHAT_APP_PAY", "微信APP支付"),
    WECHAT_SWING_CARD_PAY("WECHAT_SWING_CARD_PAY", "微信刷卡支付"),
    WECHAT_SCAN_CODE_PAY("WECHAT_SCAN_CODE_PAY", "微信扫码支付"),
    ALI_APP_PAY("ALI_APP_PAY", "支付宝APP支付"),
    ALI_APP_PAGE_PAY("ALI_APP_PAGE_PAY", "支付宝H5支付"),
    ALI_PC_PAGE_PAY("ALI_PC_PAGE_PAY", "支付宝PC页面支付"),
    ALI_SWING_CARD_PAY("ALI_SWING_CARD_PAY", "支付宝刷卡支付"),
    ALI_SCAN_CODE_PAY("ALI_SCAN_CODE_PAY", "支付宝扫码支付"),
    NET_DEPOSIT_BACK("NET_DEPOSIT_BACK", "充值充退"),
    NET_DEPOSIT_PAY_BACK("NET_DEPOSIT_PAY_BACK", "网银支付充退"),
    WECHAT_PAY_BACK("WECHAT_PAY_BACK", "微信支付退款"),
    ALI_PAY_BACK("ALI_PAY_BACK", "支付宝支付退款"),
    TRADE_PROFIT("TRADE_PROFIT", "交易清分");

    private final String code;
    private final String message;

    private TradeTypeEnum(String code, String message) {
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
        TradeTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeTypeEnum type = var4[var2];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static TradeTypeEnum find(String code) {
        TradeTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeTypeEnum status = var4[var2];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Type not legal:" + code);
    }

    public static List<TradeTypeEnum> getAll() {
        List<TradeTypeEnum> list = new ArrayList();
        TradeTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeTypeEnum status = var4[var2];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        TradeTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeTypeEnum status = var4[var2];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}
