package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.ListOrder;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游评价标签
 *
 * @author xiaohong
 * @create 2018-05-07 21:03
 **/
@Getter
@Setter
public class GuideEvalTagsOrder extends ListOrder {
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID", demo = "O00117052610240611600000")
    private String guideUserId;
}
