package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiaohong
 * @create 2017-12-12 9:38
 **/
@Data
public class PlatOrderEvalListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "导游用户ID", constraint = "导游用户ID", demo = "O00117052610240611600000")
    private String userId;
}
