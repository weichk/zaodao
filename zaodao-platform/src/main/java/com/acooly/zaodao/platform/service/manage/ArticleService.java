/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-22
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.*;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.order.GuideArticleListOrder;
import com.acooly.zaodao.platform.order.QueryArticleOrder;

import java.util.List;

/**
 * 文章表 Service接口
 * <p>
 * Date: 2017-05-22 15:36:00
 *
 * @author zhike
 */
public interface ArticleService extends EntityService<Article> {

    List<Article> getEntityByType(String type);

    List<Article> getEntityByTypeAndLabel(String type, String label);

    /**
     * 根据日期类型查询点击排行列表（day:日,week:周,moth:月）
     *
     * @param dateType
     * @return
     */
    List<Article> getArticleByDateType(String dateType);

    /**
     * 获取当过前也展示的地区列表
     *
     * @param currentPageNo
     * @return
     */
    List<AtricleAreaDto> getAreaList(Integer currentPageNo);

    /**
     * 获取当前也需要显示的地区下面的帖子
     *
     * @param currentPageNo
     * @return
     */
    List<Article> getCurrPageArticles(Integer currentPageNo);

    /**
     * 根据区域分页获取帖子
     *
     * @param area
     * @return
     */
    PageInfo<Article> getEntityListByArea(Integer currentPageNo, String area,String searchContent);

    /**
     * 获取区域文章列表
     *
     * @param area
     * @return
     */
    List<Article> getArticlesByArea(String area,String searchContent);

    /**
     * 根据帖子ID获取帖子相关的详细信息
     *
     * @param id
     * @return
     */
    ArticleInfoDto getArticleDetailsById(Long id);

    /**
     * 根据userId获取帖子列表
     * @param userId
     * @return
     */
    List<Article> findByUserId(String userId);

    /**
     * 分页查询某个子标签下导游中心文章列表
     * @param currentPageNo
     * @param articleType
     * @param lable
     * @return
     */
    PageInfo<BlogArticleInfoDto> getPageEntitysByTypeAndLable(Integer currentPageNo, String articleType, String lable,String searchContent);

    /**
     * 获取热门帖子列表
     * @param articleHotType
     * @return
     */
    List<Article> getHotEntitys(String articleHotType);

    /**
     * 发布红包帖子
     * @param article
     * @param hasRedBag
     * @param score
     * @param redBagNum
     * @param userId
     */
    void saveWithRedBag(Article article, String hasRedBag, Long score, Integer redBagNum, String userId);

    /**
     * 根据类型统计帖子数量
     *
     * @param userId
     * @param code
     * @return
     */
    int countByTypeAndStatus(String userId, String code, String status);

    /**
     * 获取文章打赏人员列表
     * @param article
     * @return
     */
    List<ArticleRewardLogDto> getArticleRewardLogList(Long article);

    /**
     * 根据order获取分页文章信息
     * @param order
     * @return
     */
    PageResult<Article> getPageArticleList(QueryArticleOrder order);

    /**
     * 根据userId及状态统计文章数量
     * @param userId
     * @param status
     * @return
     */
    int countUserArticles(String userId, String status);

    /**
     * 获取文章列表
     * @param order
     * @return
     */
    PageResult<GuideArticleDto> getArticleList(GuideArticleListOrder order);
}
