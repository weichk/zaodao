/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:45 创建
 *
 */
package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ArticleReviewDto implements Serializable{

    @OpenApiField(desc = "评论会员姓名", constraint = "评论会员姓名", demo = "张山")
    private String reviewCusRealName;

    @OpenApiField(desc = "评论会员头像", constraint = "评论会员头像", demo = "aihiuhas")
    private String reviewCusHead;

    @OpenApiField(desc = "评论时间", constraint = "评论时间", demo = "2017-05-26 23:23:59")
    private String reviewTime;

    @OpenApiField(desc = "评论内容", constraint = "评论内容", demo = "这篇文章很好")
    private String reviewContent;
}
