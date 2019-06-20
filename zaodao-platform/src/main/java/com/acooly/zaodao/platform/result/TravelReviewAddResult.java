package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohong
 * @create 2017-11-24 18:10
 **/
@Data
public class TravelReviewAddResult extends ResultBase {
    /**
     * 旅声评论ID
     */
    private Long travelReviewId;

    /**
     * 评论用户头像
     */
    private String headImg;

    /**
     * 评论用户名
     */
    private String userName;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 评论数
     */
    private Long reviewCount = 0l;
}
