/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:07 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.core.utils.Strings;
import com.acooly.module.app.enums.DeviceType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "zdRegister", type = ApiMessageType.Request)
@Data
public class ZdRegisterRequest extends ApiRequest {

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

  @Override
  public void check() throws RuntimeException {
//    //开放平台类型为空，执行原来的验证过程
//    if(openPlatformType == null){
//      if (!Strings.isNotBlank(mobileNo)) {
//        throw new IllegalArgumentException("手机号码不能为空");
//      }
//      if (!Strings.isNotBlank(password)) {
//        throw new IllegalArgumentException("密码不能为空");
//      } else {
//        if (password.length() > 17 || password.length() < 6) {
//          throw new IllegalArgumentException("密码必须为6-16位");
//        }
//      }
//      if (!Strings.isNotBlank(userName)) {
//        throw new IllegalArgumentException("昵称不能为空");
//      } else {
//        if (userName.length() > 7 || userName.length() < 1) {
//          throw new IllegalArgumentException("昵称必须为1-6位");
//        }
//      }
//      if (!Strings.isNotBlank(verifyCode)) {
//        throw new IllegalArgumentException("短信校验码");
//      }
//    }
  }
}
