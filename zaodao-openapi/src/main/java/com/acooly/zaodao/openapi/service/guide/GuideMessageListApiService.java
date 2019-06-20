package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideMessageListRequest;
import com.acooly.zaodao.openapi.message.guide.GuideMessageListResponse;
import com.acooly.zaodao.platform.dto.GuideMessageDto;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导游详情动态列表
 *
 * @author xiaohong
 * @create 2017-10-31 16:49
 **/
@OpenApiService(name = "guideMessageList", desc = "导游详情动态列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideMessageListApiService  extends BaseApiService<GuideMessageListRequest, GuideMessageListResponse> {
    @Autowired
    private GuideMessageService guideMessageService;

    @Override
    protected void doService(GuideMessageListRequest request, GuideMessageListResponse response) {
        GuideMessageListOrder guideMessageListOrder = request.toOrder(GuideMessageListOrder.class);
        guideMessageListOrder.setGid(Ids.gid());
        PageResult<GuideMessageDto> guideMessageDtoPageResult = guideMessageService.getGuideMessageList(guideMessageListOrder);
        guideMessageDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(guideMessageDtoPageResult, (u,t) ->{});
    }
}
