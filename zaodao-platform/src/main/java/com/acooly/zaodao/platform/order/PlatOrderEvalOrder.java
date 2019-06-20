package com.acooly.zaodao.platform.order;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 订单完成
 *
 * @author xiaohong
 * @create 2017-12-11 16:56
 **/
@Data
public class PlatOrderEvalOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "评论用户ID", constraint = "评论用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @NotNull
    @OpenApiField(desc = "评分", constraint = "评分")
    private Integer score;

    @NotBlank
    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    private String content;

    @OpenApiField(desc = "导游标签", constraint = "导游标签", demo = "1,3,4,5")
    private String guidTagIds;

    @Override
    public void check(){
        super.check();
        if(score < 1 || score > 5){
            throw new BusinessException("评分范围1-5");
        }
    }
}
