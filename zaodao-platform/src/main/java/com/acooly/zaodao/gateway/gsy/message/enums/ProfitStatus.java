package com.acooly.zaodao.gateway.gsy.message.enums;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum ProfitStatus implements Messageable {
    PROFIT_SUCCESS("PROFIT_SUCCESS", "清分成功"),
    PROFIT_FAIL("PROFIT_FAIL", "清分失败"),
    PROFIT_INIT("PROFIT_INIT", "未清分");

    private final String code;
    private final String message;

    private ProfitStatus(String code, String message) {
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

    public static ProfitStatus getByCode(String code) {
        ProfitStatus[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ProfitStatus _enum = var4[var2];
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }

        return null;
    }

    public static List<ProfitStatus> getAllEnum() {
        List<ProfitStatus> list = new ArrayList(values().length);
        ProfitStatus[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ProfitStatus _enum = var4[var2];
            list.add(_enum);
        }

        return list;
    }

    public static List<String> getAllEnumCode() {
        List<String> list = new ArrayList(values().length);
        ProfitStatus[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ProfitStatus _enum = var4[var2];
            list.add(_enum.code());
        }

        return list;
    }

    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        } else {
            ProfitStatus _enum = getByCode(code);
            return _enum == null ? null : _enum.getMessage();
        }
    }

    public static String getCode(CardTypeEnum _enum) {
        return _enum == null ? null : _enum.getCode();
    }

    public static Map<String, String> maps() {
        Map<String, String> map = Maps.newLinkedHashMap();
        Iterator var2 = getAllEnum().iterator();

        while(var2.hasNext()) {
            CardTypeEnum type = (CardTypeEnum)var2.next();
            map.put(type.getCode(), type.getMessage());
        }

        return map;
    }

    public String toString() {
        return this.code + ":" + this.message;
    }
}
