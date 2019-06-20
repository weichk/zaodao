/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 评论回复表 Entity
 *
 * @author zhike
 *         Date: 2017-06-01 15:14:52
 */
@Data
@Entity
@Table(name = "zd_article_review")
public class ArticleReview extends AbstractEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 回复的文章ID
     */
    private Long articleId;

    /**
     * 评论人ID
     */
    @Size(max = 64)
    private String reviewUserId;

    /**
     * 回复的评论ID
     */
    private Long parentId;

    /**
     * 评论内容
     */
    @Size(max = 0)
    private String content;

    /**
     * 被回复数
     */
    @NotNull
    private Long replyCount = 0l;

    /**
     * 查看状态
     */
    @NotNull
    private Integer readStatus = 0;

    /**
     * 评论回复列表
     */
    @Transient
    private List<ArticleReviewChildDto> articleReviewChildDtoList;

    /** 是否屏蔽 */
    private Integer shieldStatus = 0;
}
