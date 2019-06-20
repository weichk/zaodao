package com.acooly.zaodao.openapi.message.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 敏感信息类型
 *
 * @author xiaohong
 * @create 2018-06-14 10:02
 **/
public enum SensitiveKeyType  implements Messageable {
    deposit("deposit", "充值"),
    withdraw("withdraw", "提现"),
    trade("trade", "交易"),
    catrade("catrade", "钙片交易"),
    reward("reward", "打赏"),
    exchange("exchange", "兑换"),
    bindcard("bindcard", "绑卡"),
    mybankcard("mybankcard", "我的银行卡"),
    balance("balance", "账户余额"),
    point("point", "可用积分"),
    ;

    private final String code;
    private final String message;

    private SensitiveKeyType(String code, String message) {
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
        for (SensitiveKeyType type : values()) {
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
    public static SensitiveKeyType find(String code) {
        for (SensitiveKeyType status : values()) {
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
    public static List<SensitiveKeyType> getAll() {
        List<SensitiveKeyType> list = new ArrayList<SensitiveKeyType>();
        for (SensitiveKeyType status : values()) {
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
        for (SensitiveKeyType status : values()) {
            list.add(status.code());
        }
        return list;
    }

}