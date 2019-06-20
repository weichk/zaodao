package com.acooly.zaodao.platform.service.manage.impl;


import com.acooly.zaodao.common.utils.Dates;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.enums.GoodsDeliveryType;
import com.acooly.zaodao.platform.enums.ShopOrderStatus;
import com.acooly.zaodao.platform.service.manage.GoodsDeliveryService;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import com.acooly.zaodao.platform.service.manage.ShopOrderInfoService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("goodsDeliveryServic")
public class GoodsDeliveryServiceImpl implements GoodsDeliveryService {

	@Autowired
	private ShopOrderInfoService shopOrderInfoService;
	@Autowired
	private ShopGoodsService shopGoodsService;
//	@Autowired
//	private SmsSendService smsSendService;
//	@Autowired
//	private SmsTemplateService smsTemplateService;

	@Transactional
	@Override
	public void delivery(String orderNo) {
		ShopOrderInfo orderInfo = shopOrderInfoService.findByOrderNo(orderNo);
		ShopGoods goods = shopGoodsService.get(orderInfo.getGoodsId());
		Map<String, Object> map = Maps.newHashMap();
		map.put("userName", orderInfo.getUserName());
		map.put("goodsName", orderInfo.getGoodsName());
		map.put("orderNo", orderInfo.getOrderNo());
		map.put("validityDate",
				goods.getValidityDate() != null ? Dates.format(goods.getValidityDate(), Dates.CHINESE_DATE_FORMAT_LINE)
						: null);
		String verifyCode = RandomStringUtils.random(6, "0123456789");
		map.put("verifyCode", verifyCode);
		orderInfo.setVerifyCode(verifyCode);
		if (orderInfo.getDeliveryType() == GoodsDeliveryType.Virtual.getCode()) {
			orderInfo.setStatus(ShopOrderStatus.Delivering.getCode());
		}
		shopOrderInfoService.save(orderInfo);
//		smsSendService.asyncSend(orderInfo.getMobileNo(), smsTemplateService.getMessage(SmsTemplateKeys.VOUCHER_DELIVERY, map));
	}

	@Override
	public void verifyDelivery(String orderNo, String verifyCode) {
		ShopOrderInfo orderInfo = shopOrderInfoService.findByOrderNo(orderNo);
		if (orderInfo == null) {
			throw new RuntimeException("订单不存在");
		}

//		if (!StringUtils.equals(orderInfo.getVerifyCode(), verifyCode)) {
//			throw new RuntimeException("验证码错误");
//		}

	}

}
