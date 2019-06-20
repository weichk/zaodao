/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 16:42 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.service.impl;

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
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
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
@Service("gsyBusinessService")
@Slf4j
public class GsyBusinessServiceImpl implements GsyBusinessService {

    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Override
    public CustomerQueryResponse customerQuery(CustomerQueryRequest request) {
        CustomerQueryResponse response = new CustomerQueryResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.customerQuery.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("merchUserId", request.getUserId());
        map.put("isExtendInfo", request.getIsExtendInfo().toString());
        map.put("isBankcardInfo", request.getIsBankcardInfo().toString());
        map.put("isAccountInfo", request.getIsAccountInfo().toString());
        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, CustomerQueryResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("查询会员信息异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    @Override
    public CustomerRegisterResponse customerRegister(CustomerRegisterRequest request) {
        CustomerRegisterResponse response = new CustomerRegisterResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.customerRegister.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("merchUserId", request.getOutUserId());
        map.put("type", request.getType().getCode());
        map.put("email", request.getEmail());
        map.put("mobileNo", request.getMobileNo());
        map.put("comments", request.getComments());
        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, CustomerRegisterResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("会员注册异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    @Override
    public SignCardResponse signCard(SignCardRequest request) {
        SignCardResponse response = new SignCardResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.signCard.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("captcha", request.getCaptcha());
        map.put("merchUserId", request.getUserId());
        map.put("mobile", request.getMobile());
        map.put("bankCardNo", request.getBankCardNo());
        map.put("purpose", request.getPurpose().getCode());
        map.put("publicTag", request.getPublicTag().getCode());
        map.put("bankCode", request.getBankCode());
        map.put("bankName", request.getBankName());
        map.put("province", request.getProvince());
        map.put("city", request.getCity());
        map.put("bankCardType", request.getBankCardType().getCode());
        map.put("realName", request.getRealName());
        map.put("certNo", request.getCertNo());
        map.put(GsyApiConstant.NOTIFY_URL, siteGatewayUrl + GsyApiConstant.NOTIFY_SITE + "/" + GsyServiceEnum.signCard.getCode() + ".html");
        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, SignCardResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode()) || Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.processing);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("实名绑卡异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }

    @Override
    public UnSignCardResponse unSignCard(UnSignCardRequest request) {
        UnSignCardResponse response = new UnSignCardResponse();
        Map<String, String> map = Maps.newHashMap();
        map.put(GsyApiConstant.ORDER_NO, Ids.getDid());
        map.put(GsyApiConstant.PARTNER_ID, GsyApiConstant.GATEWAY_PARTNER_ID);
        map.put(GsyApiConstant.SERVICE, GsyServiceEnum.unSignCard.getCode());
        map.put("merchOrderNo", request.getMerchOrderNo());
        map.put("bindId", request.getBindId());
        map.put("merchUserId", request.getUserId());
        try {
            String responseStr = AcoolyGateway.getOpenApiClientService().doPost(GsyApiConstant.SERVICE_GATEWAY_URL, map, GsyApiConstant.GATEWAY_SECRETKEY);
            ParserConfig.getGlobalInstance().putDeserializer(Money.class, MoneyDeserializer.instance);
            response = JSON.parseObject(responseStr, UnSignCardResponse.class);
            if (Strings.equals(response.getResultCode(), ApiServiceResultCode.SUCCESS.getCode()) || Strings.equals(response.getResultCode(), ApiServiceResultCode.PROCESSING.getCode())) {
                response.setStatus(ResultStatus.success);
            } else {
                response.setStatus(ResultStatus.failure);
            }
        } catch (Exception e) {
            response.setStatus(ResultStatus.failure);
            log.info("银行卡解绑异常merchOrderNo：{},异常：{}", request.getMerchOrderNo(), e.getMessage());
        }
        return response;
    }
}
