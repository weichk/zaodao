/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 14:52 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CustomerMessageCusDao;
import com.acooly.zaodao.platform.dto.MessageDto;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.order.MessageListOrder;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class CustomerMessageDaoImpl extends AbstractJdbcTemplateDao implements CustomerMessageCusDao{

    @Override
    public PageInfo<CustomerMessage> getCustomerPageEntityInfo(PageInfo<CustomerMessage> pageInfo, String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM zd_customer_message where user_id = '"+userId+"' order by create_time desc");
        PageInfo<CustomerMessage> customerMessagePageInfo = query(pageInfo,sql.toString(),CustomerMessage.class);
        return customerMessagePageInfo;
    }

    /**
     * 获取系统信息列表
     * @param messageListOrder
     * @return
     */
    @Override
    public PageInfo<MessageDto> getPageMessageList(MessageListOrder messageListOrder) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("SELECT a.id AS customerMessageId,a.read_status AS readStatus,a.message,a.message_title AS messageTitle,");
        sbSql.append("a.create_time AS createTime,b.user_id AS userId,b.user_name AS userName,");
        sbSql.append("b.real_Name AS realName,b.head_img AS headImg,a.message_type,a.order_no");
        sbSql.append(" FROM zd_customer_message a JOIN zd_customer b ON(a.user_id=b.user_id) ");
        sbSql.append(String.format(" WHERE a.user_id='%s'", messageListOrder.getUserId()));
        sbSql.append(" ORDER BY a.create_time DESC");

        PageInfo<MessageDto> messageDtoPageInfo = query(messageListOrder.getPageInfo(), sbSql.toString(), MessageDto.class);

        return messageDtoPageInfo;
    }
}

