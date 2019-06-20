/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;
import com.acooly.zaodao.platform.entity.LeaveMessage;

import java.util.List;

/**
 * 留言表 Service接口
 * <p>
 * Date: 2017-06-01 15:14:52
 *
 * @author zhike
 */
public interface LeaveMessageService extends EntityService<LeaveMessage> {

	List<LeaveMessage> findByUserId(String userId);

	/**
	 * 分页获取导游留言消息列表
	 *
	 * @param userId
	 * @return
	 */
	PageInfo<LeaveMessageDto> getPageLeaveMessages(Integer currentPage, Integer countOfCurrentPage, String userId);

	/**
	 * 导游详情页面获取留言列表
	 * 
	 *
	 * @param pageInfo
	 * @param userId
	 * @return
	 */
	PageInfo<LeaveMessageDto> getLeaveMsgByGuideUserId(PageInfo<LeaveMessageDto> pageInfo, String userId);

	/**
	 * 获取会员未读留言消息列表
	 * @param userId
	 * @return
	 */
	List<LeaveMessage> getNotReadEntitysByUserId(String userId);

	/**
	 * 更新状态为已读
	 * @param userId
	 */
	void updateEntityReadStatus(String userId);
}
