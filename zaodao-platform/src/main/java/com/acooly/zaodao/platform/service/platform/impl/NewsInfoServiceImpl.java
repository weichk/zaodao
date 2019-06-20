/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 00:45 创建
 *
 */
package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dao.platform.NewsInfoDao;
import com.acooly.zaodao.platform.dto.NewsDto;
import com.acooly.zaodao.platform.service.platform.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service("newsInfoService")
@Slf4j
public class NewsInfoServiceImpl implements NewsInfoService {

    @Autowired
    private NewsInfoDao newsInfoDao;

    @Override
    public List<NewsDto> getNewsByType(String type, int count) {
        return newsInfoDao.getNewsByType(type, count);
    }

    @Override
    public NewsDto findNesInfoPreviousOrNext(String type, Long id, boolean isNext) {
        return newsInfoDao.findNesInfoPreviousOrNext(type, id, isNext);
    }

    @Override
    public PageInfo<NewsDto> getPageNewsListByType(Integer currentPageNo,Integer countOfCurrentPage, String type) {
        return newsInfoDao.getPageNewsListByType(getMyPageInfo(currentPageNo,countOfCurrentPage), type);
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<NewsDto> getMyPageInfo(Integer currentPage,Integer countOfCurrentPage) {
        PageInfo<NewsDto> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }
}
