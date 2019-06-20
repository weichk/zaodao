/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * 商品详情 Entity
 *
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Entity
@Table(name = "zd_shop_goods_detail")
public class ShopGoodsDetail extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 标题 */
	@Size(max=32)
    private String title;

	/** 内容 */
    private String body;

    private Long goodsId;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
//	@JoinColumn(name = "goods_id")
	@Transient
	private ShopGoods shopGoods;

	public ShopGoods getShopGoods() {
		return shopGoods;
	}

	public void setShopGoods(ShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	public String getBody(){
		return this.body;
	}
	
	public void setBody(String body){
		this.body = body;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
}
