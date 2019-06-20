package com.acooly.zaodao.test.api;

import com.acooly.core.utils.Ids;
import com.acooly.module.app.enums.DeviceType;
import com.acooly.module.appopenapi.message.LoginRequest;
import com.acooly.module.appopenapi.message.LoginResponse;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.zaodao.openapi.message.common.ZdRegisterRequest;
import com.acooly.zaodao.openapi.message.common.ZdRegisterResponse;
import org.junit.Test;

/**
 * Created by xiyang on 2017/9/18.
 */
public class ApiTests extends AbstractApiServieTests {

    {

//        gatewayUrl = "http://106.14.7.127:8084/gateway.html";
        gatewayUrl = "http://127.0.0.1:8021/gateway.do";
        partnerId = "anonymous";
        key = "anonymouanonymou";
        returnUrl = "http://127.0.0.1:8021/gateway.do";
        notifyUrl = "http://127.0.0.1:8021/gateway.do";
    }

    @Test
    public void login() {
        service = "login";
        LoginRequest request = new LoginRequest();
        request.setMerchOrderNo(Ids.oid("MERD"));
        request.setRequestNo(Ids.oid("REQU"));
        request.setUsername("13996070152");
        request.setPassword(encrypt("111111"));
        request.setAppClient(true);
        request.setCustomerIp("127.0.0.1");
        request.setDeviceType(DeviceType.IPHONE6);
        request.setDeviceId("xxxxx");
        request.setDeviceModel("xxxx");
        request(request, LoginResponse.class);
    }

    @Test
    public void zdRegister() {
        service = "zdRegister";
        ZdRegisterRequest request = new ZdRegisterRequest();
        request.setMerchOrderNo(Ids.oid("MERD"));
        request.setRequestNo(Ids.oid("REQU"));
        request.setMobileNo("13996070150");
        request.setPassword(encrypt("111111"));
        request.setAppClient(true);
        request.setVerifyCode("941563");
        request.setUserName("xxxx");
        request(request, ZdRegisterResponse.class);
    }
}
