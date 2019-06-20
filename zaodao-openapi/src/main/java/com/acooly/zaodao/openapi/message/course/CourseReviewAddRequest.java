package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 添加课程评论请求
 *
 * @author xiaohong
 * @create 2017-10-31 11:24
 **/
@Data
@OpenApiMessage(service = "courseReviewAdd", type = ApiMessageType.Request)
public class CourseReviewAddRequest extends ApiRequest {
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
