/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-31
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.Strings;
import com.acooly.zaodao.common.enums.MedalEnum;
import com.acooly.zaodao.platform.enums.GuideAuditResult;
import com.acooly.zaodao.platform.enums.ModeratorPermissionEnum;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户表 Entity
 *
 * @author zhike Date: 2017-05-31 10:02:23
 */
@Data
@Entity
@Table(name = "zd_customer")
public class Customer extends AbstractEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员唯一标识
	 */
	@Size(max = 64)
	private String userId;

	/**
	 * 观世宇用户唯一标识
	 */
	@Size(max = 64)
	private String outUserId;

	/**
	 * 电话号码
	 */
	@Size(max = 32)
	private String mobileNo;

	/**
	 * 账户名
	 */
	@NotEmpty
	@Size(max = 32)
	private String userName;

	/**
	 * 真实姓名
	 */
	@Size(max = 32)
	private String realName;

	/**
	 * 身份证号
	 */
	@Size(max = 18)
	private String idNumber;

	/**
	 * 是否实名
	 */
	@NotNull
	private Integer isCertification = 0;

	/**
	 * 登录密码
	 */
	@NotBlank
	@Size(max = 128)
	private String loginPassword;

	/**
	 * 交易密码
	 */
	@Size(max = 128)
	private String payPassword;

	/**
	 * 登录密码加密填充值
	 */
	@NotBlank
	@Size(max = 32)
	private String loginSail;

	/**
	 * 交易密码加密填充值
	 */
	@Size(max = 32)
	private String paySail;

	/**
	 * 用户头像
	 */
	private String headImg;

	/**
	 * 是否导游
     * WAIT_APPROVE(-1, "待审核"),
	   NOT_PASS(0, "否"),
	   ONE_LEVEL(1, "一级导游"),
	   TWO_APPROVE(2, "二级导游审核中"),
	   TWO_LEVEL(3, "二级导游");
	 */
	private Integer isTourGuide = 0;

	/**
	 * 是否为版主
	 */
	private Integer isModerator = 0;

	/**
	 * 是否为讲师
	 */
	private Integer isLector = 0;
	/**
	 * 版主权限
	 */
	@Enumerated(EnumType.STRING)
	private ModeratorPermissionEnum moderatorPermission = ModeratorPermissionEnum.all;

	/**
	 * 是否禁言
	 */
	private Integer isShutup = 0;

	/**
	 * 电子邮件
	 */
	@Size(max = 64)
	private String email;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 联系地址
	 */
	@Size(max = 128)
	private String contactAddress;

	/**
	 * 勋章编码，可多个，逗号分隔
	 */
	@Size(max = 128)
	private String medalCode;

	/**
	 * 微信openId
	 */
	@Size(max = 64)
	private String openId;

	/**
	 * QQ openId
	 */
	@Size(max = 64)
	private String qqId;

	/**
	 * 微博openId
	 */
	@Size(max = 64)
	private String weiboId;

	/**
	 * 是否开启商务接待{0-否,1-是}
	 */
	private Integer isBusRecept = 0;
	/**
	 * 是否开启政务接待{0-否,1-是}
	 */
	private Integer isGovRecept = 0;
	/**
	 * 是否挂靠旅行社{0-否,1-是}
	 */
	private Integer isLinkAngency = 0;

	/**
	 * 是否具备领队资质{0-否,1-是}
	 */
	private Integer isLeader = 0;

	/**
	 * 导游审核意见
	 */
	private String auditDesc;

	/**
	 * 导游审核结果
	 */
	@Enumerated(EnumType.STRING)
	private GuideAuditResult auditResult;

	/**
	 * 勋章名称，可多个，逗号分隔
	 */
	@Transient
	private String medalName;

	@Transient
	private List<MedalEnum> medalEnums = Lists.newArrayList();

	public List<MedalEnum> getMedalEnums() {
		if (Strings.isNotBlank(medalCode)) {
			String[] medalCodes = medalCode.split(",");
			for (String code : medalCodes) {
				medalEnums.add(MedalEnum.find(code));
			}
		}
		return medalEnums;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Customer{");
		sb.append("userId='").append(userId).append('\'');
		sb.append(", outUserId='").append(outUserId).append('\'');
		sb.append(", mobileNo='").append(mobileNo).append('\'');
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", realName='").append(realName).append('\'');
		sb.append(", idNumber='").append(idNumber).append('\'');
		sb.append(", isCertification=").append(isCertification);
		sb.append(", isTourGuide=").append(isTourGuide);
		sb.append(", isModerator=").append(isModerator);
		sb.append(", moderatorPermission=").append(moderatorPermission);
		sb.append(", isShutup=").append(isShutup);
		sb.append(", email='").append(email).append('\'');
		sb.append(", sex=").append(sex);
		sb.append(", age=").append(age);
		sb.append(", contactAddress='").append(contactAddress).append('\'');
		sb.append(", medalCode='").append(medalCode).append('\'');
		sb.append(", medalName='").append(medalName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
