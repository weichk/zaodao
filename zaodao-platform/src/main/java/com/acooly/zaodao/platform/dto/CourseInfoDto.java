package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 *
 * @author xiaohong
 * @create 2018-05-14 9:56
 **/
@Data
public class CourseInfoDto implements Serializable {
    private Long id;

    /** 会员唯一标识 */
    private String userId;

    /** 课程价格 */
    private Long coursePrice;

    /** 课程名称 */
    private String courseTitle;

    /** 课程简介 */
    private String courseIntro;

    /** 课程状态: */
    private CourseStatusEnum courseStatus;

    /** 发布时间: */
    private Date publishTime;

    /**
     * 真实姓名/昵称
     */
    private String realName;

    /**
     * 导游封面图
     */
    private String coverImg;

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

    /**
     * 服务器时间
     */
    private Date systemTime;

    /** 课程类型: */
    private CourseType courseType;

    /**
     * 课程标签
     */
    private String tagContent;

    /**
     * 是否已购买课程(已报名)
     */
    private Boolean isPurchased;

    /**
     * 课程封面图
     */
    private String courseImg;

    /**
     * 小节数
     */
    private Integer recordCount;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 课程资源类型{0-音频;1-视频}
     */
    private Integer mediaType;

    /**
     * 课程原类型
     */
    private CourseType sourceCourseType;
}
