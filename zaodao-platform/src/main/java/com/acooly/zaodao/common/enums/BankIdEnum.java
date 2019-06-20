/*
 * zhike.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-02-23
 *
 */
package com.acooly.zaodao.common.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 标的基本信息表 ObjectType 枚举定义
 *
 * @author zhike
 *         Date: 2017-02-23 21:19:12
 */
public enum BankIdEnum implements Messageable {
    CITIC("CITIC", "中信银行"),
    BOC("BOC", "中国银行"),
    ICBC("ICBC", "工商银行"),
    CCB("CCB", "建设银行"),
    CIB("CIB", "兴业银行"),
    ;

    private final String code;
    private final String message;

    BankIdEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (BankIdEnum type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static BankIdEnum find(String code) {
        for (BankIdEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ObjectType not legal:" + code);
    }

    /**
     * 通过枚举值查找枚举
     * @param name
     * @return
     */
    public static BankIdEnum findByName(String name){
        for (BankIdEnum status : values()){
            if(status.getMessage().equals(name)){
                return status;
            }
        }
        throw new IllegalArgumentException("ObjectType not legal:" + name);
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<BankIdEnum> getAll() {
        List<BankIdEnum> list = new ArrayList<BankIdEnum>();
        for (BankIdEnum status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllCode() {
        List<String> list = new ArrayList<String>();
        for (BankIdEnum status : values()) {
            list.add(status.code());
        }
        return list;
    }
}
