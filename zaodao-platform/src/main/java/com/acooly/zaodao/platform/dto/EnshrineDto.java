package com.acooly.zaodao.platform.dto;

import com.acooly.core.utils.Dates;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/13.
 */
@Data
public class EnshrineDto implements Serializable {

	/** 用户ID */
	@Size(max = 64)
	private String userId;

	/** 帖子ID */
	private Long id;

	private String title;

	/** 阅览数 */
	private Long readCount = 0l;

	/** 点赞数 */
	private Long praiseCount = 0l;

	private Date createTime = new Date();

	@Transient
	private String createTimeStr;

	public String getCreateTimeStr() {
		this.createTimeStr = Dates.format(getCreateTime(), Dates.CHINESE_DATE_FORMAT_LINE);
		return createTimeStr;
	}
}
