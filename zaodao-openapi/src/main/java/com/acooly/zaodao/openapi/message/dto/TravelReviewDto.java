package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaohong
 * @create 2017-10-27 15:02
 **/
@Data
public class TravelReviewDto implements Serializable {

    @OpenApiField(desc = "旅声评论ID", constraint = "旅声评论ID")
    private Long travelReviewId;

    @OpenApiField(desc = "评论用户ID", constraint = "评论用户ID")
    private String userId;

    @OpenApiField(desc = "评论用户姓名", constraint = "评论用户姓名")
    private String realName;

    @OpenApiField(desc = "用户名", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "头像", constraint = "头像")
    private String headImg;

    @OpenApiField(desc = "评论父级ID", constraint = "评论父级ID")
    private Long reviewParentId;

    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    private String content;

    @OpenApiField(desc = "评论时间", constraint = "评论时间")
    private Date createTime;
}
