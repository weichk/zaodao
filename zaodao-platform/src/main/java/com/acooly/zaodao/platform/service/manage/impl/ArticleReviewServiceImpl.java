/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.zaodao.platform.dao.manage.ArticleDao;
import com.acooly.zaodao.platform.dao.manage.ArticleReviewDao;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.dao.manage.TourGuideDao;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import com.acooly.zaodao.platform.dto.ArticleReviewInfoDto;
import com.acooly.zaodao.platform.dto.ReplyMessageDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.entity.ArticleReview;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.service.manage.ArticleReviewService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评论回复表 Service实现
 * <p>
 * Date: 2017-06-01 14:41:42
 *
 * @author zhike
 */
@Service("articleReviewService")
public class ArticleReviewServiceImpl extends EntityServiceImpl<ArticleReview, ArticleReviewDao> implements ArticleReviewService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TourGuideDao tourGuideDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private PointAccountService pointAccountService;

    @Override
    public PageInfo<ReplyMessageDto> getReplyMessagePageEntityInfo(Integer currentPage, Integer countOfCurrentPage, String userId) {
        return getEntityDao().getArticleReviewPageEntityInfo(getMyPageInfo(currentPage, countOfCurrentPage), userId);
    }

    @Override
    public PageInfo<ArticleReviewInfoDto> getArticleReviewsByArticleId(Integer currentPageNo, Integer countOfCurrentPage, Long articleId) {
        PageInfo<ArticleReviewInfoDto> result = new PageInfo<>();
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_articleId", articleId);
        map.put("NULL_parentId", null);
        Map<String, Boolean> sortMap = Maps.newHashMap();
        sortMap.put("createTime", false);
        PageInfo<ArticleReview> articleReviewPageInfo = getEntityDao().query(getReviewPageInfo(currentPageNo, countOfCurrentPage), map, sortMap);
        if (articleReviewPageInfo != null) {
            result.setTotalCount(articleReviewPageInfo.getTotalCount());
            result.setTotalPage(articleReviewPageInfo.getTotalPage());
            result.setCurrentPage(articleReviewPageInfo.getCurrentPage());
            result.setCountOfCurrentPage(articleReviewPageInfo.getCountOfCurrentPage());
            List<ArticleReviewInfoDto> articleReviewInfoDtoList = Lists.newArrayList();
            for (ArticleReview articleReview : articleReviewPageInfo.getPageResults()) {
                ArticleReviewInfoDto articleReviewInfoDto = new ArticleReviewInfoDto();
                articleReviewInfoDto.setArticleReview(articleReview);
                String reviewUserId = articleReview.getReviewUserId();
                Customer customer = customerDao.findEntityByUserId(reviewUserId);
                articleReviewInfoDto.setHeadImg(customer.getHeadImg());
                //获取贴主用户积分
                PointAccount pointAccount = pointAccountService.findByUserName(reviewUserId);
                articleReviewInfoDto.setIntegral(pointAccount.getBalance());
                articleReviewInfoDto.setIsCertification(customer.getIsCertification());
                articleReviewInfoDto.setMobileNo(customer.getMobileNo());
                articleReviewInfoDto.setRealName(customer.getRealName());
                articleReviewInfoDto.setUserName(customer.getUserName());
                TourGuide tourGuide = tourGuideDao.findEntityByUserId(reviewUserId);
                if(tourGuide != null) {
                    articleReviewInfoDto.setTourRank(tourGuide.getTourRank().getMessage());
                }
                List<Article> articleList = articleDao.getCusArticleCount(reviewUserId);
                articleReviewInfoDto.setArticleCount(articleList.size());
                articleReviewInfoDtoList.add(articleReviewInfoDto);
            }
            result.setPageResults(articleReviewInfoDtoList);
        }
        return result;
    }

    @Override
    public List<ArticleReviewChildDto> getArticleReviewChildInfo(Long parentId, Long articleId) {
        return getEntityDao().getArticleReviewChildInfo(parentId,articleId);
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<ArticleReview> getReviewPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<ArticleReview> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<ReplyMessageDto> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<ReplyMessageDto> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    @Override
    public List<ArticleReview> getCusArticleReviews(Long articleId, String userId) {
        return getEntityDao().getCusArticleReviews(articleId,userId);
    }

    @Override
    public int getNotReadEntitysByUserId(String userId) {
        return getEntityDao().getNotReadEntitysByUserId(userId);
    }

    @Override
    public void updateEntityReadStatus(String userId) {
        getEntityDao().updateEntityReadStatus(userId);
    }
}
