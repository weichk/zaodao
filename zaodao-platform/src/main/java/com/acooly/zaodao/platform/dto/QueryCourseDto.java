package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程列表查询(报名课程和课程管理)
 *
 * @author xiaohong
 * @create 2018-05-17 17:40
 **/
@Getter
@Setter
public class QueryCourseDto extends QueryBase {
    /**
     * 讲师ID
     */
    private String userId;

    /**
     * 讲师名称
     */
    private String userName;

    /**
     * 课程名称
     */
    private String courseTitle;

    /**
     * 课程类型
     */
    private CourseType courseType;

    /**
     * 课程状态
     */
    private CourseStatusEnum courseStatus;

    /**
     * 发布开始时间
     */
    private Date publishTimeStart;

    /**
     * 发布结束时间
     */
    private Date publishTimeEnd;

    /**
     * 创建开始时间
     */
    private Date createTimeStart;
    /**
     * 创建结束时间
     */
    private Date createTimeEnd;

    /**
     * 更新开始时间
     */
    private Date updateTimeStart;

    /**
     * 更新结束时间
     */
    private Date updateTimeEnd;
}
