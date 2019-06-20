package com.acooly.zaodao.platform.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导游文章数量
 *
 * @author xiaohong
 * @create 2017-12-06 14:14
 **/
@Data
public class GuideArticleDto implements Serializable {
    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容前100个字符
     */
    private String content;

    /**
     * 点攒数
     */
    private Long praiseCount = 0L;

    /**
     * 回复数
     */
    private Long reviewCount = 0L;

    /**
     * 帖子信息url
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;
}