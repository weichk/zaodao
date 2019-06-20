/*
 * 修订记录:
 * zhike@yiji.com 2017-07-01 22:21 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.core.utils.Strings;
import com.acooly.zaodao.common.enums.StampEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class BlogArticleInfoDto implements Serializable {

	private Long articleId;

	private String headImg;

	private String userName;

	private String realName;

	private String title;

	private Long readCount;

	private Long reviewCount;

	private Integer havaRedPacket;

	private Date createTime;

	private Integer essenceStatus;

	private Integer upStatus;

	private String label;

	private String cover;

	private String stampCode;

	private String stampName;

	public String getStampName() {
		if (Strings.isNotBlank(stampCode)) {
			if (StampEnum.find(stampCode) != null) {
				return StampEnum.find(stampCode).getMessage();
			}
		}
		return stampName;
	}
}
