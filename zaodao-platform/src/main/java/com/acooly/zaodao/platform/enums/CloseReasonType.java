package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单取消原因
 *
 * @author xiaohong
 * @create 2018-05-08 16:06
 **/
public enum CloseReasonType  implements Messageable {

    travel_conflict("travel_conflict", "与其他行程冲突"),
    info_error("info_error", "信息填写有误"),
    personal_reason("personal_reason", "生病等个人原因"),
    other_reason("other_reason", "其他原因"),

    ;

    private final String code;
    private final String message;

    private CloseReasonType(String code, String message) {
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
        for (CloseReasonType type : values()) {
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
    public static CloseReasonType find(String code) {
        for (CloseReasonType status : values()) {
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
    public static List<CloseReasonType> getAll() {
        List<CloseReasonType> list = new ArrayList<CloseReasonType>();
        for (CloseReasonType status : values()) {
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
        for (CloseReasonType status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
