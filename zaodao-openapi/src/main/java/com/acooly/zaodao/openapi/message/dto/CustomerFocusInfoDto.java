package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 9:27
 **/
@Data
public class CustomerFocusInfoDto implements Serializable {

    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @OpenApiField(desc = "用户名称", constraint = "用户名称")
    private String userName;

    @OpenApiField(desc = "姓名", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "用户头像", constraint = "用户头像")
    private String headImg;
}
