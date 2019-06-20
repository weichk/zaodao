package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/** Created by xiyang on 2017/9/19. */
@Data
@OpenApiMessage(service = "courseDetail", type = ApiMessageType.Request)
public class CourseDetailRequest extends ApiRequest {
  @NotNull
  @OpenApiField(desc = "课程Id", constraint = "课程Id")
  private Long courseId;

  @OpenApiField(desc = "用户Id", constraint = "用户Id")
  private String userId;

}
