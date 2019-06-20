/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 23:27 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.ArticleCusDao;
import com.acooly.zaodao.platform.dto.ArticleRewardLogDto;
import com.acooly.zaodao.platform.dto.AtricleAreaDto;
import com.acooly.zaodao.platform.dto.BlogArticleInfoDto;
import com.acooly.zaodao.platform.dto.GuideArticleDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.order.GuideArticleListOrder;
import com.acooly.zaodao.platform.order.QueryArticleOrder;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class ArticleDaoImpl extends AbstractJdbcTemplateDao implements ArticleCusDao {

  @Override
  public List<Article> getArticleByDateType(String dateType) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT za.* from zd_article za");
    sql.append(" WHERE 1 = 1");
    if (StringUtils.equals(dateType, "day")) {
      sql.append(" and to_days(za.create_time) = to_days(now())");
    } else if (StringUtils.equals(dateType, "week")) {
      sql.append(" and YEARWEEK(date_format(za.create_time,'%Y-%m-%d')) = YEARWEEK(now())");
    } else {
      sql.append(" and date_format(za.create_time,'%Y-%m')=date_format(now(),'%Y-%m')");
    }
    sql.append(" and za.article_status='published' order by za.read_count desc limit 5");
    List<Article> result = queryForList(sql.toString(), Article.class);
    return result;
  }

  @Override
  public List<AtricleAreaDto> getAreaList(Integer currentPageNo) {
    Integer minFix = (currentPageNo - 1) * 4;
    Integer maxFix = currentPageNo * 4;
    String sql =
        "SELECT area areaName FROM zd_article GROUP BY area LIMIT " + minFix + "," + maxFix + "";
    List<AtricleAreaDto> result = queryForList(sql, AtricleAreaDto.class);
    return result;
  }

  @Override
  public List<Article> getCurrPageArticles(Integer currentPageNo) {
    List<Article> result = Lists.newArrayList();
    List<AtricleAreaDto> areas = getAreaList(currentPageNo);
    if (areas.size() > 0) {
      String inStr = getInString(areas);
      StringBuilder sql = new StringBuilder();
      sql.append("select a1.* from zd_article a1");
      sql.append(
          " inner join (select a.area,a.create_time from zd_article a left join zd_article b");
      sql.append(" on a.area=b.area and a.create_time<=b.create_time");
      sql.append(" group by a.area,a.create_time");
      sql.append(" having count(b.create_time)<=3) b1");
      sql.append(
          " on a1.area=b1.area and a1.create_time=b1.create_time and a1.area in (" + inStr + ")");
      sql.append(" and a1.article_status='published' order by a1.area,a1.create_time desc");
      result = queryForList(sql.toString(), Article.class);
    }
    return result;
  }

  private String getInString(List<AtricleAreaDto> areas) {
    StringBuilder inStr = new StringBuilder();
    for (AtricleAreaDto area : areas) {
      inStr.append("'" + area.getAreaName() + "',");
    }
    String resultStr = inStr.toString().substring(0, inStr.length() - 1);
    return resultStr;
  }

  @Override
  public PageInfo<Article> getEntityListByArea(
      PageInfo<Article> pageInfo, String area, String searchContent) {
    String sql = "SELECT * FROM zd_article where area = '" + area + "'";
    if (!StringUtils.isBlank(searchContent)) {
      sql +=
          " and (title like '%"
              + searchContent
              + "%' or content like '%"
              + searchContent
              + "%' or scenic like '%"
              + searchContent
              + "%')";
    }
    sql += " and article_status='published' ORDER BY create_time desc";
    PageInfo<Article> result = query(pageInfo, sql.toString(), Article.class);
    return result;
  }

  @Override
  public PageInfo<BlogArticleInfoDto> getPageEntitysByTypeAndLable(
      PageInfo<BlogArticleInfoDto> pageInfo,
      String articleType,
      String lable,
      String searchContent) {
    StringBuilder sql = new StringBuilder();
    sql.append(
        "SELECT za.label,za.essence_status essenceStatus,za.up_status upStatus,za.id articleId,za.title ,"
            + "za.read_count readCount,za.create_time createTime,za.stamp_code stampCode,zc.head_img headImg,zc"
            + ".real_name realName,zc.user_name userName,za.has_red_bag havaRedPacket");
    sql.append(" FROM zd_article za");
    sql.append(" INNER JOIN zd_customer zc ON za.user_id = zc.user_id");
    sql.append(" WHERE 1=1");
    if (!StringUtils.equals("guideTreeHole", articleType) && StringUtils.isNotBlank(lable)) {
      sql.append(" AND za.label = '" + lable + "'");
    }
    if (StringUtils.isNotBlank(articleType)) {
      sql.append(" and za.article_type = '" + articleType + "'");
    }
    if (!StringUtils.isBlank(searchContent)) {
      sql.append(
          " and (za.title like '%"
              + searchContent
              + "%' or zc.real_name like '%"
              + searchContent
              + "%')");
    }
    sql.append(
        " and za.article_status='published' ORDER BY za.essence_status DESC,za.up_status DESC,za.create_time DESC");
    PageInfo<BlogArticleInfoDto> result = query(pageInfo, sql.toString(), BlogArticleInfoDto.class);
    return result;
  }

  @Override
  public List<Article> getHotEntitys(String articleHotType) {
    String sql =
        "SELECT za.id,za.title,za.label,za.cover,za.read_count readCount,za.create_time createTime,za.content"
            + ",za.stamp_code as stampCode,zc.head_img as headImg,zc.user_name as userName FROM zd_article as za,"
            + "zd_customer as zc"
            + " where zc.user_id=za.user_id and za.article_hot_type = '"
            + articleHotType
            + "' AND "
            + " za.article_status='published'"
            + " ORDER BY za.read_count DESC,za.up_status DESC,za.create_time DESC"
            + " LIMIT 25";
    List<Article> articles = queryForList(sql, Article.class);
    return articles;
  }

  @Override
  public List<ArticleRewardLogDto> getArticleRewardLogList(Long articleId) {
    String sql =
        "SELECT zc.user_id userId,zc.head_img headImg FROM zd_article_reward_log zarl"
            + " INNER JOIN zd_customer zc ON zarl.user_id = zc.user_id"
            + " WHERE zarl.business_id = "
            + articleId
            + " group by zc.user_id,zc.head_img limit 47";
    List<ArticleRewardLogDto> articleRewardLogs = queryForList(sql, ArticleRewardLogDto.class);
    return articleRewardLogs;
  }

  @Override
  public PageResult<Article> getPageArticleList(QueryArticleOrder order) {
    PageResult<Article> result = new PageResult<>();
    StringBuilder sql = new StringBuilder();
    sql.append(
        "select za.*,zc.head_img as headImg,zc.user_name as userName,zc.real_name as realName from zd_article za,zd_customer as zc "
            + "WHERE za.user_id=zc.user_id ");
    if (Strings.isNotBlank(order.getUserId())) {
      sql.append(" and za.user_id='" + order.getUserId() + "'");
    }
    if (order.getArticleStatus() != null) {
      sql.append(" and za.article_status='" + order.getArticleStatus() + "'");
    }
    if (order.getArticleHotType() != null) {
      sql.append(" and za.article_hot_type='" + order.getArticleHotType() + "'");
    }
    if (order.getArticleType() != null) {
      sql.append(" and za.article_type='" + order.getArticleType() + "'");
    }
    if (order.getEssenceStatus() != null) {
      sql.append(" and za.essence_status=" + order.getEssenceStatus() + "");
    }
    if (Strings.isNotBlank(order.getKeywords())) {
      sql.append(" and (za.title like '%" + order.getKeywords() + "%'");
      sql.append(" or za.area like '%" + order.getKeywords() + "%'");
      sql.append(" or zc.real_name like '%" + order.getKeywords() + "%')");
    }
    if (ArticleHotType.essence.equals(order.getArticleHotType())) {
      sql.append(" ORDER BY za.read_count DESC,za.up_status DESC,za.create_time DESC");
    } else {
      sql.append(" ORDER BY za.up_status DESC,za.essence_status DESC,za.create_time DESC");
    }
    PageInfo<Article> queryResult = query(order.getPageInfo(), sql.toString(), Article.class);
    result.setDto(queryResult);
    return result;
  }

  /**
   * 获取用户文章列表
   * @param order
   * @return
   */
  @Override
  public PageResult<GuideArticleDto> getPageGuideArticleList(GuideArticleListOrder order) {
    PageResult<GuideArticleDto> result = new PageResult<>();
    StringBuilder sbSql = new StringBuilder();
    sbSql.append("SELECT a.id AS articleId, a.title,a.content,a.praise_count AS praiseCount,a.create_time AS createTime,IFNULL(tb.reviewCount,0) AS reviewCount");
    sbSql.append(" FROM zd_article a LEFT JOIN (");
    sbSql.append(" SELECT COUNT(article_id) AS reviewCount,article_id FROM zd_article_review b JOIN (");
    sbSql.append(" SELECT a.id, a.title,a.content,a.praise_count AS praiseCount,a.create_time AS createTime ");
    sbSql.append(String.format(" FROM zd_article a WHERE a.user_id='%s'", order.getUserId()));
    sbSql.append(" ) ta ON b.article_id=ta.id ");
    sbSql.append(" GROUP BY b.article_id ");
    sbSql.append(") tb ON a.id=tb.article_id ");
    sbSql.append(String.format(" WHERE a.user_id='%s'", order.getUserId()));

    PageInfo<GuideArticleDto> guideArticleDtoPageInfo = query(order.getPageInfo(), sbSql.toString(), GuideArticleDto.class);
    result.setDto(guideArticleDtoPageInfo);

    return result;
  }
}
