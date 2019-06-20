package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加旅行社
 *
 * @author xiaohong
 * @create 2018-05-04 16:44
 **/
@Data
@OpenApiMessage(service = "travelAgencyAdd", type = ApiMessageType.Request)
public class TravelAgencyAddRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "添加旅行社信息的用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "旅行社名称", constraint = "旅行社名称")
    private String agencyName;

    @NotBlank
    @OpenApiField(desc = "许可证号", constraint = "许可证号")
    private String licenseNo;

    @OpenApiField(desc = "联系人名称", constraint = "联系人名称")
    private String contactName;

    @OpenApiField(desc = "联系电话", constraint = "联系电话")
    private String contactPhone;
}
