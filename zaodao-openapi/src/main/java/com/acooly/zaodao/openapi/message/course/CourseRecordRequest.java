package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by xiyang on 2017/9/19.
 */
@Data
@OpenApiMessage(service = "courseRecord", type = ApiMessageType.Request)
public class CourseRecordRequest extends ApiRequest {

    @NotNull
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;

    @NotNull
    @OpenApiField(desc = "音频标题", constraint = "音频标题")
    private String recordTitle;

    @NotNull
    @OpenApiField(desc = "音频地址", constraint = "音频地址")
    private String recordUrl;

    @NotNull
    @OpenApiField(desc = "音频时长", constraint = "音频时长")
    private Long recordTime;

}
