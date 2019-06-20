package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseDto;
import lombok.Data;

/**
 * Created by okobboko on 2017/10/12.
 */
@Data
@OpenApiMessage(service = "coursePurchaseList", type = ApiMessageType.Response)
public class CoursePurchaseListResponse extends PageApiResponse<CourseDto> {

}
