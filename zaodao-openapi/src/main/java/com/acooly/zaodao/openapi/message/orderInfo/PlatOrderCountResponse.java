package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.PlatOrderCountDto;
import lombok.Data;

/**
 * 订单读取状态个数统计
 *
 * @author xiaohong
 * @create 2018-06-25 11:01
 **/
@Data
@OpenApiMessage(service = "platOrderCount", type = ApiMessageType.Response)
public class PlatOrderCountResponse extends ApiResponse {
    @OpenApiField(desc = "未读订单个数统计", constraint = "未读订单个数统计")
    private PlatOrderCountDto platOrderCountDto;
}
