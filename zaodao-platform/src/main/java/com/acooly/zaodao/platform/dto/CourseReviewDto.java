package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程评论
 *
 * @author xiaohong
 * @create 2017-10-31 11:37
 **/
@Data
public class CourseReviewDto implements Serializable {
    /**
     * 评论ID
     */
    private Long courseReviewId;

    /** 课程ID */
    private Long courseId;

    /** 发布用户ID */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String headImg;

    /** 评论父级ID */
    private Long reviewParentId;

    /** 评论内容 */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;
}
