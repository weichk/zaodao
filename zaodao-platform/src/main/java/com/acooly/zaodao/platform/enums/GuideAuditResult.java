package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 导游审核结果
 *
 * @author xiaohong
 * @create 2018-05-25 11:59
 **/
public enum GuideAuditResult implements Messageable {
    one_level_ing("one_level_ing", "审核中"),
    one_level_success("one_level_success", "待完善信息"),
    one_level_failed("one_level_failed", "审核未通过"),
    two_level_ing("two_level_ing", "审核中"),
    two_level_success("two_level_success", "认证通过"),
    two_level_failed("two_level_failed", "审核未通过"),
    ;

    private final String code;
    private final String message;

    private GuideAuditResult(String code, String message) {
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
        for (GuideAuditResult type : values()) {
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
    public static GuideAuditResult find(String code) {
        for (GuideAuditResult status : values()) {
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
    public static List<GuideAuditResult> getAll() {
        List<GuideAuditResult> list = new ArrayList<GuideAuditResult>();
        for (GuideAuditResult status : values()) {
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
        for (GuideAuditResult status : values()) {
            list.add(status.code());
        }
        return list;
    }

}