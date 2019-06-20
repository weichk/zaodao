/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 11:35 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.zaodao.openapi.message.common.MessageListRequest;
import com.acooly.zaodao.openapi.message.common.MessageListResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.zaodao.platform.dto.MessageDto;
import com.acooly.zaodao.platform.order.MessageListOrder;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "messageList", desc = "系统消息", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class MessageListApiService extends BaseApiService<MessageListRequest, MessageListResponse> {
    @Autowired
    private CustomerMessageService customerMessageService;

    @Override
    protected void doService(MessageListRequest request, MessageListResponse response) {
        MessageListOrder order = request.toOrder(MessageListOrder.class);
        order.setGid(Ids.gid());
        PageResult<MessageDto> messageDtoPageResult = customerMessageService.getCustomerMessageList(order);
        messageDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(messageDtoPageResult, (u,t)->{});

    }
}
