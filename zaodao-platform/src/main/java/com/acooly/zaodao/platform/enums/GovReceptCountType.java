package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 政务接待次数
 *
 * @author xiaohong
 * @create 2018-05-21 14:22
 **/
public enum GovReceptCountType implements Messageable {

    one_to_three("one_to_three", "1到3次"),

    more_than_three("more_than_three", "3次以上"),;

    private final String code;
    private final String message;

    private GovReceptCountType(String code, String message) {
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
        for (GovReceptCountType type : values()) {
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
    public static GovReceptCountType find(String code) {
        for (GovReceptCountType status : values()) {
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
    public static List<GovReceptCountType> getAll() {
        List<GovReceptCountType> list = new ArrayList<GovReceptCountType>();
        for (GovReceptCountType status : values()) {
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
        for (GovReceptCountType status : values()) {
            list.add(status.code());
        }
        return list;
    }
}