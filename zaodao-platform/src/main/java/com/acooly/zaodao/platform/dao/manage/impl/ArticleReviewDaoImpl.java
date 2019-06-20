/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 16:17 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.ArticleReviewCusDao;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import com.acooly.zaodao.platform.dto.ReplyMessageDto;

import java.util.List;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class ArticleReviewDaoImpl extends AbstractJdbcTemplateDao implements ArticleReviewCusDao {

    @Override
    public PageInfo<ReplyMessageDto> getArticleReviewPageEntityInfo(PageInfo<ReplyMessageDto> pageInfo, String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT zar.id replyMessageId,zar.read_status readStatus,zar.create_time createTime,zc.head_img headImg,zc.real_name userName,za.title articleTitle,za.id articleId FROM zd_article_review zar");
        sql.append(" INNER JOIN zd_customer zc ON zar.review_user_id = zc.user_id");
        sql.append(" INNER JOIN zd_article za ON zar.article_id = za.id ");
        sql.append(" WHERE zar.article_id IN (SELECT id from zd_article where user_id = '" + userId + "') order by " +
                "zar.create_time desc");
        PageInfo<ReplyMessageDto> replyMessageDtoPageInfo = query(pageInfo, sql.toString(), ReplyMessageDto.class);
        return replyMessageDtoPageInfo;
    }

    @Override
    public List<ArticleReviewChildDto> getArticleReviewChildInfo(Long parentId, Long articleId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT zc.user_name userName, zc.real_name realName,zc.mobile_no mobileNo,zar.content content,zar" +
                ".create_time " +
                "createTime FROM zd_article_review zar");
        sql.append(" INNER JOIN zd_customer zc ON zar.review_user_id = zc.user_id");
        sql.append(" WHERE zar.parent_id = " + parentId + " AND zar.article_id = " + articleId + "");
        List<ArticleReviewChildDto> articleReviewChildDtos = queryForList(sql.toString(), ArticleReviewChildDto.class);
        return articleReviewChildDtos;
    }

    @Override
    public int getNotReadEntitysByUserId(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT zar.id FROM zd_article_review zar");
        sql.append(" WHERE zar.article_id IN (SELECT id from zd_article where user_id = '" + userId + "') and zar.read_status=0");
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql.toString());
        return result.size();
    }

    @Override
    public void updateEntityReadStatus(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("update zd_article_review zar set zar.read_status=1");
        sql.append(" WHERE zar.article_id IN (SELECT id from zd_article where user_id = '" + userId + "')");
        jdbcTemplate.update(sql.toString());
    }
}
