package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.MessageCountRequest;
import com.acooly.zaodao.openapi.message.common.MessageCountResponse;
import com.acooly.zaodao.platform.order.MessageCountOrder;
import com.acooly.zaodao.platform.result.MessageCountResult;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息个数
 *
 * @author xiaohong
 * @create 2017-11-08 16:39
 **/
@OpenApiService(name = "messageCount", desc = "系统消息", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class MessageCountApiService extends BaseApiService<MessageCountRequest, MessageCountResponse> {
    @Autowired
    private CustomerMessageService customerMessageService;

    @Override
    protected void doService(MessageCountRequest request, MessageCountResponse response) {
        MessageCountOrder messageCountOrder = request.toOrder(MessageCountOrder.class);
        messageCountOrder.setGid(Ids.gid());
        MessageCountResult messageCountResult = customerMessageService.getCustomerMessageCount(messageCountOrder);
        messageCountResult.throwExceptionIfNotSuccess();
        response.setMessageCount(messageCountResult.getMessageCount());
    }
}
