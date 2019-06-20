package com.acooly.zaodao.platform.order;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Strings;
import com.acooly.module.app.enums.DeviceType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 用户注册
 *
 * @author xiaohong
 * @create 2018-01-05 16:44
 **/
@Data
public class ZdRegisterOrder extends OrderBase {
    @OpenApiField(desc = "手机号码", constraint = "手机号码", demo = "18697652663")
    private String mobileNo;

    @OpenApiField(desc = "密码", constraint = "密码", demo = "12345678", security = true)
    private String password;

    @OpenApiField(desc = "短信校验码", constraint = "发送短信的时候获取", demo = "O00117052610240611600000")
    private String verifyCode;

    @OpenApiField(desc = "昵称", constraint = "昵称", demo = "12345678")
    private String userName;

    @OpenApiField(desc = "设备类型")
    private DeviceType deviceType;

    @Size(max = 64)
    @OpenApiField(desc = "设备型号")
    private String deviceModel;

    @Size(max = 64)
    @OpenApiField(desc = "设备标识")
    private String deviceId;

    @OpenApiField(desc = "开发平台", demo = "weixin,qq,weibo")
    private OpenPlatformType openPlatformType;

    @OpenApiField(desc = "开放平台用户ID", constraint = "开放平台用户ID")
    private String openUserId;

    /**
     * 正常注册验证
     */
    @Override
    public void check() {
        if (Strings.isBlank(mobileNo)) {
            throw new IllegalArgumentException("手机号码不能为空");
        } else if (Strings.isBlank(verifyCode)) {
            throw new IllegalArgumentException("短信校验码");
        } else if (Strings.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空");
        } else if (password.length() > 17 || password.length() < 6) {
            throw new IllegalArgumentException("密码必须为6-16位");
        } else if (Strings.isBlank(userName)) {
            throw new IllegalArgumentException("昵称不能为空");
        } else if (userName.length() > 7 || userName.length() < 1) {
            throw new IllegalArgumentException("昵称必须为1-6位");
        }
    }

    /**
     * 手机号存在，验证开放平台绑定参数
     */
    public void checkExistOpenPlatform() {
        if (Strings.isBlank(mobileNo)) {
            throw new BusinessException("手机号码不能为空");
        } else if (Strings.isBlank(verifyCode)) {
            throw new BusinessException("短信校验码");
        } else if(Strings.isBlank(openUserId)){
            throw new BusinessException("开放平台用户ID不能为空");
        }
    }

    /**
     * 手机号不存在存在，验证开放平台注册绑定参数
     */
    public void checkNotExistOpenPlatform(){
        if (Strings.isBlank(mobileNo)) {
            throw new BusinessException("手机号码不能为空");
        } else if (Strings.isBlank(verifyCode)) {
            throw new BusinessException("短信校验码");
        } else if (Strings.isBlank(password)) {
            throw new BusinessException("密码不能为空");
        } else if (password.length() > 17 || password.length() < 6) {
            throw new BusinessException("密码必须为6-16位");
        } else if (Strings.isBlank(userName)) {
            throw new BusinessException("昵称不能为空");
        } else if (userName.length() > 32 || userName.length() < 1) {
            //2018-10-12 xh modify，微信昵称不限制长度
            throw new BusinessException("昵称必须为1-32位");
        }else if(Strings.isBlank(openUserId)){
            throw new BusinessException("开放平台用户ID不能为空");
        }
    }
}
