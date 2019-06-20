/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 16:46 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class CustomerQueryRequest extends RequestBase {
    /**
     * 会员userId
     */
    @NotEmpty
    private String userId;

    /**
     * 是否同步查询用户实名信息
     */
    private Boolean isRealNameInfo = false;

    /**
     * 是否同步查询用户扩展信息
     */
    private Boolean isExtendInfo = false;

    /**
     * 是否同步查询用户绑卡信息
     */
    private Boolean isBankcardInfo = false;

    /**
     * 是否同步查询用户通用账户信息
     */
    private Boolean isAccountInfo = false;
}
