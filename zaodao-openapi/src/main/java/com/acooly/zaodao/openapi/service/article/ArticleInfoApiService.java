/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:52 创建
 *
 */
package com.acooly.zaodao.openapi.service.article;

import com.acooly.zaodao.openapi.message.article.ArticleInfoRequest;
import com.acooly.zaodao.openapi.message.article.ArticleInfoResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "articleInfo", desc = "帖子信息", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_article", name = "文章")
public class ArticleInfoApiService extends BaseApiService<ArticleInfoRequest, ArticleInfoResponse> {

    @Override
    protected void doService(ArticleInfoRequest request, ArticleInfoResponse response) {
        
    }
}
