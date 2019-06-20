/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 14:00 创建
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
public enum TradeStatusEnum implements Messageable {
    INIT("INIT", "初始状态"),
    PROCESSING("PROCESSING", "支付中"),
    SUCCESS("SUCCESS", "交易成功"),
    FAIL("FAIL", "交易失败"),
    CANCEL("CANCEL", "交易撤销"),
    REFUND("REFUND", "交易退款"),
    CLOSE("CLOSE", "交易关闭");

    private final String code;
    private final String message;

    private TradeStatusEnum(String code, String message) {
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
        TradeStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeStatusEnum type = var4[var2];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static TradeStatusEnum find(String code) {
        for (TradeStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public static List<TradeStatusEnum> getAll() {
        List<TradeStatusEnum> list = new ArrayList();
        TradeStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeStatusEnum status = var4[var2];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        TradeStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeStatusEnum status = var4[var2];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}

