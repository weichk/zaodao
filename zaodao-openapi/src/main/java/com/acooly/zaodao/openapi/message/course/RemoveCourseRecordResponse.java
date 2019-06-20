package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 音频删除响应
 * Created by xiaohong on 2017/9/22.
 */
@Data
@OpenApiMessage(service = "removeCourseRecord", type = ApiMessageType.Response)
public class RemoveCourseRecordResponse extends ApiResponse {
}
