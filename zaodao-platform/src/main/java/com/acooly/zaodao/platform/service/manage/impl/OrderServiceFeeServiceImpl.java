package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.common.utils.Dates;
import com.acooly.zaodao.platform.dao.manage.OrderServiceFeeDao;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDtoSort;
import com.acooly.zaodao.platform.dto.OrderServiceFeeDto;
import com.acooly.zaodao.platform.dto.QueryServiceFeeDto;
import com.acooly.zaodao.platform.entity.OrderServiceCondition;
import com.acooly.zaodao.platform.entity.OrderServiceFee;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.result.ServiceFeeListResult;
import com.acooly.zaodao.platform.service.manage.OrderServiceConditionService;
import com.acooly.zaodao.platform.service.manage.OrderServiceFeeService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 9:53
 **/
@Slf4j
@Service("orderServiceFeeService")
public class OrderServiceFeeServiceImpl extends EntityServiceImpl<OrderServiceFee,OrderServiceFeeDao> implements OrderServiceFeeService {

    @Autowired
    private OrderServiceConditionService orderServiceConditionService;

    /**
     * 获取服务费分页数据
     */
    @Override
    public PageInfo<OrderServiceFeeDto> getServiceFeeList(QueryServiceFeeDto queryServiceFeeDto) {
        PageInfo<OrderServiceFeeDto> pageInfo = this.getEntityDao().getPageOrderServiceFee(queryServiceFeeDto);
        //组合收费条件
        for(OrderServiceFeeDto feeDto : pageInfo.getPageResults()) {
            List<OrderServiceConditionDto> conditionDtos = feeDto.getOrderServiceConditionDtoList();
            feeDto.setConditionTxt(getConditionTxt(conditionDtos));
        }
        return pageInfo;
    }

    /**
     * 获取条件组合文本
     */
    private String getConditionTxt(List<OrderServiceConditionDto> conditionDtos){
        StringBuilder sbTxt = new StringBuilder();
        if(conditionDtos != null && conditionDtos.size() > 0) {
            conditionDtos.sort(new OrderServiceConditionDtoSort());
            for(int i = 0; i < conditionDtos.size(); i++){
                OrderServiceConditionDto dto = conditionDtos.get(i);

                if(dto.getConditionName() == ServiceConditionName.cancel_days){
                    if(sbTxt.toString().indexOf(dto.getConditionName().getMessage()) > -1){
                        sbTxt.append(String.format("%s%s", dto.getConditionSymbol().getMessage(), dto.getConditionValue()));
                    }else {
                        sbTxt.append(String.format("%s%s%s", dto.getConditionName().getMessage(), dto.getConditionSymbol().getMessage(), dto.getConditionValue()));
                    }
                }else if(dto.getConditionName() == ServiceConditionName.order_status){
                    if(null != PlatOrderInfoOrderStatus.find(dto.getConditionValue())) {
                        if(sbTxt.toString().indexOf(dto.getConditionName().getMessage()) > -1){
                            sbTxt.append(String.format("%s%s", dto.getConditionSymbol().getMessage(), PlatOrderInfoOrderStatus.find(dto.getConditionValue()).getMessage()));
                        }else {
                            sbTxt.append(String.format("%s%s%s", dto.getConditionName().getMessage(), dto.getConditionSymbol().getMessage(), PlatOrderInfoOrderStatus.find(dto.getConditionValue()).getMessage()));
                        }
                    }
                }
                if(i < conditionDtos.size() - 1){
                    sbTxt.append(",");
                }
            }
        }
        return sbTxt.toString();
    }

    /**
     * 添加/修改服务费实体
     */
    @Override
    @Transactional
    public OrderServiceFee saveOrderServiceFee(OrderServiceFeeDto orderServiceFeeDto, boolean isModify) {
        OrderServiceFee orderServiceFee = new OrderServiceFee();
        if(isModify) {
            orderServiceFee = this.getEntityDao().get(orderServiceFeeDto.getId());
        }
        orderServiceFee.setFeeName(orderServiceFeeDto.getFeeName());
        orderServiceFee.setFeeScale(orderServiceFeeDto.getFeeScale());
        orderServiceFee.setFeeStatus(orderServiceFeeDto.getFeeStatus());
        //固定费用(分) * 100
        orderServiceFee.setFeeAmount(Math.round(orderServiceFeeDto.getFeeAmount() * 100));
        orderServiceFee.setFeeSort(orderServiceFeeDto.getFeeSort());

        if(isModify) {
            this.getEntityDao().update(orderServiceFee);
            orderServiceConditionService.removeByFeeId(orderServiceFeeDto.getId());
        }else{
            this.getEntityDao().save(orderServiceFee);
        }

        List<OrderServiceCondition> conditions = Lists.newArrayList();
        OrderServiceFee finalOrderServiceFee = orderServiceFee;
        orderServiceFeeDto.getOrderServiceConditionDtoList().forEach(p -> {
            OrderServiceCondition condition = new OrderServiceCondition();
            BeanCopier.copy(p, condition);
            condition.setFeeId(finalOrderServiceFee.getId());
            conditions.add(condition);
        });
        orderServiceConditionService.saves(conditions);
        return orderServiceFee;
    }

    /**
     * 获取取消订单服务费
     */
    @Override
    public Money getCancelOrderServiceFee(PlatOrderInfo platOrderInfo) {
        List<OrderServiceFeeDto> serviceFees = this.getEntityDao().getOrderServiceFeeListByFeeName(ServiceFeeNameType.user_cancel);
        PlatOrderInfoOrderStatus status = platOrderInfo.getOrderStatus();
        Date start = platOrderInfo.getStartPlayTime();
        int days = Dates.getCountNaturalDay(new Date(), start);
        OrderServiceFeeDto serviceFee = getCompareOrderServiceFee(serviceFees, status, days);
        return getServiceAmount(serviceFee, platOrderInfo.getOrderAmount());
    }

    /**
     * 获取服务费规则列表
     */
    @Override
    public ServiceFeeListResult getOrderServiceFeeList() {
        ServiceFeeListResult result = new ServiceFeeListResult();
        try {
            List<OrderServiceFeeDto> list = this.getEntityDao().getOrderServiceFeeListByFeeName(null);
            result.setDto(list);
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据feeNameType获取约导服务费
     */
    @Override
    public List<OrderServiceFee> getByFeeName(ServiceFeeNameType feeNameType) {
        return this.getEntityDao().getByFeeName(feeNameType);
    }

    /**
     * 计算服务费
     */
    private Money getServiceAmount(OrderServiceFeeDto serviceFee, Long amount){
        if(null != serviceFee) {
            Long feeAmount = serviceFee.getFeeAmountLong();
            if (serviceFee.getFeeScale() == ServiceFeeScaleType.fixed_fee) {
                log.info("固定收费:{}", feeAmount);
                return Money.cent(feeAmount);
            } else if (serviceFee.getFeeScale() == ServiceFeeScaleType.percent_fee) {
                Long money = amount * feeAmount / 10000;
                log.info("百分比收费:{} * {} / 10000 = {}", amount, feeAmount, money);
                return Money.cent(money);
            }
        }
        return Money.cent(0);
    }

    /**
     * 获取比较出来的服务费收费实体
     */
    private OrderServiceFeeDto getCompareOrderServiceFee(List<OrderServiceFeeDto> serviceFees, PlatOrderInfoOrderStatus status, int days){
        for(OrderServiceFeeDto feeDto : serviceFees){
            //订单状态条件
            List<OrderServiceConditionDto> listStatus = feeDto.getOrderServiceConditionDtoList().stream().filter(p -> p.getConditionName().equals(ServiceConditionName.order_status)).collect(Collectors.toList());
            //取消订单提前天数条件
            List<OrderServiceConditionDto> listDays = feeDto.getOrderServiceConditionDtoList().stream().filter(p -> p.getConditionName().equals(ServiceConditionName.cancel_days)).collect(Collectors.toList());

            boolean resultStatus = getConditionStatusResult(listStatus, status);
            boolean resultDays = getConditionDayResult(listDays, days);

            if(resultStatus && resultDays) {
                log.info("收费规则 [{}]{} 满足条件", feeDto.getId(), feeDto.getFeeName());
                return feeDto;
            }
        }
        return null;
    }

    /**
     * 状态条件判断
     */
    private boolean getConditionStatusResult(List<OrderServiceConditionDto> list, PlatOrderInfoOrderStatus status){
        for(OrderServiceConditionDto dto: list){
            PlatOrderInfoOrderStatus conStatus = PlatOrderInfoOrderStatus.find(dto.getConditionValue());
            if(conStatus != null && conStatus.equals(status)){
                return true;
            }
        }
        return false;
    }

    /**
     * 天数条件判断
     */
    private boolean getConditionDayResult(List<OrderServiceConditionDto> list, int days) {
        for (OrderServiceConditionDto dto : list) {
            int conDays;
            try {
                conDays = Integer.parseInt(dto.getConditionValue());
            } catch (NumberFormatException e) {
                return false;
            }
            if (!compareBySymbol(conDays, days, dto.getConditionSymbol())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较天数
     */
    private boolean compareBySymbol(int conDays, int days, ServiceConditionSymbol symbol){
        if(symbol == ServiceConditionSymbol.equal_to){
            return days == conDays;
        }else if(symbol == ServiceConditionSymbol.less_equal_than){
            return days <= conDays;
        }else if(symbol == ServiceConditionSymbol.less_than){
            return days < conDays;
        }else if(symbol == ServiceConditionSymbol.more_equal_than){
            return days >= conDays;
        }else if(symbol == ServiceConditionSymbol.more_than){
            return days > conDays;
        }else return false;
    }
}
