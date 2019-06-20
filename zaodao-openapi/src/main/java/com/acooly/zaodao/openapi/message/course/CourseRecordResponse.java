package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/** Created by xiyang on 2017/9/19. */
@Data
@OpenApiMessage(service = "courseRecord", type = ApiMessageType.Response)
public class CourseRecordResponse extends ApiResponse {

  @OpenApiField(desc = "课程Id", constraint = "课程Id")
  private Long courseId;

  @OpenApiField(desc = "音频Id", constraint = "音频Id")
  private Long recordId;
}
