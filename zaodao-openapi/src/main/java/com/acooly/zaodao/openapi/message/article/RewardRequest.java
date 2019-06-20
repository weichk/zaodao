package com.acooly.zaodao.openapi.message.article;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohong on 2017/9/27.
 */
@Data
@OpenApiMessage(service = "reward", type = ApiMessageType.Request)
public class RewardRequest extends ApiRequest {
    @NotNull
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识")
    private String userId;

    @NotNull
    @OpenApiField(desc = "业务管理ID", constraint = "业务管理ID")
    private String businessId;

    @NotNull
    @OpenApiField(desc = "打赏类型", constraint = "打赏类型")
    private RewardTypeEnum type;

    @NotNull
    @OpenApiField(desc = "打赏钙片数", constraint = "打赏钙片数")
    private Long point;

    @Override
    public void check() throws RuntimeException {
        super.check();
        if (point.intValue() <= 0) {
            throw new IllegalArgumentException("打赏钙片数必须大于0");
        }
    }
}
