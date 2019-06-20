/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:57 创建
 *
 */
package com.acooly.zaodao.openapi.message.article;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "articleReview", type = ApiMessageType.Response)
public class ArticleReviewResponse extends ApiResponse{
}
