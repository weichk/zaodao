package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.dto.OpenWxUserDto;
import lombok.Data;

/**
 * 微信用户信息
 *
 * @author xiaohong
 * @create 2017-12-18 12:08
 **/
@Data
public class OpenPlatformUserResult extends ResultBase {
    @OpenApiField(desc = "开放平台用户实体", constraint = "开放平台用户实体")
    private OpenWxUserDto openWxUserDto = new OpenWxUserDto();
}
