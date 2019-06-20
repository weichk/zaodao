package com.acooly.zaodao.platform.service;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.common.enums.SendSmsType;
import com.acooly.zaodao.platform.order.ZdSendSmsOrder;

/**
 * Created by xiyang on 2017/9/18.
 */
public interface ZdSmsService {

    /**
     * 验证短信验证码
     *
     * @param mobileCaptcha
     * @return
     */
    ResultBase checkMobileCaptcha(String mobileNo, String mobileCaptcha);

    /**
     * 发送短信
     *
     * @param mobileNo
     * @param sendSmsType
     * @return
     */
    ResultBase zdSendSms(String mobileNo, SendSmsType sendSmsType);

    /**
     * 发送短信
     * @param zdSendSmsOrder
     * @return
     */
    ResultBase zdSendSms(ZdSendSmsOrder zdSendSmsOrder);
}
