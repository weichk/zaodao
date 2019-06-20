/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 15:38 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class AliScanCodePayRequest extends RequestBase {
    /**
     * 用户ip
     **/
    private String userIp;

    /**
     * 设备类型
     **/
    private DeviceTypeEnum deviceType = DeviceTypeEnum.PC;
}
