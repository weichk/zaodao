/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 17:04 创建
 *
 */
package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.common.enums.ArticleCodeTypeEnum;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class ArticleDto implements Serializable {

    @OpenApiField(desc = "文章ID", constraint = "文章ID", demo = "22")
    private Long articleId;

    @OpenApiField(desc = "帖子标题", constraint = "帖子标题", demo = "黑山谷导游词")
    private String title;

    @OpenApiField(desc = "帖子内容前100个字符", constraint = "帖子内容前100个字符", demo = "黑山谷导游词")
    private String content;

    @OpenApiField(desc = "阅读数", constraint = "阅读数", demo = "234")
    private Long readCount = 0L;

    @OpenApiField(desc = "点攒数", constraint = "点攒数", demo = "888")
    private Long praiseCount = 0L;

    @OpenApiField(desc = "用户真实姓名", constraint = "用户真实姓名", demo = "张山")
    private String realName;

    @OpenApiField(desc = "用户昵称", constraint = "用户昵称", demo = "张山")
    private String userName;

//    @OpenApiField(desc = "会员头像", constraint = "会员头像")
//    private String headImg;

    @OpenApiField(desc = "发表人ID")
    private String userId;

    @OpenApiField(desc = "文章封面")
    private String cover;

    @OpenApiField(desc = "文章封面缩略图")
    private String coverThumb;

    @OpenApiField(desc = "地区")
    private String area;

    @OpenApiField(desc = "景区")
    private String scenic;

    @OpenApiField(desc = "标签")
    private ArticleCodeTypeEnum label;

    @OpenApiField(desc = "收藏数量", constraint = "收藏数量", demo = "234")
    private Long enshrineCount = 0L;

    @OpenApiField(desc = "打赏数量", constraint = "打赏数量", demo = "234")
    private Long rewardCount = 0L;

    @OpenApiField(desc = "打赏总积分", constraint = "打赏总积分", demo = "234")
    private Long rewardTotalAmount = 0L;

    @OpenApiField(desc = "文章类型", constraint = "文章类型")
    private ArticleTypeEnum articleType;

    @OpenApiField(desc = "文章状态", constraint = "文章状态")
    private ArticleStatusEnum articleStatus;

    @OpenApiField(desc = "是否加精", constraint = "是否加精{0:不加精,1:加精}")
    private Integer essenceStatus = 0;

    @OpenApiField(desc = "是否置顶", constraint = "是否置顶{0:不置顶,1:置顶}")
    private Integer upStatus = 0;

    @OpenApiField(desc = "是否有红包", constraint = "是否有红包{1:有红包,0无红包}")
    private Integer hasRedBag = 0;

    @OpenApiField(desc = "文章图章编码", constraint = "文章图章编码")
    private String stampCode;

    @OpenApiField(desc = "帖子展示类型", constraint = "帖子展示类型")
    private ArticleHotType articleHotType;

    @OpenApiField(desc = "帖子信息url", constraint = "帖子信息url")
    private String url;

    @OpenApiField(desc = "创建时间", constraint = "创建时间")
    private Date createTime;
}
