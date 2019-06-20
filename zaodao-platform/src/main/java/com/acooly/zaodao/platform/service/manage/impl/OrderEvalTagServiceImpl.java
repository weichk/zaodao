/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-07
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.platform.dto.EvalTagDto;
import com.acooly.zaodao.platform.result.OrderEvalTagListResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.service.manage.OrderEvalTagService;
import com.acooly.zaodao.platform.dao.manage.OrderEvalTagDao;
import com.acooly.zaodao.platform.entity.OrderEvalTag;

import java.util.List;

/**
 * zd_order_eval_tag Service实现
 *
 * Date: 2018-05-07 20:38:48
 *
 * @author zhike
 *
 */
@Service("orderEvalTagService")
public class OrderEvalTagServiceImpl extends EntityServiceImpl<OrderEvalTag, OrderEvalTagDao> implements OrderEvalTagService {

    /**
     * 获取标签列表
     * @return
     */
    @Override
    public OrderEvalTagListResult getEvalTagList() {
        OrderEvalTagListResult result = new OrderEvalTagListResult();
        List<OrderEvalTag> list = this.getEntityDao().getAll();
        List<EvalTagDto> dtos = Lists.newArrayList();
        list.forEach(p ->{
            EvalTagDto dto = new EvalTagDto();
            BeanCopier.copy(p, dto);
            dtos.add(dto);
        });
        result.setDto(dtos);
        return result;
    }
}
