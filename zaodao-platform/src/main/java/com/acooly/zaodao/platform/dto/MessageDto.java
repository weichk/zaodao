/*
 * 修订记录:
 * zhike@yiji.com 2017-06-01 15:22 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.CustomerMessageType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class MessageDto implements Serializable{
    @OpenApiField(desc = "消息ID", constraint = "消息ID")
    private Long customerMessageId;

    @OpenApiField(desc = "读取状态", constraint = "读取状态", demo = "0-未读; 1-已读")
    private Integer readStatus;

    @OpenApiField(desc = "消息", constraint = "消息")
    private String message;

    @OpenApiField(desc = "消息标题", constraint = "消息标题")
    private String messageTitle;

    @OpenApiField(desc = "动态创建时间", constraint = "动态创建时间")
    private Date createTime;

    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @OpenApiField(desc = "用户名", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "姓名", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "头像", constraint = "头像")
    private String headImg;

    @OpenApiField(desc = "消息类型", constraint = "消息类型")
    private CustomerMessageType messageType;

    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String orderNo;
}

