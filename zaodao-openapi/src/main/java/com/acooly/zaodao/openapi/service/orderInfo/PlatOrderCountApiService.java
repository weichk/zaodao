package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.PlatOrderCountDto;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderCountRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderCountResponse;
import com.acooly.zaodao.platform.result.PlatOrderCountResult;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单读取状态个数统计
 *
 * @author xiaohong
 * @create 2018-06-25 10:57
 **/
@OpenApiService(name = "platOrderCount", desc = "订单读取个数统计", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderCountApiService extends BaseApiService<PlatOrderCountRequest, PlatOrderCountResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(PlatOrderCountRequest request, PlatOrderCountResponse response) {
        PlatOrderCountResult result = platOrderInfoService.getPlatOrderCount(request.getUserId());
        result.throwExceptionIfNotSuccess();
        PlatOrderCountDto dto = new PlatOrderCountDto();
        BeanCopier.copy(result, dto);
        response.setPlatOrderCountDto(dto);
    }
}
