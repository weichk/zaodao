package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.ListOrder;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 旅行社列表
 *
 * @author xiaohong
 * @create 2018-05-04 17:06
 **/
@Data
public class TravelAgencyListOrder extends ListOrder {
    @NotBlank
    @OpenApiField(desc = "添加旅行社信息的用户ID", constraint = "用户ID")
    private String userId;
}
