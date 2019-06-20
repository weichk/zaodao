/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 16:40 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.service;

import com.acooly.zaodao.gateway.gsy.message.*;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface GsyBusinessService {


    /**
     * 会员信息查询
     *
     * @param request
     * @return
     */
    @Deprecated
    CustomerQueryResponse customerQuery(CustomerQueryRequest request);

    /**
     * 会员注册
     *
     * @param request
     * @return
     */
    @Deprecated
    CustomerRegisterResponse customerRegister(CustomerRegisterRequest request);

    /**
     * 实名绑卡
     *
     * @param request
     * @return
     */
    SignCardResponse signCard(SignCardRequest request);

    /**
     * 银行卡解绑
     * @param request
     * @return
     */
    UnSignCardResponse unSignCard(UnSignCardRequest request);
}
