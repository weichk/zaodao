package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.GuideLevel;
import lombok.Data;

/**
 * Created by xiaohong on 2017/10/16.
 */
@Data
@OpenApiMessage(service = "guideSearch", type = ApiMessageType.Request)
public class GuideSearchListRequest extends PageApiRequest {
    @OpenApiField(desc = "关键字", constraint = "关键字")
    private String keywords;

    @OpenApiField(desc = "导游等级", constraint = "导游等级")
    private GuideLevel guideLevel;

    @OpenApiField(desc = "是否热门导游", constraint = "性别{0:否,1:是}")
    private Integer hotGuide;
}
