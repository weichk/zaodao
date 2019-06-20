package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.TradeListRequest;
import com.acooly.zaodao.openapi.message.common.TradeListResponse;
import com.acooly.zaodao.platform.dto.TradingRecordDto;
import com.acooly.zaodao.platform.order.TradeListOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 提现交易明细
 *
 * @author xiaohong
 * @create 2017-11-23 17:23
 **/
@OpenApiService(name = "tradeList", desc = "提现交易明细", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class TradeListApiService extends BaseApiService<TradeListRequest, TradeListResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(TradeListRequest request, TradeListResponse response) {
        TradeListOrder tradeListOrder = request.toOrder(TradeListOrder.class);
        tradeListOrder.setGid(Ids.gid());
        PageResult<TradingRecordDto> tradingRecordDtoPageResult = platOrderInfoService.getTradeList(tradeListOrder);
        tradingRecordDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(tradingRecordDtoPageResult, (u,t)->{
            t.setBalance(Money.cent(u.getBalance()));
            t.setTradingAmount(Money.cent(u.getTradingAmount()));
        });
    }
}
