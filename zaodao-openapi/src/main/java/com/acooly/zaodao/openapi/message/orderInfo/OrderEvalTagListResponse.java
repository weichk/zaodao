package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.EvalTagInfoDto;
import lombok.Data;

/**
 * 系统评价标签列表
 *
 * @author xiaohong
 * @create 2018-05-11 9:53
 **/
@Data
@OpenApiMessage(service = "orderEvalTagList", type = ApiMessageType.Response)
public class OrderEvalTagListResponse extends PageApiResponse<EvalTagInfoDto> {
}
