/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 23:26 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.zaodao.platform.dto.ArticleRewardLogDto;
import com.acooly.zaodao.platform.dto.AtricleAreaDto;
import com.acooly.zaodao.platform.dto.BlogArticleInfoDto;
import com.acooly.zaodao.platform.dto.GuideArticleDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.order.GuideArticleListOrder;
import com.acooly.zaodao.platform.order.QueryArticleOrder;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface ArticleCusDao {

    /**
     * 根据日期类型查询点击排行列表（day:日,week:周,moth:月）
     *
     * @param dateType
     * @return
     */
    List<Article> getArticleByDateType(String dateType);

    /**
     * 获取当过前也展示的地区列表
     * @param currentPageNo
     * @return
     */
    List<AtricleAreaDto> getAreaList(Integer currentPageNo);

    /**
     * 获取当前也需要显示的地区下面的帖子
     * @param currentPageNo
     * @return
     */
    List<Article> getCurrPageArticles(Integer currentPageNo);

    /**
     * 分页获取地区帖子列表
     * @param pageInfo
     * @param area
     * @return
     */
    PageInfo<Article> getEntityListByArea(PageInfo<Article> pageInfo, String area,String searchContent);

    /**
     * 分页查询某个子标签下导游中心文章列表
     * @param pageInfo
     * @param articleType
     * @param lable
     * @return
     */
    PageInfo<BlogArticleInfoDto> getPageEntitysByTypeAndLable(PageInfo<BlogArticleInfoDto> pageInfo, String articleType,String lable,String searchContent);

    /**
     * 获取热门帖子列表
     * @param articleHotType
     * @return
     */
    List<Article> getHotEntitys(String articleHotType);

    /**
     * 获取文章打赏人员列表
     * @param article
     * @return
     */
    List<ArticleRewardLogDto> getArticleRewardLogList(Long article);

    /**
     * 查询文章列表
     * @param order
     * @return
     */
    PageResult<Article> getPageArticleList(QueryArticleOrder order);


    /**
     * 查询用户(导游)文章列表
     * @param order
     * @return
     */
    PageResult<GuideArticleDto> getPageGuideArticleList(GuideArticleListOrder order);
}
