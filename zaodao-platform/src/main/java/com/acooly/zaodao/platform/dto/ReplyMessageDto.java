/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 16:08 创建
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
public class ReplyMessageDto implements Serializable {

    private Long replyMessageId;

    private String userName;

    private String headImg;

    private Long articleId;

    private String articleTitle;

    private Integer readStatus;

    private Date createTime = new Date();

}
