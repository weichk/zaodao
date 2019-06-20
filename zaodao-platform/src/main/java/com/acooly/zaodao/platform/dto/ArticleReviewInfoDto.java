/*
 * 修订记录:
 * zhike@yiji.com 2017-06-06 14:47 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.entity.ArticleReview;
import lombok.Data;

import java.io.Serializable;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ArticleReviewInfoDto implements Serializable {

    private String headImg;

    private String mobileNo;

    private String realName;

    private String userName;

    private Integer articleCount;

    private Long integral;

    private String tourRank;

    private Integer isCertification = 0;

    /**积分等级名称*/
    private String pointGradeTitle;

    private ArticleReview articleReview;
}
