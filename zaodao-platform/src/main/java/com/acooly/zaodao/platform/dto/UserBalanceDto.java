package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaohong
 * @create 2017-11-29 17:03
 **/
@Data
public class UserBalanceDto implements Serializable{
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 余额
     */
    private Long balance;
}
