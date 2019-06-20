/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ShopOrderInfoDao;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.service.manage.ShopOrderInfoService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商城订单信息 Service实现
 * <p>
 * Date: 2017-06-15 15:23:25
 *
 * @author zhike
 */
@Service("shopOrderInfoService")
public class ShopOrderInfoServiceImpl extends EntityServiceImpl<ShopOrderInfo, ShopOrderInfoDao> implements ShopOrderInfoService {

    @Override
    public ShopOrderInfo findByOrderNo(String orderNo) {
        return getEntityDao().findByOrderNo(orderNo);
    }

    @Override
    public ShopOrderInfo findById(long id) {
        return getEntityDao().findOne(id);
    }

    @Override
    public PageInfo<ShopOrderInfo> queryPageByUserName(Integer currentPage, Integer countOfCurrentPage, String userName) {
        Map<String, Object> queryParm = Maps.newHashMap();
        queryParm.put("EQ_userName", userName);
        return getEntityDao().query(getMyPageInfo(currentPage, countOfCurrentPage), queryParm, null);
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<ShopOrderInfo> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<ShopOrderInfo> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }
}
