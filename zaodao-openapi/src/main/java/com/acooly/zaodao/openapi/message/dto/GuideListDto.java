package com.acooly.zaodao.openapi.message.dto;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/** Created by xiyang on 2017/9/29. */
@Data
public class GuideListDto implements Serializable {

  /** 用户ID */
  @OpenApiField(desc = "用户id", constraint = "用户id")
  private String userId;

  @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
  private String realName;

  @OpenApiField(desc = "头像", constraint = "头像")
  private String headImg;

  @OpenApiField(desc = "导游封面图", constraint = "导游封面图")
  private String guideCoverImg;

  @OpenApiField(desc = "带团时间", constraint = "带团时间")
  private Integer tourTime = 0;

  @OpenApiField(desc = "常驻城市", constraint = "常驻城市")
  private String permanentCity;

  /** 擅长路线 */
  @Size(max = 512)
  @OpenApiField(desc = "擅长路线", constraint = "擅长路线")
  private String goodRoute;

  /** 自我介绍 */
  @Size(max = 512)
  @OpenApiField(desc = "自我介绍", constraint = "自我介绍")
  private String introduceMyself;

  /** 价格 */
  @OpenApiField(desc = "价格", constraint = "价格")
  private Money pricePerDay;

  /** 语种 */
  @OpenApiField(desc = "语种", constraint = "语种")
  private String language;

  /** 星级 */
  @OpenApiField(desc = "星级", constraint = "星级")
  private Integer starLevel;

  /** 是否为人气导游{1:是,0:否} */
  @OpenApiField(desc = "是否为人气导游", constraint = "是否为人气导游{1:是,0:否}")
  private Integer hotGuide;
}
