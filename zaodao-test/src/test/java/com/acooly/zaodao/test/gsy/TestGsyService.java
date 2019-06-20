/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 18:07 创建
 *
 */
package com.acooly.zaodao.test.gsy;

import com.acooly.core.common.boot.Apps;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.module.test.AppWebTestBase;
import com.acooly.zaodao.gateway.gsy.message.*;
import com.acooly.zaodao.gateway.gsy.message.dto.TradeCustomerInfo;
import com.acooly.zaodao.gateway.gsy.message.enums.*;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class TestGsyService extends AppWebTestBase {
    protected static final String PROFILE = "sdev";

    static {
        Apps.setProfileIfNotExists(PROFILE);
    }

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    /**
     * 测试创建订单
     */
    @Test
    public void testTradeCreate() {
        TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
        tradeCreateRequest.setMerchOrderNo(Ids.oid());
        tradeCreateRequest.setAmount(Money.amout("2.0"));
        tradeCreateRequest.setTradeName("早导网付款");
        tradeCreateRequest.setSellerUserId("GSY17080310165806000003");
        tradeCreateRequest.setTradeTime(new Date());
        TradeCustomerInfo tradeCustomerInfo = new TradeCustomerInfo();
        tradeCustomerInfo.setRealName("韦崇凯");
        tradeCustomerInfo.setMobileNo("18696725229");
        tradeCustomerInfo.setIdentityCard("500221198810192313");
        tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
        tradeCreateRequest.setTradeProfitType(TradeProfitTypeEnum.MANUAL);
        TradeCreateResponse response = gsyTradePayService.tradeCreate(tradeCreateRequest);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试支付宝扫码
     */
    @Test
    public void testAliScanCodePay() {
        AliScanCodePayRequest aliScanCodePayRequest = new AliScanCodePayRequest();
        aliScanCodePayRequest.setMerchOrderNo("O00117080822462305000001");
        try {
            aliScanCodePayRequest.setUserIp(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.getStackTrace();
        }
        aliScanCodePayRequest.setDeviceType(DeviceTypeEnum.PC);
        AliScanCodePayResponse response = gsyTradePayService.aliScanCodePay(aliScanCodePayRequest);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试微信扫码
     */
    @Test
    public void testWechatScanCodePay() {
        WechatScanCodePayRequest wechatScanCodePayRequest = new WechatScanCodePayRequest();
        wechatScanCodePayRequest.setMerchOrderNo("o17081510322137450001");
        try {
            wechatScanCodePayRequest.setUserIp(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.getStackTrace();
        }
        wechatScanCodePayRequest.setDeviceType(DeviceTypeEnum.PC);
        WechatScanCodePayResponse response = gsyTradePayService.wechatScanCodePay(wechatScanCodePayRequest);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试发送短息
     */
    @Test
    public void testSendCaptchaSms() {
        SendCaptchaSmsRequest sendCaptchaSmsRequest = new SendCaptchaSmsRequest();
        sendCaptchaSmsRequest.setMerchOrderNo(Ids.oid());
        sendCaptchaSmsRequest.setMobileNo("18696725229");
        sendCaptchaSmsRequest.setSmsTemplateEnum(SmsTemplateEnum.WITHDRAW);
        Map<String,Object> map = Maps.newHashMap();
        map.put("amount","0.05");
        sendCaptchaSmsRequest.setMap(map);
        SendCaptchaSmsResponse response = gsyTradePayService.sendCaptchaSms(sendCaptchaSmsRequest);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试提现
     */
    @Test
    public void testWithdraw() {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setMerchOrderNo(Ids.getDid());
        withdrawRequest.setBindId("BANK17080310170006000004");
        withdrawRequest.setCaptchaCode("5757");
//        withdrawRequest.setUserId("GSY17080310165806000003");
        withdrawRequest.setAmount(Money.amout("0.05"));
        withdrawRequest.setTradeTime(new Date());
        withdrawRequest.setDeviceType(DeviceTypeEnum.PC);
        WithdrawResponse response = gsyTradePayService.withdraw(withdrawRequest);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试会员信息查询
     */
    @Test
    public void testCustomerQuery() {
        CustomerQueryRequest request = new CustomerQueryRequest();
        request.setMerchOrderNo(Ids.getDid());
        request.setUserId("GSY17080310165806000003");
        request.setIsBankcardInfo(true);
        request.setIsAccountInfo(true);
        CustomerQueryResponse response = gsyBusinessService.customerQuery(request);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试会员注册
     */
    @Test
    public void testCustomerRegister() {
        CustomerRegisterRequest registerRequest = new CustomerRegisterRequest();
        registerRequest.setOutUserId("O00117080100392500100001");
        registerRequest.setType(CustomerTypeEnum.PERSON);
        registerRequest.setEmail("");
        registerRequest.setMobileNo("18696725229");
        registerRequest.setComments("导游审核注册");
        CustomerRegisterResponse response = gsyBusinessService.customerRegister(registerRequest);
        System.out.printf("响应转化response:"+ JSON.toJSONString(response));
    }

    /**
     * 测试实名绑卡
     */
    @Test
    public void testSignCard() {
        SignCardRequest request = new SignCardRequest();
        request.setCaptcha("2345");
        request.setUserId("GSY17080310165806000003");
        request.setMobile("18696725229");
        request.setBankCardNo("6210300016754256");
        request.setPurpose(Purpose.WITHDRAW);
        request.setPublicTag(PublicTagEnum.N);
        SignCardResponse response = gsyBusinessService.signCard(request);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试银行卡解绑
     */
    @Test
    public void testUnSignCard(){
        UnSignCardRequest request = new UnSignCardRequest();
        request.setBindId("GSY17080310165806000003");
        request.setUserId("GSY17080310165806000003");
        UnSignCardResponse response = gsyBusinessService.unSignCard(request);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }

    /**
     * 测试交易清分
     */
    @Test
    public void tradeProfit() {
        TradeProfitRequest request = new TradeProfitRequest();
        request.setMerchOrderNo("o17081510322137450001");
        TradeProfitResponse response = gsyTradePayService.tradeProfit(request);
        System.out.printf("响应转化response:"+JSON.toJSONString(response));
    }
}
