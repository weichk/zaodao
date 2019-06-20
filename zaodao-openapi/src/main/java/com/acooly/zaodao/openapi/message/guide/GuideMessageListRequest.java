package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 导游详情动态请求
 *
 * @author xiaohong
 * @create 2017-10-31 16:51
 **/
@Data
@OpenApiMessage(service = "guideMessageList", type = ApiMessageType.Request)
public class GuideMessageListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    /**
     * 0-我发起的：userId关注了xxx
     * 1-我收到的：xxx关注了userId
     */
    @NotNull
    @OpenApiField(desc = "消息请求标识", constraint = "消息请求标识", demo = "0-我发起的; 1-我收到的")
    private Integer messageFlag;
}
