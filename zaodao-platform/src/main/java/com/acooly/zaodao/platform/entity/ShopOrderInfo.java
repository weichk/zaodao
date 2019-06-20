/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 商城订单信息 Entity
 *
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Entity
@Table(name = "zd_shop_order_info")
public class ShopOrderInfo extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 订单号 */
	@Size(max=32)
    private String orderNo;

	/** 商品名称 */
	@NotEmpty
	@Size(max=64)
    private String goodsName;

	/** 商品id */
    private Long goodsId;

	/** 商品数量 */
	@NotNull
    private Integer quantity;

	/** 订单金额 */
    private Long amount;

	/** 销售价格 */
	@NotNull
    private Long sellAmount = 0l;

	/** 供货商名称 */
	@NotEmpty
	@Size(max=64)
    private String supplier;

	/** 供货商ID */
	@NotNull
    private Long supplierId;

	/** 客户ID */
    private Long customerId;

	/** 客户用户名 */
	@NotEmpty
	@Size(max=32)
    private String userName;

	/** 姓名 */
	@Size(max=32)
    private String realName;

	/** 省 */
	@NotEmpty
	@Size(max=16)
    private String provName;

	/** 市 */
	@NotEmpty
	@Size(max=16)
    private String cityName;

	/** 区 */
	@NotEmpty
	@Size(max=16)
    private String distName;

	/** 送货地址 */
	@NotEmpty
	@Size(max=128)
    private String address;

	/** 手机号码 */
	@NotEmpty
	@Size(max=32)
    private String mobileNo;

	/** 备注 */
	@NotEmpty
	@Size(max=128)
    private String comments;

	/** 收货验证码 */
	@NotEmpty
	@Size(max=16)
    private String verifyCode;

	/** 投递方式 */
	@NotNull
    private Integer deliveryType;

	/** 状态 */
	@NotNull
    private Integer status;



	


	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getGoodsName(){
		return this.goodsName;
	}
	
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}

	public Long getGoodsId(){
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}

	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public Long getAmount(){
		return this.amount;
	}
	
	public void setAmount(Long amount){
		this.amount = amount;
	}

	public Long getSellAmount(){
		return this.sellAmount;
	}
	
	public void setSellAmount(Long sellAmount){
		this.sellAmount = sellAmount;
	}

	public String getSupplier(){
		return this.supplier;
	}
	
	public void setSupplier(String supplier){
		this.supplier = supplier;
	}

	public Long getSupplierId(){
		return this.supplierId;
	}
	
	public void setSupplierId(Long supplierId){
		this.supplierId = supplierId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}
	
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getRealName(){
		return this.realName;
	}
	
	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getProvName(){
		return this.provName;
	}
	
	public void setProvName(String provName){
		this.provName = provName;
	}

	public String getCityName(){
		return this.cityName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getDistName(){
		return this.distName;
	}
	
	public void setDistName(String distName){
		this.distName = distName;
	}

	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

	public String getMobileNo(){
		return this.mobileNo;
	}
	
	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getComments(){
		return this.comments;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}

	public String getVerifyCode(){
		return this.verifyCode;
	}
	
	public void setVerifyCode(String verifyCode){
		this.verifyCode = verifyCode;
	}

	public Integer getDeliveryType(){
		return this.deliveryType;
	}
	
	public void setDeliveryType(Integer deliveryType){
		this.deliveryType = deliveryType;
	}

	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}



}
