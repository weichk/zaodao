package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.dto.FundInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 查询充提订单(单笔)
 *
 * @author xiaohong
 * @create 2018-06-07 9:50
 **/
@Getter
@Setter
public class FundQueryRequest extends RequestBase {
    /**
     * 原商户交易订单号
     */
    private String origMerchOrdeNo;
}
