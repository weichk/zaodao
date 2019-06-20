/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 *
 */
package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单表 PlatOrderInfoOrderStatus 枚举定义
 *
 * @author zhike
 * Date: 2017-05-24 23:14:32
 */
public enum PlatOrderInfoOrderStatus implements Messageable {

    pay("pay", "已支付"),

    noPay("noPay", "未支付"),

    close("close", "已关闭"),

    delete("delete", "已删除"),

    processing("processing", "服务中"), //处理中

    fail("fail", "失败"),

    confirm("confirm", "已完成"),

    refund("refund", "已退款"),

    confirming("confirming", "待确认"),

    evaluated("evaluated", "已评价")
    ;

    private final String code;
    private final String message;

    private PlatOrderInfoOrderStatus(String code, String message) {
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
        for (PlatOrderInfoOrderStatus type : values()) {
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
    public static PlatOrderInfoOrderStatus find(String code) {
        for (PlatOrderInfoOrderStatus status : values()) {
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
    public static List<PlatOrderInfoOrderStatus> getAll() {
        List<PlatOrderInfoOrderStatus> list = new ArrayList<PlatOrderInfoOrderStatus>();
        for (PlatOrderInfoOrderStatus status : values()) {
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
        for (PlatOrderInfoOrderStatus status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
