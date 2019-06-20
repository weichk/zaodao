package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.TradeCaListRequest;
import com.acooly.zaodao.openapi.message.common.TradeCaListResponse;
import com.acooly.zaodao.platform.dto.PointTradeInfDto;
import com.acooly.zaodao.platform.order.TradeCaListOrder;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 钙片明细列表
 *
 * @author xiaohong
 * @create 2017-11-29 19:21
 **/
@OpenApiService(name = "tradeCaList", desc = "钙片明细列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class TradeCaListApiService extends BaseApiService<TradeCaListRequest, TradeCaListResponse> {
    @Autowired
    private CustomerService customerService;
    @Override
    protected void doService(TradeCaListRequest request, TradeCaListResponse response) {
        TradeCaListOrder order = request.toOrder(TradeCaListOrder.class);
        order.setGid(Ids.gid());
        PageResult<PointTradeInfDto> result = customerService.getPointTradeList(order);
        result.throwExceptionIfNotSuccess();
        response.setPageResult(result, (u,t)->{});
    }
}
