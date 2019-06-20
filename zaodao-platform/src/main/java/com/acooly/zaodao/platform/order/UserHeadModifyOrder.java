package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xiaohong on 2017/10/17.
 */
@Data
public class UserHeadModifyOrder extends OrderBase {
    /**
     * 用户ID
     */
    @NotBlank
    private String userId;

    /**
     * 用户头像地址
     */
    @NotBlank
    private String headImg;
}
