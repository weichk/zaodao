package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导游文章列表
 *
 * @author xiaohong
 * @create 2017-12-06 14:08
 **/
@Data
public class GuideArticleIntoDto implements Serializable {
    @OpenApiField(desc = "文章ID", constraint = "文章ID", demo = "22")
    private Long articleId;

    @OpenApiField(desc = "帖子标题", constraint = "帖子标题", demo = "黑山谷导游词")
    private String title;

    @OpenApiField(desc = "帖子内容前100个字符", constraint = "帖子内容前100个字符", demo = "黑山谷导游词")
    private String content;

    @OpenApiField(desc = "点攒数", constraint = "点攒数", demo = "123")
    private Long praiseCount = 0L;

    @OpenApiField(desc = "回复数", constraint = "回复数", demo = "123")
    private Long reviewCount = 0L;

    @OpenApiField(desc = "帖子信息url", constraint = "帖子信息url")
    private String url;

    @OpenApiField(desc = "创建时间", constraint = "创建时间")
    private Date createTime;
}
