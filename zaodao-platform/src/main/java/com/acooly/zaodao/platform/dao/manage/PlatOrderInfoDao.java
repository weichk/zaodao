/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 订单表 JPA Dao
 *
 * Date: 2017-05-24 23:14:32
 * 
 * @author zhike
 *
 */
public interface PlatOrderInfoDao extends EntityJpaDao<PlatOrderInfo, Long>, PlatOrderInfoCusDao {

	/**
	 * 已支付和服务中的日期都不能被约导
	 */
	@Query(value = "select * from zd_plat_order_info where tour_guide_id = ?1 and ((end_play_time >= ?2 and end_play_time <= ?3) or (start_play_time >= ?2 and start_play_time <= ?3)) and order_status in('pay','processing')", nativeQuery = true)
	List<PlatOrderInfo> findLatestOrder(String userId, String start, String end);

	/**
	 * 根据订单号查询订单
	 */
	PlatOrderInfo findByOrderNo(String orderNo);

	/**
	 * 根据订单号(唯一索引)查询订单(加锁)
	 */
	@Query(value = "select * from zd_plat_order_info where order_no=?1 for update", nativeQuery = true)
	PlatOrderInfo findByOrderNoWithLock(String orderNo);

	/**
	 * 将订单从一个状态修改到另一个状态(超过30分钟)
	 * @param fromStatus
	 * @param toStatus
	 */
	@Modifying
	@Query(value = "UPDATE zd_plat_order_info SET order_status=?2 WHERE order_status=?1 AND TIMESTAMPDIFF(MINUTE, create_time, now()) > 30", nativeQuery = true)
	void updatePlatOrder(String fromStatus, String toStatus);

	/**
	 * 七天已完成客户未确认订单(精确到分钟)
	 */
	@Query(value = "SELECT * FROM zd_plat_order_info WHERE order_status='confirming' AND TIMESTAMPDIFF(MINUTE,guide_confirm_time,NOW())>10080", nativeQuery = true)
    List<PlatOrderInfo> getConfirmingOrder();

	/**
	 * 获取我发出的未读订单数量
	 */
	@Query(value = "SELECT COUNT(1) FROM zd_plat_order_info WHERE tourist_id=?1 AND (read_status_tourist=0 OR read_status_tourist is null)", nativeQuery = true)
	Integer getUnReadMySendCount(String userId);

	/**
	 * 获取我收到的未读订单数量
	 */
	@Query(value = "SELECT COUNT(1) FROM zd_plat_order_info WHERE tour_guide_id=?1 AND (read_status_guide=0 OR read_status_guide is null)", nativeQuery = true)
	Integer getUnReadMyReceiveCount(String userId);

	/**
	 * 修改导游已读字段
	 */
	@Modifying
	@Query(value = "UPDATE zd_plat_order_info SET read_status_guide ='1' WHERE order_no=?1 AND (read_status_guide=0 OR read_status_guide is null)", nativeQuery = true)
	Integer modifyReadStatusGuide(String orderNo);

	/**
	 * 修改游客已读字段
	 */
	@Modifying
	@Query(value = "UPDATE zd_plat_order_info SET read_status_tourist ='1' WHERE order_no=?1 AND (read_status_tourist=0 OR read_status_tourist is null)", nativeQuery = true)
	Integer modifyReadStatusTourist(String orderNo);
}
