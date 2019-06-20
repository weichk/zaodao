package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.CourseRecordDto;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

/**
 * Created by xiyang on 2017/9/19.
 */
@Data
@OpenApiMessage(service = "courseDetail", type = ApiMessageType.Response)
public class CourseDetailResponse extends ApiResponse {

  @OpenApiField(desc = "课程Id", constraint = "课程Id")
  private Long courseId;

  @OpenApiField(desc = "所需积分", constraint = "所需积分")
  private Long coursePrice = 0L;

  @OpenApiField(desc = "课程标题", constraint = "课程标题")
  private String courseTitle;

  @OpenApiField(desc = "课程简介", constraint = "课程简介")
  private String courseIntro;

  /** 讲课人姓名 */
  @OpenApiField(desc = "讲课人姓名", constraint = "讲课人姓名")
  private String realName;

  @OpenApiField(desc = "讲课人头像", constraint = "讲课人头像")
  private String headImg;

  @OpenApiField(desc = "讲课人个人简介", constraint = "讲课人个人简介")
  private String introduceMyself;

  @OpenApiField(desc = "课程录音", constraint = "课程录音")
  private List<CourseRecordDto> recordList = Lists.newArrayList();

  @OpenApiField(desc = "是否购买标识", constraint = "是否购买标识(0-未购买;1-已购买)")
  private int purchaseFlag;

  @OpenApiField(desc = "是否已关注", constraint = "是否已关注(0-未关注; 1-已关注)")
  private Integer focusFlag;

  @OpenApiField(desc = "关注数量", constraint = "关注数量")
  private Long focusCount;

  @OpenApiField(desc = "打赏数量", constraint = "打赏数量")
  private Long rewardCount;

  @OpenApiField(desc = "课程状态", constraint = "课程状态")
  @Enumerated(EnumType.STRING)
  private CourseStatusEnum courseStatus;

  @OpenApiField(desc = "课程已报名人数", constraint = "课程已报名人数")
  private Long userCount;

  @OpenApiField(desc = "课程总时长", constraint = "课程总时长(s)")
  private Long sumRecordTime;

  @OpenApiField(desc = "课程总时长文本", constraint = "课程总时长文本(约2分钟)")
  private String sumRecordTimeText;

  @OpenApiField(desc = "课程时间", constraint = "课程时间")
  private Date courseTime;

  @OpenApiField(desc = "报名截止时间", constraint = "报名截止时间")
  private Date endlineTime;

  @OpenApiField(desc = "报名人员头像", constraint = "报名人员头像(最多6名): xxx.jpg|yyy.jpg")
  private String userHeadImgUrl;

  @OpenApiField(desc = "课程封面", constraint = "课程封面")
  private String courseImg;

  @OpenApiField(desc = "小节数", constraint = "小节数")
  private Integer recordCount;

  @OpenApiField(desc = "上传时间", constraint = "上传时间")
  private Date createTime;

  @OpenApiField(desc = "课程讲师ID", constraint = "课程讲师ID")
  private String userId;
}
