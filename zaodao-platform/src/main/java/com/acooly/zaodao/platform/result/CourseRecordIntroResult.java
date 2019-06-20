package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/25.
 */
@Data
public class CourseRecordIntroResult extends ResultBase {
    /**
     * 音频id
     */
    private Long recordId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 音频状态
     */
    private RecordStatusEnum recordStatus;
    /**
     * 音频标题
     */
    private String recordTitle;
    /**
     * 音频地址
     */
    private String recordUrl;
}
