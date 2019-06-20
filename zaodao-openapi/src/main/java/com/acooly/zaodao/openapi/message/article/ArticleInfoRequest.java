/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:21 创建
 *
 */
package com.acooly.zaodao.openapi.message.article;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "articleInfo", type = ApiMessageType.Request)
public class ArticleInfoRequest extends ApiRequest{

    @NotNull
    @OpenApiField(desc = "文章ID", constraint = "文章ID", demo = "88")
    private Long articleId;
}
