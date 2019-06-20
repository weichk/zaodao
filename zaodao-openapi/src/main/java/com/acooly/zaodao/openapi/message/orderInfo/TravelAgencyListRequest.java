package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 旅行社列表
 *
 * @author xiaohong
 * @create 2018-05-04 16:49
 **/
@Data
@OpenApiMessage(service = "travelAgencyList", type = ApiMessageType.Request)
public class TravelAgencyListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "添加旅行社信息的用户ID", constraint = "用户ID")
    private String userId;
}
