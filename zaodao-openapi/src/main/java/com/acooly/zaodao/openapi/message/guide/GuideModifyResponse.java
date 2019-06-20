package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 导游信息修改
 *
 * @author xiaohong
 * @create 2018-05-25 17:01
 **/
@Data
@OpenApiMessage(service = "guideModify", type = ApiMessageType.Response)
public class GuideModifyResponse extends ApiResponse {
}
