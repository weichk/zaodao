package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;

/**
 * 课程列表
 *
 * @author xiaohong
 * @create 2017-10-31 12:08
 **/
public interface CourseFocusCusDao {

    /**
     * 获取关注课程分页列表
     * @param courseFocusListOrder
     * @return
     */
    PageInfo<CourseFocusDto> getPageCourseFocusList(CourseFocusListOrder courseFocusListOrder);
}
