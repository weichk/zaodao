package com.acooly.zaodao.platform.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 旅声
 *
 * @author xiaohong
 * @create 2017-10-27 17:30
 **/
@Data
public class TravelVoiceDto implements Serializable {
    /**
     * 旅声ID
     */
    private Long travelVoiceId;

    /** 发布用户ID */
    private String userId;
    /**
     * 发布用户名称
     */
    private String realName;
    /**
     * 用户头像
     */
    private String headImg;

    /** 标题 */
    private String title;

    /** 文章内容 */
    private String content;

    /** 点赞数 */
    private Long praiseCount = 0l;

    /** 评论数 */
    private Long reviewCount = 0l;

    /** 发布位置名称 */
    private String positionName;

    /** 发布位置纬度 */
    private String positionLat;

    /** 发布位置经度 */
    private String positionLng;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否点过赞 0-未点赞 1-已点赞
     */
    private Integer praiseFlag;

    /**
     * 是否关注旅声作者 0-未关注 1-已关注
     */
    private Integer focusAuthorFlag;
    /**
     * 资源
     */
    private List<TravelResourceDto> travelResourceList = Lists.newArrayList();
}
