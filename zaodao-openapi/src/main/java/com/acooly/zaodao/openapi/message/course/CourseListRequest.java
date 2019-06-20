package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.LessThanCurrentEnum;
import com.acooly.zaodao.platform.enums.PublishStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/** Created by xiyang on 2017/9/19. */
@Data
@OpenApiMessage(service = "courseList", type = ApiMessageType.Request)
public class CourseListRequest extends PageApiRequest {

  @OpenApiField(desc = "用户id", constraint = "用户id(查询userid上传的课程)")
  private String userId;

  @OpenApiField(desc = "App用户ID", constraint = "App用户ID(查询用户购买的课程)")
  private String appUserId;

  @OpenApiField(desc = "课程标题", constraint = "课程标题")
  private String courseTitle;

  @OpenApiField(desc = "课程内容", constraint = "课程内容")
  private String courseIntro;

  @OpenApiField(desc = "课程状态", constraint = "课程状态，不填表示查全部，首页需要传入已发布,我发布的课程列表不传")
  private CourseStatusEnum courseStatus;

  @NotNull
  @OpenApiField(desc = "是否小于当前时间", constraint = "我发布的课程传入no，其他传入yes")
  private LessThanCurrentEnum lessThanCurrent;

  @OpenApiField(desc = "关键字", constraint = "关键字")
  private String keywords;

}
