package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;

/**
 * 开放平台手机号验证
 *
 * @author xiaohong
 * @create 2018-01-05 15:54
 **/
@Data
public class CustomerMobileCheckResult extends ResultBase{
    /**
     * 手机号是否存在
     */
    private boolean isExist;

    /**
     * 手机号是否绑定开放平台账号
     */
    private boolean isBinding;
}
