package com.acooly.zaodao.openapi.message.dto;

import lombok.Data;

/**
 * 银行列表
 *
 * @author xiaohong
 * @create 2017-11-27 10:42
 **/
@Data
public class BankInfoDto {
    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡图片路径
     */
    private String bankImageUrl;
}
