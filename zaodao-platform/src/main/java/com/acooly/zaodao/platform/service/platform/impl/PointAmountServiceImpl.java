package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import com.acooly.zaodao.platform.service.platform.PointAmountService;
import com.acooly.zaodao.platform.service.platform.PointTradeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("pointAmountService")
@Slf4j
public class PointAmountServiceImpl implements PointAmountService {


	@Autowired
	private PointTradeInfoService pointTradeInfoService;
	@Autowired
	private ShopGoodsService shopGoodsService;

	@Override
	public void pointPayment(String goodsId, String quantity, String provName, String cityName, String distName,
                             String address, String comments, String realName, String mobileNo, Customer customer) {
		ShopGoods goods = shopGoodsService.get(Long.valueOf(goodsId));

		Date validityDate = goods.getValidityDate();
		if (validityDate != null) {
			if ((new Date()).getTime() - validityDate.getTime() > 0) {
				throw new RuntimeException("购买时间大于商品有效期");
			}
		}

		if (goods.getTotalQuantity() - goods.getSellQuantity() < Integer.valueOf(quantity)) {
			throw new RuntimeException("商品库存数量不足。");
		}

		ShopOrderInfo orderInfo = new ShopOrderInfo();
		orderInfo.setQuantity(Integer.valueOf(quantity));
		orderInfo.setAmount(goods.getCredits() * orderInfo.getQuantity());
		orderInfo.setSellAmount(goods.getMarketCredits() * orderInfo.getQuantity());
		orderInfo.setProvName(provName);
		orderInfo.setCityName(cityName);
		orderInfo.setDistName(distName);
		orderInfo.setAddress(address);
		orderInfo.setCustomerId(customer.getId());
		orderInfo.setRealName(realName);
		orderInfo.setMobileNo(mobileNo);
		orderInfo.setUserName(customer.getUserId());
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getName());
		orderInfo.setSupplier(goods.getShopSupplier().getName());
		orderInfo.setSupplierId(goods.getShopSupplier().getId());
		orderInfo.setDeliveryType(goods.getDeliveryType());
		orderInfo.setComments(comments);
		pointTradeInfoService.consumePoint(customer.getId(), orderInfo, orderInfo.getAmount());
	}

}
