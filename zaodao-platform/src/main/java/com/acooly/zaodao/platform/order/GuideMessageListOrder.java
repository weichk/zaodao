package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 导游详情动态请求
 *
 * @author xiaohong
 * @create 2017-10-31 17:05
 **/
@Data
public class GuideMessageListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID")
    private String userId;

    /**
     * 0-我发起的：userId关注了xxx
     * 1-我收到的：xxx关注了userId
     */
    @NotNull
    @OpenApiField(desc = "消息请求标识", constraint = "消息请求标识", demo = "0-我发起的; 1-我收到的")
    private Integer messageFlag;
}
