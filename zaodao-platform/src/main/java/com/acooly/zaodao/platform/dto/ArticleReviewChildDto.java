/*
 * 修订记录:
 * zhike@yiji.com 2017-06-07 16:10 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ArticleReviewChildDto implements Serializable {

    /**
     * 回复人昵称
     */
    private String userName;

    /**
     * 回复人真实姓名
     */
    private String realName;

    /**
     * 回复人电话号码
     */
    private String mobileNo;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    private Date createTime;
}
