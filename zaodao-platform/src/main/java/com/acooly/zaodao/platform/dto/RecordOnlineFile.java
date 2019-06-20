package com.acooly.zaodao.platform.dto;

import com.acooly.module.ofile.domain.OnlineFile;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 课程文件
 *
 * @author xiaohong
 * @create 2018-05-24 9:43
 **/
@Data
public class RecordOnlineFile extends OnlineFile {
    /**
     * 音频时间
     */
    private long recordTime;

    /**
     * 音频标题
     */
    private String recordTitle;
}
