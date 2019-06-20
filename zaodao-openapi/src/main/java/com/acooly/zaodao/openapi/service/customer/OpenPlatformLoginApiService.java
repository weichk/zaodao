package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.app.domain.AppCustomer;
import com.acooly.module.app.enums.EntityStatus;
import com.acooly.module.app.service.AppCustomerService;
import com.acooly.module.appopenapi.AppOpenapiProperties;
import com.acooly.module.appopenapi.support.AppApiLoginService;
import com.acooly.module.appopenapi.support.LoginDto;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.OpenPlatformLoginRequest;
import com.acooly.zaodao.openapi.message.customer.OpenPlatformLoginResponse;
import com.acooly.zaodao.openapi.message.dto.OpenLoginDto;
import com.acooly.zaodao.platform.order.OpenPlatformLoginOrder;
import com.acooly.zaodao.platform.result.OpenPlatformLoginResult;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;

/**
 * 开放平台登录
 *
 * @author xiaohong
 * @create 2017-12-13 15:25
 **/
@OpenApiService(name = "openPlatformLogin", desc = "开放平台用户登录", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_openPlatform", name = "开放平台")
public class OpenPlatformLoginApiService extends BaseApiService<OpenPlatformLoginRequest, OpenPlatformLoginResponse> {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AppOpenapiProperties appOpenapiProperties;

    @Autowired
    private AppCustomerService appCustomerService;

    @Override
    protected void doService(OpenPlatformLoginRequest request, OpenPlatformLoginResponse response) {
        OpenPlatformLoginOrder order = request.toOrder(OpenPlatformLoginOrder.class);
        order.setGid(Ids.gid());
        OpenPlatformLoginResult result = customerService.openPlaformLogin(order);
        result.throwExceptionIfNotSuccess();

        OpenLoginDto openLoginDto = response.getOpenLoginDto();
        openLoginDto.setCustomerId(result.getCustomerId());
        if (request.isAppClient()) {
            AppCustomer appCustomer = this.appCustomerService.loadAppCustomer(result.getAccessKey(), EntityStatus.Enable);
            if (appCustomer == null) {
                appCustomer = new AppCustomer();
                appCustomer.setUserName(result.getAccessKey());
                appCustomer.setAccessKey(result.getAccessKey());
                appCustomer.setDeviceId(request.getDeviceId());
                appCustomer.setDeviceType(request.getDeviceType());
                appCustomer.setDeviceModel(request.getDeviceModel());
                appCustomer = this.appCustomerService.createAppCustomer(appCustomer);
            } else {
                String activeProfile = System.getProperty("spring.profiles.active");
                if ("online".equals(activeProfile) && this.appOpenapiProperties.isDeviceIdCheck() && !StringUtils.equals(request.getDeviceId(), appCustomer.getDeviceId())) {
                    throw new RuntimeException("设备ID与绑定的设备ID不符");
                }
                if (this.appOpenapiProperties.isSecretKeyDynamic()) {
                    appCustomer = this.appCustomerService.updateSecretKey(appCustomer);
                }
            }
            openLoginDto.setAccessKey(appCustomer.getAccessKey());
            openLoginDto.setSecretKey(appCustomer.getSecretKey());
        }
    }
}
