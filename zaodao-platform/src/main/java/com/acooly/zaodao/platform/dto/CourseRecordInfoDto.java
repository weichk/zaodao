package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**音频
 * Created by xiaohong on 2017/9/25.
 */
@Data
public class CourseRecordInfoDto implements Serializable {
    /**
     * 音频ID
     */
    private Long recordId;
    /**
     * 用户id
     */
    private String userId;

    /**
     *课程Id
     */
    private Long courseId;

    /**
     * 音频标题
     */
    private String recordTitle;

    /**
     * 音频地址
     */
    private String recordUrl;

    /**
     * 音频时长
     */
    private Long recordTime;
}
