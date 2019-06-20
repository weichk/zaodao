package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导游详情
 *
 * @author xiaohong
 * @create 2017-11-28 18:07
 **/
@Data
public class GuideInfoOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID(是否关注该导游)", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID", demo = "O00117052610240611600000")
    private String guideUserId;
}
