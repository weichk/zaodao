package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单评价
 *
 * @author xiaohong
 * @create 2017-12-12 9:35
 **/
@Data
public class PlatOrderEvalInfoDto  implements Serializable {

    @OpenApiField(desc = "评价用户ID", constraint = "评价用户ID")
    private String userId;

    @OpenApiField(desc = "评价用户名", constraint = "评价用户名")
    private String userName;

    @OpenApiField(desc = "评价用户真实姓名", constraint = "评价用户真实姓名")
    private String realName;

    @OpenApiField(desc = "评价用户头像", constraint = "评价用户头像")
    private String headImg;

    @OpenApiField(desc = "评分", constraint = "评分")
    private Integer score;

    @OpenApiField(desc = "评价内容", constraint = "评价内容")
    private String content;

    @OpenApiField(desc = "评价时间", constraint = "评价时间")
    private Date createTime;
}
