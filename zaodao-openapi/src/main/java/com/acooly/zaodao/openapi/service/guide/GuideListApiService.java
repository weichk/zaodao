package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideListRequest;
import com.acooly.zaodao.openapi.message.guide.GuideListResponse;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.order.QueryGuideListOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by xiyang on 2017/9/28. */
@OpenApiService(name = "guideList", desc = "导游列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideListApiService extends BaseApiService<GuideListRequest, GuideListResponse> {

  @Autowired private TourGuideService tourGuideService;

  @Override
  protected void doService(GuideListRequest request, GuideListResponse response) {
    QueryGuideListOrder order = request.toOrder(QueryGuideListOrder.class);
    order.setGid(Ids.gid());
    PageResult<GuideInfoDto> pageResult = tourGuideService.queryGuideList(order);
    pageResult.throwExceptionIfNotSuccess();
    if (pageResult.getDto() != null) {
      response.setPageResult(
          pageResult,
          (guideInfoDto, guideListDto) -> {
            guideListDto.setPricePerDay(Money.cent(guideInfoDto.getPricePerDay()));
          });
    }
  }
}
