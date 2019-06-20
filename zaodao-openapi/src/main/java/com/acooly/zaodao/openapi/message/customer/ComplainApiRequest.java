package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.openapi.message.enums.ComplainType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 投诉
 *
 * @author xiaohong
 * @create 2018-01-04 16:07
 **/
@Data
@OpenApiMessage(service = "customerComplain", type = ApiMessageType.Request)
public class ComplainApiRequest extends ApiRequest{
    @NotNull
    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "投诉内容", constraint = "投诉内容")
    private String content;

    @NotNull
    @OpenApiField(desc = "投诉类型", constraint = "投诉类型{不文明内容,反动言论,色情内容}")
    private ComplainType complainType;
}
