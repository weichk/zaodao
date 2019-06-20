package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 课程关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 15:28
 **/
@Data
public class CourseFocusListOrder  extends PageOrder  {
    @NotBlank
    @OpenApiField(desc = "用户Id", constraint = "用户Id")
    private String userId;
}
