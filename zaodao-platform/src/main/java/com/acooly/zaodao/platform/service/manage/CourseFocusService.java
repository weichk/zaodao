/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.entity.CourseFocus;
import com.acooly.zaodao.platform.order.CourseFocusAddOrder;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;
import com.acooly.zaodao.platform.result.CourseFocusResult;

/**
 * zd_course_focus Service接口
 *
 * Date: 2017-10-31 11:17:56
 * @author zhike
 *
 */
public interface CourseFocusService extends EntityService<CourseFocus> {

    /**
     * 添加课程关注记录
     * @param courseFocusAddOrder
     * @return
     */
    CourseFocusResult addCourseFocus(CourseFocusAddOrder courseFocusAddOrder);

    /**
     * 获取列表
     * @param courseFocusListOrder
     * @return
     */
    PageResult<CourseFocusDto> getCourseFocusList(CourseFocusListOrder courseFocusListOrder);

    /**
     * 获取课程关注
     * @param userId
     * @param courseId
     * @return
     */
    CourseFocus getByUserIdAndFocusCourseId(String userId, Long courseId);

    /**
     * 获取课程被关注数量
     * @param courseId
     * @return
     */
    Long getFocusCountByFocusCourseId(Long courseId);
}
