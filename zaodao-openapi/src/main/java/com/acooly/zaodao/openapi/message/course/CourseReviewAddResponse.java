package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 添加课程评论响应
 *
 * @author xiaohong
 * @create 2017-10-31 11:24
 **/
@Data
@OpenApiMessage(service = "courseReviewAdd", type = ApiMessageType.Response)
public class CourseReviewAddResponse extends ApiResponse {
}
