package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by okobboko on 2017/9/29.
 */
@Data
public class ForgetPasswordOrder extends OrderBase {
    /**
     * 短信校验码
     */
    @NotBlank
    private String verifyCode;
    /**
     * 新密码
     */
    @NotBlank
    private String newPassword;

    /**
     * 手机号
     */
    @NotBlank
    private String mobileNo;
}
