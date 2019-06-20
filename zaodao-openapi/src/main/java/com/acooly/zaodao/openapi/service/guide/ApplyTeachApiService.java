/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:47 创建
 *
 */
package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.ApplyTeachRequest;
import com.acooly.zaodao.openapi.message.guide.ApplyTeachResponse;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "applyTeach", desc = "申请讲课", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class ApplyTeachApiService extends BaseApiService<ApplyTeachRequest, ApplyTeachResponse> {

  @Autowired private CustomerService customerService;

  @Override
  protected void doService(ApplyTeachRequest request, ApplyTeachResponse response) {
    ResultBase result = customerService.applyTeach(request.getUserId(), request.getRealName());
    result.throwExceptionIfNotSuccess();
  }
}
