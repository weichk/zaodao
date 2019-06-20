package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 开放平台访问key
 *
 * @author xiaohong
 * @create 2017-12-18 11:07
 **/
@Data
public class OpenWxUserDto implements Serializable {
    @OpenApiField(desc = "昵称", constraint = "昵称")
    private String nickName;

    @OpenApiField(desc = "头像", constraint = "头像")
    private String headImgUrl;
}
