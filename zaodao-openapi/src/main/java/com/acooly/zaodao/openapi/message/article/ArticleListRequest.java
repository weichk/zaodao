/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 18:13 创建
 *
 */
package com.acooly.zaodao.openapi.message.article;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "articleList", type = ApiMessageType.Request)
public class ArticleListRequest extends PageApiRequest {

  @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
  private String userId;

  @OpenApiField(desc = "文章热门标识")
  private ArticleHotType articleHotType;

  @OpenApiField(desc = "文章类型")
  private ArticleTypeEnum articleType;

  @OpenApiField(desc = "是否加精", constraint = "是否加精{0:不加精,1:加精}")
  private Integer essenceStatus;

  @OpenApiField(desc = "App登录用户ID", constraint = "App登录用户ID")
  private String appUserId;

  @OpenApiField(desc = "关键字", constraint = "关键字")
  private String keywords;

  /** 文章状态 {@link com.acooly.zaodao.platform.enums.ArticleStatusEnum} */
  @OpenApiField(desc = "文章状态", constraint = "文章状态")
  private ArticleStatusEnum articleStatus = ArticleStatusEnum.published;
}
