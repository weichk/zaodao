/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:23 创建
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
public enum RealNameAuthEnum implements Messageable {
    AUTH_NO("AUTH_NO", "未认证"),
    AUTH_ING("AUTH_ING", "认证中"),
    AUTH_OK("AUTH_OK", "已认证"),
    AUTH_OVER("AUTH_OVER", "认证过期");

    private final String code;
    private final String message;

    private RealNameAuthEnum(String code, String message) {
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
        RealNameAuthEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RealNameAuthEnum type = var1[var3];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static RealNameAuthEnum find(String code) {
        RealNameAuthEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RealNameAuthEnum status = var1[var3];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("PayType not legal:" + code);
    }

    public static List<RealNameAuthEnum> getAll() {
        List<RealNameAuthEnum> list = new ArrayList();
        RealNameAuthEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RealNameAuthEnum status = var1[var3];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        RealNameAuthEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RealNameAuthEnum status = var1[var3];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}

