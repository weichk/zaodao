/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ArticleReview;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 评论回复表 JPA Dao
 * <p>
 * Date: 2017-06-01 14:41:42
 *
 * @author zhike
 */
public interface ArticleReviewDao extends EntityJpaDao<ArticleReview, Long>, ArticleReviewCusDao {

    @Query(nativeQuery = true, value = "SELECT count(1) FROM zd_article_review where article_id = ?1")
    Long getArticleReviewsCount(Long articleId);

    /**
     * 获取当前用户评论列表
     * @param articleId
     * @param userId
     * @return
     */
    @Query(value = "FROM ArticleReview where articleId = ?1 and reviewUserId = ?2")
    List<ArticleReview> getCusArticleReviews(Long articleId, String userId);
}
