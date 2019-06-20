package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.LessThanCurrentEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 课程首页列表
 *
 * @author xiaohong
 * @create 2018-05-31 17:38
 **/
@Data
@OpenApiMessage(service = "courseHomeList", type = ApiMessageType.Request)
public class CourseHomeListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "App用户ID", constraint = "App用户ID(查询用户购买的课程)")
    private String appUserId;

}
