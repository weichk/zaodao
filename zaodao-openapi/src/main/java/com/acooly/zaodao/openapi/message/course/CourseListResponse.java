package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseDto;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by xiyang on 2017/9/19.
 */
@Data
@OpenApiMessage(service = "courseList", type = ApiMessageType.Response)
public class CourseListResponse extends PageApiResponse<CourseDto> {

}
