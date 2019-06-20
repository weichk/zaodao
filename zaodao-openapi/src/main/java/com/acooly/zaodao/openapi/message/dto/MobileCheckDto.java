package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 开放平台手机号验证
 *
 * @author xiaohong
 * @create 2018-01-05 15:51
 **/
@Data
public class MobileCheckDto implements Serializable {

    @OpenApiField(desc = "手机号是否存在", constraint = "手机号是否存在")
    private boolean isExist;

    @OpenApiField(desc = "手机号是否绑定开放平台账号", constraint = "手机号是否绑定开放平台账号")
    private boolean isBinding;
}
