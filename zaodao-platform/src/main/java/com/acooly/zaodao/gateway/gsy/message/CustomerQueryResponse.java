/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 16:46 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.dto.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class CustomerQueryResponse extends ResponseBase {

    /**
     * 会员信息
     */
    @NotNull
    @OpenApiField(desc = "会员信息", constraint = "会员信息")
    private CustomerDto customerDto;

    /**
     * 用户扩展信息(当请求参数isExtendInfo为true时返回值)
     */
    private CustomerExtendDto customerExtendDto;

    /**
     * 用户企业实名信息(当请求参数isRealNameInfo为true,并且用户类型为企业用户时返回值)
     */
    private CustomerEnterpriseDto customerEnterpriseDto;

    /**
     * 用户个人实名信息(当请求参数isRealNameInfo为true,并且用户类型为个人用户时返回值)
     */
    private CustomerIdentityDto customerIdentityDto;

    /**
     * 用户绑卡信息(当请求参数isBankcardInfo为true时返回值)
     */
    private List<CustomerBankcardDto> bankcards;

    /**
     * 用户账户信息(当请求参数isAccountInfo为true时返回)
     */
    private AccountInfo accountInfo;
}
