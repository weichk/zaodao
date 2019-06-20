package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideSearchListRequest;
import com.acooly.zaodao.openapi.message.guide.GuideSearchListResponse;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.order.QueryGuideSearchOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/** Created by okobboko on 2017/10/16. */
@OpenApiService(name = "guideSearch", desc = "导游查询", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideSearchApiService extends BaseApiService<GuideSearchListRequest, GuideSearchListResponse> {

  @Autowired private TourGuideService tourGuideService;

  @Override
  protected void doService(
      GuideSearchListRequest guideSearchListRequest,
      GuideSearchListResponse guideSearchListResponse) {
    QueryGuideSearchOrder queryGuideSearchOrder =
        guideSearchListRequest.toOrder(QueryGuideSearchOrder.class);

    PageResult<GuideInfoDto> pageResult =
        tourGuideService.queryGuideSearchList(queryGuideSearchOrder);
    pageResult.throwExceptionIfNotSuccess();
    if (pageResult.getDto() != null) {
      guideSearchListResponse.setPageResult(
          pageResult,
          (guideInfoDto, guideListDto) -> {
            guideListDto.setPricePerDay(Money.cent(guideInfoDto.getPricePerDay()));
          });
    }
  }
}
