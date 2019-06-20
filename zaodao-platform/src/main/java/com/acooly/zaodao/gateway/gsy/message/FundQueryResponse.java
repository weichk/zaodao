package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.dto.FundInfoDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询充提订单(单笔)
 *
 * @author xiaohong
 * @create 2018-06-07 9:52
 **/
@Getter
@Setter
public class FundQueryResponse extends ResponseBase {
    /**
     * 资金信息
     */
    private FundInfoDto fundInfo;
}
