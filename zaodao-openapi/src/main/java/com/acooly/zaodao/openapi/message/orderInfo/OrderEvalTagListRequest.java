package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;

/**
 * 系统评价列表
 *
 * @author xiaohong
 * @create 2018-05-11 9:52
 **/
@Data
@OpenApiMessage(service = "orderEvalTagList", type = ApiMessageType.Request)
public class OrderEvalTagListRequest extends PageApiRequest {
    
}
