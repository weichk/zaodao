/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:58 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiNotify;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "deductDeposit", type = ApiMessageType.Notify)
public class DeductDepositNotify extends ApiNotify{
}
