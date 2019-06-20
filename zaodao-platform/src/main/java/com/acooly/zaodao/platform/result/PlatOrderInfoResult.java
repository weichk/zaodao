package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
public class PlatOrderInfoResult extends ResultBase {
    /** 订单号 */
    private String platOrderNo;
}
