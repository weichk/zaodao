/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:16 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message.enums;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public enum PublicTagEnum implements Messageable {
    Y("Y", "对公"),
    N("N", "对私"),
    NY("NY", "对公对私");

    private final String code;
    private final String message;

    private PublicTagEnum(String code, String message) {
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

    public static PublicTagEnum getByCode(String code) {
        PublicTagEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            PublicTagEnum _enum = var4[var2];
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }

        return null;
    }

    public static List<PublicTagEnum> getAllEnum() {
        List<PublicTagEnum> list = new ArrayList(values().length);
        PublicTagEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            PublicTagEnum _enum = var4[var2];
            list.add(_enum);
        }

        return list;
    }

    public static List<String> getAllEnumCode() {
        List<String> list = new ArrayList(values().length);
        PublicTagEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            PublicTagEnum _enum = var4[var2];
            list.add(_enum.code());
        }

        return list;
    }

    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        } else {
            PublicTagEnum _enum = getByCode(code);
            return _enum == null ? null : _enum.getMessage();
        }
    }

    public static String getCode(PublicTagEnum _enum) {
        return _enum == null ? null : _enum.getCode();
    }

    public static Map<String, String> maps() {
        Map<String, String> map = Maps.newLinkedHashMap();
        Iterator var2 = getAllEnum().iterator();

        while(var2.hasNext()) {
            PublicTagEnum type = (PublicTagEnum)var2.next();
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public String toString() {
        return this.code + ":" + this.message;
    }
}
