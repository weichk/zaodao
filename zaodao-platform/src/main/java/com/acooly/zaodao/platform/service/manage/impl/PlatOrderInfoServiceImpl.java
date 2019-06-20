/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.certification.CertificationService;
import com.acooly.module.certification.enums.BankCardResult;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.enums.AccountTransferTypeEnum;
import com.acooly.zaodao.account.info.*;
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.account.util.AccountConstants;
import com.acooly.zaodao.common.enums.BankIdEnum;
import com.acooly.zaodao.common.utils.Dates;
import com.acooly.zaodao.gateway.gsy.message.*;
import com.acooly.zaodao.gateway.gsy.message.dto.TradeCustomerInfo;
import com.acooly.zaodao.gateway.gsy.message.enums.DelayType;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeProfitStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeProfitTypeEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.dao.manage.PlatOrderInfoDao;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.dto.ScanCodePay;
import com.acooly.zaodao.platform.dto.TradingRecordDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.*;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.platform.service.platform.GsyNotifyService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 订单表 Service实现
 * <p>
 * Date: 2017-05-24 23:14:32
 *
 * @author zhike
 */
@Service("platOrderInfoService")
@Slf4j
public class PlatOrderInfoServiceImpl extends EntityServiceImpl<PlatOrderInfo, PlatOrderInfoDao>
        implements PlatOrderInfoService {

    @Autowired
    private PlatOrderInfoDao platOrderInfoDao;

    @Autowired
    private TourGuideService tourGuideService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private TradeOrderInfoService tradeOrderInfoService;

    @Autowired
    private CustomerCardService customerCardService;

    @Autowired
    private GsyNotifyService gsyNotifyService;

    @Autowired
    private PointTradeService pointTradeService;

    @Autowired
    private PlatOrderEvalService platOrderEvalService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TravelAgencyService travelAgencyService;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private OrderServiceFeeService orderServiceFeeService;

    @Autowired
    private CertificationService certificationService;

    @Value("${zaodao.rate.money2ca}")
    private String rateMoney2ca;

    @Value("${gsyPay.gateway.partnerId}")
    private String gatewayPartnerId;

    @Value("${gsyPay.gateway.zdUserId}")
    private String zdUserId;

    @Value("${zaodao.withdraw.delayType}")
    private String delayType;
    //private final String gatewayPartnerId = "o17112913080179520001";

    @Override
    public List<PlatOrderInfo> findLatestOrder(String userId) {
        Date dStart = new Date(); // 当前时间
        Date dEnd = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dStart);// 把当前时间赋给日历
        calendar.add(calendar.MONTH, 3); // 设置为后3月
        dEnd = calendar.getTime(); // 得到前3月的时间
        return platOrderInfoDao.findLatestOrder(userId, com.acooly.core.utils.Dates.format(dStart), com.acooly.core.utils.Dates.format(dEnd));
    }

    @Override
    public PageInfo<OrderInfoDto> getPageOrderInfoListByUserId(Integer currentPageNo, Integer countOfCurrentPage,
                                                               String userId, String orderStatus, Integer isTourGuide) {
        return getEntityDao().getPageOrderInfoListByUserId(getMyPageInfo(currentPageNo, countOfCurrentPage), userId,
                orderStatus, isTourGuide);
    }

    @Override
    public PlatOrderInfo findByOrderNo(String orderNo) {
        return platOrderInfoDao.findByOrderNo(orderNo);
    }

    @Override
    public PlatOrderInfo findByOrderNoWithLock(String orderNo){
        return platOrderInfoDao.findByOrderNoWithLock(orderNo);
    }

    @Override
    @Transactional
    public PlatOrderInfo createOrder(PlatOrderInfo platOrderInfo) {
        if (platOrderInfo == null) {
            throw new BusinessException("参数错误");
        }
        TourGuide guide = tourGuideService.findByUserIdWithLock(platOrderInfo.getTourGuideId());
        if (guide == null) {
            throw new BusinessException("导游信息不存在");
        }
        List<String> days = Dates.getBetweenDates(platOrderInfo.getStartPlayTime(), platOrderInfo.getEndPlayTime());
        List<String> orderDays = tourGuideService.getOrderLockDays(platOrderInfo.getTourGuideId());
        List<String> restDays = tourGuideService.getGuideRestDays(platOrderInfo.getTourGuideId());
        for (String day : days) {
            for (String restDay : restDays) {
                if (day.equals(restDay)) {
                    throw new BusinessException("该日期已被预定！");
                }
            }
            for (String orderDay : orderDays) {
                if (day.equals(orderDay)) {
                    throw new BusinessException("该日期已被预定！");
                }
            }
        }
        platOrderInfo.setDayCount(days.size());
        platOrderInfo.setOrderAmount(guide.getPricePerDay() * days.size());
        platOrderInfoDao.save(platOrderInfo);
        //获取导游信息
        Customer guideCustomer = customerService.findEntityByUserId(platOrderInfo.getTourGuideId());
        Customer touristCustomer = customerService.findEntityByUserId(platOrderInfo.getTouristId());
        //创建交易
        TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
        tradeCreateRequest.setMerchOrderNo(platOrderInfo.getOrderNo());
        tradeCreateRequest.setAmount(Money.cent(platOrderInfo.getOrderAmount()));
        tradeCreateRequest.setTradeName("早导网约导付费");
        tradeCreateRequest.setSellerUserId(guideCustomer.getUserId());
        tradeCreateRequest.setTradeTime(new Date());
        TradeCustomerInfo tradeCustomerInfo = new TradeCustomerInfo();
        tradeCustomerInfo.setRealName(touristCustomer.getRealName());
        tradeCustomerInfo.setMobileNo(touristCustomer.getMobileNo());
        tradeCustomerInfo.setIdentityCard(touristCustomer.getIdNumber());
        tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
        tradeCreateRequest.setTradeProfitType(TradeProfitTypeEnum.MANUAL);
        TradeCreateResponse tradeCreateResponse = gsyTradePayService.tradeCreate(tradeCreateRequest);
        if (tradeCreateResponse.getStatus() != ResultStatus.success) {
            log.info("用户【{}】约导付款创建交易失败：{}", touristCustomer.getMobileNo(), tradeCreateResponse.getResultMessage());
            throw new BusinessException("创建交易失败");
        }
        log.info("用户【{}】约导付款创建交易成功", touristCustomer.getMobileNo());
        return platOrderInfo;
    }

    /**
     * API创建订单
     */
    public PlatOrderInfoResult createPlatOrder(PlatOrderInfoOrder platOrderInfoOrder) {
        PlatOrderInfoResult platOrderInfoResult = new PlatOrderInfoResult();
        try {
            platOrderInfoOrder.check();
            Customer customer = customerService.getUser(platOrderInfoOrder.getUserId());
            TourGuide guide = tourGuideService.findByUserIdWithLock(platOrderInfoOrder.getGuideUserId());
            if (guide == null) {
                throw new BusinessException("未找到导游信息");
            }

            if (Strings.isNotBlank(platOrderInfoOrder.getPlatOrderNo())) {
                //订单号不为空则修改订单(联系人信息)
                updateOrderInfo(platOrderInfoOrder);
                platOrderInfoResult.setPlatOrderNo(platOrderInfoOrder.getPlatOrderNo());
                log.info("修改订单{}成功！", platOrderInfoOrder.getPlatOrderNo());
            } else {
                //订单号为空则接观世宇创建订单
                PlatOrderInfo platOrderInfo = addOrderGsy(platOrderInfoOrder, customer, guide);
                platOrderInfoResult.setPlatOrderNo(platOrderInfo.getOrderNo());
                log.info("创建订单{}成功！", platOrderInfo.getOrderNo());
            }
        } catch (BusinessException e) {
            log.info("业务异常！{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            throw e;
        }

        return platOrderInfoResult;
    }

    /**
     * 修改订单
     */
    private void updateOrderInfo(PlatOrderInfoOrder order) {
        //PlatOrderInfo platOrderInfo = this.platOrderInfoDao.findByOrderNo(order.getPlatOrderNo());
        PlatOrderInfo platOrderInfo = this.platOrderInfoDao.findByOrderNoWithLock(order.getPlatOrderNo());
        if (platOrderInfo == null) {
            String message = String.format("未找到订单%s，修改失败", order.getPlatOrderNo());
            log.info(message);
            throw new BusinessException(ResultCode.PARAMETER_ERROR, message);
        } else {
            platOrderInfo.setContactPhone(order.getContactPhone());
            platOrderInfo.setContactName(order.getContactName());
            platOrderInfo.setContactMemo(order.getContactMemo());

            //修改旅行社
            if(order.getReservationType() != null) {
                platOrderInfo.setTravelAgencyId(order.getTravelAgencyId());
                platOrderInfo.setReservationType(order.getReservationType());
            }
            this.platOrderInfoDao.save(platOrderInfo);
        }
    }

    /**
     * 接观世宇平台添加订单
     */
    private PlatOrderInfo addOrderGsy(PlatOrderInfoOrder platOrderInfoOrder, Customer customer, TourGuide guide) {
        PlatOrderInfo platOrderInfo = getPlatOrderInfoFromOrder(platOrderInfoOrder, customer, guide);
        //2018-05-15 xh modify,因为修改订单金额时无法修改观世宇订单金额，
        // 所以创建订单放入支付订单方法
        //请求观世宇创建订单
//        TradeCreateResponse response = createGsyTrade(platOrderInfo, customer, guide);
//        if (response.getStatus() == ResultStatus.success) {
//            this.platOrderInfoDao.save(platOrderInfo);
//        } else {
//            String message = String.format("创建订单失败，%s", response.getResultMessage());
//            log.info(String.format("接观世宇创建订单失败, %s", response.getResultMessage()));
//            throw new BusinessException(message);
//        }

        this.platOrderInfoDao.save(platOrderInfo);
        return platOrderInfo;
    }

    /**
     * 创建订单成功后，观世宇回调成功，添加早导网,导游和游客账务
     */
    @Override
    @Transactional
    public void createPlatOrderAccount(PlatOrderInfo platOrderInfo){
        //交易金额
        Money money = Money.cent(platOrderInfo.getOrderAmount());
        //早导网上账
        DepositInfo depositZaodao = new DepositInfo();
        depositZaodao.setBusinessId(platOrderInfo.getOrderNo());
        depositZaodao.setBizOrderNo(Ids.oid());
        depositZaodao.setAccountNo(gatewayPartnerId);
        depositZaodao.setAmount(money);
        accountService.deposit(depositZaodao);
        //游客上账
        DepositInfo depositInfoTourist = new DepositInfo();
        depositInfoTourist.setBusinessId(platOrderInfo.getOrderNo());
        depositInfoTourist.setBizOrderNo(depositZaodao.getBizOrderNo());
        depositInfoTourist.setUserId(platOrderInfo.getTouristId());
        depositInfoTourist.setAmount(money);
        depositInfoTourist.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
        depositInfoTourist.setFreezeAmount(money);
        accountService.deposit(depositInfoTourist);
    }

    /**
     * 修改订单金额
     */
    @Override
    @Transactional
    public ResultBase modifyOrderAmount(AmountModifyOrder order) {
        ResultBase result = new ResultBase();
        try{
            order.check();
            //PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNo(order.getPlatOrderNo());
            PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNoWithLock(order.getPlatOrderNo());
            if(PlatOrderInfoOrderStatus.noPay != platOrderInfo.getOrderStatus()){
                throw new BusinessException(String.format("%s状态不能修改金额", platOrderInfo.getOrderStatus().getMessage()));
            }
            platOrderInfo.setOrderAmount(order.getOrderAmount().getCent());
            this.getEntityDao().update(platOrderInfo);
        } catch (BusinessException e) {
            log.info("业务异常！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * PlatOrderInfoOrder+Customer转换为PlatOrderInfo
     */
    private PlatOrderInfo getPlatOrderInfoFromOrder(PlatOrderInfoOrder platOrderInfoOrder, Customer customer,
                                                    TourGuide guide) {
        if (platOrderInfoOrder == null) {
            throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,转换参数不能为空", ResultCode.PARAMETER_ERROR.getMessage()));
        }
        //获取约导天数
        int dayCount = tourGuideService.getGuideDays(platOrderInfoOrder.getGuideUserId(), platOrderInfoOrder
                .getStartTime(), platOrderInfoOrder.getEndTime());

        PlatOrderInfo platOrderInfo = new PlatOrderInfo();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        platOrderInfo.setOrderNo(Ids.oid());
        platOrderInfo.setStartTime(simpleDateFormat.format(platOrderInfoOrder.getStartTime()));
        platOrderInfo.setEndTime(simpleDateFormat.format(platOrderInfoOrder.getEndTime()));
        platOrderInfo.setStartPlayTime(platOrderInfoOrder.getStartTime());
        platOrderInfo.setEndPlayTime(platOrderInfoOrder.getEndTime());
        platOrderInfo.setAdultCount(Integer.parseInt(platOrderInfoOrder.getAdultNum()));
        platOrderInfo.setChildCount(Integer.parseInt(platOrderInfoOrder.getChildrenNum()));
        platOrderInfo.setTourGuideId(platOrderInfoOrder.getGuideUserId());
        platOrderInfo.setTouristId(customer.getUserId());
        platOrderInfo.setOrderStatus(PlatOrderInfoOrderStatus.noPay);
        platOrderInfo.setContactName(customer.getRealName());
        platOrderInfo.setContactPhone(customer.getMobileNo());
        platOrderInfo.setContactName(platOrderInfoOrder.getContactName());
        platOrderInfo.setContactPhone(platOrderInfoOrder.getContactPhone());
        platOrderInfo.setContactMemo(platOrderInfoOrder.getContactMemo());
        platOrderInfo.setDayCount(dayCount);
        platOrderInfo.setOrderAmount(guide.getPricePerDay() * dayCount);
        platOrderInfo.setPricePerDay(guide.getPricePerDay());
        platOrderInfo.setReservationType(platOrderInfoOrder.getReservationType());
        platOrderInfo.setReadStatusGuide(0);
        platOrderInfo.setReadStatusTourist(0);

        if(platOrderInfoOrder.getReservationType() == ReservationType.agency){
            platOrderInfo.setTravelAgencyId(platOrderInfoOrder.getTravelAgencyId());
        }
        return platOrderInfo;
    }

    /**
     * 获取交易实体
     */
    private TradeCustomerInfo getTradeCustomerInfo(Customer customer) {
        TradeCustomerInfo tradeCustomerInfo = new TradeCustomerInfo();
        tradeCustomerInfo.setRealName(customer.getRealName());
        tradeCustomerInfo.setMobileNo(customer.getMobileNo());
        tradeCustomerInfo.setIdentityCard(customer.getIdNumber());

        return tradeCustomerInfo;
    }

    /**
     * 调用观世宇接口创建订单
     */
    private TradeCreateResponse createGsyTrade(String orderTradeNo, PlatOrderInfo platOrderInfo, Customer customer) {
        //创建交易
        TradeCustomerInfo tradeCustomerInfo = getTradeCustomerInfo(customer);
        //创建Request
        TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
        //tradeCreateRequest.setMerchOrderNo(platOrderInfo.getOrderNo());
        tradeCreateRequest.setMerchOrderNo(orderTradeNo);
        tradeCreateRequest.setAmount(Money.cent(platOrderInfo.getOrderAmount()));
        tradeCreateRequest.setTradeName("早导网约导付费");
        tradeCreateRequest.setSellerUserId(zdUserId);
        tradeCreateRequest.setTradeTime(new Date());
        tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
        tradeCreateRequest.setTradeProfitType(TradeProfitTypeEnum.MANUAL);

        return gsyTradePayService.tradeCreate(tradeCreateRequest);
    }

    public PlatOrderInfo confirm(Customer customer, String orderNo) {
        //PlatOrderInfo orderInfo = getEntityDao().findByOrderNo(orderNo);
        PlatOrderInfo orderInfo = getEntityDao().findByOrderNoWithLock(orderNo);
        if (!orderInfo.getTouristId().equals(customer.getUserId())) {
            throw new BusinessException("订单信息有误!");
        }
        //交易清分
        log.info("处理交易清分...");
        TradeProfitRequest request = new TradeProfitRequest();
        request.setMerchOrderNo(orderNo);
        TradeProfitResponse response = gsyTradePayService.tradeProfit(request);

        if (response.getProfitStatus() == TradeProfitStatusEnum.PROFIT_SUCCESS) {
            orderInfo.setOrderStatus(PlatOrderInfoOrderStatus.confirm);
            //更新交易记录余额字段
            log.info("更新最后一条交易记录...");
            TradingRecord lastRecord = tradingRecordService.findLastRecodeByUserId(orderInfo.getTourGuideId());
            Long oldBalance = 0L;
            if (lastRecord != null) {
                oldBalance = lastRecord.getBalance();
            }
            log.info("更新交易记录余额字段...");
            TradingRecord tradingRecord = tradingRecordService.findRecodeByUserIdAndOrderNo(orderInfo.getTourGuideId(), orderInfo.getId());
            if (tradingRecord != null) {
                tradingRecord.setBalance(oldBalance + response.getAmount().getCent() - response.getProfitAmount().getCent());
                tradingRecordService.update(tradingRecord);
            }
            getEntityDao().update(orderInfo);
            return orderInfo;
        } else {
            log.info(String.format("确认失败，%s", response.getResultMessage()));
            throw new BusinessException(String.format("确认失败，%s", response.getResultMessage()));
        }
    }

    @Override
    public PageInfo<PlatOrderInfo> getPageOrderInfoListByOrderStatus(Integer currentPageNo, Integer countOfCurrentPage, String orderStatus) {
        return getEntityDao().getPageOrderInfoListByOrderStatus(getOrderPageInfo(currentPageNo, countOfCurrentPage), orderStatus);
    }

    /**
     * 调整订单状态
     */
    @Override
    public ResultBase changePlatOrderStatus(PlatOrderStatusChangeOrder order) {
        ResultBase resultBase = new ResultBase();
        try {
            PlatOrderInfoOrderStatus status = order.getStatus();

            //PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNo(order.getPlatOrderNo());
            PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNoWithLock(order.getPlatOrderNo());
            log.info(String.format("订单[%s]原状态[%s]", order.getPlatOrderNo(), platOrderInfo.getOrderStatus().getCode()));
            log.info(String.format("订单[%s]修改为[%s]状态", order.getPlatOrderNo(), order.getStatus().getCode()));

            if (status == PlatOrderInfoOrderStatus.confirm) {
                //确认订单完成
                if(platOrderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.confirm) {
                    throw new BusinessException("订单已经确认完成");
                }
                confirmOrder(platOrderInfo, order);
            }else if(status == PlatOrderInfoOrderStatus.close){
                if(platOrderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.close) {
                    throw new BusinessException("订单已经关闭");
                }
                //取消订单
                closeOrder(platOrderInfo, order);
            }else if(status == PlatOrderInfoOrderStatus.confirming){
                Date current = new Date();
                if(platOrderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.confirming) {
                    throw new BusinessException("订单已经是待确认状态");
                }else if(Dates.getCountNaturalDay(platOrderInfo.getEndPlayTime(), current) < 0){
                    throw new BusinessException("当前日期大于等于旅游结束日期才能点击结束服务");
                }
                platOrderInfo.setGuideConfirmTime(current);
                platOrderInfo.setOrderStatus(status);
                this.getEntityDao().update(platOrderInfo);
            }else {
                platOrderInfo.setOrderStatus(status);
                this.getEntityDao().update(platOrderInfo);
            }
        } catch (BusinessException e) {
            log.info("订单修改状态失败！{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            throw e;
        }
        return resultBase;
    }

    /**
     * 订单确认完成（controller调用）
     */
    @Override
    public void confirmOrder(Customer customer, String orderNo) {
        //PlatOrderInfo platOrderInfo = getEntityDao().findByOrderNo(orderNo);
        PlatOrderInfo platOrderInfo = getEntityDao().findByOrderNoWithLock(orderNo);
        if(platOrderInfo == null){
            throw new BusinessException("未找到订单");
        } else if (!platOrderInfo.getTouristId().equals(customer.getUserId())) {
            throw new BusinessException("订单信息有误");
        }

        PlatOrderStatusChangeOrder order = new PlatOrderStatusChangeOrder();
        order.setStatus(PlatOrderInfoOrderStatus.confirm);
        confirmOrder(platOrderInfo, order);

        //this.getEntityDao().update(platOrderInfo);
    }

    /**
     * 订单确认完成
     */
    @Transactional
    public void confirmOrder(PlatOrderInfo platOrderInfo, PlatOrderStatusChangeOrder order){
        platOrderInfo.setOrderStatus(order.getStatus());
        //平台服务费
        Money platFee = getGuideServiceFee(platOrderInfo);
        Money orderAmount = Money.cent(platOrderInfo.getOrderAmount());
        //给导游的费用
        Long cent = orderAmount.getCent() - platFee.getCent();
        if(cent <= 0){
            //如果订单金额小于约导服务费，则不取约导服务费
            cent = platOrderInfo.getOrderAmount();
            log.info("订单金额{}分小于约导服务费，则不取约导服务费(不给平台转账)", cent);
        }

        Money money = Money.cent(cent);
        log.info("订单[{}]费用={}分,平台服务费={}分,导游收款={}分", platOrderInfo.getOrderNo(), platOrderInfo.getOrderAmount(), platFee.getCent(), money.getCent());
        //游客转账给导游
        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setBizOrderNo(Ids.oid());
        transferInfo.setBusinessId(platOrderInfo.getOrderNo());
        transferInfo.setTransferType(AccountTransferTypeEnum.TRANSFER);
        transferInfo.setPayerUserId(platOrderInfo.getTouristId());
        transferInfo.setPayerFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
        transferInfo.setPayerFreezeAmount(money);
        transferInfo.setPayeeUserId(platOrderInfo.getTourGuideId());
        transferInfo.setAmount(money);
        accountService.transfer(transferInfo);

        //导游账户
        Account account = accountService.findByUserId(platOrderInfo.getTourGuideId());
        //导游收费费：导游账户余额in(订单金额),后面需要写一条平台收费记录
        TradingRecord tradingRecord = new TradingRecord();
        tradingRecord.setInOutType(InOutType.in);
        tradingRecord.setOrderId(platOrderInfo.getId());
        tradingRecord.setTradingType(TradingType.guide_fee);
        tradingRecord.setUserid(platOrderInfo.getTourGuideId());
        tradingRecord.setTradingAmount(orderAmount.getCent());
        tradingRecord.setBalance(account.getBalance());
        tradingRecord.setTradeMethod("账户余额");
        tradingRecord.setTradeBusiness(TradeBusiness.platOrder);
        tradingRecordService.save(tradingRecord);

        //游客给转账给平台
        if((platOrderInfo.getOrderAmount() - platFee.getCent() > 0) && platFee.getCent() > 0){
            log.info("订单{}收取平台服务费{}", platOrderInfo.getOrderNo(), platFee.getCent());
            TransferInfo transferInfo1 = new TransferInfo();
            transferInfo1.setBizOrderNo(Ids.oid());
            transferInfo1.setBusinessId(platOrderInfo.getOrderNo());
            transferInfo1.setTransferType(AccountTransferTypeEnum.TRANSFER);
            transferInfo1.setPayerUserId(platOrderInfo.getTouristId());
            transferInfo1.setPayerFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
            transferInfo1.setPayerFreezeAmount(platFee);
            transferInfo1.setPayeeUserId(gatewayPartnerId);
            transferInfo1.setAmount(platFee);
            accountService.transfer(transferInfo1);

            //导游服务费：导游账户余额out(平台收费)
            TradingRecord tradingRecord1 = new TradingRecord();
            tradingRecord1.setInOutType(InOutType.out);
            tradingRecord1.setOrderId(platOrderInfo.getId());
            tradingRecord1.setTradingType(TradingType.plat_service);
            tradingRecord1.setUserid(platOrderInfo.getTourGuideId());
            tradingRecord1.setTradingAmount(platFee.getCent());
            tradingRecord1.setBalance(account.getBalance());
            tradingRecord1.setTradeMethod("账户余额");
            tradingRecord1.setTradeBusiness(TradeBusiness.platOrder);
            tradingRecordService.save(tradingRecord1);
        }
        this.getEntityDao().update(platOrderInfo);
    }

    /**
     * 获取约导服务费
     */
    @Override
    public Money getGuideServiceFee(PlatOrderInfo platOrderInfo){
        Money money = Money.cent(0);
        List<OrderServiceFee> orderServiceFeeList = orderServiceFeeService.getByFeeName(ServiceFeeNameType.order_service);
        if(orderServiceFeeList != null && orderServiceFeeList.size() > 0){
            OrderServiceFee fee = orderServiceFeeList.get(0);
            if(fee.getFeeScale() == ServiceFeeScaleType.fixed_fee){
                money = Money.cent(fee.getFeeAmount());
            }else if(fee.getFeeScale() == ServiceFeeScaleType.percent_fee){
                money = Money.cent(platOrderInfo.getOrderAmount() * fee.getFeeAmount() / 10000);
            }
        }
        return money;
    }

    /**
     * 订单未读个数统计
     */
    @Override
    public PlatOrderCountResult getPlatOrderCount(String userId) {
        PlatOrderCountResult result = new PlatOrderCountResult();
        try {
            Integer unReadMySendCount = this.getEntityDao().getUnReadMySendCount(userId);
            Integer unReadMyReceiveCount = this.getEntityDao().getUnReadMyReceiveCount(userId);
            result.setUnReadMySendCount(unReadMySendCount);
            result.setUnReadMyReceiveCount(unReadMyReceiveCount);
            result.setUnReadSumCount(unReadMySendCount + unReadMyReceiveCount);
            result.setUnReadPointCount(0);
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * 取消订单逻辑处理
     */
    @Override
    @Transactional
    public void closeOrder(PlatOrderInfo platOrderInfo, PlatOrderStatusChangeOrder order){
        if(Dates.getCountNaturalDay(new Date(), platOrderInfo.getStartPlayTime()) <= 0){
            throw new BusinessException("当前日期已经超过开始游玩日期，不能取消订单");
        }

        if(platOrderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.pay) {
            String bizOrderNo = Ids.oid();
            //已支付且是我约的订单执行取消操作，则扣除服务费
            if (order.getCloseOrderCall() == OrderCallEnum.callSomebody) {
                //计算服务费
                Money serviceAmount = orderServiceFeeService.getCancelOrderServiceFee(platOrderInfo);
                Money orderAmount = Money.cent(platOrderInfo.getOrderAmount());
                log.info("订单金额={},服务费={}", orderAmount.getCent(), serviceAmount.getCent());
                //1 游客解冻orderAmount
                UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
                unFreezeInfo.setBizOrderNo(bizOrderNo);
                unFreezeInfo.setBusinessId(platOrderInfo.getOrderNo());
                unFreezeInfo.setUserId(platOrderInfo.getTouristId());
                unFreezeInfo.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
                unFreezeInfo.setUnFreezeAmount(orderAmount);
                accountService.unFreeze(unFreezeInfo);

                //游客账户
                Account account = accountService.findByUserId(platOrderInfo.getTouristId());
                //游客：游客账户余额in退款
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setInOutType(InOutType.in);
                tradingRecord.setOrderId(platOrderInfo.getId());
                tradingRecord.setTradingType(TradingType.close_order);
                tradingRecord.setUserid(platOrderInfo.getTouristId());
                tradingRecord.setTradingAmount(orderAmount.getCent());
                tradingRecord.setBalance(account.getBalance());
                tradingRecord.setTradeMethod("账户余额");
                tradingRecord.setTradeBusiness(TradeBusiness.platOrder);
                tradingRecordService.save(tradingRecord);

                if(orderAmount.getCent() >= serviceAmount.getCent() && serviceAmount.getCent() > 0){
                    log.info("订单金额({})>=服务费({})，则收取服务费", orderAmount.getCent(), serviceAmount.getCent());
                    log.info("游客转账({})给导游", serviceAmount.getCent());
                    //2 游客转账serviceAmount给导游
                    TransferInfo transferInfo = new TransferInfo();
                    transferInfo.setBizOrderNo(bizOrderNo);
                    transferInfo.setBusinessId(platOrderInfo.getOrderNo());
                    transferInfo.setTransferType(AccountTransferTypeEnum.TRANSFER);
                    transferInfo.setPayerUserId(platOrderInfo.getTouristId());
                    transferInfo.setPayeeUserId(platOrderInfo.getTourGuideId());
                    transferInfo.setAmount(serviceAmount);
                    accountService.transfer(transferInfo);
                    //导游账户
                    Account account1 = accountService.findByUserId(platOrderInfo.getTourGuideId());
                    //导游服务费：导游账户余额in服务费
                    TradingRecord tradingRecord1 = new TradingRecord();
                    tradingRecord1.setInOutType(InOutType.in);
                    tradingRecord1.setOrderId(platOrderInfo.getId());
                    tradingRecord1.setTradingType(TradingType.close_fee);
                    tradingRecord1.setUserid(platOrderInfo.getTourGuideId());
                    tradingRecord1.setTradingAmount(serviceAmount.getCent());
                    tradingRecord1.setBalance(account1.getBalance());
                    tradingRecord1.setTradeMethod("账户余额");
                    tradingRecord1.setTradeBusiness(TradeBusiness.platOrder);
                    tradingRecordService.save(tradingRecord1);

                    //游客账户
                    Account account2 = accountService.findByUserId(platOrderInfo.getTouristId());
                    //游客：游客账户余额out
                    TradingRecord tradingRecord2 = new TradingRecord();
                    tradingRecord2.setInOutType(InOutType.out);
                    tradingRecord2.setOrderId(platOrderInfo.getId());
                    tradingRecord2.setTradingType(TradingType.close_fee);
                    tradingRecord2.setUserid(platOrderInfo.getTouristId());
                    tradingRecord2.setTradingAmount(serviceAmount.getCent());
                    tradingRecord2.setBalance(account2.getBalance());
                    tradingRecord2.setTradeMethod("账户余额");
                    tradingRecord2.setTradeBusiness(TradeBusiness.platOrder);
                    tradingRecordService.save(tradingRecord2);
                }
                //给导游发送消息
                sendCancelMessage(platOrderInfo.getOrderNo(), platOrderInfo.getTourGuideId(), false);
            } else if (order.getCloseOrderCall() == OrderCallEnum.callMe) {
                //已支付，约我的订单，取消(导游取消)，全额退,且订单评分为0
                UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
                unFreezeInfo.setBizOrderNo(bizOrderNo);
                unFreezeInfo.setBusinessId(platOrderInfo.getOrderNo());
                unFreezeInfo.setUserId(platOrderInfo.getTouristId());
                unFreezeInfo.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
                unFreezeInfo.setUnFreezeAmount(Money.cent(platOrderInfo.getOrderAmount()));
                accountService.unFreeze(unFreezeInfo);
                //游客账户
                Account account = accountService.findByUserId(platOrderInfo.getTouristId());
                //游客：游客账户余额in退款
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setInOutType(InOutType.in);
                tradingRecord.setOrderId(platOrderInfo.getId());
                tradingRecord.setTradingType(TradingType.guide_close_order);
                tradingRecord.setUserid(platOrderInfo.getTouristId());
                tradingRecord.setTradingAmount(platOrderInfo.getOrderAmount());
                tradingRecord.setBalance(account.getBalance());
                tradingRecord.setTradeMethod("账户余额");
                tradingRecord.setTradeBusiness(TradeBusiness.platOrder);
                tradingRecordService.save(tradingRecord);
                //保存订单评价,0分
                savePlatOrderEval(platOrderInfo.getOrderNo(), platOrderInfo.getTouristId(), platOrderInfo.getTourGuideId());
                //给游客发送消息
                sendCancelMessage(platOrderInfo.getOrderNo(), platOrderInfo.getTouristId(), true);
            }
        }
        platOrderInfo.setOrderStatus(order.getStatus());
        platOrderInfo.setCloseOrderCall(order.getCloseOrderCall());
        platOrderInfo.setCloseReasonType(order.getCloseReasonType());
        platOrderInfo.setCloseReasonDesc(order.getCloseReasonDesc());
        this.getEntityDao().update(platOrderInfo);
    }

    /**
     * 添加订单评价为0分
     */
    private void savePlatOrderEval(String orderNo, String touristId, String tourGuideId){
        PlatOrderEval platOrderEval = new PlatOrderEval();
        platOrderEval.setOrderNo(orderNo);
        platOrderEval.setTouristId(touristId);
        platOrderEval.setTourGuideId(tourGuideId);
        platOrderEval.setScore(0);
        platOrderEvalService.save(platOrderEval);
    }

    /**
     * 游客取消订单，给导游发送消息
     * 导游取消订单，给游客发送消息
     * */
    private void sendCancelMessage(String orderNo, String userId, boolean isGuide){
        StringBuilder msg = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        msg.append(String.format("【订单取消】订单号%s于%s被", orderNo, dateFormat.format(date)));
        if(isGuide){
            msg.append("导游");
        }else{
            msg.append("游客");
        }
        msg.append("取消，详情查看订单。");

        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setUserId(userId);
        customerMessage.setMessageTitle("订单取消");
        customerMessage.setMessage(msg.toString());
        customerMessage.setMessageType(CustomerMessageType.close_order);
        customerMessage.setOrderNo(orderNo);
        customerMessageService.save(customerMessage);
    }

    /**
     * 完成订单
     */
    @Override
     public ResultBase setPlatOrderFinish(PlatOrderFinishOrder order) {
        ResultBase resultBase = new ResultBase();

        try {
            order.check();
            PlatOrderStatusChangeOrder changeOrder = new PlatOrderStatusChangeOrder();
            changeOrder.setPlatOrderNo(order.getPlatOrderNo());
            changeOrder.setStatus(PlatOrderInfoOrderStatus.confirm);
            resultBase = changePlatOrderStatus(changeOrder);
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("系统错误！");
        }

        return resultBase;
    }

    /**
     * 获取订单列表
     */
    @Override
    public PageResult<OrderInfoDto> getPagePlateOrderList(QueryPlateOrder order) {
        String userId = "";
        String statusCode = "";
        int orderCall = 0;
        PageInfo<OrderInfoDto> pageInfo = new PageInfo<OrderInfoDto>();
        PageResult<OrderInfoDto> pageResult = new PageResult<OrderInfoDto>();

        if (null != order) {
            userId = order.getUserId();
            statusCode = order.getOrderStatus() != null ? order.getOrderStatus().code() : "";
            orderCall = order.getOrderCallEnum() == OrderCallEnum.callMe ? 1 : 0;
        }
        try {
            //页大小和页号
            pageInfo.setCountOfCurrentPage(order.getPageInfo().getCountOfCurrentPage());
            pageInfo.setCurrentPage(order.getPageInfo().getCurrentPage());
            pageInfo = platOrderInfoDao.getPageOrderInfoListByUserId(pageInfo, userId, statusCode, orderCall);

            if (null != pageInfo && null != pageInfo.getPageResults() && Strings.isNotBlank(order.getUserId())) {
                pageInfo.getPageResults().forEach(p -> {
                    //加锁读和修改
                    modifyReadStatus(p.getPlatOrderNo(), order.getUserId());
                });
            }
            pageResult.setDto(pageInfo);
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }
        return pageResult;
    }

    /**
     * 修改是否已读状态
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyReadStatus(String orderNo, String userId){
        //加锁读
        PlatOrderInfo orderInfo = this.getEntityDao().findByOrderNoWithLock(orderNo);
        if (orderInfo.getTouristId().equals(userId) && 1 != orderInfo.getReadStatusTourist()) {
            //游客+未读 -> 修改为已读
            this.platOrderInfoDao.modifyReadStatusTourist(orderNo);
        } else if (orderInfo.getTourGuideId().equals(userId) && 1 != orderInfo.getReadStatusGuide()) {
            //导游+未读 -> 修改为已读
            this.platOrderInfoDao.modifyReadStatusGuide(orderNo);
        }
    }

    /**
     * 支付订单
     */
    @Override
    public PlatOrderPayResult payPlatOrder(PlatOrderPayOrder platOrderPayOrder) {
        PlatOrderPayResult result = new PlatOrderPayResult();
        try {
            platOrderPayOrder.check();
            //查找约导订单
            //PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNo(platOrderPayOrder.getPlatOrderNo());
            PlatOrderInfo platOrderInfo = this.getEntityDao().findByOrderNoWithLock(platOrderPayOrder.getPlatOrderNo());
            if (platOrderInfo == null) {
                throw new BusinessException(String.format("%s,找不到订单信息", ResultCode.PARAMETER_ERROR.getMessage()));
            }else if(platOrderInfo.getOrderStatus() != PlatOrderInfoOrderStatus.noPay){
                throw new BusinessException("订单不是未支付状态");
            }
            Customer customer = customerService.getUser(platOrderPayOrder.getUserId());

            //创建交易订单
            TradeOrderInfo tradeOrderInfo = getTradeOrderInfo(customer, platOrderInfo.getOrderNo(), Ids.oid(), Money.cent(platOrderInfo.getOrderAmount()), OrderTradeTypeEnum.pay, OrderStatusEnum.pay);
            tradeOrderInfoService.save(tradeOrderInfo);
            log.info("订单{}创建支付订单：{}", platOrderInfo.getOrderNo(), tradeOrderInfo.getOrderTradeNo());
            //创建观世宇订单
            TradeCreateResponse response = createGsyTrade(tradeOrderInfo.getOrderTradeNo(), platOrderInfo, customer);
            //成功或者订单已经存在
            if (response.getStatus() == ResultStatus.success) {
                //请求观世宇支付订单
                ScanCodePay scanCodePay = payGsyTrade(tradeOrderInfo.getOrderTradeNo(), platOrderPayOrder.getUserIp(), platOrderPayOrder.getDeviceType(), platOrderPayOrder.getPayType());
                if (scanCodePay.getStatus() == ResultStatus.failure) {
                    log.info("观世宇支付失败！{}", scanCodePay.getMessage());
                    result.setStatus(ResultStatus.failure);
                    result.setCode(ResultStatus.failure.getCode());
                    result.setDetail(scanCodePay.getMessage());
                } else {
                    result.setCodeUrl(scanCodePay.getCodeUrlImg());
                    result.setCodeUrlContent(scanCodePay.getCodeUrlContentEx());
                }
            }else{
                log.info("创建观世宇订单失败！{}", response.getResultDetail());
                result.setStatus(ResultStatus.failure);
                result.setCode(ResultStatus.failure.getCode());
                result.setDetail(response.getResultDetail());
            }
        } catch (BusinessException e) {
            log.info("业务异常！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 获取订单
     */
    @Override
    public PlatOrderDetailResult getPlatOrder(PlatOrderDetailOrder order) {
        PlatOrderDetailResult result = new PlatOrderDetailResult();
        try {
            order.check();
            OrderInfoDto orderInfoDto = this.getEntityDao().getOrderDetail(order.getPlatOrderNo());

            if (orderInfoDto == null) {
                throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,找不到订单信息", ResultCode.PARAMETER_ERROR.getMessage()));
            }
            BeanCopier.copy(orderInfoDto, result);
            //获取旅行社信息
            if(orderInfoDto.getTravelAgencyId() != null){
                TravelAgency travelAgency = travelAgencyService.get(orderInfoDto.getTravelAgencyId());
                if(travelAgency != null) {
                    result.setTravelAgencyId(travelAgency.getId());
                    result.setAgencyName(travelAgency.getAgencyName());
                }
            }
            //2018-06-01 xh add
            //计算取消订单服务费
            /*Money serviceAmount = Money.cent(0);
            if(orderInfoDto.getOrderStatus() == PlatOrderInfoOrderStatus.pay) {
                serviceAmount = getServiceAmount(orderInfoDto.getOrderAmount(), orderInfoDto.getStartPlayTime());
            } */
            PlatOrderInfo platOrderInfo = this.getEntityDao().get(orderInfoDto.getOrderInfoId());
            Money serviceAmount = orderServiceFeeService.getCancelOrderServiceFee(platOrderInfo);
            log.info("计算取消订单服务费:{}分", serviceAmount.getCent());
            result.setServceAmount(serviceAmount);
            if(Dates.getCountNaturalDay(new Date(), orderInfoDto.getStartPlayTime()) <= 0){
                result.setOrderCloseble(false);
            }else{
                result.setOrderCloseble(true);
            }
            //取消原因描述,如果描述为空，原因枚举不为空，则取枚举值文本
            if(Strings.isBlank(result.getCloseReasonDesc()) && result.getCloseReasonType() != null){
                result.setCloseReasonDesc(result.getCloseReasonType().getMessage());
            }
            PlatOrderEval platOrderEval = platOrderEvalService.getPlatOrderEval(orderInfoDto.getUserId(), orderInfoDto.getPlatOrderNo());
            if (platOrderEval != null) {
                PlatOrderEvalDto platOrderEvalDto = new PlatOrderEvalDto();
                platOrderEvalDto.setContent(platOrderEval.getContent());
                platOrderEvalDto.setScore(platOrderEval.getScore());
                platOrderEvalDto.setCreateTime(platOrderEval.getCreateTime());
                result.setPlatOrderEvalDto(platOrderEvalDto);
            }
        } catch (BusinessException e) {
            log.info("获取订单详情出错！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

    /**
     * 客户提现
     */
    @Override
    public ResultBase withdrawPlat(WithdrawDepositOrder order) {
        ResultBase result = new ResultBase();
        try {
            order.check();
            Customer customer = customerService.getUser(order.getUserId());
            CustomerCard customerCard = customerCardService.getEntityByUserId(customer.getUserId());
            if (customerCard == null) {
                throw new BusinessException("未找到用户绑定的银行卡信息");
            } else if (Strings.isBlank(customerCard.getCardNo())) {
                throw new BusinessException("银行号不能为空");
            }
            //保存提现交易订单(未支付)
            TradeOrderInfo tradeOrderInfo = getTradeOrderInfo(customer, "", Ids.oid(), order.getAmount(), OrderTradeTypeEnum.withdraw);
            //账户明细显示信息: 招商银行(尾号1234)
            String cardNo = customerCard.getCardNo();
            String text = String.format("%s(尾号%s)", customerCard.getCardName(), cardNo.substring(cardNo.length() - 4, cardNo.length()));

            tradeOrderInfo.setTradeText(text);
            tradeOrderInfoService.save(tradeOrderInfo);

            //冻结提现金额
            FreezeInfo freezeInfo = new FreezeInfo();
            freezeInfo.setUserId(order.getUserId());
            freezeInfo.setBizOrderNo(Ids.oid());
            freezeInfo.setBusinessId(tradeOrderInfo.getOrderTradeNo());
            freezeInfo.setFreezeAmount(order.getAmount());
            accountService.freeze(freezeInfo);

            //观世宇提现到卡异步
            WithdrawCardResponse response = withdrawCard(customer, customerCard, tradeOrderInfo.getOrderTradeNo(), tradeOrderInfo.getTradeTime(), order.getAmount(), order.getDeviceType());

            //接观世宇提现到卡(汇付到卡)失败后,解冻提现金额,交易订单标记为失败
            if (response.getStatus() != ResultStatus.processing) {
                //解冻提现金额
                UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
                unFreezeInfo.setBizOrderNo(freezeInfo.getBizOrderNo());
                unFreezeInfo.setBusinessId(freezeInfo.getBusinessId());
                unFreezeInfo.setUserId(freezeInfo.getUserId());
                unFreezeInfo.setFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
                unFreezeInfo.setUnFreezeAmount(order.getAmount());
                accountService.unFreeze(unFreezeInfo);

                //交易订单修改为失败
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.fail);
                tradeOrderInfoService.update(tradeOrderInfo);

                log.info("发起提现失败！{}", response.getResultDetail());
                result.setStatus(ResultStatus.failure);
                result.setCode(ResultStatus.failure.getCode());
                result.setDetail(String.format("发起提现失败！%s", response.getResultDetail()));
            }else if(response.getStatus() == ResultStatus.processing){
                //交易订单修改为支付中
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.processing);
                tradeOrderInfoService.update(tradeOrderInfo);
            }
        } catch (BusinessException e) {
            log.info("发起提现业务异常！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     *提现到卡(汇付到卡)
     */
    private WithdrawCardResponse withdrawCard(Customer customer, CustomerCard customerCard, String orderNo, Date tradeTime, Money amount, DeviceTypeEnum deviceType) {
        WithdrawCardRequest request = new WithdrawCardRequest();
        request.setMerchUserId(zdUserId);
        request.setRealName(customer.getRealName());
        request.setBankCardNo(customerCard.getCardNo());
        request.setBankCode(customerCard.getBankCode());
        request.setMerchOrderNo(orderNo);
        request.setTradeTime(tradeTime);
        request.setAmount(amount);
        request.setDeviceType(deviceType);
        request.setDelay(DelayType.find(delayType));

        return gsyTradePayService.withdrawCard(request);
    }


    /**
     * 绑定银行卡
     */
    @Override
    public ResultBase bindingCard(BindingCardOrder order) {
        ResultBase result = new ResultBase();
        try {
            order.check();
            Customer customer = customerService.getUser(order.getUserId());
            //已绑定银行卡则解绑
            CustomerCard customerCard = customerCardService.getEntityByUserId(order.getUserId());
            if(null != customerCard){
                customerCardService.removeById(customerCard.getId());
            }
            //已绑定银行卡则解绑
//            UnSignCardResponse unSignCardResponse = customerCardService.unSignCardGsy(customer);
//            if (unSignCardResponse.getStatus() == ResultStatus.failure) {
//                result.setStatus(ResultStatus.failure);
//                result.setCode(ResultStatus.failure.getCode());
//                result.setDetail(unSignCardResponse.getResultMessage());
//                return result;
//            }
            //绑定银行卡
            //SignCardResponse signCardResponse = customerCardService.doSignCardGsy(customer, order.getBankCardNo(), order.getRealName(), order.getBankCode(), order.getMobileNo(), order.getIdNumber());

            //验证四要素
            BankCardResult bankCardResult = certificationService.bankCardCertFour(order.getRealName(), order.getBankCardNo(), order.getIdNumber(), order.getMobileNo());
            //验证成功则执行绑卡逻辑
            if (bankCardResult.getStatus() == ResultStatus.success) {
                //保存绑卡信息
                customerCard = new CustomerCard();
                //customerCard.setCardName(bankCardResult.getBankName());
                customerCard.setCardName(BankIdEnum.find(order.getBankCode()).getMessage());
                customerCard.setBankCode(order.getBankCode());
                customerCard.setCardNo(order.getBankCardNo());
                customerCard.setMobileNo(order.getMobileNo());
                customerCard.setUserId(customer.getUserId());
                customerCardService.save(customerCard);
                log.info(String.format("用户[{%s}]实名绑卡[%s]成功", customer.getMobileNo(), order.getBankCardNo()));
                // 更新实名信息
                customer.setRealName(order.getRealName());
                customer.setIdNumber(order.getIdNumber());
                customer.setIsCertification(1);
                customerService.update(customer);
            } else {
                result.setStatus(ResultStatus.failure);
                result.setCode(ResultStatus.failure.getCode());
                result.setDetail(bankCardResult.getDetail());
            }
        } catch (BusinessException e) {
            log.info("绑定银行卡出现错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 获取交易明细列表
     */
    @Override
    public PageResult<TradingRecordDto> getTradeList(TradeListOrder tradeListOrder) {
        PageResult<TradingRecordDto> pageResult = new PageResult<>();
        try {
            tradeListOrder.check();
            pageResult = PageResult.from(tradingRecordService.getPageTradingRecordList(tradeListOrder));
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }
        return pageResult;
    }

    /**
     * 充值
     */
    @Override
    public DeductDepositResult deductDeposit(DeductDepositOrder order) {
        DeductDepositResult result = new DeductDepositResult();

        try {
            order.check();
            Customer customer = customerService.getUser(order.getUserId());
            //创建充值交易订单
            TradeOrderInfo tradeOrderInfo = getTradeOrderInfo(customer, "", Ids.oid(), order.getAmount(), OrderTradeTypeEnum.deposit);
            log.info("创建充值交易订单成功");
            tradeOrderInfoService.save(tradeOrderInfo);
            //接观世宇充值
            ScanCodePay scanCodePay = depositGsy(customer, tradeOrderInfo.getOrderTradeNo(), order.getAmount(), order.getUserIp(), order.getDeviceType(), order.getPayType());
            if (scanCodePay.getStatus() == ResultStatus.success) {
                result.setCodeUrl(scanCodePay.getCodeUrlImg());
                result.setCodeUrlContent(scanCodePay.getCodeUrlContentEx());
            } else {
                //接观世宇充值失败后,订单标记为失败
                tradeOrderInfo.setOrderStatus(OrderStatusEnum.fail);
                tradeOrderInfoService.update(tradeOrderInfo);
                log.info("充值失败！{}", scanCodePay.getMessage());
                result.setStatus(ResultStatus.failure);
                result.setCode(ResultStatus.failure.getCode());
                result.setDetail(scanCodePay.getMessage());
            }
        } catch (BusinessException e) {
            log.info("充值业务错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

    /**
     * 钙片交易
     */
    @Transactional
    @Override
    public ResultBase exchangeCaTrade(ExchangeCaTradeOrder order) {
        ResultBase result = new ResultBase();
        try {
            order.check();
            //金钱 = 钙片数量 / 比率
            Money amount = new Money();
            amount.setCent(order.getCaNumber() * 100 / Long.parseLong(rateMoney2ca));
            log.info(String.format("兑换钙片金额: %s", amount));
            //用户
            Customer customer = customerService.getUser(order.getUserId());
            //创建充值交易订单
            TradeOrderInfo tradeOrderInfo = getTradeOrderInfo(customer, "", Ids.oid(), amount, OrderTradeTypeEnum.catrade);
            tradeOrderInfo.setTradeTime(new Date());
            tradeOrderInfo.setFinishTime(new Date());
            tradeOrderInfoService.save(tradeOrderInfo);
            //由调用观世宇余额支付修改为直接下账 by xiyang 2018/4/15
            saveCaTrade(tradeOrderInfo, amount, order.getCaNumber());
        } catch (BusinessException e) {
            log.info(String.format("钙片兑换业务错误！%s", e.getMessage()));
            throw e;
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * 保存钙片兑换业务数据
     *
     * @param tradeOrderInfo
     * @param amount
     * @param caNumber
     */
    @Transactional
    public void saveCaTrade(TradeOrderInfo tradeOrderInfo, Money amount, Integer caNumber) {

        Account account = accountService.findByUserId(tradeOrderInfo.getUserId());
        WithdrawInfo withdrawInfo = new WithdrawInfo();
        withdrawInfo.setAccountNo(account.getAccountNo());
        withdrawInfo.setBizOrderNo(Ids.oid());
        withdrawInfo.setAmount(amount);
        withdrawInfo.setBusinessId(tradeOrderInfo.getOrderNo());
        //直接下账对应金额 by xiyang 2018/4/15
        accountService.withdraw(withdrawInfo);
        //交易订单表修改为已支付
        tradeOrderInfo.setOrderStatus(OrderStatusEnum.pay);
        tradeOrderInfoService.update(tradeOrderInfo);
        //写交易记录表
        gsyNotifyService.writePositTradingRecord(account, amount, TradingType.catrade, TradeMethod.balance.getMessage(), tradeOrderInfo.getId(), TradeBusiness.tradeOrder);
        //成功用户账户添加钙片
        //pointAccountService.pointProduce(account.getUserId(), caNumber);
        //积分明细
        PointTradeDto pointTradeDto = new PointTradeDto();
        pointTradeDto.setBusiId(account.getUserId());
        pointTradeDto.setBusiType("catrade");
        pointTradeDto.setBusiTypeText("兑换钙片");
        pointTradeService.pointProduce(account.getUserId(), caNumber, pointTradeDto);
    }

    /**
     * 接观世宇创建充值订单
     */
    private ScanCodePay depositGsy(Customer customer, String orderNo, Money amount, String userIp, DeviceTypeEnum deviceType, OrderPayTypeEnum payType) {
        ScanCodePay scanCodePay = new ScanCodePay();
        try {
            //创建交易
            TradeCustomerInfo tradeCustomerInfo = getTradeCustomerInfo(customer);
            //创建Request
            TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
            tradeCreateRequest.setMerchOrderNo(orderNo);
            tradeCreateRequest.setAmount(amount);
            tradeCreateRequest.setTradeName("早导网充值");
            //修改为zaodao商户id作为收款方充值 by xiyang 2018/4/15
            tradeCreateRequest.setSellerUserId(zdUserId);
            tradeCreateRequest.setTradeTime(new Date());
            tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
            tradeCreateRequest.setTradeProfitType(TradeProfitTypeEnum.AUTO);
            //创建(充值)订单
            TradeCreateResponse tradeCreateResponse = gsyTradePayService.tradeCreate(tradeCreateRequest);
            //支付(充值)订单
            if (tradeCreateResponse.getStatus() == ResultStatus.success) {
                scanCodePay = payGsyTrade(orderNo, userIp, deviceType, payType);
            } else {
                String message = tradeCreateResponse.getResultDetail();
                message = Strings.isBlank(message) ? tradeCreateResponse.getResultMessage() : message;
                log.info(String.format("创建充值订单订单%s失败！%s", orderNo, message));
                scanCodePay.setStatus(ResultStatus.failure);
                scanCodePay.setMessage(message);
            }
        } catch (Exception e) {
            scanCodePay.setStatus(ResultStatus.failure);
            scanCodePay.setMessage(String.format("观世宇创建充值订单%s出现错误,%s", orderNo, e.getMessage()));
        }
        return scanCodePay;
    }

    /**
     * 获取交易订单
     */
    @Override
    public TradeOrderInfo getTradeOrderInfo(Customer customer, String orderNo, String orderTradeNo, Money amount, OrderTradeTypeEnum orderTradeType) {
        TradeOrderInfo tradeOrderInfo = new TradeOrderInfo();

        tradeOrderInfo.setAmount(amount.getCent());
        tradeOrderInfo.setOrderNo(orderNo);
        tradeOrderInfo.setOrderStatus(OrderStatusEnum.noPay);
        tradeOrderInfo.setUserId(customer.getUserId());
        tradeOrderInfo.setOrderTradeType(orderTradeType);
        tradeOrderInfo.setTradeName(orderTradeType.getMessage());
        tradeOrderInfo.setTradeTime(new Date());
        tradeOrderInfo.setOrderTradeNo(orderTradeNo);

        return tradeOrderInfo;
    }

    /**
     * 获取交易订单
     */
    @Override
    public TradeOrderInfo getTradeOrderInfo(Customer customer, String orderNo, String orderTradeNo, Money amount, OrderTradeTypeEnum orderTradeType, OrderStatusEnum orderStatus) {
        TradeOrderInfo tradeOrderInfo = new TradeOrderInfo();

        tradeOrderInfo.setAmount(amount.getCent());
        tradeOrderInfo.setOrderNo(orderNo);
        tradeOrderInfo.setOrderStatus(orderStatus);
        tradeOrderInfo.setUserId(customer.getUserId());
        tradeOrderInfo.setOrderTradeType(orderTradeType);
        tradeOrderInfo.setTradeName(orderTradeType.getMessage());
        tradeOrderInfo.setTradeTime(new Date());
        tradeOrderInfo.setOrderTradeNo(orderTradeNo);

        return tradeOrderInfo;
    }



    /**
     * 客户提现
     */
    private WithdrawResponse withdrawGsy(Customer customer, String orderNo, Date datetime, Money money, String captchaCode, DeviceTypeEnum deviceType, String bindId) {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setMerchOrderNo(orderNo);
        withdrawRequest.setBindId(bindId);
        withdrawRequest.setCaptchaCode(captchaCode);
        withdrawRequest.setMerchUserId(customer.getUserId());
        withdrawRequest.setAmount(money);
        withdrawRequest.setTradeTime(datetime);
        withdrawRequest.setDeviceType(deviceType);
        WithdrawResponse withdrawResponse = gsyTradePayService.withdraw(withdrawRequest);

        return withdrawResponse;
    }

    /**
     * 调用观世宇接口支付订单
     */
    private ScanCodePay payGsyTrade(String orderTradeNo, String userIp, DeviceTypeEnum deviceType, OrderPayTypeEnum payType) {
        ScanCodePay scanCodePay = new ScanCodePay();
        if (payType == OrderPayTypeEnum.aliScan) {
            //生成支付宝二维码
            AliScanCodePayRequest aliScanCodePayRequest = new AliScanCodePayRequest();
            aliScanCodePayRequest.setMerchOrderNo(orderTradeNo);
            aliScanCodePayRequest.setDeviceType(deviceType);
            aliScanCodePayRequest.setUserIp(userIp);
            AliScanCodePayResponse aliScanCodePayResponse = gsyTradePayService.aliScanCodePay(aliScanCodePayRequest);

            if (aliScanCodePayResponse.getStatus() != ResultStatus.processing) {
                String message = aliScanCodePayResponse.getResultDetail();
                if(Strings.isBlank(message)){
                    message = aliScanCodePayResponse.getResultMessage();
                }
                log.info("生成支付宝二维码失败：{}", message);
                scanCodePay.setMessage(message);
                scanCodePay.setStatus(ResultStatus.failure);
            } else {
                scanCodePay.setCodeUrlContent(aliScanCodePayResponse.getCodeUrlContent());
                scanCodePay.setCodeUrlImg(aliScanCodePayResponse.getCodeUrlImg());
                scanCodePay.setCodeUrlContentEx(aliScanCodePayResponse.getCodeUrlContentExt());
                scanCodePay.setStatus(ResultStatus.success);
            }
        } else if (payType == OrderPayTypeEnum.weixinScan) {
            //生成微信二维码
            WechatScanCodePayRequest wechatScanCodePayRequest = new WechatScanCodePayRequest();
            //wechatScanCodePayRequest.setMerchOrderNo(orderNo);
            wechatScanCodePayRequest.setMerchOrderNo(orderTradeNo);
            wechatScanCodePayRequest.setUserIp(userIp);
            wechatScanCodePayRequest.setDeviceType(deviceType);
            WechatScanCodePayResponse wechatScanCodePayResponse = gsyTradePayService.wechatScanCodePay(wechatScanCodePayRequest);

            if (wechatScanCodePayResponse.getStatus() != ResultStatus.processing) {
                log.info("生成微信二维码失败：{}", wechatScanCodePayResponse.getResultMessage());
                scanCodePay.setMessage(wechatScanCodePayResponse.getResultMessage());
                scanCodePay.setStatus(ResultStatus.failure);
            } else {
                scanCodePay.setCodeUrlContent(wechatScanCodePayResponse.getCodeUrlContent());
                scanCodePay.setCodeUrlImg(wechatScanCodePayResponse.getCodeUrlImg());
                scanCodePay.setCodeUrlContentEx(wechatScanCodePayResponse.getCodeUrlContentExt());
                scanCodePay.setStatus(ResultStatus.success);
            }
        } else {
            String message = String.format("不存在的支付方式payType=%s", payType);
            log.info("生成微信二维码失败：{}", message);
            scanCodePay.setMessage(message);
            scanCodePay.setStatus(ResultStatus.failure);
        }

        return scanCodePay;
    }

    /**
     * 接观世宇创建余额支付订单
     *
     * @param customer
     * @param orderNo
     * @param userIp
     * @return
     */
    private BalancePayResponse balancePayGsy(Customer customer, Money amount, String orderNo, String userIp) {
        BalancePayResponse response = new BalancePayResponse();
        try {
            //创建交易
            TradeCustomerInfo tradeCustomerInfo = getTradeCustomerInfo(customer);
            //创建Request
            TradeCreateRequest tradeCreateRequest = new TradeCreateRequest();
            tradeCreateRequest.setMerchOrderNo(orderNo);
            tradeCreateRequest.setAmount(amount);
            tradeCreateRequest.setTradeName("余额支付");
            tradeCreateRequest.setSellerUserId("zaodao");
            tradeCreateRequest.setTradeTime(new Date());
            tradeCreateRequest.setTradeCustomerInfo(tradeCustomerInfo);
            tradeCreateRequest.setTradeProfitType(TradeProfitTypeEnum.AUTO);
            //创建余额支付订单
            TradeCreateResponse tradeCreateResponse = gsyTradePayService.tradeCreate(tradeCreateRequest);
            //余额支付订单
            if (tradeCreateResponse.getStatus() == ResultStatus.success) {
                response = balancePayGsyTrade(orderNo, customer.getUserId(), userIp);
            } else {
                response.setStatus(ResultStatus.failure);
                String message = tradeCreateResponse.getResultDetail();
                message = Strings.isBlank(message) ? tradeCreateResponse.getResultMessage() : message;
                response.setResultDetail(message);
                log.info(String.format("创建余额支付订单%s失败！%s", orderNo, message));
            }
        } catch (Exception e) {
            log.info(String.format("创建余额支付订单%s错误！%s", orderNo, e.getMessage()));
            response.setStatus(ResultStatus.failure);
            response.setResultDetail(e.getMessage());
        }
        return response;
    }

    /**
     * 接观世宇余额支付
     *
     * @param orderNo
     * @param userId
     * @param userIp
     * @return
     */
    private BalancePayResponse balancePayGsyTrade(String orderNo, String userId, String userIp) {
        BalancePayRequest balancePayRequest = new BalancePayRequest();
        balancePayRequest.setMerchOrderNo(orderNo);
        balancePayRequest.setPayerMerchUserId(userId);
        balancePayRequest.setUserIp(userIp);

        return gsyTradePayService.balancePay(balancePayRequest);
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<OrderInfoDto> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<OrderInfoDto> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<PlatOrderInfo> getOrderPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<PlatOrderInfo> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    /**
     * 处理执行订单支付超时定时任务
     */
    @Override
    public void executePlatOrderCloseTask() {
        this.getEntityDao().updatePlatOrder(PlatOrderInfoOrderStatus.noPay.getCode(), PlatOrderInfoOrderStatus.close.getCode());
    }

    /**
     * 订单完成客户未确认，超过七天给导游上账
     */
    @Override
    @Transactional
    public void guideAccountUp() {
        //查询已完成游客未确认的订单，且超过七天
        List<PlatOrderInfo> list = this.getEntityDao().getConfirmingOrder();
        list.forEach(p ->{
            String bizOrderNo = Ids.oid();
            Money platFee = getGuideServiceFee(p);
            Long cent = p.getOrderAmount() - platFee.getCent();
            if(cent <= 0){
                //如果订单金额小于等于约导服务费，则不取约导服务费
                cent = p.getOrderAmount();
                log.info("订单金额小于等于约导服务费，则不取约导服务费(不给平台转账)");
            }
            Money money = Money.cent(cent);

            log.info("定时执行：订单[{}]费用={}分,约导服务费={}分,导游收款={}分", p.getOrderNo(), p.getOrderAmount(), platFee.getCent(), money.getCent());
            //1 游客转账money给导游
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setBizOrderNo(bizOrderNo);
            transferInfo.setBusinessId(p.getOrderNo());
            transferInfo.setTransferType(AccountTransferTypeEnum.TRANSFER);
            transferInfo.setPayerUserId(p.getTouristId());
            transferInfo.setPayerFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
            transferInfo.setPayerFreezeAmount(money);
            transferInfo.setPayeeUserId(p.getTourGuideId());
            transferInfo.setAmount(money);
            accountService.transfer(transferInfo);

            Account account = accountService.findByUserId(p.getTourGuideId());
            //导游收费：导游账户余额in订单金额，后面需要写一条平台收费
            TradingRecord tradingRecord = new TradingRecord();
            tradingRecord.setInOutType(InOutType.in);
            tradingRecord.setOrderId(p.getId());
            tradingRecord.setTradingType(TradingType.guide_fee);
            tradingRecord.setUserid(p.getTourGuideId());
            tradingRecord.setTradingAmount(p.getOrderAmount());
            tradingRecord.setBalance(account.getBalance());
            tradingRecord.setTradeMethod("账户余额");
            tradingRecord.setTradeBusiness(TradeBusiness.platOrder);
            tradingRecordService.save(tradingRecord);

            //2 游客转账给平台
            if((p.getOrderAmount() - platFee.getCent()) > 0 && platFee.getCent() > 0){
                TransferInfo transferInfo1 = new TransferInfo();
                transferInfo1.setBizOrderNo(bizOrderNo);
                transferInfo1.setBusinessId(p.getOrderNo());
                transferInfo1.setTransferType(AccountTransferTypeEnum.TRANSFER);
                transferInfo1.setPayerUserId(p.getTouristId());
                transferInfo1.setPayerFreezeType(AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT);
                transferInfo1.setPayerFreezeAmount(platFee);
                transferInfo1.setPayeeUserId(gatewayPartnerId);
                transferInfo1.setAmount(platFee);
                accountService.transfer(transferInfo1);

                //导游服务费：导游账户余额out(平台收费)
                TradingRecord tradingRecord1 = new TradingRecord();
                tradingRecord1.setInOutType(InOutType.out);
                tradingRecord1.setOrderId(p.getId());
                tradingRecord1.setTradingType(TradingType.plat_service);
                tradingRecord1.setUserid(p.getTourGuideId());
                tradingRecord1.setTradingAmount(platFee.getCent());
                tradingRecord1.setBalance(account.getBalance());
                tradingRecord1.setTradeMethod("账户余额");
                tradingRecord1.setTradeBusiness(TradeBusiness.platOrder);
                tradingRecordService.save(tradingRecord1);
            }

            //订单确认为完成
            p.setOrderStatus(PlatOrderInfoOrderStatus.confirm);
            this.getEntityDao().update(p);
        });
    }
}
