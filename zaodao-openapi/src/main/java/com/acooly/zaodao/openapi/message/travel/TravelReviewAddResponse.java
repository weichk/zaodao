package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

import java.util.Date;

/**
 * Created by xiaohong on 2017/10/27.
 */
@Data
@OpenApiMessage(service = "travelReviewAdd", type = ApiMessageType.Response)
public class TravelReviewAddResponse extends ApiResponse {
    @OpenApiField(desc = "旅声评论ID", constraint = "旅声评论ID")
    private Long travelReviewId;

    @OpenApiField(desc = "评论用户头像", constraint = "评论用户头像")
    private String headImg;

    @OpenApiField(desc = "评论用户名", constraint = "评论用户名")
    private String userName;

    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    private String content;

    @OpenApiField(desc = "创建时间", constraint = "创建时间")
    private Date createTime;

    @OpenApiField(desc = "评论数", constraint = "评论数")
    private Long reviewCount = 0l;
}
