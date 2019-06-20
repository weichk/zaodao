/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-22
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.enums.PointTradeType;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.platform.dao.manage.ArticleDao;
import com.acooly.zaodao.platform.dao.manage.ArticleReviewDao;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.dao.manage.TourGuideDao;
import com.acooly.zaodao.platform.dto.*;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.enums.RedBagType;
import com.acooly.zaodao.platform.order.GuideArticleListOrder;
import com.acooly.zaodao.platform.order.QueryArticleOrder;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.acooly.zaodao.platform.service.manage.RedBagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文章表 Service实现
 * <p>
 * Date: 2017-05-22 15:36:01
 *
 * @author zhike
 */
@Slf4j
@Service("articleService")
public class ArticleServiceImpl extends EntityServiceImpl<Article, ArticleDao> implements ArticleService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TourGuideDao tourGuideDao;

    @Autowired
    private ArticleReviewDao articleReviewDao;

    @Autowired
    private PointTradeService pointTradeService;

    @Autowired
    private RedBagService redBagService;

    @Autowired
    private PointAccountService pointAccountService;

    @Override
    public List<Article> getEntityByType(String type) {
        return getEntityDao().getEntityByType(type);
    }

    @Override
    public List<Article> getEntityByTypeAndLabel(String type, String label) {
        return getEntityDao().getEntityByTypeAndLabel(type, label);
    }

    @Override
    public List<Article> getArticleByDateType(String dateType) {
        return getEntityDao().getArticleByDateType(dateType);
    }

    @Override
    public List<Article> getCurrPageArticles(Integer currentPageNo) {
        return getEntityDao().getCurrPageArticles(currentPageNo);
    }

    @Override
    public List<AtricleAreaDto> getAreaList(Integer currentPageNo) {
        return getEntityDao().getAreaList(currentPageNo);
    }

    @Override
    public PageInfo<Article> getEntityListByArea(Integer currentPageNo, String area, String searchContent) {
        return this.getEntityDao().getEntityListByArea(getMyPageInfo(currentPageNo), area, searchContent);
    }

    @Override
    public List<Article> getArticlesByArea(String area, String searchContent) {
        PageInfo<Article> articlePageInfo = getEntityDao().getEntityListByArea(getMyPageInfo(1), area, searchContent);
        List<Article> articleList = articlePageInfo.getPageResults();
        return articleList;
    }

    @Override
    public ArticleInfoDto getArticleDetailsById(Long id) {
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        Article article = getEntityDao().get(id);
        articleInfoDto.setArticle(article);
        String landlordUserId = article.getUserId();
        Customer customer = customerDao.findEntityByUserId(landlordUserId);
        articleInfoDto.setHeadImg(customer.getHeadImg());
        articleInfoDto.setIsCertification(customer.getIsCertification());
        articleInfoDto.setMobileNo(customer.getMobileNo());
        articleInfoDto.setRealName(customer.getRealName());
        articleInfoDto.setUserName(customer.getUserName());
        if (StringUtils.isNotBlank(customer.getMedalCode())) {
            articleInfoDto.setMedalCodes(customer.getMedalCode().split(","));
        }
        //注释导游等级
//		TourGuide tourGuide = tourGuideDao.findEntityByUserId(landlordUserId);
//		articleInfoDto.setTourRank(tourGuide.getTourRank().getMessage());
        List<Article> articleList = getEntityDao().getCusArticleCount(landlordUserId);
        articleInfoDto.setArticleCount(articleList.size());
        // 获取贴主用户积分
        PointAccount pointAccount = pointAccountService.findByUserName(article.getUserId());
        articleInfoDto.setIntegral(pointAccount.getBalance());
        return articleInfoDto;
    }

    @Override
    public List<Article> findByUserId(String userId) {
        return getEntityDao().findByUserId(userId);
    }

    @Override
    public PageInfo<BlogArticleInfoDto> getPageEntitysByTypeAndLable(Integer currentPageNo, String articleType,
                                                                     String lable, String searchContent) {
        PageInfo<BlogArticleInfoDto> blogArticleInfoDtoPageInfo = getEntityDao()
                .getPageEntitysByTypeAndLable(getBlogArticlePageInfo(currentPageNo), articleType, lable, searchContent);
        for (BlogArticleInfoDto articleInfoDto : blogArticleInfoDtoPageInfo.getPageResults()) {
            Long reviewCount = articleReviewDao.getArticleReviewsCount(articleInfoDto.getArticleId());
            articleInfoDto.setReviewCount(reviewCount);
        }
        return blogArticleInfoDtoPageInfo;
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<Article> getMyPageInfo(Integer currentPage) {
        PageInfo<Article> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(10);
        return pageinfo;
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<BlogArticleInfoDto> getBlogArticlePageInfo(Integer currentPage) {
        PageInfo<BlogArticleInfoDto> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(10);
        return pageinfo;
    }

    @Override
    public List<Article> getHotEntitys(String articleHotType) {
        return getEntityDao().getHotEntitys(articleHotType);
    }

    @Override
    @Transactional
    public void saveWithRedBag(Article article, String hasRedBag, Long score, Integer redBagNum, String userId) {
        if (article.getId() != null) {
            getEntityDao().update(article);
        } else {
            getEntityDao().save(article);
        }
        String orderNo = Ids.oid();
        if ("true".equals(hasRedBag)) {
            if (redBagNum > score) {
                throw new BusinessException("积分必须大于红包个数");
            }
            RedBag redBag = new RedBag();
            redBag.setRefId(article.getId());
            redBag.setRedBagType(RedBagType.score.getCode());
            redBag.setSurplusNum(redBagNum);
            redBag.setTotalNum(redBagNum);
            redBag.setTotalAmount(score);
            redBag.setUserId(userId);
            redBag.setOrderNo(orderNo);
            redBag.setSurplusAmount(score);
            redBagService.save(redBag);
            PointTradeDto dto = new PointTradeDto();
            dto.setBusiId(orderNo);
            dto.setBusiData(userId);
            dto.setBusiType(PointTradeType.expense.getCode());
            dto.setBusiTypeText(PointTradeType.expense.getMessage());
            dto.setMemo("帖子红包");
            pointTradeService.pointExpense(userId, score, false, dto);
            article.setHasRedBag(1);
        }
        PointTradeDto dto = new PointTradeDto();
        dto.setBusiId(orderNo);
        dto.setBusiData(userId);
        dto.setBusiType(PointTradeType.produce.getCode());
        dto.setBusiTypeText(PointTradeType.produce.getMessage());
        dto.setMemo("发帖");
        pointTradeService.pointProduce(userId, 1, dto);
    }

    @Override
    public int countByTypeAndStatus(String userId, String code, String status) {
        return getEntityDao().countByTypeAndStatus(userId, code, status);
    }

    @Override
    public List<ArticleRewardLogDto> getArticleRewardLogList(Long articleId) {
        return getEntityDao().getArticleRewardLogList(articleId);
    }

    @Override
    public PageResult<Article> getPageArticleList(QueryArticleOrder order) {
        PageResult<Article> result = getEntityDao().getPageArticleList(order);
        replaceContent(result.getDto().getPageResults());
        return result;
    }

    @Override
    public int countUserArticles(String userId, String status) {
        return getEntityDao().countUserArticles(userId,status);
    }

    /**
     * 获取文章列表
     * @param order
     * @return
     */
    @Override
    public PageResult<GuideArticleDto> getArticleList(GuideArticleListOrder order) {
        PageResult<GuideArticleDto> pageResult = new PageResult<>();
        try{
            order.check();
            pageResult = this.getEntityDao().getPageGuideArticleList(order);
        } catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }
        return pageResult;
    }

    public void replaceContent(List<Article> articleList) {
        for (Article article : articleList) {
            String htmlStr = article.getContent(); // 含html标签的字符串
            Pattern p_html1;
            Matcher m_html1;
            String IMG_html1 = "<img\\s[^>]+/>";
            p_html1 = Pattern.compile(IMG_html1);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤IMG标签
            // <p>段落替换为换行
            htmlStr = htmlStr.replaceAll("<p .*?>", "\r\n");
            // <br><br/>替换为换行
            htmlStr = htmlStr.replaceAll("<br\\s*/?>", "\r\n");
            // 去掉其它的<>之间的东西
            htmlStr = htmlStr.replaceAll("\\<.*?>", "");
            if (htmlStr.length() > 110) {
                article.setContent(StringUtils.substring(htmlStr, 0, 110) + "......");
            } else {
                article.setContent(htmlStr);
            }

        }
    }
}
