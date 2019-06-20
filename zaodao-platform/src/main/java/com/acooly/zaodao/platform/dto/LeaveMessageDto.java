/*
 * 修订记录:
 * zhike@yiji.com 2017-06-13 15:01 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.entity.LeaveMessageReply;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class LeaveMessageDto implements Serializable {

    private Long messageId;

    private String headImg;

    private String realName;

    private String mobileNo;

    private Date createTime;

    private String content;

    private String leaveCusUserId;

    private List<LeaveMessageReply> leaveMessageReplys;
}
