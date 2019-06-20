package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import lombok.Data;

import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

/**
 * 导游详情动态
 *
 * @author xiaohong
 * @create 2017-10-31 16:53
 **/
@Data
public class GuideMessageIntoDto implements Serializable {

    @OpenApiField(desc = "导游详情动态ID", constraint = "导游详情动态ID")
    private Long guidMessageId;

    @OpenApiField(desc = "读取状态", constraint = "读取状态", demo = "0-未读; 1-已读")
    private Integer readStatus;

    @OpenApiField(desc = "标题", constraint = "标题")
    private String title;

    @OpenApiField(desc = "动态类型", constraint = "动态类型")
    private GuideMessageType messageType;

    @OpenApiField(desc = "动态类型名称", constraint = "动态类型名称")
    private String messageName;

    @OpenApiField(desc = "动态内容", constraint = "动态内容")
    private String content;

    @OpenApiField(desc = "内容BomId", constraint = "内容BomId")
    private String contentBomId;

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
}
