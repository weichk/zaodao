package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseDto;
import lombok.Data;

/**
 * 课程首页列表
 *
 * @author xiaohong
 * @create 2018-05-31 17:43
 **/
@Data
@OpenApiMessage(service = "courseHomeList", type = ApiMessageType.Response)
public class CourseHomeListResponse extends PageApiResponse<CourseDto> {
}
