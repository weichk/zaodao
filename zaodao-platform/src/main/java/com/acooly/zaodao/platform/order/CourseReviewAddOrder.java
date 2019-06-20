package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 课程评论添加
 *
 * @author xiaohong
 * @create 2017-10-31 11:27
 **/
@Data
public class CourseReviewAddOrder extends OrderBase {
    @NotNull
    @OpenApiField(desc = "课程ID", constraint = "课程ID")
    private Long courseId;

    @NotBlank
    @OpenApiField(desc = "评论用户ID", constraint = "评论用户ID")
    private String userId;

    @OpenApiField(desc = "评论父级ID", constraint = "评论父级ID")
    private Long reviewParentId;

    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    @NotBlank
    private String content;
}
