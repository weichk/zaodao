package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.GuideMessageCusDao;
import com.acooly.zaodao.platform.dao.manage.GuideMessageDao;
import com.acooly.zaodao.platform.dto.GuideMessageDto;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;
import lombok.extern.slf4j.Slf4j;

/**
 * 导游详情
 *
 * @author xiaohong
 * @create 2017-10-31 17:14
 **/
@Slf4j
public class GuideMessageDaoImpl extends AbstractJdbcTemplateDao implements GuideMessageCusDao {
    /**
     * 获取导游详情动态列表
     * @param guideMessageListOrder
     * @return
     */
    @Override
    public PageInfo<GuideMessageDto> getPageGuideMessageList(GuideMessageListOrder guideMessageListOrder) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT a.id AS guidMessageId,a.read_status AS readStatus,a.title,a.message_type AS messageType,a.message_name AS messageName,a.content,a.content_bom_id AS contentBomId,");
        sql.append(" a.create_time AS createTime,b.user_id AS userId,b.user_name AS userName, b.real_Name AS realName,b.head_img AS headImg");
        sql.append(" FROM zd_guide_message a JOIN zd_customer b  ON (a.user_id=b.user_id)");
        if(guideMessageListOrder.getMessageFlag() == 1) {
            sql.append(String.format(" WHERE a.content_user_id='%s' ", guideMessageListOrder.getUserId()));
        }else{
            sql.append(String.format(" WHERE a.user_id='%s' ", guideMessageListOrder.getUserId()));
        }
        sql.append(" ORDER BY a.create_time DESC");

        PageInfo<GuideMessageDto> pageInfo = query(guideMessageListOrder.getPageInfo(), sql.toString(), GuideMessageDto.class);

        return pageInfo;
    }
    /* 2017-11-10 xh modify
    @Override
    public PageInfo<GuideMessageDto> getPageGuideMessageList(GuideMessageListOrder guideMessageListOrder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (");
        sql.append("SELECT a.id AS guidMessageId,a.read_status AS readStatus,a.title,a.message_type AS messageType,a.message_name AS messageName,a.content,a.content_bom_id AS contentBomId,");
        sql.append(" a.create_time AS createTime,b.user_id AS userId,b.user_name AS userName, b.real_Name AS realName,b.head_img AS headImg");
        sql.append(" FROM zd_guide_message a JOIN zd_customer b ");
        if(guideMessageListOrder.getMessageFlag() == 1) {
            sql.append(String.format(" ON (a.user_id=b.user_id) WHERE a.content_bom_id='%s' ", guideMessageListOrder.getUserId()));
            sql.append(" UNION ");
            sql.append(" SELECT a.id AS guidMessageId,a.read_status AS readStatus,a.title,");
            sql.append(" a.message_type AS messageType,a.message_name AS messageName,a.content,");
            sql.append(" a.content_bom_id AS contentBomId,");
            sql.append(" a.create_time AS createTime,c.user_id AS userId,c.user_name AS userName,");
            sql.append(" c.real_Name AS realName,c.head_img AS headImg");
            sql.append(" FROM zd_guide_message a ");
            sql.append(" JOIN zd_travel_voice b ON(a.content_bom_id=b.id)");
            sql.append(" JOIN zd_customer c ON (a.user_id=c.user_id)");
            sql.append(String.format(" WHERE b.user_id='%s'", guideMessageListOrder.getUserId()));
            sql.append("UNION ");
            sql.append(" SELECT a.id AS guidMessageId,a.read_status AS readStatus,a.title,");
            sql.append(" a.message_type AS messageType,a.message_name AS messageName,a.content,");
            sql.append(" a.content_bom_id AS contentBomId,");
            sql.append(" a.create_time AS createTime,c.user_id AS userId,c.user_name AS userName, ");
            sql.append(" c.real_Name AS realName,c.head_img AS headImg");
            sql.append(" FROM zd_guide_message a ");
            sql.append(" JOIN zd_course b ON(a.content_bom_id=b.id)");
            sql.append(" JOIN zd_customer c ON (a.user_id=c.user_id)");
            sql.append(String.format(" WHERE b.user_id='%s'", guideMessageListOrder.getUserId()));
        }else{
            sql.append(String.format(" ON (a.user_id=b.user_id) WHERE a.user_id='%s' ", guideMessageListOrder.getUserId()));
        }
        sql.append(") ta ORDER BY ta.createTime DESC");

        log.info(sql.toString());
        PageInfo<GuideMessageDto> pageInfo = query(guideMessageListOrder.getPageInfo(), sql.toString(), GuideMessageDto.class);

        return pageInfo;
    }*/
}
