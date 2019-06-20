package com.acooly.zaodao.platform.service.manage;

/**
 * 商品投递实现
 * 
 * @author zhangpu
 *
 */
public interface GoodsDeliveryService {

	void delivery(String orderNo);

	void verifyDelivery(String orderNo, String verifyCode);

}
