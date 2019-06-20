/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 15:56 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message.enums;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public enum DeviceTypeEnum {
    PC("PC", "PC电脑"),
    IOS("IOS", "IOS设备"),
    ANDROID("ANDROID", "android设备");

    private String code;
    private String message;

    private DeviceTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static DeviceTypeEnum byCode(String code) {
        DeviceTypeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            DeviceTypeEnum enums = var4[var2];
            if (code.equalsIgnoreCase(enums.getCode())) {
                return enums;
            }
        }

        return null;
    }
}