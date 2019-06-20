/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:21 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.common.enums.MedalEnum;
import com.acooly.zaodao.platform.enums.GuideAuditResult;
import com.acooly.zaodao.platform.enums.IsTourGuide;
import com.acooly.zaodao.platform.enums.ModeratorPermissionEnum;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Getter
@Setter
@OpenApiMessage(service = "userInfo", type = ApiMessageType.Response)
public class UserInfoResponse extends ApiResponse {

    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "观世宇用户唯一标识", constraint = "观世宇用户唯一标识", demo = "O00117052610240611600000")
    private String outUserId;

    @OpenApiField(desc = "电话号码", constraint = "电话号码")
    private String mobileNo;

    @OpenApiField(desc = "昵称", constraint = "昵称")
    private String userName;

    @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
    private String realName;

    @OpenApiField(desc = "是否实名", constraint = "是否实名{0:否,1:是}")
    private Integer isCertification = 0;

    @OpenApiField(desc = "用户头像", constraint = "用户头像")
    private String headImg;

    @OpenApiField(desc = "是否导游", constraint = "是否导游{-1:待审核;0-否;1-一级导游;2-二级导游审核中;3-二级导游}")
    private Integer isTourGuide = 0;

    @OpenApiField(desc = "导游审核意见", constraint = "导游审核意见")
    private String auditDesc;

    @OpenApiField(desc = "导游审核结果", constraint = "导游审核结果")
    private GuideAuditResult auditResult;

    @OpenApiField(desc = "是否讲师", constraint = "是否讲师{0:否,1:是,-1:申请中}")
    private Integer isLector = 0;

    @OpenApiField(desc = "是否为版主", constraint = "是否为版主{0:否,1:是}")
    private Integer isModerator = 0;

    @OpenApiField(desc = "版主权限", constraint = "版主权限")
    private ModeratorPermissionEnum moderatorPermission;

    @OpenApiField(desc = "是否禁言", constraint = "是否禁言{0:否,1:是}")
    private Integer isShutup = 0;

    @OpenApiField(desc = "电子邮件", constraint = "电子邮件{0:否,1:是}")
    private String email;

    @OpenApiField(desc = "性别", constraint = "性别{0:女,1:男}")
    private Integer sex = 0;

    @OpenApiField(desc = "年龄", constraint = "年龄")
    private Integer age;

    @OpenApiField(desc = "联系地址", constraint = "联系地址")
    private String contactAddress;

    @OpenApiField(desc = "勋章列表", constraint = "勋章列表")
    private List<MedalEnum> medalEnums = Lists.newArrayList();

    @OpenApiField(desc = "积分", constraint = "积分")
    private Long point = 0l;

    @OpenApiField(desc = "积分等级", constraint = "积分等级")
    private Integer pointLevel;

    @OpenApiField(desc = "积分等级名称", constraint = "积分等级名称")
    private String pointName;

    @OpenApiField(desc = "余额", constraint = "余额")
    private Money balance = new Money(0);

    /** 文章数量  */
    @OpenApiField(desc = "文章数量", constraint = "文章数量")
    private Integer articleNums;

    @OpenApiField(desc = "关注个数", constraint = "关注个数")
    private int count;

    @OpenApiField(desc = "粉丝数", constraint = "粉丝数")
    private int focusCount;

    @OpenApiField(desc = "用户绑定卡数量", constraint = "用户绑定卡数量")
    private int bindCardCount;

    @OpenApiField(desc = "身份证", constraint = "身份证")
    private String idNumber;
}
