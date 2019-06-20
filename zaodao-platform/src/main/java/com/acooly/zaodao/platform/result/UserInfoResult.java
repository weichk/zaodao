package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.common.enums.MedalEnum;
import com.acooly.zaodao.platform.enums.GuideAuditResult;
import com.acooly.zaodao.platform.enums.ModeratorPermissionEnum;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoResult extends ResultBase {

  /** 会员唯一标识 */
  @Size(max = 64)
  private String userId;

  /** 观世宇用户唯一标识 */
  @Size(max = 64)
  private String outUserId;

  /** 电话号码 */
  @Size(max = 32)
  private String mobileNo;

  /** 昵称 */
  @NotEmpty
  @Size(max = 32)
  private String userName;

  /** 真实姓名 */
  @Size(max = 32)
  private String realName;

  /** 身份证号 */
  @Size(max = 18)
  private String idNumber;

  /** 是否实名 */
  @NotNull private Integer isCertification = 0;

  /** 登录密码 */
  @NotBlank
  @Size(max = 128)
  private String loginPassword;

  /** 交易密码 */
  @Size(max = 128)
  private String payPassword;

  /** 登录密码加密填充值 */
  @NotBlank
  @Size(max = 32)
  private String loginSail;

  /** 交易密码加密填充值 */
  @Size(max = 32)
  private String paySail;

  /** 用户头像 */
  private String headImg;

  /**
   * 是否导游{-1:待审核;0-否;1-一级导游;2-二级导游审核中;3-二级导游}
   */
  private Integer isTourGuide = 0;

  /**
   * 导游审核意见
   */
  private String auditDesc;

  /**
   * 导游审核结果
   */
  private GuideAuditResult auditResult;

  /** 是否为版主 */
  private Integer isModerator = 0;

  /** 是否为讲师 */
  private Integer isLector = 0;
  /** 版主权限 */
  @Enumerated(EnumType.STRING)
  private ModeratorPermissionEnum moderatorPermission = ModeratorPermissionEnum.all;

  /** 是否禁言 */
  private Integer isShutup = 0;

  /** 电子邮件 */
  @Size(max = 64)
  private String email;

  /** 性别 */
  private Integer sex = 1;

  /** 年龄 */
  private Integer age;

  /** 联系地址 */
  @Size(max = 128)
  private String contactAddress;

  /** 勋章编码，可多个，逗号分隔 */
  @Size(max = 128)
  private String medalCode;

  private List<MedalEnum> medalEnums = Lists.newArrayList();

  private Long point = 0l;

  private Integer pointLevel;

  private String pointName;

  private Money balance = new Money(0);

  /** 文章数量  */
  private Integer articleNums;

  /**
   * 关注个数
   */
  private int count;

  /**
   * 粉丝数
   */
  private int focusCount;

  /**
   * 用户绑定卡数量
   */
  private int bindCardCount;
}
