/*
 * 修订记录:
 * zhike@yiji.com 2017-03-08 14:10 创建
 *
 */
package com.acooly.zaodao.portal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class MobileCaptchaDto implements Serializable {
    /**
     * 验证码有效时间（秒）
     */
    public static final int VALID_TIME = 60;

    /**
     * 手机验证码发送时间
     */
    private Date sendTime = new Date();

    /**
     * 手机验证码
     */
    private String mobileCaptcha;


    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMobileCaptcha() {
        return mobileCaptcha;
    }

    public void setMobileCaptcha(String mobileCaptcha) {
        this.mobileCaptcha = mobileCaptcha;
    }
}
