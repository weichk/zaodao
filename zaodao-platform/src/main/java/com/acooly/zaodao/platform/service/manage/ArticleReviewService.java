/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import com.acooly.zaodao.platform.dto.ArticleReviewInfoDto;
import com.acooly.zaodao.platform.dto.ReplyMessageDto;
import com.acooly.zaodao.platform.entity.ArticleReview;

import java.util.List;

/**
 * 评论回复表 Service接口
 *
 * Date: 2017-06-01 14:41:42
 * @author zhike
 *
 */
public interface ArticleReviewService extends EntityService<ArticleReview> {
    /**
     * 分页获取用户回复消息列表
     * @param userId
     * @return
     */
    PageInfo<ReplyMessageDto> getReplyMessagePageEntityInfo(Integer currentPage, Integer countOfCurrentPage, String userId);

    /**
     * 分页获取文章回复列表
     *
     * @param articleId
     * @return
     */
    PageInfo<ArticleReviewInfoDto> getArticleReviewsByArticleId(Integer currentPageNo, Integer countOfCurrentPage, Long articleId);

    /**
     * 获取评论回复列表
     * @param parentId
     * @return
     */
    List<ArticleReviewChildDto> getArticleReviewChildInfo(Long parentId, Long articleId);

    /**
     * 获取当前用户评论列表
     * @param articleId
     * @param userId
     * @return
     */
    List<ArticleReview> getCusArticleReviews(Long articleId, String userId);


    /**
     * 获取当前会员未读文章留言信息条数
     * @param userId
     * @return
     */
    int getNotReadEntitysByUserId(String userId);

    /**
     * 更新状态为已读
     * @param userId
     */
    void updateEntityReadStatus(String userId);
}
