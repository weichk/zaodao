package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.enums.ProcessType;
import com.acooly.zaodao.openapi.message.orderInfo.ModifyProcessRequest;
import com.acooly.zaodao.openapi.message.orderInfo.ModifyProcessResponse;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.PlatOrderStatusChangeOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导游修改订单状态(开始服务,结束服务)
 *
 * @author xiaohong
 * @create 2018-05-09 9:36
 **/
@OpenApiService(name = "modifyProcess", desc = "修改订单状态", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class ModifyProcessApiService extends BaseApiService<ModifyProcessRequest, ModifyProcessResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(ModifyProcessRequest request, ModifyProcessResponse response) {
        ProcessType processType = request.getProcessType();
        //开始服务 -> 订单修改为 服务提供中
        //结束服务 -> 订单修改为 待游客确认
        PlatOrderInfoOrderStatus status = null;
        if(processType == ProcessType.start){
            status = PlatOrderInfoOrderStatus.processing;
        }else if(processType == ProcessType.end){
            status = PlatOrderInfoOrderStatus.confirming;
        }else if(processType == ProcessType.finish){
            status = PlatOrderInfoOrderStatus.confirm;
        }
        PlatOrderStatusChangeOrder order = new PlatOrderStatusChangeOrder();
        order.setStatus(status);
        order.setPlatOrderNo(request.getPlatOrderNo());
        order.setGid(Ids.gid());

        ResultBase result = platOrderInfoService.changePlatOrderStatus(order);
        result.throwExceptionIfNotSuccess();
    }
}
