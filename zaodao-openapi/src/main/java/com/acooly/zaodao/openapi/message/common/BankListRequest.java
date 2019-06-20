package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

/**
 * 银行列表请求
 *
 * @author xiaohong
 * @create 2017-11-23 18:44
 **/
@Data
@OpenApiMessage(service = "bankList", type = ApiMessageType.Request)
public class BankListRequest extends ApiRequest {
}
