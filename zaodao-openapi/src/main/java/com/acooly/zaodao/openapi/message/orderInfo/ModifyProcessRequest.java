package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.openapi.message.enums.ProcessType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author xiaohong
 * @create 2018-05-09 9:40
 **/
@Data
@OpenApiMessage(service = "modifyProcess", type = ApiMessageType.Request)
public class ModifyProcessRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @NotNull
    @OpenApiField(desc = "行程服务状态", constraint = "行程服务状态", demo = "开始服务;结束服务")
    private ProcessType processType;
}
