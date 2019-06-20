package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseReviewInfoDto;
import lombok.Data;

/**
 * 课程评论列表响应
 *
 * @author xiaohong
 * @create 2017-10-31 11:34
 **/
@Data
@OpenApiMessage(service = "courseReviewList", type = ApiMessageType.Response)
public class CourseReviewListResponse extends PageApiResponse<CourseReviewInfoDto> {
    
}
