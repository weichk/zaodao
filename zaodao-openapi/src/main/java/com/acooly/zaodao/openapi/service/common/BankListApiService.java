package com.acooly.zaodao.openapi.service.common;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.common.enums.BankIdEnum;
import com.acooly.zaodao.openapi.message.common.BankListRequest;
import com.acooly.zaodao.openapi.message.common.BankListResponse;
import com.acooly.zaodao.openapi.message.dto.BankInfoDto;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * 银行列表
 *
 * @author xiaohong
 * @create 2017-11-23 18:42
 **/
@Slf4j
@OpenApiService(name = "bankList", desc = "银行列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class BankListApiService extends BaseApiService<BankListRequest, BankListResponse> {

    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Override
    protected void doService(BankListRequest request, BankListResponse response) {
        List<BankIdEnum> list = BankIdEnum.getAll();
        List<BankInfoDto> bankInfoDtoList = Lists.newArrayList();

        for(BankIdEnum bankIdEnum : list){
            BankInfoDto bankInfoDto = new BankInfoDto();
            bankInfoDto.setBankCode(bankIdEnum.getCode());
            bankInfoDto.setBankName(bankIdEnum.getMessage());
            bankInfoDto.setBankImageUrl(String.format("%s/bankImg_80/%s_80.png", siteGatewayUrl, bankIdEnum.getCode()));
            bankInfoDtoList.add(bankInfoDto);
        }
        response.setBankInfoDtoList(bankInfoDtoList);
    }
}
