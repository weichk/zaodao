package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 课程评论列表请求
 *
 * @author xiaohong
 * @create 2017-10-31 11:33
 **/
@Data
@OpenApiMessage(service = "courseReviewList", type = ApiMessageType.Request)
public class CourseReviewListRequest extends PageApiRequest{
    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;
}
