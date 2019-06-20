/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:30 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.enums.CustomerTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class CustomerRegisterRequest extends RequestBase {
    /** 外部会员唯一ID */
    @NotEmpty(message = "外部会员唯一ID不能为空")
    private String outUserId;

    /**
     * 密码
     */
    private String password;

    /** 注册会员类型 */
    @NotNull(message = "注册类型不能为空")
    private CustomerTypeEnum type;

    /** 联系邮箱 */
    private String email;

    /** 手机号 */
    @NotEmpty(message = "短手机号不能为空")
    private String mobileNo;

    /** 备注 */
    private String comments;

}
