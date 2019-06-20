package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaohong
 * @create 2017-10-27 15:05
 **/
@Data
public class TravelReviewDto implements Serializable {
    /**
     * 旅声评论ID
     */
    private Long travelReviewId;
    /**
     * 评论用户ID
     */
    private String userId;
    /**
     * 评论用户姓名
     */
    private String realName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 评论父级ID
     */
    private Long reviewParentId;

    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date createTime;
}