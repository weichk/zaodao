package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 检查订单约导日期请求
 *
 * @author xiaohong
 * @create 2017-11-21 14:13
 **/
@Data
@OpenApiMessage(service = "guideDateCheck", type = ApiMessageType.Request)
public class GuideDateCheckRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID")
    private String guideUserId;

    @NotNull
    @OpenApiField(desc = "开始时间", constraint = "开始时间")
    private Date startTime;

    @NotNull
    @OpenApiField(desc = "结束时间", constraint = "结束时间")
    private Date endTime;
}
