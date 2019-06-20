/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-21
 */
package com.acooly.zaodao.platform.service.manage.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.LeaveMessageReplyDao;
import com.acooly.zaodao.platform.entity.LeaveMessageReply;
import com.acooly.zaodao.platform.service.manage.LeaveMessageReplyService;

/**
 * 留言回复表 Service实现
 *
 * Date: 2017-06-21 16:42:50
 *
 * @author zhike
 *
 */
@Service("leaveMessageReplyService")
public class LeaveMessageReplyServiceImpl extends EntityServiceImpl<LeaveMessageReply, LeaveMessageReplyDao> implements LeaveMessageReplyService {

}
