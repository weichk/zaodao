package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.CourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.CoursePurchase;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;

/**
 * Created by okobboko on 2017/10/12.
 */
public interface CoursePurchaseCusDao  {

    /**
     * 获取已购买课程列表
     * @param order
     * @return
     */
    PageInfo<CourseDto> getPageCoursePurchaseList(QueryCoursePurchaseOrder order);
}
