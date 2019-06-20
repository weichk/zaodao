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
public enum ArticleTypeEnum implements Messageable {
    guideSecret("guideSecret", "导游秘籍"),
    travelStrategy("travelStrategy", "旅游攻略"),
    experienceTour("experienceTour", "带团日志"),
    guideTreeHole("guideTreeHole", "导游树洞"),
    caseAnalysis("caseAnalysis", "案例分析"),;

    private final String code;
    private final String message;

    ArticleTypeEnum(String code, String message) {
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
        for (ArticleTypeEnum type : values()) {
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
    public static ArticleTypeEnum find(String code) {
        for (ArticleTypeEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ObjectType not legal:" + code);
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<ArticleTypeEnum> getAll() {
        List<ArticleTypeEnum> list = new ArrayList<ArticleTypeEnum>();
        for (ArticleTypeEnum status : values()) {
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
        for (ArticleTypeEnum status : values()) {
            list.add(status.code());
        }
        return list;
    }
}
