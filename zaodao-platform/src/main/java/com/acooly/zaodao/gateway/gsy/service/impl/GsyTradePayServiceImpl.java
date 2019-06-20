/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:29 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.service.impl;

import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.openapi.framework.common.enums.ApiServiceResultCode;
import com.acooly.openapi.framework.common.utils.json.MoneyDeserializer;
import com.acooly.openapi.tool.AcoolyGateway;
import com.acooly.zaodao.gateway.gsy.GsyApiConstant;
import com.acooly.zaodao.gateway.gsy.message.*;
import com.acooly.zaodao.gateway.gsy.message.enums.GsyServiceEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeProfitStatusEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service("gsyTradePay")
@Slf4j
public class GsyTradePayServiceImpl implements GsyTradePayService {

    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Override
    public TradeCreateResponse tradeCreate(TradeCreateRequest request) {
        TradeCreateResponse response = new TradeCreateResponse();
        Map<String, String> map = Maps.newHashMap();

        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.orderCreate.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("tradeName", request.getTradeName());
        map.put("sellerMerchUserId", request.getSellerUserId());
        map.put("payerCustomerInfo", JSON.toJSONString(request.getTradeCustomerInfo()));
        map.put("tradeProfitType", request.getTradeProfitType().code());
        map.put("amount", request.getAmount().toStandardString());
        map.put("tradeTime", Dates.format(request.getTradeTime(), Dates.CHINESE_DATETIME_FORMAT_LINE));

        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.orderCreate.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, TradeCreateResponse.class);

            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("创建订单异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }

        return response;
    }

    @Override
    public AliScanCodePayResponse aliScanCodePay(AliScanCodePayRequest request) {
        AliScanCodePayResponse response = new AliScanCodePayResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.aliScanQRCodePay.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("userIp", request.getUserIp());
        map.put("deviceType", request.getDeviceType().getCode());

        //2017-11-29 xh modify
        //String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.orderCreate.getCode());
        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.aliScanQRCodePay.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, AliScanCodePayResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode()) || Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("生成支付宝扫码异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }


    @Override
    public WechatScanCodePayResponse wechatScanCodePay(WechatScanCodePayRequest request) {
        WechatScanCodePayResponse response = new WechatScanCodePayResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.wechatScanQRCodePay.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("userIp", request.getUserIp());
        map.put("deviceType", request.getDeviceType().getCode());

        //String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.orderCreate.getCode());
        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.wechatScanQRCodePay.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, WechatScanCodePayResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())||Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("生成微信扫码异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    @Override
    public SendCaptchaSmsResponse sendCaptchaSms(SendCaptchaSmsRequest request) {
        SendCaptchaSmsResponse response = new SendCaptchaSmsResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.sendCaptchaSms.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("mobileNo", request.getMobileNo());
        map.put("smsTemplateEnum", request.getSmsTemplateEnum().getCode());
        map.put("map", JSON.toJSONString(request.getMap()));
        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, SendCaptchaSmsResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("发送短息merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    @Override
    public WithdrawResponse withdraw(WithdrawRequest request) {
        WithdrawResponse response = new WithdrawResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.withdraw.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("userIp", request.getUserIp());
        map.put("bindId", request.getBindId());
        map.put("captchaCode", request.getCaptchaCode());
        map.put("merchUserId", request.getMerchUserId());
        map.put("amount", request.getAmount().toStandardString());
        map.put("tradeTime", Dates.format(request.getTradeTime(), Dates.CHINESE_DATETIME_FORMAT_LINE));
        map.put("deviceType", request.getDeviceType().getCode());

        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.withdraw.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, WithdrawResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())||Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("提现异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    /**
     * 交易清分
     */
    @Override
    public TradeProfitResponse tradeProfit(TradeProfitRequest request) {
        TradeProfitResponse response = new TradeProfitResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.orderProfit.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("origMerchOrdeNo", request.getMerchOrderNo());

        String callback = String.format("%s%s/%s.html", siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.orderProfit.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, TradeProfitResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())||Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                if(response.getProfitStatus() == TradeProfitStatusEnum.PROFIT_SUCCESS) {
                    response.setStatus(ResultStatus.success);
                }else {
                    response.setStatus(ResultStatus.failure);
                }
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("交易清分异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    /**
     * 观世宇余额支付
     */
    @Override
    public BalancePayResponse balancePay(BalancePayRequest request) {
        BalancePayResponse response = new BalancePayResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.balancePay.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("payerMerchUserId", request.getPayerMerchUserId());
        map.put("userIp ", request.getUserIp());

//        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.balancePay.getCode());
//        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
//        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, BalancePayResponse.class);

            if (response.getResultCode().equals(ApiServiceResultCode.SUCCESS.getCode()) || response.getResultCode().equals(ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("提现异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    /**
     * 提现到卡(汇付到卡)
     */
    @Override
    public WithdrawCardResponse withdrawCard(WithdrawCardRequest request) {
        WithdrawCardResponse response = new WithdrawCardResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.withdrawCard.getCode());

        map.put("merchUserId", request.getMerchUserId());
        map.put("amount", request.getAmount().toStandardString());
        map.put("tradeTime", Dates.format(request.getTradeTime(), Dates.CHINESE_DATETIME_FORMAT_LINE));
        map.put("deviceType", request.getDeviceType().getCode());
        map.put("bankCode", request.getBankCode());
        map.put("bankCardNo", request.getBankCardNo());
        map.put("realName", request.getRealName());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("delay", request.getDelay().getCode());

        String callback = String.format("%s%s/%s.html",siteGatewayUrl, GsyApiConstant.NOTIFY_SITE , GsyServiceEnum.withdrawCard.getCode());
        log.info(String.format("mecherOrderNo=%s, callback=%s", request.getMerchOrderNo(), callback));
        map.put(GsyApiConstant.NOTIFY_URL, callback);

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, WithdrawCardResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())||Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("提现异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    /**
     * 查询充提订单(单笔)
     */
    @Override
    public FundQueryResponse fundQuery(FundQueryRequest request) {
        FundQueryResponse response = new FundQueryResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.fundQuery.getCode());

        map.put("origMerchOrdeNo", request.getOrigMerchOrdeNo());

        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, FundQueryResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("查询充提订单异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }
}
