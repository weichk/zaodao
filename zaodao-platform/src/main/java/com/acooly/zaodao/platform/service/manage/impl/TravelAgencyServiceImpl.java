/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-04
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import com.acooly.zaodao.platform.order.TravelAgencyAddOrder;
import com.acooly.zaodao.platform.order.TravelAgencyListOrder;
import com.acooly.zaodao.platform.result.TravelAgencyAddResult;
import com.acooly.zaodao.platform.result.TravelAgencyListResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.service.manage.TravelAgencyService;
import com.acooly.zaodao.platform.dao.manage.TravelAgencyDao;
import com.acooly.zaodao.platform.entity.TravelAgency;

import java.util.List;

/**
 * 旅行社 Service实现
 *
 * Date: 2018-05-04 16:34:01
 *
 * @author zhike
 *
 */
@Slf4j
@Service("travelAgencyService")
public class TravelAgencyServiceImpl extends EntityServiceImpl<TravelAgency, TravelAgencyDao> implements TravelAgencyService {

    /**
     * 获取旅行社列表
     */
    @Override
    public TravelAgencyListResult getTravelAgencyInfos(TravelAgencyListOrder order) {
        TravelAgencyListResult result = new TravelAgencyListResult();
        try {
            order.check();
            List<TravelAgency> list = this.getEntityDao().getTravelAgencyList(order.getUserId());
            List<TravelAgencyDto> travelAgencyDtoList = Lists.newArrayList();
            list.forEach(p ->{
                TravelAgencyDto dto = new TravelAgencyDto();
                BeanCopier.copy(p, dto);
                travelAgencyDtoList.add(dto);
            });
            result.setDto(travelAgencyDtoList);
        } catch (BusinessException e) {
            log.info("操作失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("操作失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("操作失败！");
        }
        return result;
    }

    /**
     * 添加旅行社
     */
    @Override
    public TravelAgencyAddResult addTravelAgency(TravelAgencyAddOrder order) {
        TravelAgencyAddResult result = new TravelAgencyAddResult();
        try{
            order.check();
            TravelAgency travelAgency = new TravelAgency();
            travelAgency.setUserId(order.getUserId());
            travelAgency.setAgencyName(order.getAgencyName());
            travelAgency.setLicenseNo(order.getLicenseNo());
            travelAgency.setContactName(order.getContactName());
            travelAgency.setContactPhone(order.getContactPhone());
            checkTravelAgencyExist(travelAgency);
            this.getEntityDao().save(travelAgency);
            TravelAgencyDto dto = new TravelAgencyDto();
            BeanCopier.copy(travelAgency, dto);
            result.setTravelAgencyDto(dto);
        }catch (BusinessException e){
            log.info("保存失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("保存失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("保存失败！");
        }
        return result;
    }

    /**
     * 检查用户添加的旅行社是否存在
     */
    private void checkTravelAgencyExist(TravelAgency travelAgency){
        TravelAgency agency = this.getEntityDao().findByUserIdAndAgencyName(travelAgency.getUserId(), travelAgency.getAgencyName());
        if(agency != null){
            throw new BusinessException(String.format("用户已经添加旅行社:%s", travelAgency.getAgencyName()));
        }

        agency = this.getEntityDao().findByUserIdAndLicenseNo(travelAgency.getUserId(), travelAgency.getLicenseNo());
        if(agency != null){
            throw new BusinessException(String.format("用户已经添加旅行社许可证:%s", travelAgency.getLicenseNo()));
        }
    }
}
