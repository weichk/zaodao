/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:47 创建
 *
 */
package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.ApplyGuideRequest;
import com.acooly.zaodao.openapi.message.guide.ApplyGuideResponse;
import com.acooly.zaodao.platform.order.ApplyGuideOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 * 导游二级认证
 * @author zhike@yiji.com
 */
@OpenApiService(name = "applyGuide", desc = "导游二级认证", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class ApplyGuideApiService extends BaseApiService<ApplyGuideRequest, ApplyGuideResponse> {

    @Autowired
    private TourGuideService tourGuideService;

    @Override
    protected void doService(ApplyGuideRequest request, ApplyGuideResponse response) {
        ApplyGuideOrder order = request.toOrder(ApplyGuideOrder.class);
        order.setGid(Ids.gid());
        ResultBase resultBase = tourGuideService.applyGuide(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
