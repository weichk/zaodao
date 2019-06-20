/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 16:27 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.enums.SmsTemplateEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class SendCaptchaSmsRequest extends RequestBase {

    /**
     * 手机号
     */
    @NotBlank
    private String mobileNo;

    /**
     * 模板名称
     */
    @NotNull
    private SmsTemplateEnum smsTemplateEnum;

    /**
     * 短息参数
     */
    @NotNull
    private Map<String, Object> map;
}
