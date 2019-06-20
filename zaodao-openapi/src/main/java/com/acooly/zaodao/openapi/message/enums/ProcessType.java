package com.acooly.zaodao.openapi.message.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 行程状态
 *
 * @author xiaohong
 * @create 2018-05-09 9:53
 **/
public enum ProcessType implements Messageable {
    start("start", "开始服务"),
    end("end", "结束服务"),
    finish("finish", "确认完成服务"),
    ;

    private final String code;
    private final String message;

    private ProcessType(String code, String message) {
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
        for (ProcessType type : values()) {
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
    public static ProcessType find(String code) {
        for (ProcessType status : values()) {
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
    public static List<ProcessType> getAll() {
        List<ProcessType> list = new ArrayList<ProcessType>();
        for (ProcessType status : values()) {
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
        for (ProcessType status : values()) {
            list.add(status.code());
        }
        return list;
    }

}