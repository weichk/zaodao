package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游文章列表
 *
 * @author xiaohong
 * @create 2017-12-06 14:05
 **/
@Data
@OpenApiMessage(service = "guideArticleList", type = ApiMessageType.Request)
public class GuideArticleListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "O00117052610240611600001")
    private String userId;
}
