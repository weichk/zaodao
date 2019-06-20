package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseFocusInfoDto;
import lombok.Data;

/**
 * 课程关注列表响应
 *
 * @author xiaohong
 * @create 2017-10-31 15:25
 **/
@Data
@OpenApiMessage(service = "courseFocusList", type = ApiMessageType.Response)
public class CourseFocusListResponse extends PageApiResponse<CourseFocusInfoDto> {
}
