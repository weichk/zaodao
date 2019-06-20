/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:30 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.ResponseBase;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class CustomerRegisterResponse extends ResponseBase {

    /**
     * 用户ID
     **/
    private String userId;
}
