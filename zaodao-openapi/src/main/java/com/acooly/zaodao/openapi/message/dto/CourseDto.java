package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import lombok.Data;
import org.apache.xpath.operations.Bool;

import java.io.Serializable;
import java.util.Date;

/** Created by xiyang on 2017/9/21. */
@Data
public class CourseDto implements Serializable {

    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;

    @OpenApiField(desc = "课程标题", constraint = "课程标题")
    private String courseTitle;

    @OpenApiField(desc = "课程简介", constraint = "课程简介")
    private String courseIntro;

    @OpenApiField(desc = "发布时间", constraint = "发布时间")
    private Date publishTime;

    @OpenApiField(desc = "真实姓名", constraint = "真实姓名")
    private String realName;

    @OpenApiField(desc = "导游封面图", constraint = "导游封面图")
    private String coverImg;

    @OpenApiField(desc = "课程封面图", constraint = "课程封面图")
    private String courseImg;

    @OpenApiField(desc = "已购买课程ID", constraint = "已购买课程ID")
    private Long coursePurchaseId;

    @OpenApiField(desc = "已报名人数", constraint = "已报名人数")
    private Long userCount;

    @OpenApiField(desc = "课程总时长", constraint = "课程总时长")
    private Long sumRecordTime;

    @OpenApiField(desc = "课程总时长文本", constraint = "课程总时长文本(约2分钟)")
    private String sumRecordTimeText;

    @OpenApiField(desc = "课程报名截止时间", constraint = "课程报名截止时间")
    private Date endlineTime;

    @OpenApiField(desc = "课程时间", constraint = "课程时间")
    private Date courseTime;

    @OpenApiField(desc = "服务器时间", constraint = "服务器时间")
    private Date systemTime;

    @OpenApiField(desc = "课程类型", constraint = "课程类型(报名课程,正式课程)")
    private CourseType courseType;

    @OpenApiField(desc = "课程标签", constraint = "课程标签")
    private String tagContent;

    @OpenApiField(desc = "是否已购买课程", constraint = "是否已购买课程(已报名)")
    private Boolean isPurchased;

    @OpenApiField(desc = "课程状态", constraint = "课程状态")
    private CourseStatusEnum courseStatus;

    @OpenApiField(desc = "小节数", constraint = "小节数")
    private Integer recordCount;

    @OpenApiField(desc = "上传时间", constraint = "上传时间")
    private Date createTime;

    @OpenApiField(desc = "课程资源类型", constraint = "课程资源类型{0-音频;1-视频}")
    private Integer mediaType;
}
