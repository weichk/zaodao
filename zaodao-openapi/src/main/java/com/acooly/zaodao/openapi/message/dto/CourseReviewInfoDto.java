package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import jxl.write.DateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程评论列表
 *
 * @author xiaohong
 * @create 2017-10-31 11:39
 **/
@Data
public class CourseReviewInfoDto implements Serializable {

    @OpenApiField(desc = "评论ID", constraint = "评论ID")
    private Long courseReviewId;

    @OpenApiField(desc = "课程ID", constraint = "课程ID")
    private Long courseId;

    @OpenApiField(desc = "发布用户ID", constraint = "发布用户ID")
    private String userId;

    @OpenApiField(desc = "用户名", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "姓名", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "头像", constraint = "头像")
    private String headImg;

    @OpenApiField(desc = "评论父级ID", constraint = "评论父级ID")
    private Long reviewParentId;

    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    private String content;

    @OpenApiField(desc = "创建时间", constraint = "创建时间")
    private Date createTime;
}
