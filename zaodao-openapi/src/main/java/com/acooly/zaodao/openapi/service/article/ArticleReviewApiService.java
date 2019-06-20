/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 18:00 创建
 *
 */
package com.acooly.zaodao.openapi.service.article;

import com.acooly.zaodao.openapi.message.article.ArticleReviewRequest;
import com.acooly.zaodao.openapi.message.article.ArticleReviewResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "articleReview", desc = "帖子评论", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_article", name = "文章")
public class ArticleReviewApiService extends BaseApiService<ArticleReviewRequest, ArticleReviewResponse> {

    @Override
    protected void doService(ArticleReviewRequest request, ArticleReviewResponse response) {

    }
}
