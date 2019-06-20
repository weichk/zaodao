/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:36 创建
 *
 */
package com.acooly.zaodao.gateway.base;

import com.acooly.core.utils.enums.Messageable;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ResponseBase {
    /**
     * 响应状态(success:成功，fail:失败，processing:处理中)
     */
    private Messageable status;

    /**
     * 服务响应CODE
     */
    private String resultCode;

    /**
     * 服务响应信息
     */
    private String resultMessage;

    /**
     * 外部订单号
     */
    private String merchOrderNo;

    /**
     * 服务响应详细信息
     */
    private String resultDetail;
}
