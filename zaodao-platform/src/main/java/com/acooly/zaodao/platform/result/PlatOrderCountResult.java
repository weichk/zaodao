package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

/**
 * 订单读取状态个数统计
 *
 * @author xiaohong
 * @create 2018-06-25 11:13
 **/
@Data
public class PlatOrderCountResult extends ResultBase {
    /**
     * 未读订单个数
     */
    private Integer unReadSumCount;

    /**
     * 未读我发出的订单个数
     */
    private Integer unReadMySendCount;

    /**
     * 未读我收到的订单个数
     */
    private Integer unReadMyReceiveCount;

    /**
     * 未读积分消费订单个数
     */
    private Integer unReadPointCount;
}
