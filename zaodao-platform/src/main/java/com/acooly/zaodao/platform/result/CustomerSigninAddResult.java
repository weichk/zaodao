package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;

/**
 * 用户签到
 *
 * @author xiaohong
 * @create 2017-11-03 12:10
 **/
@Data
public class CustomerSigninAddResult extends ResultBase {
    /**
     * 签到次数
     */
    private Integer times;
}
