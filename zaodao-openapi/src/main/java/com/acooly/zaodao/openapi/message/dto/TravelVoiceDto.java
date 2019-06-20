package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiaohong
 * @create 2017-10-27 17:25
 **/
@Data
public class TravelVoiceDto implements Serializable {

    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
    private String userId;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
    private String realName;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
    private String headImg;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
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

    @OpenApiField(desc = "创建时间", constraint = "创建时间")
    private Date createTime;

    /**
     * 0-未点赞; 1-已经点赞
     */
    @OpenApiField(desc = "是否点过赞", constraint = "是否点过赞")
    private Integer praiseFlag;

    /**
     * 是否关注旅声作者 0-未关注 1-已关注
     */
    @OpenApiField(desc = "是否关注旅声作者", constraint = "是否关注旅声作者")
    private Integer focusAuthorFlag;

    @OpenApiField(desc = "资源", constraint = "资源")
    private List<TravelResourceInfoDto> travelResourceList = Lists.newArrayList();
}
