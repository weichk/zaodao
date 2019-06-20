package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单读取状态个数统计
 * @author xiaohong
 * @create 2018-06-25 11:04
 **/
@Data
public class PlatOrderCountDto implements Serializable {

    @OpenApiField(desc = "未读订单个数", constraint = "未读订单个数")
    private Integer unReadSumCount;

    @OpenApiField(desc = "未读我发出的订单个数", constraint = "未读我发出的订单个数")
    private Integer unReadMySendCount;

    @OpenApiField(desc = "未读我收到的订单个数", constraint = "未读我收到的订单个数")
    private Integer unReadMyReceiveCount;

    @OpenApiField(desc = "未读积分消费订单个数", constraint = "未读积分消费订单个数")
    private Integer unReadPointCount;
}
