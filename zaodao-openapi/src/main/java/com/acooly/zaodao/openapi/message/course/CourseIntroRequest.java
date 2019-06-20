package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by xiyang on 2017/9/19.
 */
@Data
@OpenApiMessage(service = "courseIntro", type = ApiMessageType.Request)
public class CourseIntroRequest extends ApiRequest {

    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @OpenApiField(desc = "课程Id", constraint = "课程Id(存在则修改)")
    private Long courseId;

    @NotBlank
    @OpenApiField(desc = "课程标题", constraint = "课程标题")
    private String courseTitle;

    @NotBlank
    @OpenApiField(desc = "课程简介", constraint = "课程简介")
    private String courseIntro;

    @OpenApiField(desc = "课程图片", constraint = "课程图片")
    private String courseImg;

    @NotNull
    @OpenApiField(desc = "课程价格", constraint = "课程价格(钙片)")
    private Long coursePrice;

    @NotNull
    @OpenApiField(desc = "课程时长", constraint = "课程时长(min)")
    private Long sumRecordTime;

    @OpenApiField(desc = "课程备注", constraint = "课程备注")
    private String courseDesc;

    @NotBlank
    @OpenApiField(desc = "课程音频集合", constraint = "课程音频集合", demo = "[{\"recordTitle\":\"1\",\"recordUrl\":\"\",\"recordTime\":\"\"}]")
    private String courseRecordListJson ;
}
