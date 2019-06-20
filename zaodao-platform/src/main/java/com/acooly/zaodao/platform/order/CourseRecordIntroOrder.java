package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohong on 2017/9/25.
 */
@Data
public class CourseRecordIntroOrder extends OrderBase {
    /**
     * 音频id
     */
    @NotNull
    private Long recordId;
    /**
     * 用户id
     */
    @NotBlank
    private String userId;
    /**
     * 课程id
     */
    @NotNull
    private Long courseId;
    /**
     * 音频状态
     */
    @NotNull
    private RecordStatusEnum recordStatus;
    /**
     * 音频标题
     */
    @NotBlank
    private String recordTitle;
    /**
     * 音频地址
     */
    @NotBlank
    private String recordUrl;

    /**
     * 音频时长
     */
    @NotNull
    private Long recordTime;
}
