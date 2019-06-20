/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 00:44 创建
 *
 */
package com.acooly.zaodao.platform.service.platform;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.NewsDto;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface NewsInfoService {

    /**
     * 根据新闻类型查询新闻列表
     *
     * @param type
     * @return
     */
    List<NewsDto> getNewsByType(String type, int count);

    /**
     * 获取当前新闻的上一条或者下一条
     *
     * @param type   新闻类型
     * @param id     当前新闻ID
     * @param isNext true:下一条，false:上一条
     * @return
     */
    NewsDto findNesInfoPreviousOrNext(String type, Long id, boolean isNext);

    /**
     * 分页获取新闻列表
     *
     * @param currentPageNo
     * @param type
     * @return
     */
    PageInfo<NewsDto> getPageNewsListByType(Integer currentPageNo,Integer countOfCurrentPage, String type);
}
