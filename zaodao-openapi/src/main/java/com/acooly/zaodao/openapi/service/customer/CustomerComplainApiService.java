package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.ComplainApiRequest;
import com.acooly.zaodao.openapi.message.customer.ComplainApiResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 投诉
 *
 * @author xiaohong
 * @create 2018-01-04 16:03
 **/
@Slf4j
@OpenApiService(name = "customerComplain", desc = "客户投诉", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer_complain", name = "客户投诉")
public class CustomerComplainApiService extends BaseApiService<ComplainApiRequest, ComplainApiResponse>{

    @Override
    protected void doService(ComplainApiRequest request, ComplainApiResponse response) {
        ResultBase resultBase = new ResultBase();
        log.info("投诉旅声ID：{}", request.getTravelVoiceId());
        log.info("投诉类型：{}", request.getComplainType());
        log.info("投诉内容：{}", request.getContent());
        resultBase.throwExceptionIfNotSuccess();
    }
}
