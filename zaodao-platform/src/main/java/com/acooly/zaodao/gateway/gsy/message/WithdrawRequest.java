/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 15:42 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class WithdrawRequest extends RequestBase {

    /**
     * 代扣绑卡ID
     */
    @NotBlank
    private String bindId;

    /**
     * 短信验证码
     */
    @NotBlank
    private String captchaCode;

    /**
     * 用户UserId
     */
    @NotBlank
    private String merchUserId;

    /**
     * 交易金额
     */
    @NotNull
    private Money amount;

    /**
     * 交易时间
     */
    @NotNull
    private Date tradeTime;

    /**
     * 设备类型
     **/
    @NotNull
    private DeviceTypeEnum deviceType = DeviceTypeEnum.ANDROID;

    /**
     * 用户ip
     **/
    private String userIp;
}
