/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:22 创建
 *
 */
package com.acooly.zaodao.openapi.message.article;

import com.acooly.zaodao.openapi.message.dto.ArticleDto;
import com.acooly.zaodao.platform.entity.ArticleReview;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "articleInfo", type = ApiMessageType.Response)
public class ArticleInfoResponse extends ApiResponse {

    @OpenApiField(desc = "文章", constraint = "文章")
    private ArticleDto article;

    @OpenApiField(desc = "评论列表", constraint = "评论列表")
    private List<ArticleReview> articleReviewList;
}
