package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.GuideMessageType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导游详情动态
 *
 * @author xiaohong
 * @create 2017-10-31 17:08
 **/
@Data
public class GuideMessageDto implements Serializable {

    /**
     * 导游详情动态ID
     */
    private Long guidMessageId;
    /**
     * 0-未读; 1-已读
     */
    private Integer readStatus;
    /**
     * 标题
     */
    private String title;
    /**
     * 动态类型
     */
    private GuideMessageType messageType;
    /**
     * 动态类型名称
     */
    private String messageName;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 内容BomId
     */
    private String contentBomId;
    /**
     * 动态创建时间
     */
    private Date createTime;
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
     * 头像
     */
    private String headImg;
}
