/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 13:42 创建
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
public enum TradeProfitTypeEnum implements Messageable {
    MANUAL("MANUAL", "手动"),
    AUTO("AUTO", "自动");

    private final String code;
    private final String message;

    private TradeProfitTypeEnum(String code, String message) {
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
        TradeProfitTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitTypeEnum type = var4[var2];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static TradeProfitTypeEnum find(String code) {
        TradeProfitTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitTypeEnum status = var4[var2];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("ClearType not legal:" + code);
    }

    public static List<TradeProfitTypeEnum> getAll() {
        List<TradeProfitTypeEnum> list = new ArrayList();
        TradeProfitTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitTypeEnum status = var4[var2];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        TradeProfitTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitTypeEnum status = var4[var2];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}

