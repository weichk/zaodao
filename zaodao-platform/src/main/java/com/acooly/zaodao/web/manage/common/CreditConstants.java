package com.acooly.zaodao.web.manage.common;

import com.acooly.module.point.enums.PointTradeType;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.enums.GoodsDeliveryType;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreditConstants {

	public static final int COMMON_OK = 1;
	public static final int COMMON_NO = 0;
	public static final Map<Integer, String> COMMON_SWITCH_MAPPING = new LinkedHashMap<Integer, String>();
	static {
		COMMON_SWITCH_MAPPING.put(COMMON_OK, "有效");
		COMMON_SWITCH_MAPPING.put(COMMON_NO, "无效");
	}

	/** 实物 **/
	public static final int Physical = 1;
	/** 代金券 **/
	public static final int Virtual = 2;
	/** 红包 **/
	public static final int RedPacket = 3;
	/** 积分 **/
	public static final int Integral = 4;
	
	
	public static Map<Integer, String> allShopGoodsStatuss = Maps.newTreeMap();
	static {
		allShopGoodsStatuss.put(ShopGoods.STATUS_SALES, "上架");
		allShopGoodsStatuss.put(ShopGoods.STATUS_NO_SALES, "下架");
	}

	
	public static Map<Integer, String> allDeliveryTypes = GoodsDeliveryType.mapping();
	
	
	
	public static Map<String, String> allTradeTypes = Maps.newLinkedHashMap();
	static {
		allTradeTypes.put(PointTradeType.produce.getCode(), "产生");
		allTradeTypes.put(PointTradeType.produce.getCode(), "消费");
	}
//	public static Map<String, String> allTradeCodes = CreditTrade.mapping();
}
