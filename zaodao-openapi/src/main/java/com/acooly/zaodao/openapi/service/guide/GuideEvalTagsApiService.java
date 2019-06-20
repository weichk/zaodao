package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideEvalTagsRequest;
import com.acooly.zaodao.openapi.message.guide.GuideEvalTagsResponse;
import com.acooly.zaodao.platform.order.GuideEvalTagsOrder;
import com.acooly.zaodao.platform.result.GuideTagListResult;
import com.acooly.zaodao.platform.service.manage.OrderGuideTagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导游评价标签
 *
 * @author xiaohong
 * @create 2018-05-07 20:54
 **/
@OpenApiService(name = "guideEvalTags", desc = "导游评价标签", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideEvalTagsApiService extends BaseApiService<GuideEvalTagsRequest, GuideEvalTagsResponse> {
    @Autowired
    private OrderGuideTagService orderGuideTagService;

    @Override
    protected void doService(GuideEvalTagsRequest request, GuideEvalTagsResponse response) {
        GuideEvalTagsOrder order = request.toOrder(GuideEvalTagsOrder.class);
        order.setGid(Ids.gid());
        GuideTagListResult result = orderGuideTagService.getGuideEvalTags(order);
        result.throwExceptionIfNotSuccess();
        response.setRows(result.getDto());
    }
}
