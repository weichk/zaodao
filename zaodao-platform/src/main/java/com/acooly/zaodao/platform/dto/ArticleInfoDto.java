/*
 * 修订记录:
 * zhike@yiji.com 2017-06-03 11:22 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.entity.Article;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ArticleInfoDto implements Serializable {

    private String headImg;

    private String mobileNo;

    private String realName;

    private String userName;

    private Integer articleCount;

    private Long integral;

    private String tourRank;

    private Integer isCertification = 0;

    /**
     * 是否为版主
     */
    private Integer isModerator = 0;

    /**
     * 版主权限
     */
    private String moderatorPermission;

    private Article article;

    /**
     * 积分等级名称
     */
    private String pointGradeTitle;

    /**
     * 是否点赞
     */
    private Integer havaPraise = 0;

    /**
     * 是否点赞
     */
    private Integer havaEnshrine = 0;

    /**
     * 帖子打赏用户列表
     */
    private List<ArticleRewardLogDto> articleRewardLogDtoList;

    /**
     * 图章列表
     */
    List<StampCodeDto> stampCodes;

    /**
     *用户勋章
     */
    String[] medalCodes;
}
