package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.EvalTagInfoDto;
import com.acooly.zaodao.openapi.message.orderInfo.OrderEvalTagListRequest;
import com.acooly.zaodao.openapi.message.orderInfo.OrderEvalTagListResponse;
import com.acooly.zaodao.platform.result.OrderEvalTagListResult;
import com.acooly.zaodao.platform.service.manage.OrderEvalTagService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 评价标签列表
 *
 * @author xiaohong
 * @create 2018-05-11 9:51
 **/
@OpenApiService(name = "orderEvalTagList", desc = "评价标签列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class OrderEvalTagListApiService extends BaseApiService<OrderEvalTagListRequest, OrderEvalTagListResponse> {
    @Autowired
    private OrderEvalTagService orderEvalTagService;

    @Override
    protected void doService(OrderEvalTagListRequest request, OrderEvalTagListResponse response) {
        OrderEvalTagListResult result = orderEvalTagService.getEvalTagList();
        result.throwExceptionIfNotSuccess();

        List<EvalTagInfoDto> list = Lists.newArrayList();
        result.getDto().forEach(p ->{
            EvalTagInfoDto dto = new EvalTagInfoDto();
            BeanCopier.copy(p, dto);
            list.add(dto);
        });
        response.setRows(list);
    }
}
