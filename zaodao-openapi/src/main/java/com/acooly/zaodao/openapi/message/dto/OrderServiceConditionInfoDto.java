package com.acooly.zaodao.openapi.message.dto;

import com.acooly.zaodao.platform.enums.ServiceConditionName;
import com.acooly.zaodao.platform.enums.ServiceConditionSymbol;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 服务费条件
 *
 * @author xiaohong
 * @create 2018-06-01 18:17
 **/
@Getter
@Setter
public class OrderServiceConditionInfoDto implements Serializable {
    /**
     * 条件名
     */
    private ServiceConditionName conditionName;

    /**
     * 条件符号
     */
    private ServiceConditionSymbol conditionSymbol;

    /**
     * 条件值
     */
    private String conditionValue;
}
