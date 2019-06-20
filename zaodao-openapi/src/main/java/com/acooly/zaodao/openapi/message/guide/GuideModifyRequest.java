package com.acooly.zaodao.openapi.message.guide;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 导游信息修改
 *
 * @author xiaohong
 * @create 2018-05-25 17:00
 **/
@Data
@OpenApiMessage(service = "guideModify", type = ApiMessageType.Request)
public class GuideModifyRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "导游用户ID", constraint = "导游用户ID")
    private String userId;

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

    @OpenApiField(desc = "导游封面图", constraint = "导游封面图")
    private String guideCoverImg;

    @OpenApiField(desc = "自我介绍", constraint = "自我介绍")
    private String introduceMyself;
}
