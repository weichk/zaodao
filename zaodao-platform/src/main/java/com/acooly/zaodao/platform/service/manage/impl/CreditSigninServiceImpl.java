/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Dates;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.platform.dao.manage.CreditSigninDao;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.entity.CreditSignin;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.manage.CreditSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 积分签到记录 Service实现
 * <p>
 * Date: 2017-06-15 15:23:25
 *
 * @author zhike
 */
@Service("creditSigninService")
public class CreditSigninServiceImpl extends EntityServiceImpl<CreditSignin, CreditSigninDao> implements CreditSigninService {

    @Autowired
    private PointTradeService pointTradeService;

    @Resource
    private CustomerDao customerDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CreditSignin CerditSignin(Customer customer) {
        try {
            //保存签到记录
            CreditSignin creditSignin = calcSignPoint(customer.getId());
            return  creditSignin;
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw new BusinessException(e.getMessage());
            } else {
                throw new BusinessException("签到失败");
            }
        }
    }

    /**
     * 计算签到积分
     *
     * @param customerId
     * @return
     */
    protected CreditSignin calcSignPoint(Long customerId) {
        Customer customer = customerDao.get(customerId);
        String userId = customer.getUserId();
        Date today = new Date();
        String todayStr = Dates.format(today, Dates.CHINESE_DATE_FORMAT_LINE);
        String yesterdayStr = Dates.format(Dates.addDay(today, -1), Dates.CHINESE_DATE_FORMAT_LINE);
        CreditSignin creditSignin = getEntityDao().lastRecord(userId);
        int times = 1;
        if (creditSignin != null) {
            String signTime = Dates.format(creditSignin.getSignTime(), Dates.CHINESE_DATE_FORMAT_LINE);
            if (signTime.equals(todayStr)) {
                throw new RuntimeException("对不起，每天只能签到一次");
            }
            if (signTime.equals(yesterdayStr)) {
                times = creditSignin.getTimes();
                times = times+1;
            }
        } else {
            creditSignin = new CreditSignin();
        }
        creditSignin.setSignTime(new Date());
        creditSignin.setUsername(customer.getUserId());
        creditSignin.setTimes(times);
        getEntityDao().save(creditSignin);
        //签到获得积分
        int point = 1;
        if(times >= 20) {
            point = 2;
        }
        PointTradeDto pointTradeDto = new PointTradeDto();
        pointTradeDto.setBusiId(creditSignin.getId() + "");
        pointTradeDto.setBusiType("cerditSignin");
        pointTradeDto.setBusiTypeText("签到");
        pointTradeService.pointProduce(customer.getUserId(), point, pointTradeDto);
        return creditSignin;
    }

    @Override
    public CreditSignin isCerditSignin(Customer customer) {
        String userId = customer.getUserId();
        Date today = new Date();
        String todayStr = Dates.format(today, Dates.CHINESE_DATE_FORMAT_LINE);
        CreditSignin creditSignin = getEntityDao().lastRecord(userId);
        if (creditSignin != null) {
            String signTime = Dates.format(creditSignin.getSignTime(), Dates.CHINESE_DATE_FORMAT_LINE);
            if (signTime.equals(todayStr)) {
                creditSignin.setTodayCreditSignin(true);
            }
        }
        return creditSignin;
    }
}
