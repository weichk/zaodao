package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户关注个数
 *
 * @author xiaohong
 * @create 2017-10-30 18:07
 **/
@Data
public class CustomerFocusCountResult extends ResultBase {
    /**
     * 关注个数
     */
    private int count;

    /**
     * 粉丝数
     */
    private int focusCount;
}
