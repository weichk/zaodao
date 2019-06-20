package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.EvalInfoDto;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderDetailRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderDetailResponse;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.PlatOrderDetailOrder;
import com.acooly.zaodao.platform.result.PlatOrderDetailResult;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单详情
 *
 * @author xiaohong
 * @create 2017-11-22 18:09
 */
@OpenApiService(name = "platOrderDetail", desc = "订单详情", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderDetailApiService
    extends BaseApiService<PlatOrderDetailRequest, PlatOrderDetailResponse> {
  @Autowired private PlatOrderInfoService platOrderInfoService;

  @Override
  protected void doService(PlatOrderDetailRequest request, PlatOrderDetailResponse response) {
    PlatOrderDetailOrder platOrderDetailOrder = request.toOrder(PlatOrderDetailOrder.class);
    platOrderDetailOrder.setGid(Ids.gid());
    PlatOrderDetailResult result = platOrderInfoService.getPlatOrder(platOrderDetailOrder);
    result.throwExceptionIfFailure();
    BeanCopier.copy(result, response);
    response.setOrderAmount(Money.cent(result.getOrderAmount()));
    response.setPricePerday(Money.cent(result.getPricePerday()));
    //订单评价
    if(result.getPlatOrderEvalDto() != null) {
      EvalInfoDto evalInfoDto = new EvalInfoDto();
      evalInfoDto.setContent(result.getPlatOrderEvalDto().getContent());
      evalInfoDto.setScore(result.getPlatOrderEvalDto().getScore());
      evalInfoDto.setCreateTime(result.getPlatOrderEvalDto().getCreateTime());
      response.setEvalInfoDto(evalInfoDto);
    }
    response.setOrderStatusText(response.getOrderStatus().getMessage());
    if (PlatOrderInfoOrderStatus.noPay.equals(response.getOrderStatus())
        || PlatOrderInfoOrderStatus.fail.equals(response.getOrderStatus())) {
      response.setPayAgain(true);
    } else {
      response.setPayAgain(false);
    }
    if (PlatOrderInfoOrderStatus.noPay.equals(response.getOrderStatus())
        || PlatOrderInfoOrderStatus.fail.equals(response.getOrderStatus())) {
      response.setCancel(true);
    } else {
      response.setCancel(false);
    }
    if (request.getUserId().equals(result.getUserId())) {
      response.setMyself(true);
    } else {
      response.setMyself(false);
    }
  }
}
