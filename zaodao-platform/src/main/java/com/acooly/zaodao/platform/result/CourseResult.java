package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.dto.CourseRecordInfoDto;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/** 课程信息 Created by xiaohong on 2017/9/25. */
@Data
public class CourseResult extends ResultBase {
  /** 会员唯一标识 */
  private String userId;

  /** 课程价格 */
  private Long coursePrice;

  /** 课程id */
  private Long courseId;

  /** 课程名称 */
  private String courseTitle;

  /** 课程简介 */
  private String courseIntro;

  /** 课程状态: */
  private CourseStatusEnum courseStatus;

  /** 讲课人姓名 */
  private String realName;

  /** 讲课人头像 */
  private String headImg;

  /** 讲课人个人简介 */
  private String introduceMyself;

  /** 课程音频 */
  private List<CourseRecordInfoDto> recordList = Lists.newArrayList();

/** 是否购买标识(0-未购买;1-已购买)*/
  private int purchaseFlag;

  /**
   * 是否已关注 0-未关注; 1-已关注
   */
  private Integer focusFlag;

  /**
   * 关注数量
   */
  private Long focusCount;

  /**
   * 打赏数量
   */
  private Long rewardCount;

  /**
   * 课程已报名人数
   */
  private Long userCount;

  /**
   * 课程总时长(s)
   */
  private Long sumRecordTime;

  /**
   * 课程时间
   */
  private Date courseTime;

  /**
   * 报名截止时间
   */
  private Date endlineTime;

  /**
   * 报名人员头像(最多6名): xxx.jpg|yyy.jpg
   */
  private String userHeadImgUrl;

  /**
   * 课程封面
   */
  private String courseImg;

  /**
   * 发布时间
   */
  private Date createTime;

  /**
   * 小节数
   */
  private Integer recordCount;
}
