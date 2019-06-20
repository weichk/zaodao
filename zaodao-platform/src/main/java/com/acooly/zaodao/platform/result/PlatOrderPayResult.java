package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

/**
 * 订单支付
 *
 * @author xiaohong
 * @create 2017-11-22 15:49
 **/
@Data
public class PlatOrderPayResult extends ResultBase {
    /**
     * 二维码http路径
     */
    private String codeUrl;

    /**
     * 二维码链接内容
     */
    private String codeUrlContent;
}
