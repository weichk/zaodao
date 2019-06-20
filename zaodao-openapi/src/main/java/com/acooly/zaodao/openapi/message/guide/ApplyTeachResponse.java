/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 18:13 创建
 *
 */
package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "applyTeach", type = ApiMessageType.Response)
public class ApplyTeachResponse extends ApiResponse {

    @OpenApiField(desc = "是否讲师", constraint = "是否讲师{0:否,1:是,-1:申请中}")
    private Integer isLector = -1;
}
