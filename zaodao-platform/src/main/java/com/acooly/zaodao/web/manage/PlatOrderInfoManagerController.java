/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 订单表 管理控制器
 *
 * @author zhike
 * Date: 2017-05-24 23:14:32
 */
@Controller
@RequestMapping(value = "/manage/customer/platOrderInfo")
public class PlatOrderInfoManagerController extends AbstractJQueryEntityController<PlatOrderInfo, PlatOrderInfoService> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private PlatOrderInfoService platOrderInfoService;


    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allOrderStatuss", PlatOrderInfoOrderStatus.mapping());
    }

    @Override
    protected PlatOrderInfo onSave(HttpServletRequest request, HttpServletResponse response, Model model, PlatOrderInfo entity, boolean isCreate) throws Exception {
        entity.setOrderAmount(entity.getOrderAmount() * 100);
        entity.setPricePerDay(entity.getPricePerDay() * 100);
        return super.onSave(request, response, model, entity, isCreate);
    }
}
