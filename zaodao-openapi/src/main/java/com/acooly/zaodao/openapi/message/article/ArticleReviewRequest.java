/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:57 创建
 *
 */
package com.acooly.zaodao.openapi.message.article;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "articleReview", type = ApiMessageType.Request)
public class ArticleReviewRequest extends ApiRequest{

    @NotNull
    @OpenApiField(desc = "文章ID", constraint = "文章ID", demo = "88")
    private Long articleId;

    @NotNull
    @OpenApiField(desc = "会员ID", constraint = "会员ID", demo = "88")
    private Long userId;

    @NotBlank
    @OpenApiField(desc = "评论内容", constraint = "评论内容", demo = "这篇文章可以")
    private String reviewContent;
}
