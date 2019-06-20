/*
 * 修订记录:
 * zhike@yiji.com 2017-08-10 10:21 创建
 *
 */
package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.info.DepositInfo;
import com.acooly.zaodao.account.info.UnFreezeInfo;
import com.acooly.zaodao.account.info.WithdrawInfo;
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.account.util.AccountConstants;
import com.acooly.zaodao.gateway.gsy.message.TradeCreateNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawCardNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawNotify;
import com.acooly.zaodao.gateway.gsy.message.enums.FundStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import com.acooly.zaodao.platform.dto.UserBalanceDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.platform.service.platform.GsyNotifyService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service("gsyNotifyService")
@Slf4j
public class GsyNotifyServiceImpl implements GsyNotifyService {

    @Autowired
    private TradeOrderInfoService tradeOrderInfoService;

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TourGuideService tourGuideService;

    @Autowired
    private AccountService accountService;

    @Value("${gsyPay.gateway.partnerId}")
    private String gatewayPartnerId;

//    /**
//     * 获取客户账户余额
//     *
//     * @param userId
//     * @return
//     */
//    @Override
//    public UserBalanceDto getCustomerBalanceGsy(String userId) {
//        UserBalanceDto userBalanceDto = new UserBalanceDto();
//        Account accountInfo = accountService.findByUserId(Long.parseLong(userId));
//        if (accountInfo == null) {
//            throw new BusinessException("该会员账户不存在");
//        }
//        userBalanceDto.setBalance(accountInfo.getBalance());
//        userBalanceDto.setUserId(userId);
//        return userBalanceDto;
//    }
//
//    /**
//     * 获取观世宇客户信息
//     *
//     * @param userId
//     * @return
//     */
//    @Deprecated
//    private AccountInfo getCustomerInfoGsy(String userId) {
//        AccountInfo accountInfo = null;
//        CustomerQueryRequest request = new CustomerQueryRequest();
//        request.setMerchOrderNo(Ids.getDid());
//        request.setUserId(userId);
//        request.setIsAccountInfo(true);
//        CustomerQueryResponse response = gsyBusinessService.customerQuery(request);
//
//        if (response.getStatus() == ResultStatus.success) {
//            accountInfo = response.getAccountInfo();
//        } else {
//            log.info("接观世宇获取用户信息失败");
//        }
//        return accountInfo;
//    }

    /**
     * 提现异步处理
     */
    @Transactional
    public void withdraw(WithdrawNotify notify) {
        try {
            //幂等性校验
            TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByOrderNo(notify.getMerchOrderNo());
            if (tradeOrderInfo == null) {
                log.info("此笔提现交易订单不存在，orderNO={}", notify.getMerchOrderNo());
                return;
            }
            if (tradeOrderInfo.getOrderStatus() != OrderStatusEnum.noPay
                    && tradeOrderInfo.getOrderStatus() != OrderStatusEnum.processing) {
                log.info("此笔提现交易订单已处理，orderNO={}", notify.getMerchOrderNo());
                return;
            }
            //更新流水表
            if (FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.SUCCESS) {
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.pay);
            } else if (FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.FAIL) {
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.fail);
            } else if (FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.CLOSE) {
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.close);
            } else {
                log.info("订单交易状态为orderStatus={}暂不用处理", notify.getFundStatus());
                return;
            }
            tradeOrderInfo.setFinishTime(new Date());
            tradeOrderInfoService.update(tradeOrderInfo);

            //成功写入交易记录
            if (FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.SUCCESS) {

                Account account = accountService.findByUserId(tradeOrderInfo.getUserId());
                //写入交易记录
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setInOutType(InOutType.out);
                tradingRecord.setOrderId(tradeOrderInfo.getId());
                tradingRecord.setTradingType(TradingType.withdraw);
                tradingRecord.setUserid(tradeOrderInfo.getUserId());
                tradingRecord.setTradingAmount(Money.amout(notify.getAmount()).getCent());
                tradingRecord.setBalance(account.getBalance());
                tradingRecord.setTradeMethod(tradeOrderInfo.getTradeText());
                tradingRecord.setTradeBusiness(TradeBusiness.tradeOrder);
                tradingRecordService.save(tradingRecord);

                WithdrawInfo withdrawInfo = new WithdrawInfo();
                withdrawInfo.setAccountNo(account.getAccountNo());
                withdrawInfo.setBizOrderNo(Ids.oid());
                withdrawInfo.setAmount(Money.amout(notify.getAmount()));
                withdrawInfo.setFreezeAmount(Money.amout(notify.getAmount()));
                withdrawInfo.setBusinessId(tradeOrderInfo.getOrderNo());

                accountService.withdraw(withdrawInfo);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 交易状态转订单状态
     */
    private OrderStatusEnum getOrderStatus(String tradeStatus) {
        OrderStatusEnum orderStatusEnum = null;
        if (Strings.isNotBlank(tradeStatus)) {
            TradeStatusEnum tradeStatusEnum = TradeStatusEnum.find(tradeStatus);
            if (tradeStatusEnum == TradeStatusEnum.SUCCESS) {
                orderStatusEnum = OrderStatusEnum.pay;
            } else if (tradeStatusEnum == TradeStatusEnum.FAIL) {
                orderStatusEnum = OrderStatusEnum.fail;
            } else if (tradeStatusEnum == TradeStatusEnum.CLOSE) {
                orderStatusEnum = OrderStatusEnum.close;
            } else if (tradeStatusEnum == TradeStatusEnum.REFUND) {
                orderStatusEnum = OrderStatusEnum.refund;
            } else {
                String message = String.format("交易订单状态为orderStatus=%s，暂不用处理", tradeStatus);
                log.info(message);
                throw new BusinessException(message);
            }
        }
        return orderStatusEnum;
    }

    /**
     * 交易状态转约导订单状态
     */
    private PlatOrderInfoOrderStatus getPlatOrderStatus(String tradeStatus) {
        PlatOrderInfoOrderStatus platOrderInfoOrderStatus = null;
        TradeStatusEnum tradeStatusEnum = TradeStatusEnum.find(tradeStatus);

        if (tradeStatusEnum == TradeStatusEnum.SUCCESS) {
            platOrderInfoOrderStatus = PlatOrderInfoOrderStatus.pay;
        } else if (tradeStatusEnum == TradeStatusEnum.FAIL) {
            platOrderInfoOrderStatus = PlatOrderInfoOrderStatus.fail;
        } else if (tradeStatusEnum == TradeStatusEnum.CLOSE) {
            platOrderInfoOrderStatus = PlatOrderInfoOrderStatus.close;
        } else if (tradeStatusEnum == TradeStatusEnum.REFUND) {
            platOrderInfoOrderStatus = PlatOrderInfoOrderStatus.refund;
        } else {
            String message = String.format("交易订单状态为orderStatus=%s，暂不用处理", tradeStatus);
            log.info(message);
            throw new BusinessException(message);
        }
        return platOrderInfoOrderStatus;
    }

    /**
     * 交易类型转订单交易类型
     */
    private OrderSubTradeTypeEnum getOrderSubTradeType(String tradeType) {
        OrderSubTradeTypeEnum orderSubTradeTypeEnum = null;
        TradeTypeEnum tradeTypeEnum = TradeTypeEnum.find(tradeType);

        if (tradeTypeEnum == TradeTypeEnum.WECHAT_SCAN_CODE_PAY) {
            orderSubTradeTypeEnum = OrderSubTradeTypeEnum.weiScan;
        } else if (tradeTypeEnum == TradeTypeEnum.ALI_SCAN_CODE_PAY) {
            orderSubTradeTypeEnum = OrderSubTradeTypeEnum.aliScan;
        } else {
            String message = String.format("订单交易类型为tradeType=%s，暂不用处理", tradeType);
            log.info(message);
            throw new BusinessException(message);
        }
        return orderSubTradeTypeEnum;
    }

    /**
     * 修改约导订单
     */
    @Transactional
    public void updatePlatOrderInfo(PlatOrderInfo platOrderInfo, TradeCreateNotify notify, TradeMethod tradeMethod, String customerUserId, String guideUserId) {
        try {
            if (platOrderInfo.getOrderStatus() != PlatOrderInfoOrderStatus.noPay
                    && platOrderInfo.getOrderStatus() != PlatOrderInfoOrderStatus.processing) {
                log.info(String.format("订单%s已处理", notify.getMerchOrderNo()));
            } else {
                //更新约导订单
                platOrderInfo.setOrderStatus(getPlatOrderStatus(notify.getTradeStatus()));
                platOrderInfoService.update(platOrderInfo);
                log.info(String.format("修改订单%s成功", notify.getMerchOrderNo()));
                //添加交易记录
                TradeStatusEnum tradeStatusEnum = TradeStatusEnum.find(notify.getTradeStatus());

                //成功则添加给早导网,游客和导游添加账务数据
                if (tradeStatusEnum == TradeStatusEnum.SUCCESS) {
                    platOrderInfoService.createPlatOrderAccount(platOrderInfo);
                }

                if (tradeStatusEnum == TradeStatusEnum.SUCCESS || tradeStatusEnum == TradeStatusEnum.REFUND) {
                    log.info(String.format("约导订单%s,开始填写交易记录...", platOrderInfo.getOrderNo()));
                    Money amount = Money.cent(platOrderInfo.getOrderAmount());
                    Account customerAccount = accountService.findByUserId(customerUserId);
                    //Account guideAccount = accountService.findByUserId(guideUserId);

                    //写两条交易记录,客户付款和导游收款
                    writePositTradingRecord(customerAccount, amount, TradingType.travel, tradeMethod.getMessage(), platOrderInfo.getId(), TradeBusiness.platOrder);
                    //2018-06-12 xh modify，导游上账时候，才能写这条交易记录
                    //writePositTradingRecord(guideAccount, amount, TradingType.deposit, tradeMethod.getMessage(), platOrderInfo.getId(), TradeBusiness.platOrder);
                }
            }
        } catch (BusinessException e) {
            log.info(String.format("更新交易订单%s出错！%s", platOrderInfo.getOrderNo(), e.getMessage()));
            throw e;
        } catch (Exception e) {
            log.info(String.format("系统错误！%s", platOrderInfo.getOrderNo(), e.getMessage()));
            throw e;
        }
    }

    /**
     * 执行约导订单回调逻辑
     */
    @Transactional
    public void executePlatOrderInfo(TradeCreateNotify notify, TradeMethod tradeMethod) {
        try {
            //加锁读
            PlatOrderInfo platOrderInfo = platOrderInfoService.findByOrderNoWithLock(notify.getMerchOrderNo());
            if (platOrderInfo == null) {
                log.info(String.format("未找到约导订单%s", notify.getMerchOrderNo()));
                throw new BusinessException(String.format("未找到约导订单%s", notify.getMerchOrderNo()));
            }
            Customer customer = customerService.getUser(platOrderInfo.getTouristId());
            Customer guide = customerService.getUser(platOrderInfo.getTourGuideId());

            //修改逻辑 加事务
            updatePlatOrderInfo(platOrderInfo, notify, tradeMethod, customer.getUserId(), guide.getUserId());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 获取用户余额实体
     */
    private UserBalanceDto getUserBalanceDto(String userId, Long balance) {
        UserBalanceDto userBalanceDto = new UserBalanceDto();
        userBalanceDto.setUserId(userId);
        userBalanceDto.setBalance(balance);
        return userBalanceDto;
    }

//    /**
//     * 支付宝、微信扫码异步处理(暂时作废)
//     *
//     * @param notify
//     */
//    @Transactional
//    public void tradeCreate(TradeCreateNotify notify) {
//        log.info(String.format("开始处理订单%s的过程...", notify.getMerchOrderNo()));
//        TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByOrderNo(notify.getMerchOrderNo());
//        if (tradeOrderInfo == null) {
//            log.info("交易订单为空, 尝试处理约导订单...");
//            executePlatOrderInfo(notify, TradeMethod.unknown);
//        } else {
//            log.info("尝试处理交易订单...");
//            UserBalanceDto ubCustomer = getCustomerBalanceGsy(tradeOrderInfo.getUserId());
//            updateTradeOrderInfo(tradeOrderInfo, notify, TradeMethod.unknown, ubCustomer);
//            TradeStatusEnum tradeStatusEnum = TradeStatusEnum.find(notify.getTradeStatus());
//            if (tradeStatusEnum == TradeStatusEnum.SUCCESS || tradeStatusEnum == TradeStatusEnum.REFUND) {
//                writePositTradingRecord(ubCustomer, Money.cent(tradeOrderInfo.getAmount()), TradingType.deposit, TradeMethod.unknown.getMessage(), tradeOrderInfo.getId(), TradeBusiness.tradeOrder);
//            }
//        }
//        log.info(String.format("结束处理订单%s的过程...", notify.getMerchOrderNo()));
//    }

    /**
     * 支付逻辑处理
     */
    @Override
    @Transactional
    public void payNotify(TradeCreateNotify notify, TradeMethod tradeMethod) {
        try {
            log.info("开始查询约导订单[{}]...", notify.getMerchOrderNo());
            //TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByOrderNo(notify.getMerchOrderNo());
            TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByTradeOrderNo(notify.getMerchOrderNo());
            if (tradeOrderInfo != null) {
                //更新支付交易订单完成时间
                if(Strings.isNotBlank(notify.getTradeTime())){
                    tradeOrderInfo.setTradeTime(Dates.parse(notify.getTradeTime()));
                }else {
                    tradeOrderInfo.setTradeTime(new Date());
                }

                //订单号不为空则是约导订单
                String orderNo = tradeOrderInfo.getOrderNo();
                if (Strings.isNotBlank(orderNo)) {
                    //约导订单
                    log.info("约导订单[{}]状态为{},尝试处理约导订单...", orderNo, tradeOrderInfo.getOrderTradeType().getMessage());
                    notify.setMerchOrderNo(orderNo);
                    executePlatOrderInfo(notify, tradeMethod);
                    tradeOrderInfo.setFinishTime(new Date());
                    tradeOrderInfoService.update(tradeOrderInfo);
                } else {
                    log.info("未查询到约导订单,开始处理交易订单[{}]...", notify.getMerchOrderNo());
                    log.info("交易订单[{}]状态为{},尝试处理交易订单...", tradeOrderInfo.getOrderTradeNo(), tradeOrderInfo.getOrderTradeType().getMessage());
                    //修改逻辑 加事务
                    updateTradeOrderInfo(tradeOrderInfo, notify, tradeMethod);
                }
            } else {
                throw new BusinessException("未找到交易订单记录,merchOrderNo={}", notify.getMerchOrderNo());
            }
            log.info(String.format("结束处理订单%s的过程...", notify.getMerchOrderNo()));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改交易订单
     */
    @Transactional
    public void updateTradeOrderInfo(TradeOrderInfo tradeOrderInfo, TradeCreateNotify notify, TradeMethod tradeMethod) {
        try {
            if (tradeOrderInfo.getOrderStatus() != OrderStatusEnum.noPay
                    && tradeOrderInfo.getOrderStatus() != OrderStatusEnum.processing) {
                log.info(String.format("交易订单%s已处理", notify.getMerchOrderNo()));
                return;
            }
            //获取交易订单状态
            tradeOrderInfo.setOrderStatus(getOrderStatus(notify.getTradeStatus()));
            //获取订单交易类型
            OrderSubTradeTypeEnum orderSubTradeType = getOrderSubTradeType(notify.getTradeType());
            tradeOrderInfo.setOrderSubTradeType(orderSubTradeType);
            tradeOrderInfo.setFinishTime(new Date());
            //这里判断交易方式最好使用观世宇回调参数判断
            if (tradeMethod == null || tradeMethod == TradeMethod.unknown) {
                if (orderSubTradeType == OrderSubTradeTypeEnum.aliScan) {
                    tradeMethod = TradeMethod.alipay;
                } else if (orderSubTradeType == OrderSubTradeTypeEnum.weiScan) {
                    tradeMethod = TradeMethod.weixin;
                } else {
                    tradeMethod = TradeMethod.balance;
                }
            }
            tradeOrderInfoService.update(tradeOrderInfo);
            log.info(String.format("修改交易订单%s成功", notify.getMerchOrderNo()));
            //处理交易订单
            TradeStatusEnum tradeStatusEnum = TradeStatusEnum.find(notify.getTradeStatus());
            if (tradeStatusEnum == TradeStatusEnum.SUCCESS || tradeStatusEnum == TradeStatusEnum.REFUND) {
                Money amount = Money.cent(tradeOrderInfo.getAmount());
                Account account = accountService.findByUserId(tradeOrderInfo.getUserId());
                //创建交易记录
                writePositTradingRecord(account, amount, TradingType.deposit, tradeMethod.getMessage(), tradeOrderInfo.getId(), TradeBusiness.tradeOrder);
                DepositInfo depositInfo = new DepositInfo();
                depositInfo.setBizOrderNo(Ids.oid());
                depositInfo.setUserId(account.getUserId());
                depositInfo.setAmount(amount);
                accountService.deposit(depositInfo);
            }
        } catch (BusinessException e) {
            log.info(String.format("更新交易订单%s出错！%s", tradeOrderInfo.getOrderTradeNo(), e.getMessage()));
            throw e;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 客户交易记录
     */
    @Override
    public void writePositTradingRecord(Account account, Money money, TradingType tradingType, String tradeMethod, Long orderId, TradeBusiness tradeBusiness) {
        log.info(String.format("开始处理交易记录信息, userId=%s", account.getUserId()));
        InOutType inOutType = tradingType == TradingType.deposit ? InOutType.in : InOutType.out;
        TradingRecord tradingRecord = new TradingRecord();
        tradingRecord.setInOutType(inOutType);
        tradingRecord.setUserid(account.getUserId());
        tradingRecord.setTradingAmount(money.getCent());
        tradingRecord.setBalance(account.getBalance());
        tradingRecord.setTradingType(tradingType);
        tradingRecord.setTradeMethod(tradeMethod);
        tradingRecord.setOrderId(orderId);
        tradingRecord.setTradeBusiness(tradeBusiness);
        tradingRecordService.save(tradingRecord);
        log.info(String.format("保存交易记录信息成功: %s", JSONObject.toJSON(tradingRecord)));
    }

    /**
     * 提现到卡(汇付到卡)
     */
    @Override
    @Transactional
    public void withdrawCard(WithdrawCardNotify notify) {
        TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByTradeOrderNo(notify.getMerchOrderNo());
        if (tradeOrderInfo == null) {
            throw new BusinessException("未找到交易订单");
        }
        if(!Strings.isNotBlank(notify.getAmount())) {
            throw new BusinessException("交易金额为空");
        }

        Long money = Money.amout(notify.getAmount()).getCent();
        Account account = accountService.findByUserId(tradeOrderInfo.getUserId());

        if(FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.SUCCESS) {
            tradeOrderInfo.setFinishTime(new Date());
            if(Strings.isNotBlank(notify.getTradeTime())) {
                tradeOrderInfo.setTradeTime(Dates.parse(notify.getTradeTime()));
            }else{
                tradeOrderInfo.setTradeTime(new Date());
            }
            tradeOrderInfo.setOrderStatus(OrderStatusEnum.pay);
            tradeOrderInfoService.update(tradeOrderInfo);

            //写入交易记录
            TradingRecord tradingRecord = new TradingRecord();
            tradingRecord.setInOutType(InOutType.out);
            tradingRecord.setOrderId(tradeOrderInfo.getId());
            tradingRecord.setTradingType(TradingType.withdraw);
            tradingRecord.setUserid(tradeOrderInfo.getUserId());
            tradingRecord.setTradingAmount(money);
            tradingRecord.setBalance(account.getBalance());
            tradingRecord.setTradeMethod(tradeOrderInfo.getTradeText());
            tradingRecord.setTradeBusiness(TradeBusiness.tradeOrder);
            tradingRecordService.save(tradingRecord);

            //游客解冻下账
            WithdrawInfo withdrawInfo = new WithdrawInfo();
            withdrawInfo.setAccountNo(account.getAccountNo());
            withdrawInfo.setBizOrderNo(Ids.oid());
            withdrawInfo.setAmount(Money.cent(money));
            withdrawInfo.setBusinessId(tradeOrderInfo.getOrderNo());
            withdrawInfo.setFreezeAmount(Money.cent(money));
            withdrawInfo.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
            accountService.withdraw(withdrawInfo);

            //早导网下账
            WithdrawInfo withdrawInfo1 = new WithdrawInfo();
            withdrawInfo1.setAccountNo(gatewayPartnerId);
            withdrawInfo1.setBizOrderNo(withdrawInfo.getBizOrderNo());
            withdrawInfo1.setAmount(Money.cent(money));
            withdrawInfo1.setBusinessId(tradeOrderInfo.getOrderNo());
            accountService.withdraw(withdrawInfo1);
        }else if(FundStatusEnum.find(notify.getFundStatus()) == FundStatusEnum.PROCESSING){
            //处理中则不修改交易订单
        }else{
            //游客解冻提现金额
            UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
            unFreezeInfo.setBizOrderNo(Ids.oid());
            unFreezeInfo.setBusinessId(tradeOrderInfo.getOrderNo());
            unFreezeInfo.setUserId(account.getUserId());
            unFreezeInfo.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
            unFreezeInfo.setUnFreezeAmount(Money.cent(money));
            accountService.unFreeze(unFreezeInfo);
            //修改交易订单为失败
            tradeOrderInfo.setOrderStatus(OrderStatusEnum.fail);
            tradeOrderInfoService.update(tradeOrderInfo);
        }
    }
}
