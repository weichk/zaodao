/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:40 创建
 *
 */
package com.acooly.zaodao.gateway.base;

import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class NotifyBase {
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
}
