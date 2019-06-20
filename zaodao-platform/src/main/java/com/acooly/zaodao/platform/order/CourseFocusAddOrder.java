package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.esotericsoftware.kryo.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加课程关注
 * @author xiaohong
 * @create 2017-10-31 15:08
 **/
@Data
public class CourseFocusAddOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户Id", constraint = "用户Id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;

    @NotNull
    @OpenApiField(desc = "关注标识", constraint = "关注标识", demo = "0-取消关注;1-关注")
    private Integer focusFlag;
}
