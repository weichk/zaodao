/*
 * 修订记录:
 * zhike@yiji.com 2017-08-09 14:38 创建
 *
 */
package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.gateway.gsy.message.*;
import com.acooly.zaodao.gateway.gsy.message.dto.TradeCustomerInfo;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;
import com.acooly.zaodao.platform.enums.OrderPayTypeEnum;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import com.acooly.zaodao.platform.service.manage.TradeOrderInfoService;
import com.acooly.zaodao.platform.service.platform.DeductWithdrawService;
import com.acooly.zaodao.platform.service.platform.base.DepositResult;
import com.acooly.zaodao.platform.service.platform.base.WithdrawResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service("deductWithdrawService")
@Slf4j
public class DeductWithdrawServiceImpl implements DeductWithdrawService {

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    @Autowired
    private TradeOrderInfoService tradeOrderInfoService;

    @Autowired
    private CustomerCardService customerCardService;

    @Override
    public String tradeCreate(Customer customer, String amount) {
        try {
            //平台保存订单信息
            TradeOrderInfo tradeOrderInfo = new TradeOrderInfo();
            tradeOrderInfo.setAmount(Money.amout(amount).getCent());
            tradeOrderInfo.setOrderNo(Ids.oid());
            tradeOrderInfo.setOrderStatus(OrderStatusEnum.noPay);
            tradeOrderInfo.setUserId(customer.getUserId());
            tradeOrderInfo.setOutUserId(customer.getOutUserId());
            tradeOrderInfo.setOrderTradeType(OrderTradeTypeEnum.deposit);
            tradeOrderInfo.setTradeName("早导网充值");
            tradeOrderInfo.setTradeTime(new Date());
            tradeOrderInfoService.save(tradeOrderInfo);
            //创建订单
            TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
            tradeCreateRequest.setMerchOrderNo(tradeOrderInfo.getOrderNo());
            tradeCreateRequest.setAmount(Money.amout(amount));
            tradeCreateRequest.setTradeName(tradeOrderInfo.getTradeName());
            tradeCreateRequest.setSellerUserId(customer.getUserId());
            tradeCreateRequest.setTradeTime(tradeOrderInfo.getTradeTime());
            TradeCustomerInfo tradeCustomerInfo = new TradeCustomerInfo();
            tradeCustomerInfo.setRealName(customer.getRealName());
            tradeCustomerInfo.setMobileNo(customer.getMobileNo());
            tradeCustomerInfo.setIdentityCard(customer.getIdNumber());
            tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
            TradeCreateResponse tradeCreateResponse = gsyTradePayService.tradeCreate(tradeCreateRequest);
            if (tradeCreateResponse.getStatus() != ResultStatus.success) {
                log.info("创建交易失败：{}", tradeCreateResponse.getResultMessage());
                throw new BusinessException("创建交易失败");
            }
            tradeOrderInfo.setTradeText(tradeCreateResponse.getTradeNo());

            return tradeCreateResponse.getMerchOrderNo();
        } catch (Exception e) {
            log.info("创建交易失败：{}", e.getMessage());
            throw new BusinessException("创建交易失败");
        }
    }

    @Transactional
    public DepositResult deposit(String orderNo, String payType) {

        DepositResult result = new DepositResult();
        try {
            String serviceIp = "";
            try {
                serviceIp = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                log.info("获取本机ip失败：{}", e.getMessage());
            }
            if (Strings.equals(payType, OrderPayTypeEnum.aliScan.getCode())) {
                //生成支付宝二维码
                AliScanCodePayRequest aliScanCodePayRequest = new AliScanCodePayRequest();
                aliScanCodePayRequest.setMerchOrderNo(orderNo);
                aliScanCodePayRequest.setDeviceType(DeviceTypeEnum.PC);
                aliScanCodePayRequest.setUserIp(serviceIp);
                AliScanCodePayResponse aliScanCodePayResponse = gsyTradePayService.aliScanCodePay(aliScanCodePayRequest);
                if (aliScanCodePayResponse.getStatus() != ResultStatus.processing) {
                    String message = aliScanCodePayResponse.getResultDetail();
                    if(Strings.isBlank(message)){
                        message = aliScanCodePayResponse.getResultMessage();
                    }
                    log.info("生成支付宝二维码失败：{}", aliScanCodePayResponse.getResultMessage());
                    throw new BusinessException(String.format("生成支付宝二维码失败,%s", message));
                }
                result.setScanUrl(aliScanCodePayResponse.getCodeUrlImg());
            } else if (Strings.equals(payType, OrderPayTypeEnum.weixinScan.getCode())) {
                //生成微信二维码
                WechatScanCodePayRequest wechatScanCodePayRequest = new WechatScanCodePayRequest();
                wechatScanCodePayRequest.setMerchOrderNo(orderNo);
                wechatScanCodePayRequest.setUserIp(serviceIp);
                wechatScanCodePayRequest.setDeviceType(DeviceTypeEnum.PC);
                WechatScanCodePayResponse wechatScanCodePayResponse = gsyTradePayService.wechatScanCodePay(wechatScanCodePayRequest);
                if (wechatScanCodePayResponse.getStatus() != ResultStatus.processing) {
                    String message = wechatScanCodePayResponse.getResultDetail();
                    if(Strings.isBlank(message)){
                        message = wechatScanCodePayResponse.getResultMessage();
                    }
                    log.info("生成微信二维码失败：{}", wechatScanCodePayResponse.getResultMessage());
                    throw new BusinessException(String.format("生成微信二维码失败,%s", message));
                }
                result.setScanUrl(wechatScanCodePayResponse.getCodeUrlImg());
            } else {
                throw new BusinessException("不存在的支付方式orderPayType={}", payType);
            }

        } catch (Exception e) {
            log.info("生成支付二维码异常：{}", e.getMessage());
            throw new BusinessException("支付失败");
        }
        return result;
    }

    @Transactional
    public WithdrawResult withdraw(Customer customer, String amount, String captchaCode) {
        WithdrawResult result = new WithdrawResult();
        try {
            //平台保存订单信息
            TradeOrderInfo tradeOrderInfo = new TradeOrderInfo();
            tradeOrderInfo.setAmount(Money.amout(amount).getCent());
            tradeOrderInfo.setOrderNo(Ids.oid());
            tradeOrderInfo.setOrderStatus(OrderStatusEnum.noPay);
            tradeOrderInfo.setUserId(customer.getUserId());
            tradeOrderInfo.setOutUserId(customer.getOutUserId());
            tradeOrderInfo.setOrderTradeType(OrderTradeTypeEnum.withdraw);
            tradeOrderInfo.setTradeName("早导网提现");
            tradeOrderInfo.setTradeTime(new Date());
            tradeOrderInfoService.save(tradeOrderInfo);
            //提现
            CustomerCard customerCard = customerCardService.getEntityByUserId(customer.getUserId());
            WithdrawRequest withdrawRequest = new WithdrawRequest();
            withdrawRequest.setMerchOrderNo(tradeOrderInfo.getOrderNo());
            withdrawRequest.setBindId(customerCard.getBindId());
            withdrawRequest.setCaptchaCode(captchaCode);
            withdrawRequest.setMerchUserId(customer.getUserId());
            withdrawRequest.setAmount(Money.amout(amount));
            withdrawRequest.setTradeTime(tradeOrderInfo.getTradeTime());
            withdrawRequest.setDeviceType(DeviceTypeEnum.PC);
            WithdrawResponse withdrawResponse = gsyTradePayService.withdraw(withdrawRequest);
            if (withdrawResponse.getStatus() != ResultStatus.processing) {
                log.info("提现失败：{}", withdrawResponse.getResultMessage());
                throw new BusinessException(withdrawResponse.getResultMessage());
            }
//            tradeOrderInfo.setBankOrderNo(withdrawResponse.getTradeNo());
        } catch (Exception e) {
            log.info("提现失败：{}", e.getMessage());
            throw new BusinessException("提现失败");
        }
        return result;
    }
}
