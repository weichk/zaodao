package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by okobboko on 2017/10/12.
 */
@Data
public class CoursePurchaseOrder extends OrderBase {
    /**
     * 用户id
     */
    @NotBlank
    private String userId;
    /**
     * 课程id
     */
    @NotNull
    private Long courseId;
}
