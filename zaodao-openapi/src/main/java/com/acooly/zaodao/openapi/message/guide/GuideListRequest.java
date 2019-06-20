/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 18:13 创建
 *
 */
package com.acooly.zaodao.openapi.message.guide;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "guideList", type = ApiMessageType.Request)
public class GuideListRequest extends PageApiRequest {

  @OpenApiField(desc = "出行时间", constraint = "出行时间")
  @NotNull
  private Date startTime;

  @OpenApiField(desc = "结束时间", constraint = "结束时间")
  @NotNull
  private Date endTime;

  @OpenApiField(desc = "性别", constraint = "性别{0:女,1:男}")
  private Integer sex;

  @OpenApiField(desc = "是否热门导游", constraint = "性别{0:否,1:是}")
  private Integer hotGuide;

  @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
  private String realName;

  @OpenApiField(desc = "城市", constraint = "城市")
  private String city;

  @OpenApiField(desc = "语言", constraint = "语言")
  private String language;

  @OpenApiField(desc = "价格区间最小值", constraint = "价格区间最小值")
  private Money priceMin;

  @OpenApiField(desc = "价格区间最大值", constraint = "价格区间最大值")
  private Money priceMax;

  @OpenApiField(desc = "关键字", constraint = "关键字")
  private String keywords;
}
