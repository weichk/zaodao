package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.CourseType;
import lombok.Data;

import java.util.Date;

/**
 * Created by okobboko on 2017/10/13.
 */
@Data
public class CourseDto {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 课程Id
     */
    private Long courseId;

    /**
     * 课程标题
     */
    private String courseTitle;

    /**
     * 课程简介
     */
    private String courseIntro;

    /**
     * 创建时间
     */
    private Date publishTime;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 已购买课程ID
     */
    private Long coursePurchaseId;

    /**
     * 报名人数
     */
    private Long userCount;

    /**
     * 课程总时长
     */
    private Long sumRecordTime;

    /**
     * 课程报名截止时间
     */
    private Date endlineTime;

    /**
     * 课程时间
     */
    private Date courseTime;


    /** 课程类型: */
    private CourseType courseType;

    /**
     * 课程封面
     */
    private String courseImg;
}
