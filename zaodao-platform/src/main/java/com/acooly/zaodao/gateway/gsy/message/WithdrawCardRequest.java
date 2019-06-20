package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.enums.DelayType;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 提现到卡(汇付到卡)
 *
 * @author xiaohong
 * @create 2018-06-05 16:12
 **/
@Getter
@Setter
public class WithdrawCardRequest extends RequestBase implements Serializable {
    /**
     * 商户用户ID
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
     * 银行编码
     */
    @NotBlank
    private String bankCode;

    /**
     * 银行卡号
     */
    @NotBlank
    private String bankCardNo;

    /**
     * 真实姓名
     */
    @NotBlank
    private String realName;

    /**
     * 提现到账方式
     */
    private DelayType delay;
}
