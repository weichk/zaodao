package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.order.CustomerSigninAddOrder;
import com.acooly.zaodao.platform.order.ForgetPasswordOrder;
import com.acooly.zaodao.platform.order.ModifyPasswordOrder;
import com.acooly.zaodao.platform.service.ZdSmsService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户密码
 * Created by xiaohong on 2017/9/29.
 */
@Slf4j
@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerService customerServiced;

    @Autowired
    private ZdSmsService zdSmsService;

    /**
     * 修改密码
     */
    @Override
    @Transactional
    public ResultBase ModifyPassword(ModifyPasswordOrder order) {
        ResultBase resultBase = new ResultBase();

        try {
            order.check();
            //验证用户
            Customer customer = customerDao.findEntityByUserId(order.getUserId());
            if (null == customer) {
                throw new BusinessException(ResultCode.FAILURE, String.format("%s,未找到用户", ResultCode.FAILURE.getMessage()));
            }

            boolean checker = customerServiced.checkPassword(customer, order.getOldPassword());
            if(checker) {
                customerServiced.findPassword(customer, order.getNewPassword());
            }else{
                throw new BusinessException(ResultCode.FAILURE, String.format("%s,旧密码错误", ResultCode.FAILURE.getMessage()));
            }
//            //验证旧密码
//            if (!customer.getLoginPassword().equals(order.getOldPassword())) {
//                throw new BusinessException(ResultCode.FAILURE, String.format("%s,旧密码错误", ResultCode.FAILURE.getMessage()));
//            }
//            //更新密码
//            customer.setLoginPassword(order.getNewPassword());
//            customerDao.update(customer);
        }catch (OrderCheckException e){
            log.info("参数错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultCode.PARAMETER_ERROR.getCode());
            resultBase.setDetail(e.getMessage());
        }catch (BusinessException e){
            log.info("执行失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setDetail(ResultCode.FAILURE.getMessage());
        }
        return resultBase;
    }

    /**
     * 忘记密码
     * @param forgetPasswordOrder
     * @return
     */
    @Override
    @Transactional
    public ResultBase ForgetPassword(ForgetPasswordOrder forgetPasswordOrder) {
        ResultBase resultBase = new ResultBase();

        try{
            forgetPasswordOrder.check();
            Customer customer = customerDao.findEntityByMobileNo(forgetPasswordOrder.getMobileNo());
            if(customer == null){
                throw new BusinessException("手机号码不存在");
            }
            //获取验证码校验结果
            resultBase = zdSmsService.checkMobileCaptcha(forgetPasswordOrder.getMobileNo(), forgetPasswordOrder.getVerifyCode());
            //校验成功则保存新密码
            if(resultBase.getCode().equals(ResultCode.SUCCESS.getCode())){
                customer.setLoginPassword(forgetPasswordOrder.getNewPassword());
                customerServiced.entryptPassword(customer);
                customerDao.update(customer);
            }
        }catch (OrderCheckException ex){
            log.info("参数错误！{}", ex.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultCode.PARAMETER_ERROR.getCode());
            resultBase.setDetail(ex.getMessage());
        }catch (Exception e){
            log.info("执行失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setDetail(ResultCode.FAILURE.getMessage());
        }

        return resultBase;
    }
}
