package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评价信息
 *
 * @author xiaohong
 * @create 2017-12-19 14:12
 **/
@Data
public class EvalInfoDto implements Serializable {

    @OpenApiField(desc = "评分", constraint = "评分")
    private Integer score;

    @OpenApiField(desc = "评价内容", constraint = "评价内容")
    private String content;

    @OpenApiField(desc = "评价时间", constraint = "评价时间")
    private Date createTime;
}
