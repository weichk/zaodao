/*
 * 修订记录:
 * zhike@yiji.com 2017-08-15 10:09 创建
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
public enum TradeProfitStatusEnum implements Messageable {
    PROFIT_INIT("PROFIT_INIT", "未清分"),
    PROFIT_SUCCESS("PROFIT_SUCCESS", "清分成功"),
    PROFIT_FAIL("PROFIT_FAIL", "清分失败");

    private final String code;
    private final String message;

    private TradeProfitStatusEnum(String code, String message) {
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
        TradeProfitStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitStatusEnum type = var4[var2];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static TradeProfitStatusEnum find(String code) {
        TradeProfitStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitStatusEnum status = var4[var2];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("ClearStatus not legal:" + code);
    }

    public static List<TradeProfitStatusEnum> getAll() {
        List<TradeProfitStatusEnum> list = new ArrayList();
        TradeProfitStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitStatusEnum status = var4[var2];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        TradeProfitStatusEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            TradeProfitStatusEnum status = var4[var2];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}
