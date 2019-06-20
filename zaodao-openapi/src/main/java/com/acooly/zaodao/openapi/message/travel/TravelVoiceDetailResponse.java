package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.TravelResourceInfoDto;
import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 旅声详情响应
 *
 * @author xiaohong
 * @create 2017-10-30 9:35
 **/
@Data
@OpenApiMessage(service = "travelVoiceDetail", type = ApiMessageType.Response)
public class TravelVoiceDetailResponse extends ApiResponse {

    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
    private String userId;

    @OpenApiField(desc = "发布用户名称", constraint = "发布用户名称")
    private String realName;

    @OpenApiField(desc = "用户头像", constraint = "用户头像")
    private String headImg;

    @OpenApiField(desc = "标题", constraint = "标题")
    private String title;

    @OpenApiField(desc = "文章内容", constraint = "文章内容")
    private String content;

    @OpenApiField(desc = "点赞数", constraint = "点赞数")
    private Long praiseCount = 0l;

    @OpenApiField(desc = "评论数", constraint = "评论数")
    private Long reviewCount = 0l;

    @OpenApiField(desc = "发布位置名称", constraint = "发布位置名称")
    private String positionName;

    @OpenApiField(desc = "发布位置纬度", constraint = "发布位置纬度")
    private String positionLat;

    @OpenApiField(desc = "发布位置经度", constraint = "发布位置经度")
    private String positionLng;

    @OpenApiField(desc = "资源", constraint = "资源")
    private List<TravelResourceInfoDto> travelResourceDtoList = Lists.newArrayList();
}
