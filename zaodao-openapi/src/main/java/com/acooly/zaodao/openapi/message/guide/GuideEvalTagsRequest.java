package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游评价标签
 *
 * @author xiaohong
 * @create 2018-05-07 20:56
 **/
@Data
@OpenApiMessage(service = "guideEvalTags", type = ApiMessageType.Request)
public class GuideEvalTagsRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID", demo = "O00117052610240611600000")
    private String guideUserId;
}
