package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 兑换比率
 *
 * @author xiaohong
 * @create 2017-11-27 17:26
 **/
@Data
public class ExchangeRateDto implements Serializable {
    @OpenApiField(desc = "金钱转换钙片比率", constraint = "金钱转换钙片比率", demo = "10(1元10个钙片)")
    private String money2CaRate;
}
