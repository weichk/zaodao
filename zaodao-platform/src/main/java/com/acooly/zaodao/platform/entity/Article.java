/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-22
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.Dates;
import com.acooly.zaodao.common.enums.ArticleCodeTypeEnum;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * 文章表 Entity
 *
 * @author zhike Date: 2017-05-22 16:35:27
 */
@Data
@Entity
@Table(name = "zd_article")
public class Article extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 发表人ID */
	private String userId;

	/** 发表人手机号码 */
	@Size(max = 32)
	private String mobileNo;

	/** 文章标题 */
	@Size(max = 64)
	private String title;

	/** 文章内容 */
	private String content;

	/** 文章封面 */
	@Size(max = 128)
	private String cover;

	/** 文章封面缩略图 */
	@Size(max = 128)
	private String coverThumb;

	/** 地区 */
	@Size(max = 64)
	private String area;

	/** 景区 */
	@Size(max = 64)
	private String scenic;

	/** 标签 {@link com.acooly.zaodao.common.enums.ArticleCodeTypeEnum} */
	@Enumerated(EnumType.STRING)
	private ArticleCodeTypeEnum label;

	/** 阅览数 */
	private Long readCount = 0l;

	/** 点赞数 */
	private Long praiseCount = 0l;

	/**
	 * 收藏数量
	 */
	private Long enshrineCount=0L;

	/**
	 * 打赏数量
	 */
	private Long rewardCount = 0L;

	/**
	 * 打赏总金额
	 */
	private Long rewardTotalAmount=0L;

	/** 文章类型 {@link ArticleTypeEnum} */
	@Enumerated(EnumType.STRING)
	private ArticleTypeEnum articleType;

	/** 文章状态 {@link com.acooly.zaodao.platform.enums.ArticleStatusEnum} */
	@Enumerated(EnumType.STRING)
	private ArticleStatusEnum articleStatus;

	/** 是否加精 */
	private Integer essenceStatus = 0;

	/** 是否置顶 */
	private Integer upStatus = 0;

	/** 是否有红包{1:有红包,0无红包} */
	private Integer hasRedBag = 0;

	/** 文章图章编码 */
	@Size(max = 128)
	private String stampCode;

	/** 帖子展示类型 */
	@Enumerated(EnumType.STRING)
	private ArticleHotType articleHotType = ArticleHotType.general;

	@Transient
	private String headImg;

	@Transient
	private String userName;

	@Transient
	private String createTimeStr;

	public String getCreateTimeStr() {
		this.createTimeStr = Dates.format(getCreateTime(), Dates.CHINESE_DATE_FORMAT_LINE);
		return createTimeStr;
	}
}
