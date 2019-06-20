/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-07
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dto.GuideEvalTagDto;
import com.acooly.zaodao.platform.order.GuideEvalTagsOrder;
import com.acooly.zaodao.platform.result.GuideTagListResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.service.manage.OrderGuideTagService;
import com.acooly.zaodao.platform.dao.manage.OrderGuideTagDao;
import com.acooly.zaodao.platform.entity.OrderGuideTag;

import java.util.List;

/**
 * zd_order_guide_tag Service实现
 *
 * Date: 2018-05-07 20:39:18
 *
 * @author zhike
 *
 */
@Slf4j
@Service("orderGuideTagService")
public class OrderGuideTagServiceImpl extends EntityServiceImpl<OrderGuideTag, OrderGuideTagDao> implements OrderGuideTagService {

    /**
     * 获取导游标签统计
     */
    @Override
    public GuideTagListResult getGuideEvalTags(GuideEvalTagsOrder order) {
        GuideTagListResult result = new GuideTagListResult();
        try{
            order.check();
            List<GuideEvalTagDto> list = Lists.newArrayList();
            List<Object[]> objs = this.getEntityDao().getGuideEvalTags(order.getGuideUserId());
            objs.forEach(p ->{
                Long tagCount = Long.parseLong(p[0].toString());
                String tagContent = p[1].toString();
                list.add(new GuideEvalTagDto(tagCount, tagContent));
            });
            result.setDto(list);
        } catch (BusinessException e){
            log.info("获取导游标签失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
            e.printStackTrace();
        }
        return result;
    }
}
