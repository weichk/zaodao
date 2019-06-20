/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.web.manage.common.CreditConstants;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * 商品信息 Entity
 *
 * @author zhike
 *         Date: 2017-06-15 15:23:25
 */
@Entity
@Table(name = "zd_shop_goods")
public class ShopGoods extends AbstractEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    public static final int STATUS_SALES = 1;
    public static final int STATUS_NO_SALES = 2;

    /**
     * 编码
     */
    @Size(max = 40)
    private String code;

    /**
     * 名称
     */
    @Size(max = 64)
    private String name;

    /**
     * 品牌
     */
    @Size(max = 32)
    private String brand;

    /**
     * 简介
     */
    @Size(max = 128)
    private String subject;

    /**
     * 商品LOGO
     */
    @Size(max = 128)
    private String logo;

    /**
     * 送货方式
     */
    private Integer deliveryType = 1;

    /**
     * 积分定价
     */
    private Long credits = 0l;

    /**
     *市场定价
     */
    private Long marketCredits = 0L;

    /**
     *最大购买数量
     */
    private Integer maxBuyNum = 0;

    /**
     *推荐产品{0:不是,1:是}
     */
    private Integer isHot = 0;

    /**
     * 销售价格
     */
    private Long sellAmount;

    /**
     * 积分折扣价格
     */
    private Long discountCredits = 0l;

    /**
     * 单位
     */
    @Size(max = 16)
    private String unit;

    /**
     * 上架数量
     */
    private Integer totalQuantity = 0;

    /**
     * 销售数量
     */
    private Integer sellQuantity = 0;

    /**
     * 商品介绍内容ID
     */
    private Long contentId;

    /**
     * 有效期
     */
    private Date validityDate;

    /**
     * 状态
     */
    private Integer status = 1;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private ShopGoodsType shopGoodsType;
    /**
     * 供应商ID
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private ShopSupplier shopSupplier;

//    @OneToMany(mappedBy = "shopGoods", cascade = CascadeType.REFRESH)
    @Transient
    private ShopGoodsDetail shopGoodsDetail;

    public ShopGoodsDetail getShopGoodsDetail() {
        return shopGoodsDetail;
    }

    public void setShopGoodsDetail(ShopGoodsDetail shopGoodsDetail) {
        this.shopGoodsDetail = shopGoodsDetail;
    }

    public void setShopGoodsDetails(Set<ShopGoodsDetail> shopGoodsDetails) {
        this.shopGoodsDetail = shopGoodsDetail;
    }

    public ShopSupplier getShopSupplier() {
        return shopSupplier;
    }

    public void setShopSupplier(ShopSupplier shopSupplier) {
        this.shopSupplier = shopSupplier;
    }

    public ShopGoodsType getShopGoodsType() {
        return shopGoodsType;
    }

    public void setShopGoodsType(ShopGoodsType shopGoodsType) {
        this.shopGoodsType = shopGoodsType;
    }


    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getDeliveryType() {
        return this.deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Long getCredits() {
        return this.credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public Long getSellAmount() {
        return this.sellAmount;
    }

    public void setSellAmount(Long sellAmount) {
        if (deliveryType == CreditConstants.Integral) {
            this.sellAmount = sellAmount;
        } else {
            this.sellAmount = 0l;
        }
    }

    public Long getDiscountCredits() {
        return this.discountCredits;
    }

    public void setDiscountCredits(Long discountCredits) {
        this.discountCredits = discountCredits;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getSellQuantity() {
        return this.sellQuantity;
    }

    public void setSellQuantity(Integer sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Long getContentId() {
        return this.contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Date getValidityDate() {
        return this.validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getMarketCredits() {
        return marketCredits;
    }

    public void setMarketCredits(Long marketCredits) {
        this.marketCredits = marketCredits;
    }

    public Integer getMaxBuyNum() {
        return maxBuyNum;
    }

    public void setMaxBuyNum(Integer maxBuyNum) {
        this.maxBuyNum = maxBuyNum;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
