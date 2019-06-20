package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

/**
 * 音频删除请求
 * Created by xiaohong on 2017/9/22.
 */
@Data
@OpenApiMessage(service = "removeCourseRecord", type = ApiMessageType.Request)
public class RemoveCourseRecordRequest extends ApiRequest {
    @OpenApiField(desc = "音频Id", constraint = "音频Id")
    private Long recordId;
}
