/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 16:14 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import com.acooly.zaodao.platform.dto.ReplyMessageDto;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface ArticleReviewCusDao {

    /**
     * 分页获取用户评论回复消息列表
     * @param userId
     * @return
     */
    PageInfo<ReplyMessageDto> getArticleReviewPageEntityInfo(PageInfo<ReplyMessageDto> pageInfo, String userId);

    /**
     * 获取评论回复列表
     * @param parentId
     * @param articleId
     * @return
     */
    List<ArticleReviewChildDto> getArticleReviewChildInfo(Long parentId, Long articleId);

    /**
     * 获取当前会员未读文章留言信息条数
     * @param userId
     * @return
     */
    int getNotReadEntitysByUserId(String userId);

    /**
     * 跟新为已读状态
     * @param userId
     */
    void updateEntityReadStatus(String userId);
}
