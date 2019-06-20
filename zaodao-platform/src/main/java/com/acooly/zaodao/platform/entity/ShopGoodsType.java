/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品分类 Entity
 *
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Entity
@Table(name = "zd_shop_goods_type")
public class ShopGoodsType extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 父ID */
    private Long parentId;

	/** 编码 */
	@Size(max=32)
    private String code;

	/** 名称 */
	@Size(max=32)
    private String name;

	/** 排序时间 */
    private Long sortTime;

	/** 备注 */
	@Size(max=128)
    private String comments;

	@Transient
	private List<ShopGoodsType> children = new LinkedList<>();

	@Transient
	private List<ShopGoods> shopGoodsList = new LinkedList<>();

	public List<ShopGoodsType> getChildren() {
		return children;
	}

	public void setChildren(List<ShopGoodsType> children) {
		this.children = children;
	}

	public void addChild(ShopGoodsType node) {
		this.children.add(node);
	}

	public List<ShopGoods> getShopGoodsList() {
		return shopGoodsList;
	}

	public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
		this.shopGoodsList = shopGoodsList;
	}

	public Long getParentId(){
		return this.parentId;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public Long getSortTime(){
		return this.sortTime;
	}
	
	public void setSortTime(Long sortTime){
		this.sortTime = sortTime;
	}

	public String getComments(){
		return this.comments;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static class NodeComparator implements Comparator<ShopGoodsType> {
		@Override
		public int compare(ShopGoodsType node1, ShopGoodsType node2) {
			long orderTime1 = node1.getSortTime();
			long orderTime2 = node2.getSortTime();
			return orderTime1 > orderTime2 ? -1 : (orderTime1 == orderTime2 ? 0 : 1);
		}
	}


}
