package com.acooly.zaodao.openapi.message.article;

import com.acooly.core.common.web.support.JsonResult;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/27.
 */
@Data
@OpenApiMessage(service = "reward", type = ApiMessageType.Response)
public class RewardResponse extends ApiResponse {

}
