/*
 * 修订记录:
 * zhike@yiji.com 2017-06-13 15:05 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.LeaveMessageCusDao;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class LeaveMessageDaoImpl extends AbstractJdbcTemplateDao implements LeaveMessageCusDao {

	@Override
	public PageInfo<LeaveMessageDto> getPageLeaveMessages(PageInfo<LeaveMessageDto> pageInfo, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
		        "SELECT zlm.id messageId,zlm.leave_id leaveCusUserId,zc.real_name realName,zc.mobile_no mobileNo,zc.head_img headImg,zlm.create_time createTime,zlm.content content");
		sql.append(" FROM zd_leave_message zlm");
		sql.append(" INNER JOIN zd_customer zc ON zlm.leave_id = zc.user_id");
		sql.append(" WHERE zlm.user_id = '" + userId + "'");
		PageInfo<LeaveMessageDto> result = query(pageInfo, sql.toString(), LeaveMessageDto.class);
		return result;
	}

	@Override
	public PageInfo<LeaveMessageDto> getLeaveMsgByGuideUserId(PageInfo<LeaveMessageDto> pageInfo, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT z.user_id,z.leave_id as leaveCusUserId,z.content,r.reply_content,z.create_time,c.head_img FROM " +
				"zd_customer "
		        + "AS c,zd_leave_message AS z,zd_leave_message_reply AS r where z.id = r.message_id AND c.user_id=z.leave_id "
		        + "and z.user_id = '" + userId + "'");
		PageInfo<LeaveMessageDto> result = query(pageInfo, sql.toString(), LeaveMessageDto.class);
		return result;
	}
}
