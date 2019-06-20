/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:22 创建
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
public enum CustomerTypeEnum implements Messageable {
    PERSON("PERSON", "个人"),
    BUSINESS("BUSINESS", "企业"),
    INDIVIDUAL("INDIVIDUAL", "个体户");

    private final String code;
    private final String message;

    private CustomerTypeEnum(String code, String message) {
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
        CustomerTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CustomerTypeEnum type = var1[var3];
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public static CustomerTypeEnum find(String code) {
        CustomerTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CustomerTypeEnum status = var1[var3];
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("PayType not legal:" + code);
    }

    public static List<CustomerTypeEnum> getAll() {
        List<CustomerTypeEnum> list = new ArrayList();
        CustomerTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CustomerTypeEnum status = var1[var3];
            list.add(status);
        }

        return list;
    }

    public static List<String> getAllCode() {
        List<String> list = new ArrayList();
        CustomerTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CustomerTypeEnum status = var1[var3];
            list.add(status.code());
        }

        return list;
    }

    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}

