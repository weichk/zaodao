package com.acooly.zaodao.platform.service.platform;


import com.acooly.zaodao.platform.entity.Customer;

/**
 * 积分
 * 
 */
public interface PointAmountService {

	/**
	 * 积分兑换
	 * 
	 * @param goodsId
	 * @param quantity
	 * @param provName
	 * @param cityName
	 * @param distName
	 * @param address
	 * @param comments
	 * @param customer
	 */
	void pointPayment(String goodsId, String quantity, String provName, String cityName, String distName,
                      String address, String comments, String realName, String mobileNo, Customer customer);
}
