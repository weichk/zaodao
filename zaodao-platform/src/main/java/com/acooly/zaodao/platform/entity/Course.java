/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 */
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * zd_course Entity
 *
 * @author zhike Date: 2017-09-22 13:55:30
 */
@Entity
@Getter
@Setter
@Table(name = "zd_course")
public class Course extends AbstractEntity {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /** 会员唯一标识 */
  @NotEmpty
  @Size(max = 64)
  private String userId;

  /** 课程价格 */
  private Long coursePrice;

  /** 课程名称 */
  @NotEmpty
  @Size(max = 255)
  private String courseTitle;

  /** 课程简介 */
  @NotEmpty
  private String courseIntro;

  /** 课程状态: */
  @Enumerated(EnumType.STRING)
  @NotNull
  private CourseStatusEnum courseStatus;

  /** 发布时间: */
  private Date publishTime;

  /**
   * 标签内容(格式：经典,精品)
   */
  public String tagContent;

  /**
   * 截止时间
   */
  private Date endlineTime;

  /**
   * 课程时间
   */
  private Date courseTime;

  /**
   * 课程总时长
   */
  private Long sumRecordTime;

  /**
   * 已报名人数
   */
  private Long userCount = 0L;

  /** 课程封面图 */
  private String courseImg;

  /**
   * 课程备注
   */
  private String courseDesc;

  /**
   * 课程类型
   */
  @Enumerated(EnumType.STRING)
  public CourseType courseType;

  /**
   * 讲师
   */
  @Transient
  private String realName;

  /**
   * 导游封面图
   */
  @Transient
  private String coverImg;

  /**
   * 课程源类型
   */
  @Enumerated(EnumType.STRING)
  private CourseType sourceCourseType;
}
