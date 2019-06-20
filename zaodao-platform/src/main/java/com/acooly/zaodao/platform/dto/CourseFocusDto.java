package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程关注
 *
 * @author xiaohong
 * @create 2017-10-31 15:32
 **/
@Data
public class CourseFocusDto implements Serializable {
    /**
     * 用户ID
     */
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
     * 用户头像
     */
    private String headImg;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 课程简介
     */
    private String courseIntro;
    /**
     * 课程图片
     */
    private String courseImg;
    /**
     * 课程标题
     */
    private String courseTitle;
    /**
     * 课程创建时间
     */
    private Date createTime;
}
