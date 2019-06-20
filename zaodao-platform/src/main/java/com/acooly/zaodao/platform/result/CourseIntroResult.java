package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import lombok.Getter;
import lombok.Setter;

/** Created by xiyang on 2017/9/22. */
@Getter
@Setter
public class CourseIntroResult extends ResultBase {

  /** 会员唯一标识 */
  private String userId;

  /** 课程id */
  private Long courseId;

  /** 课程名称 */
  private String courseTitle;

  /** 课程简介 */
  private String courseIntro;

  /** 课程状态: */
  private CourseStatusEnum courseStatus;
}
