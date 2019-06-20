/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.LeaveMessageDao;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;
import com.acooly.zaodao.platform.entity.LeaveMessage;
import com.acooly.zaodao.platform.entity.LeaveMessageReply;
import com.acooly.zaodao.platform.service.manage.LeaveMessageReplyService;
import com.acooly.zaodao.platform.service.manage.LeaveMessageService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 留言表 Service实现
 * <p>
 * Date: 2017-06-01 15:14:52
 *
 * @author zhike
 */
@Service("leaveMessageService")
public class LeaveMessageServiceImpl extends EntityServiceImpl<LeaveMessage, LeaveMessageDao>
        implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private LeaveMessageReplyService leaveMessageReplyService;

    @Override
    public List<LeaveMessage> findByUserId(String userId) {
        return leaveMessageDao.findByUserId(userId);
    }

    @Override
    public PageInfo<LeaveMessageDto> getPageLeaveMessages(Integer currentPage, Integer countOfCurrentPage,
                                                          String userId) {
        PageInfo<LeaveMessageDto> pageInfo = getEntityDao().getPageLeaveMessages(getMyPageInfo(currentPage, countOfCurrentPage), userId);
        for (LeaveMessageDto LeaveMessageDto : pageInfo.getPageResults()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_messageId", LeaveMessageDto.getMessageId());
            List<LeaveMessageReply> leaveMessageReplies = leaveMessageReplyService.query(map, null);
            LeaveMessageDto.setLeaveMessageReplys(leaveMessageReplies);
        }
        return pageInfo;
    }

    @Override
    public PageInfo<LeaveMessageDto> getLeaveMsgByGuideUserId(PageInfo<LeaveMessageDto> pageInfo, String userId) {
        PageInfo<LeaveMessageDto> resultPage = leaveMessageDao.getLeaveMsgByGuideUserId(pageInfo, userId);
        for (LeaveMessageDto LeaveMessageDto : resultPage.getPageResults()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_messageId", LeaveMessageDto.getMessageId());
            List<LeaveMessageReply> leaveMessageReplies = leaveMessageReplyService.query(map, null);
            LeaveMessageDto.setLeaveMessageReplys(leaveMessageReplies);
        }
        return resultPage;
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<LeaveMessageDto> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<LeaveMessageDto> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    @Override
    public List<LeaveMessage> getNotReadEntitysByUserId(String userId) {
        return getEntityDao().getNotReadEntitysByUserId(userId);
    }

    @Override
    public void updateEntityReadStatus(String userId) {
        getEntityDao().updateEntityReadStatus(userId);
    }
}
