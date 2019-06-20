/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-17
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 红包表 Entity
 *
 * @author zhike Date: 2017-07-17 21:45:54
 */
@Entity
@Table(name = "zd_red_bag")
public class RedBag extends AbstractEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String orderNo;

    /**
     * 用户ID
     */
    @NotEmpty
    @Size(max = 64)
    private String userId;

    /**
     * 关联id
     */
    @NotNull
    private Long refId;

    /**
     * 红包类型{@link com.acooly.zaodao.platform.enums.RedBagType}
     */
    @NotEmpty
    @Size(max = 32)
    private String redBagType;

    /**
     * 红包个数
     */
    @NotNull
    private Integer totalNum;

    /**
     * 剩余个数
     */
    @NotNull
    private Integer surplusNum;

    /**
     * 金额
     */
    @NotNull
    private Long totalAmount;

    /**
     * 剩余金额
     */
    private Long surplusAmount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRedBagType() {
        return redBagType;
    }

    public void setRedBagType(String redBagType) {
        this.redBagType = redBagType;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Long surplusAmount) {
        this.surplusAmount = surplusAmount;
    }
}
