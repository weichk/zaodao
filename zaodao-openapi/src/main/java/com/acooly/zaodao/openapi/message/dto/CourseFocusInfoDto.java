package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 15:31
 **/
@Data
public class CourseFocusInfoDto implements Serializable{

    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @OpenApiField(desc = "用户名", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "姓名", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "用户头像", constraint = "用户头像")
    private String headImg;

    @OpenApiField(desc = "课程ID", constraint = "课程ID")
    private Long courseId;

    @OpenApiField(desc = "课程简介", constraint = "课程简介")
    private String courseIntro;

    @OpenApiField(desc = "课程图片", constraint = "课程图片")
    private String courseImg;

    @OpenApiField(desc = "课程标题", constraint = "课程标题")
    private String courseTitle;

    @OpenApiField(desc = "课程创建时间", constraint = "课程创建时间")
    private Date createTime;
}
