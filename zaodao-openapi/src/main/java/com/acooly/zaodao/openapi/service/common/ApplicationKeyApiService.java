package com.acooly.zaodao.openapi.service.common;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ApplicationKeyRequest;
import com.acooly.zaodao.openapi.message.common.ApplicationKeyResponse;
import com.acooly.zaodao.openapi.message.dto.ApplicationKeyDto;
import com.acooly.zaodao.openapi.message.enums.SensitiveKeyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * App关键字
 *
 * @author xiaohong
 * @create 2018-06-14 9:51
 **/
@Slf4j
@OpenApiService(name = "applicationKey", desc = "App关键字", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ApplicationKeyApiService extends BaseApiService<ApplicationKeyRequest, ApplicationKeyResponse> {

    @Value("${zaodao.hide.sensitive.all}")
    private String hideSensitiveAll;

    @Value("${zaodao.hide.sensitive.userid}")
    private String hideSensitiveUserId;

    @Override
    protected void doService(ApplicationKeyRequest request, ApplicationKeyResponse response) {
        Boolean isHide = Boolean.valueOf(hideSensitiveAll) || request.getUserId().equals(hideSensitiveUserId);
        response.setApplicationKeyDto(getApplicationKey(isHide));
    }

    /**
     * 获取关键字
     */
    private ApplicationKeyDto getApplicationKey(boolean isHide){
        ApplicationKeyDto dto = new ApplicationKeyDto();
        dto.setIsHide(isHide);
        if(isHide) {
            dto.setKey0(SensitiveKeyType.deposit.getMessage());
            dto.setKey1(SensitiveKeyType.withdraw.getMessage());
            dto.setKey2(SensitiveKeyType.trade.getMessage());
            dto.setKey3(SensitiveKeyType.catrade.getMessage());
            dto.setKey4(SensitiveKeyType.reward.getMessage());
            dto.setKey5(SensitiveKeyType.exchange.getMessage());
            dto.setKey6(SensitiveKeyType.bindcard.getMessage());
            dto.setKey7(SensitiveKeyType.mybankcard.getMessage());
            dto.setKey8(SensitiveKeyType.balance.getMessage());
            dto.setKey9(SensitiveKeyType.point.getMessage());
        }
        return dto;
    }
}
