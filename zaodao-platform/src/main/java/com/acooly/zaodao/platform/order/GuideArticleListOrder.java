package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游文章列表
 *
 * @author xiaohong
 * @create 2017-12-06 14:15
 **/
@Data
public class GuideArticleListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "O00117052610240611600001")
    private String userId;
}
