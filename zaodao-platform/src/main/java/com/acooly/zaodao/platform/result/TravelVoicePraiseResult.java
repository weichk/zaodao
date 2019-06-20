package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

/**
 * 用户点赞
 *
 * @author xiaohong
 * @create 2017-11-06 11:37
 **/
@Data
public class TravelVoicePraiseResult extends ResultBase {
    /**
     * 点赞数
     */
    private Long praiseCount = 0l;

    /**
     * 是否已经点过赞 0-未点赞; 1-已点赞
     */
    private Integer praiseFlag;
}
