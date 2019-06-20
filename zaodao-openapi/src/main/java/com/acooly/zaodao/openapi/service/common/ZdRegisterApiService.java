/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:03 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
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
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.gateway.gsy.service.WeixinService;
import com.acooly.zaodao.openapi.message.common.ZdRegisterRequest;
import com.acooly.zaodao.openapi.message.common.ZdRegisterResponse;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import com.acooly.zaodao.platform.order.ZdRegisterOrder;
import com.acooly.zaodao.platform.service.ZdSmsService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.result.CustomerRegisterResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Slf4j
@OpenApiService(name = "zdRegister", desc = "注册", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ZdRegisterApiService extends BaseApiService<ZdRegisterRequest, ZdRegisterResponse> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AppOpenapiProperties appOpenapiProperties;
    @Autowired
    private AppApiLoginService appApiLoginService;
    @Autowired
    private AppCustomerService appCustomerService;

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private ZdSmsService zdSmsService;

    @Value("zaodao.wx.appId")
    private String wxAppId;

    @Value("zaodao.wx.secretKey")
    private String wxSecretKey;

    @Override
    protected void doService(ZdRegisterRequest request, ZdRegisterResponse response) {
        ZdRegisterOrder order = request.toOrder(ZdRegisterOrder.class);
        order.setGid(Ids.gid());
        Customer customer = new Customer();
        CustomerRegisterResult result = new CustomerRegisterResult();
        if(order.getOpenPlatformType() == null) {
            //如果开放平台登录方式为空，则正常注册
            order.check();
            setCustomer(customer, order);
            //系统注册和观世宇注册
            result = customerService.register(customer, order.getVerifyCode());
            result.throwExceptionIfNotSuccess();
        }else {
            result = openPlatformCheck(order, customer);
            result.throwExceptionIfNotSuccess();
        }
        response.setUserId(result.getCustomer().getUserId());
        //返回用户加密key
        LoginDto dto = new LoginDto();
        dto.setAccessKey(result.getCustomer().getUserName());
        dto.setCustomerId(customer.getUserId());
        AppCustomer appCustomer = this.appCustomerService.loadAppCustomer(dto.getAccessKey(), EntityStatus.Enable);
        if(request.isAppClient()) {
            if(appCustomer == null) {
                appCustomer = getAppCustomer(dto, order);
                appCustomer = this.appCustomerService.createAppCustomer(appCustomer);
            } else {
                String activeProfile = System.getProperty("spring.profiles.active");
                if("online".equals(activeProfile) && this.appOpenapiProperties.isDeviceIdCheck() && !StringUtils.equals(order.getDeviceId(), appCustomer.getDeviceId())) {
                    throw new RuntimeException("设备ID与绑定的设备ID不符");
                }
                if(this.appOpenapiProperties.isSecretKeyDynamic()) {
                    appCustomer = this.appCustomerService.updateSecretKey(appCustomer);
                }
            }
            response.setAccessKey(appCustomer.getAccessKey());
            response.setSecretKey(appCustomer.getSecretKey());
        }
    }

    /**
     * 组织获取AppCustomer
     * @param dto
     * @param order
     * @return
     */
    private AppCustomer getAppCustomer(LoginDto dto, ZdRegisterOrder order){
        AppCustomer appCustomer = new AppCustomer();

        appCustomer.setUserName(dto.getAccessKey());
        appCustomer.setAccessKey(dto.getAccessKey());
        appCustomer.setDeviceId(order.getDeviceId());
        appCustomer.setDeviceType(order.getDeviceType());
        appCustomer.setDeviceModel(order.getDeviceModel());

        return  appCustomer;
    }

    /**
     * 验证开放平台参数
     * @param order
     * @param customer
     * @return
     */
    private CustomerRegisterResult openPlatformCheck(ZdRegisterOrder order, Customer customer){
        CustomerRegisterResult result = new CustomerRegisterResult();
        try {
            //验证码
            ResultBase resultBase = zdSmsService.checkMobileCaptcha(order.getMobileNo(), order.getVerifyCode());
            resultBase.throwExceptionIfNotSuccess();
            //电话号码是否存在
            Customer openCustomer = customerService.findEntityByMobileNo(order.getMobileNo());
            //不存在，则是新用户，走注册流程
            if(openCustomer == null){
                log.info("新用户，走注册流程");
                order.checkNotExistOpenPlatform();
                setCustomer(customer, order);
                //根据开放平台类型填写实体参数
                customerService.setOpenUserId(order.getOpenUserId(), order.getOpenPlatformType(), customer);
                //注册流程
                result = customerService.register(customer, order.getVerifyCode());
            }else{
                log.info("用户已注册：{}", openCustomer);
                order.checkExistOpenPlatform();
                //根据开放平台类型填写实体参数
                customerService.setOpenUserId(order.getOpenUserId(), order.getOpenPlatformType(), openCustomer);
                this.customerService.save(openCustomer);
                result.setCustomer(openCustomer);
            }
        }catch (BusinessException e){
            log.info(String.format("开放平台参数验证出错,%s", e.getMessage()));
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 设置Customer
     * @param customer
     * @param order
     */
    private void setCustomer(Customer customer, ZdRegisterOrder order){
        customer.setMobileNo(order.getMobileNo());
        customer.setLoginPassword(order.getPassword());
        customer.setUserName(order.getUserName());
        customer.setUserId(Ids.oid());
    }
}
