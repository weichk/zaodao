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
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.BusReceptCountType;
import com.acooly.zaodao.platform.enums.GovReceptCountType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "applyGuide", type = ApiMessageType.Request)
public class ApplyGuideRequest extends ApiRequest{
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识")
    private String userId;

    @OpenApiField(desc = "导游封面图", constraint = "导游封面图")
    private String guideCoverImg;

    @NotBlank
    @Size(max = 32)
    @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
    private String realName;

    @OpenApiField(desc = "性别", constraint = "性别{0:女,1:男}")
    private Integer sex = 1;

    @Size(max = 512)
    @OpenApiField(desc = "个人介绍", constraint = "个人介绍")
    private String introduceMyself;

    @OpenApiField(desc = "常驻城市", constraint = "常驻城市")
    private String permanentCity;

    @NotNull
    @OpenApiField(desc = "带团年限", constraint = "带团年限")
    private Integer tourTime = 0;

    @OpenApiField(desc = "带团语种", constraint = "带团语种(中文,英文,日语)")
    private String language;

    @NotNull
    @OpenApiField(desc = "价格/天（元）", constraint = "价格/天（元）")
    private Money pricePerDay;

    @Size(max = 512)
    @OpenApiField(desc = "擅长路线", constraint = "擅长路线")
    private String goodRoute;

    @OpenApiField(desc = "商务接待次数", constraint = "商务接待次数")
    private BusReceptCountType busReceptCount;

    @OpenApiField(desc = "政务接待次数", constraint = "政务接待次数")
    private GovReceptCountType govReceptCount;
}
