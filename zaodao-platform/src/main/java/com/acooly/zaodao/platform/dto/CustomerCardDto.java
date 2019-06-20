package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaohong
 * @create 2017-11-24 19:19
 **/
@Data
public class CustomerCardDto implements Serializable {
    /**
     * ID
     */
    private Long customerCardId;

    /** 用户手机号码 */
    private String mobileNo;

    /** 绑卡号 */
    private String cardNo;

    /**
     * 绑卡id(签约流水号)
     */
    private String bindId;

    /** 绑卡中文名称 */
    private String cardName;

    /**
     * 银行code
     */
    private String bankCode;
    /**
     * 银行卡图片路径
     */
    private String bankImageUrl;
}
