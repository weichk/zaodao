package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户反馈
 *
 * @author xiaohong
 * @create 2017-12-01 14:08
 **/
@Data
public class FeedbackApplyOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "反馈标题", constraint = "反馈标题", demo = "早导网app使用情况")
    private String title;

    @NotBlank
    @OpenApiField(desc = "反馈内容", constraint = "反馈内容", demo = "app不错哟")
    private String content;
}
