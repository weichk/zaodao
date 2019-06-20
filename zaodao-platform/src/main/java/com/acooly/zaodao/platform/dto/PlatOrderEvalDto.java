package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单评价
 *
 * @author xiaohong
 * @create 2017-12-12 9:54
 **/
@Data
public class PlatOrderEvalDto implements Serializable {

    /**
     * 评价用户ID
     */
    private String userId;
    /**
     * 评价用户名
     */
    private String userName;
    /**
     * 评价用户真实姓名
     */
    private String realName;
    /**
     * 评价用户头像
     */
    private String headImg;
    /**
     * 评分
     */
    private Integer score;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价时间
     */
    private Date createTime;
}
