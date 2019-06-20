package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.ServiceFeeScaleType;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-06-01 18:09
 **/
@Getter
@Setter
public class OrderServiceFeeInfoDto implements Serializable {
    @OpenApiField(desc = "差值(x)", constraint = "差值(x)")
    private String daysText;

    @OpenApiField(desc = "服务费收取标准", constraint = "服务费收取标准")
    private String feeText;
}
