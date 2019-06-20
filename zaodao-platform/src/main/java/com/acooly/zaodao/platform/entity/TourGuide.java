/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-02
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.Money;
import com.acooly.zaodao.platform.enums.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 导游信息表 Entity
 *
 * @author zhike Date: 2017-06-02 00:04:53
 */
@Data
@Entity
@Table(name = "zd_tour_guide")
public class TourGuide extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@NotBlank
	@Size(max = 64)
	private String userId;

	/** 导游证号 */
	@Size(max = 32)
	private String guideNo;

	/**
	 * 导游封面图
	 */
	private String guideCoverImg;

	/** 导游资格证号 */
	@Size(max = 32)
	private String guideCertificateNo;

	/**
	 * 导游资格证图片
	 */
	private String guideCertificateImg;

	/** 带团时间 */
	private Integer tourTime = 0;

	/** 导游等级 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private TourRank tourRank;

	/** 常驻城市 */
	private String permanentCity;

	/** 所在省市 */
	@Size(max = 255)
	private String province;

	/** 擅长路线 */
	@Size(max = 512)
	private String goodRoute;

	/** 自我介绍 */
	@Size(max = 512)
	private String introduceMyself;

	/** 导游专长（爱好） */
	@Size(max = 128)
	private String speciality;

	/** 价格 */
	private Long pricePerDay = 0l;

	/** 语种 */
	private String language;

	/** 星级 */
	private Integer starLevel;

	/** 是否为人气导游{1:是,0:否} */
	private int hotGuide;

	/** 导游休息日，逗号分隔的yyyy-MM-dd **/
	private String restDays;

	/** 导游等级 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private GuideLevel guideLevel;

	/**
	 * 业务属性
	 */
	@Transient
	private int amount = 0;

	public int getAmount() {
		this.amount = Money.cent(pricePerDay).getAmount().intValue();
		return amount;
	}

	/**
	 * 商务接待次数
	 */
	@Enumerated(EnumType.STRING)
	private BusReceptCountType busReceptCount;

	/**
	 * 政务接待次数
	 */
	@Enumerated(EnumType.STRING)
	private GovReceptCountType govReceptCount;

	/**
	 * 上次修改带团价格时间
	 */
	private Date modifyPriceTime;
}
