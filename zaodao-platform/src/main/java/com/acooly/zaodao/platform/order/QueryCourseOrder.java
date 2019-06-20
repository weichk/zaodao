package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.LessThanCurrentEnum;
import com.acooly.zaodao.platform.enums.PublishStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 课程查询参数
 * Created by xiaohong on 2017/9/25.
 */
@Data
public class QueryCourseOrder extends PageOrder {
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
