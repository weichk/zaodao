package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/** Created by xiyang on 2017/9/21. */
@Data
public class CourseRecordDto implements Serializable {
  @OpenApiField(desc = "音频ID", constraint = "音频ID")
  private String recordId;

  @OpenApiField(desc = "用户id", constraint = "用户id")
  private String userId;

  @OpenApiField(desc = "课程Id", constraint = "课程Id")
  private Long courseId;

  @OpenApiField(desc = "音频标题", constraint = "音频标题")
  private String recordTitle;

  @OpenApiField(desc = "音频地址", constraint = "音频地址")
  private String recordUrl;

  @OpenApiField(desc = "音频时长", constraint = "音频时长")
  private Long recordTime;
}
