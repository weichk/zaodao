package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 11:55
 **/
@Setter
@Getter
public class OrderServiceConditionDto extends OrderServiceFeeDto {

    private Long id;

    /** 服务费ID */
    private Long feeId;

    /**
     * 条件名(订单状态)
     */
    @Enumerated(EnumType.STRING)
    private ServiceConditionName conditionName;

    /**
     * 条件符号(等于)
     */
    @Enumerated(EnumType.STRING)
    private ServiceConditionSymbol conditionSymbol;

    /**
     * 条件值(已支付)
     */
    private String conditionValue;
}
