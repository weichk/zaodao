package com.acooly.zaodao.openapi.message.dto;

import com.acooly.module.point.enums.PointTradeType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.InOutType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钙片
 * @author xiaohong
 * @create 2017-11-29 19:37
 **/
@Data
public class PointTradeInfoDto implements Serializable {

    @OpenApiField(desc = "钙片明细ID", constraint = "钙片明细ID")
    private Long pointTradeId;

    @OpenApiField(desc = "钙片交易操作", constraint = "钙片交易操作", demo = "产生,冻结")
    private PointTradeType tradeType;

    @OpenApiField(desc = "钙片变更数量", constraint = "钙片变更数量", demo = "2")
    private Long amount;

    @OpenApiField(desc = "收入支出", constraint = "收入支出", demo = "in=收入,out=支出")
    private InOutType inOutType;

    @OpenApiField(desc = "创建时间", constraint = "创建时间", demo = "2017-11-29 12:12:30")
    private Date createTime;

    @OpenApiField(desc = "钙片描述", constraint = "钙片描述", demo = "注册赠送积分")
    private String busiTypeText;
}
