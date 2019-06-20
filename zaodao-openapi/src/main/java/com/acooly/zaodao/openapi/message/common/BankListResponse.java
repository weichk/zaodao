package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.BankInfoDto;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 银行列表响应
 *
 * @author xiaohong
 * @create 2017-11-23 18:45
 **/
@Data
@OpenApiMessage(service = "bankList", type = ApiMessageType.Response)
public class BankListResponse extends ApiResponse {
    @OpenApiField(desc = "银行列表", constraint = "银行列表", demo = "bankCode=BOC,bankName=中国银行,bankImageUrl=http://xxx/bankImg_80/BOC_80.png")
    private List<BankInfoDto> bankInfoDtoList = Lists.newArrayList();
}
