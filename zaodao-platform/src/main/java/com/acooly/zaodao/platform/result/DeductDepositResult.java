package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;

/**
 * 充值
 * @author xiaohong
 * @create 2017-11-27 15:57
 **/
@Data
public class DeductDepositResult extends ResultBase {
    /**
     * 二维码http路径
     */
    private String codeUrl;

    /**
     * 二维码链接内容
     */
    private String codeUrlContent;
}
