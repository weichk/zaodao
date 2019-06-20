package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 订单评价
 *
 * @author xiaohong
 * @create 2017-12-11 11:40
 **/
@Data
@OpenApiMessage(service = "platOrderEval", type = ApiMessageType.Request)
public class PlatOrderEvalRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "评论用户ID", constraint = "评论用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @NotNull
    @OpenApiField(desc = "评分", constraint = "评分")
    private Integer score;

    @NotBlank
    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    private String content;

    @OpenApiField(desc = "导游标签", constraint = "导游标签", demo = "1,3,4,5")
    private String guidTagIds;
}
