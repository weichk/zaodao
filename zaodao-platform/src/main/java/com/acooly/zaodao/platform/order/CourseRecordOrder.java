package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohong on 2017/9/22.
 */
@Data
public class CourseRecordOrder extends OrderBase {
    /**
     * 音频ID
     */
    @NotNull
    private Long recordId;

    @NotNull
    private RecordStatusEnum recordStatus;
}
