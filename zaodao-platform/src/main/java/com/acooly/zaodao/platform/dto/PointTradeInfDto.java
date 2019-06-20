package com.acooly.zaodao.platform.dto;

import com.acooly.module.point.enums.PointTradeType;
import com.acooly.zaodao.platform.enums.InOutType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钙片
 *
 * @author xiaohong
 * @create 2017-11-29 19:39
 **/
@Data
public class PointTradeInfDto implements Serializable {
    /**
     * 钙片明细ID
     */
    private Long pointTradeId;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 交易后钙片余额
     */
    private Long balance;

    /**
     * 钙片交易操作
     */
    private PointTradeType tradeType;
    /**
     * 钙片交易数额
     */
    private Long amount;

    /**
     * 收入支出
     */
    private InOutType inOutType;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 积分描述
     */
    private String busiTypeText;
}
