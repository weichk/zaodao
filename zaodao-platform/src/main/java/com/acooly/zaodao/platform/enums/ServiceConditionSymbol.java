package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 条件符号
 *
 * @author xiaohong
 * @create 2018-05-28 18:29
 **/
public enum ServiceConditionSymbol implements Messageable {

    equal_to("equal_to", "等于"),
    more_than("more_than", "大于"),
    more_equal_than("more_equal_than", "大于等于"),
    less_than("less_than", "小于"),
    less_equal_than("less_equal_than", "小于等于"),
    ;

    private final String code;
    private final String message;

    private ServiceConditionSymbol(String code, String message) {
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
        for (ServiceConditionSymbol type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code
     *            查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException
     *             如果 code 没有对应的 Status 。
     */
    public static ServiceConditionSymbol find(String code) {
        for (ServiceConditionSymbol status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<ServiceConditionSymbol> getAll() {
        List<ServiceConditionSymbol> list = new ArrayList<ServiceConditionSymbol>();
        for (ServiceConditionSymbol status : values()) {
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
        for (ServiceConditionSymbol status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
