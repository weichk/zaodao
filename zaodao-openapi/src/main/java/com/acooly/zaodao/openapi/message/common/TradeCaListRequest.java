package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.TradingType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 钙片交易明细
 *
 * @author xiaohong
 * @create 2017-11-29 19:25
 **/
@Data
@OpenApiMessage(service = "tradeCaList", type = ApiMessageType.Request)
public class TradeCaListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "开始时间", constraint = "开始时间", demo = "2017-11-10")
    private Date startTime;

    @OpenApiField(desc = "结束时间", constraint = "结束时间", demo = "2017-11-15")
    private Date endTime;
}
