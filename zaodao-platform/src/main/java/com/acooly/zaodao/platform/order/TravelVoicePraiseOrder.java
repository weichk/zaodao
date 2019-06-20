package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 旅声点赞
 *
 * @author xiaohong
 * @create 2017-10-30 14:30
 **/
@Data
public class TravelVoicePraiseOrder  extends OrderBase {
    @NotNull
    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @NotBlank
    @OpenApiField(desc = "点赞用户", constraint = "点赞用户")
    public String userId;

    @NotNull
    @OpenApiField(desc = "点赞标识", constraint = "点赞标识", demo = "0-取消点赞;1-点赞")
    private Integer praiseFlag;
}
