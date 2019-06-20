package com.acooly.zaodao.platform.service;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.sms.SmsService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.CommonUtils;
import com.acooly.zaodao.common.enums.SendSmsType;
import com.acooly.zaodao.gateway.gsy.message.SendCaptchaSmsRequest;
import com.acooly.zaodao.gateway.gsy.message.SendCaptchaSmsResponse;
import com.acooly.zaodao.gateway.gsy.message.enums.SmsTemplateEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.order.ZdSendSmsOrder;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.portal.dto.MobileCaptchaDto;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by xiyang on 2017/9/18.
 */
@Slf4j
@Service
public class ZdSmsServiceImpl implements ZdSmsService {

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Value("${register.template.code}")
    private String registerTemplateCode;

    @Value("${find.password.template.code}")
    private String findPasswordTemplateCode;

    @Value("${modify.modify.template.code}")
    private String modifyMobileNoTemplateCode;

    @Value("${binding.card.template.code}")
    private String bindingCardTemplateCode;

    @Value("${withdraw.card.template.code}")
    private String withdrawCardTemplateCode;

    @Value("${check.mobile.template.code}")
    private String checkMobileTemplateCode;

    @Override
    public ResultBase checkMobileCaptcha(String mobileNo, String mobileCaptcha) {
        ResultBase resultBase = new ResultBase();
        try {
            MobileCaptchaDto mobileCaptchaDto = (MobileCaptchaDto) redisClientService.getRedis(SysConstant
                    .MOBILE_CAPTCHA_SESSION + mobileNo);
            if (mobileCaptchaDto == null) {
                throw new BusinessException("手机号码有误");
            }
            Date putDate = mobileCaptchaDto.getSendTime();
            Date nowDate = new Date();
            long interval = (nowDate.getTime() - putDate.getTime()) / 1000;
            if (interval > 60) {
                log.info("手机验证码已经失效");
                resultBase.setCode(ResultCode.FAILURE.getCode());
                resultBase.setStatus(ResultStatus.failure);
                resultBase.setDetail("手机验证码已经失效");
            } else if (!Strings.equals(mobileCaptcha, mobileCaptchaDto.getMobileCaptcha())) {
                log.info("手机验证码错误");
                resultBase.setCode(ResultCode.FAILURE.getCode());
                resultBase.setStatus(ResultStatus.failure);
                resultBase.setDetail("手机验证码已经失效");
            }
        } catch (Exception e) {
            log.info("校验验证码失败{}", e.getMessage());
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setDetail(e.getMessage());
        }
        return resultBase;
    }

    /**
     * 发送短信
     * @param zdSendSmsOrder
     * @return
     */
    @Override
    public ResultBase zdSendSms(ZdSendSmsOrder zdSendSmsOrder){
        ResultBase resultBase = new ResultBase();
        try{
            zdSendSmsOrder.check();
            SendSmsType sendSmsType = zdSendSmsOrder.getSendSmsType();
            //提现短信发送至观世宇平台，其它发送到短信平台
            if(sendSmsType == SendSmsType.withdraw){
                if(zdSendSmsOrder.getAmount() != null && zdSendSmsOrder.getAmount().getCent() > 0) {
                    sendGsysms(zdSendSmsOrder.getMobileNo(), zdSendSmsOrder.getAmount());
                }else{
                    throw new BusinessException("提现金额应该大于0");
                }
            } else{
                resultBase = zdSendSms(zdSendSmsOrder.getMobileNo(), zdSendSmsOrder.getSendSmsType());
            }
        } catch(BusinessException bx){
            log.info("发送短信失败,{}", bx.getMessage());
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setDetail(bx.getMessage());
        }catch (Exception e) {
            log.info("发送短信失败,{}", e.getMessage());
            resultBase.setCode(ResultCode.FAILURE.getCode());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setDetail(e.getMessage());
        }
        return resultBase;
    }

    /**
     * 发送短信
     * @param mobileNo
     * @param sendSmsType
     * @return
     */
    @Override
    public ResultBase zdSendSms(String mobileNo, SendSmsType sendSmsType) {
        ResultBase result = new ResultBase();
        try {
            String randomNum = CommonUtils.randomNum();
            String content = "";

            if (SendSmsType.register == sendSmsType) {
                Customer customer = customerService.findEntityByMobileNo(mobileNo);
                if (customer != null) {
                    throw new BusinessException("手机号码已存在");
                }
                content = Strings.replace(registerTemplateCode, "code", randomNum);
            } else if (SendSmsType.findPassword == sendSmsType) {
                content = Strings.replace(findPasswordTemplateCode, "code", randomNum);
            } else if (SendSmsType.modifyMobileNo == sendSmsType) {
                content = Strings.replace(modifyMobileNoTemplateCode, "code", randomNum);
            } else if(SendSmsType.checkMobileBind == sendSmsType){
                content = Strings.replace(checkMobileTemplateCode, "code", randomNum);
            }else {
                log.info("不支持sendSmsType={}发送短息", sendSmsType);
                throw new BusinessException("不支持此类型发送短息");
            }
            smsService.send(mobileNo, content);
            MobileCaptchaDto mobileCaptchaDto = new MobileCaptchaDto();
            mobileCaptchaDto.setMobileCaptcha(randomNum);
            log.info("用户{}发送短息成功", mobileNo);
            result.setDetail("短息发送成功！");
            // 将手机验证码存入redis
            redisClientService.setRedis(SysConstant.MOBILE_CAPTCHA_SESSION + mobileNo, mobileCaptchaDto);
        } catch (Exception e) {
            log.info("发送短信失败{}", e.getMessage());
            result.setDetail(e.getMessage());
            result.setCode(ResultCode.FAILURE.getCode());
            result.setStatus(ResultStatus.failure);
        }
        return result;
    }
    /**
     * 向观世宇平台发送提现短信验证码
     * @param mobileNo
     * @param amount
     * @return
     */
    private SendCaptchaSmsResponse sendGsysms(String mobileNo, Money amount) {
        Map<String, Object> map = Maps.newHashMap();
        SendCaptchaSmsRequest sendCaptchaSmsRequest = new SendCaptchaSmsRequest();

        sendCaptchaSmsRequest.setMerchOrderNo(Ids.oid());
        sendCaptchaSmsRequest.setMobileNo(mobileNo);
        sendCaptchaSmsRequest.setSmsTemplateEnum(SmsTemplateEnum.WITHDRAW);
        map.put("amount", amount.toStandardString());
        sendCaptchaSmsRequest.setMap(map);
        SendCaptchaSmsResponse sendCaptchaSmsResponse = gsyTradePayService.sendCaptchaSms(sendCaptchaSmsRequest);

        if (sendCaptchaSmsResponse.getStatus() != ResultStatus.success) {
            String message = String.format("调用观世宇平台向用户[%s]发送提现短信失败：%s", mobileNo, sendCaptchaSmsResponse.getResultMessage());
            log.error(message);
            throw new BusinessException(message);
        }

        return sendCaptchaSmsResponse;
    }
}
