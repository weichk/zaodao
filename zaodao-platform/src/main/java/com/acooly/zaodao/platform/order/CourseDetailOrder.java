package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohong on 2017/10/12.
 */
@Data
public class CourseDetailOrder extends OrderBase {
    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;

    @OpenApiField(desc = "用户Id", constraint = "用户Id")
    private String userId;
}
