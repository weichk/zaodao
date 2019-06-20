package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 课程评论列表
 *
 * @author xiaohong
 * @create 2017-10-31 11:35
 **/
@Data
public class CourseReviewListOrder extends PageOrder {
    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;
}
