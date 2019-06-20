package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import lombok.Data;

/**
 * Created by okobboko on 2017/10/12.
 */
@Data
@OpenApiMessage(service = "coursePurchaseList", type = ApiMessageType.Request)
public class CoursePurchaseListRequest extends PageApiRequest {
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @OpenApiField(desc = "课程标题", constraint = "课程标题")
    private String courseTitle;

    @OpenApiField(desc = "课程内容", constraint = "课程内容")
    private String courseIntro;

    @OpenApiField(desc = "课程状态", constraint = "课程状态")
    private CourseStatusEnum courseStatus;

    @OpenApiField(desc = "课程类型", constraint = "课程类型")
    private CourseType courseType;
}
