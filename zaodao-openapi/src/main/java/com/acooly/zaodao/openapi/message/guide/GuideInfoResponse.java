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
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "guideInfo", type = ApiMessageType.Response)
public class GuideInfoResponse extends ApiResponse {

    @OpenApiField(desc = "导游用户ID", constraint = "导游用户ID")
    private String userId;

    @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
    private String realName;

    @OpenApiField(desc = "导游用户头像", constraint = "导游用户头像")
     private String headImg;

    @OpenApiField(desc = "导游封面图", constraint = "导游封面图")
    private String guideCoverImg;

    @OpenApiField(desc = "带团时间", constraint = "带团时间")
    private Integer tourTime = 0;

    /** 常驻城市 */
    @OpenApiField(desc = "常驻城市", constraint = "常驻城市")
    private String permanentCity;

    @Size(max = 512)
    @OpenApiField(desc = "擅长路线", constraint = "擅长路线")
    private String goodRoute;

    @Size(max = 512)
    @OpenApiField(desc = "自我介绍", constraint = "自我介绍")
    private String introduceMyself;

    @OpenApiField(desc = "价格", constraint = "单位（元）")
    private Money pricePerDay;

    @OpenApiField(desc = "语种", constraint = "语种")
    private String language;

    @OpenApiField(desc = "星级", constraint = "星级")
    private Integer starLevel;

    @OpenApiField(desc = "是否为人气导游", constraint = "是否为人气导游{1:是,0:否} ")
    private Integer hotGuide;

    @OpenApiField(desc = "年龄", constraint = "年龄", demo = "26")
    private Integer age;

    @OpenApiField(desc = "文章数量", constraint = "文章数量", demo = "5")
    private Integer articleNums;

    @OpenApiField(desc = "是否被关注", constraint = "是否被关注{1:是,0:否} ")
    private Integer focused;

    @OpenApiField(desc = "爱好", constraint = "爱好")
    private String speciality;

    @OpenApiField(desc = "评论数量", constraint = "评论数量")
    private Integer reviewCount;

    @OpenApiField(desc = "关注人数", constraint = "关注人数")
    private Integer count;

    @OpenApiField(desc = "粉丝数", constraint = "粉丝数")
    private Integer focusCount;

    @OpenApiField(desc = "导游电话号码", constraint = "导游电话号码")
    private String mobileNo;

    @OpenApiField(desc = "性别", constraint = "性别")
    private Integer sex;

    @OpenApiField(desc = "导游资格证", constraint = "导游资格证")
    private String guideCertificateImg;

    @OpenApiField(desc = "是否开启商务接待", constraint = "是否开启商务接待", demo = "{0-否,1-是}")
    private Integer isBusRecept;

    @OpenApiField(desc = "是否开启政务接待", constraint = "是否开启政务接待", demo = "{0-否,1-是}")
    private Integer isGovRecept;
}
