package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.TravelVoiceType;
import lombok.Data;

/**
 * 旅声列表
 * @author xiaohong
 * @create 2017-10-27 17:27
 **/
@Data
public class TravelVoiceListOrder extends PageOrder {
    @OpenApiField(desc = "旅声作者ID", constraint = "旅声作者ID")
    private String userId;

    @OpenApiField(desc = "App用户ID", constraint = "App用户ID")
    private String appUserId;

    @OpenApiField(desc = "旅声分类", constraint = "旅声分类", demo = "热门,关注")
    private TravelVoiceType travelVoiceType;
}
