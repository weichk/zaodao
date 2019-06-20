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
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.common.enums.BankIdEnum;
import com.acooly.zaodao.gateway.gsy.message.SignCardRequest;
import com.acooly.zaodao.gateway.gsy.message.SignCardResponse;
import com.acooly.zaodao.gateway.gsy.message.UnSignCardRequest;
import com.acooly.zaodao.gateway.gsy.message.UnSignCardResponse;
import com.acooly.zaodao.gateway.gsy.message.enums.PublicTagEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.Purpose;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.platform.dao.manage.CustomerCardDao;
import com.acooly.zaodao.platform.dto.CustomerCardDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.order.BankCardListOrder;
import com.acooly.zaodao.platform.order.UnbindingCardOrder;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户绑卡信息表 Service实现
 *
 * Date: 2017-05-24 22:44:01
 *
 * @author zhike
 *
 */
@Slf4j
@Service("customerCardService")
public class CustomerCardServiceImpl extends EntityServiceImpl<CustomerCard, CustomerCardDao> implements CustomerCardService {
    @Value("${site.gateway.url}")
    private String siteGatewayUrl;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    @Override
    public CustomerCard getEntityByUserId(String userId) {
        return getEntityDao().getEntityByUserId(userId);
    }

    @Override
    public void deleteEntityByUserId(String userId) {
        getEntityDao().deleteEntityByUserId(userId);
    }

    /**
     * 获取用户银行卡列表
     * @param bankCardListOrder
     * @return
     */
    @Override
    public PageResult<CustomerCardDto> getCustomerCardList(BankCardListOrder bankCardListOrder) {
        PageResult<CustomerCardDto> pageResult = new PageResult<>();

        try{
            bankCardListOrder.check();
            List<CustomerCard> list = this.getEntityDao().getByUserId(bankCardListOrder.getUserId());
            List<CustomerCardDto> customerCardDtoList = Lists.newArrayList();

            for (CustomerCard customerCard : list){
                CustomerCardDto customerCardDto = new CustomerCardDto();
                BeanCopier.copy(customerCard, customerCardDto);
                customerCardDto.setCustomerCardId(customerCard.getId());
                //BankIdEnum bankId = BankIdEnum.findByName(customerCardDto.getBankCode());
                customerCardDto.setBankCode(customerCardDto.getBankCode());
                customerCardDto.setBankImageUrl(String.format("%s/bankImg_80/%s_80.png", siteGatewayUrl, customerCardDto.getBankCode()));
                customerCardDtoList.add(customerCardDto);
            }
            PageInfo<CustomerCardDto> pageInfo = new PageInfo<>();
            pageInfo.setPageResults(customerCardDtoList);
            pageResult.setDto(pageInfo);
        }
        catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }

    @Autowired
    private CustomerCardDao customerCardDao;
    /**
     * 解绑银行卡
     */
    @Override
    public ResultBase unbindingCard(UnbindingCardOrder order) {
        ResultBase result = new ResultBase();
        try {
            order.check();
            Customer customer = customerService.getUser(order.getUserId());
            //移除观世宇解绑
            /*UnSignCardResponse unSignCardResponse = unSignCardGsy(customer);
            if(unSignCardResponse.getStatus() == ResultStatus.failure){
                result.setStatus(ResultStatus.failure);
                result.setCode(ResultStatus.failure.getCode());
                result.setDetail(unSignCardResponse.getResultMessage());
            }*/
            CustomerCard customerCard = customerCardDao.getEntityByUserId(order.getUserId());
            if(null != customerCard) {
                customerCardDao.removeById(customerCard.getId());
            }
        }catch (BusinessException e){
            log.info("解绑银行卡失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        }catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

    /**
     * 接观世宇解绑银行卡
     * @param customer
     */
    @Override
    public UnSignCardResponse unSignCardGsy(Customer customer) {
        //如果已绑定则解绑原来的银行卡，目前支持解绑一张银行卡
        UnSignCardResponse unSignCardResponse = new UnSignCardResponse();
        CustomerCard customerCard = this.getEntityDao().getEntityByUserId(customer.getUserId());
        if (customerCard != null) {
            UnSignCardRequest unSignCardRequest = new UnSignCardRequest();
            unSignCardRequest.setBindId(customerCard.getBindId());
            unSignCardRequest.setUserId(customer.getUserId());
            unSignCardResponse = gsyBusinessService.unSignCard(unSignCardRequest);

            if (unSignCardResponse.getStatus() == ResultStatus.failure) {
                log.info(String.format("用户[%s]解绑失败,观世宇返回消息: %s", customer.getMobileNo(), unSignCardResponse.getResultMessage()));
                //throw new BusinessException(unSignCardResponse.getResultMessage());
            } else {
                log.info(String.format("移除用户[%s]已绑定的银行卡[%s]", customer.getMobileNo(), customerCard.getCardNo()));
                this.getEntityDao().deleteEntityByUserId(customer.getUserId());
            }
        }
        return unSignCardResponse;
    }
    /**
     * 接观世宇绑定银行卡
     */
    @Override
    public SignCardResponse doSignCardGsy(Customer customer, String bankCardNo, String realName, String bankCode, String mobileNo, String idNumber){
        // 银行卡实名签约验卡
        SignCardRequest signCardRequest = new SignCardRequest();
        signCardRequest.setUserId(customer.getUserId());
        signCardRequest.setMobile(mobileNo);
        signCardRequest.setBankCardNo(bankCardNo);
        signCardRequest.setRealName(realName);
        signCardRequest.setCertNo(idNumber);
        signCardRequest.setPurpose(Purpose.WITHDRAW);
        signCardRequest.setPublicTag(PublicTagEnum.N);
        signCardRequest.setBankCode(bankCode);
        SignCardResponse signCardResponse = gsyBusinessService.signCard(signCardRequest);

        if (signCardResponse.getStatus() == ResultStatus.failure) {
            String message = String.format("用户[%s]绑定新银行卡[%s]失败,观世宇平台返回消息: %s",customer.getMobileNo(), bankCardNo, signCardResponse.getResultMessage());
            log.info(message);
            //throw new BusinessException(signCardResponse.getResultMessage());
        }else{
            // 保存银行卡信息
            CustomerCard customerCard = new CustomerCard();
            customerCard.setCardName(signCardResponse.getBankName());
            customerCard.setCardNo(bankCardNo);
            customerCard.setMobileNo(mobileNo);
            customerCard.setUserId(customer.getUserId());
            customerCard.setBindId(signCardResponse.getBindId());
            this.getEntityDao().save(customerCard);
            log.info(String.format("用户[{%s}]实名绑卡[%s]成功", customer.getMobileNo(), bankCardNo));
        }
        return signCardResponse;
    }

    /**
     * 获取绑定
     * @param userId
     * @return
     */
    @Override
    public List<CustomerCard> getBindCardListByUserId(String userId) {
        return this.getEntityDao().getByUserId(userId);
    }

    /**
     * 获取绑卡数量
     * @param userId
     * @return
     */
    @Override
    public int getBindCardCountByUserId(String userId) {
        List<CustomerCard> list = getBindCardListByUserId(userId);
        return list == null ? 0 : list.size();
    }
}
