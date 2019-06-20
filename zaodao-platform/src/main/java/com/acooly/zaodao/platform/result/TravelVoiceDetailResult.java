package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 旅声详情
 *
 * @author xiaohong
 * @create 2017-10-30 10:01
 **/
@Data
public class TravelVoiceDetailResult extends ResultBase {
    /**
     * 旅声ID
     */
    private Long travelVoiceId;

    /**
     * 发布用户ID
     */
    private String userId;

    /**
     * 发布用户名称
     */
    private String realName;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Long praiseCount = 0l;

    /**
     * 评论数
     */
    private Long reviewCount = 0l;

    /**
     * 发布位置名称
     */
    private String positionName;

    /**
     * 发布位置纬度
     */
    private String positionLat;

    /**
     * 发布位置经度
     */
    private String positionLng;

    /**
     * 资源
     */
    private List<TravelResourceDto> travelResourceDtoList = Lists.newArrayList();
}
