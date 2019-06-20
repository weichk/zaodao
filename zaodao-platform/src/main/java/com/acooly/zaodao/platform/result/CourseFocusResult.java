package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

/**
 * 课程关注
 * @author xiaohong
 * @create 2017-11-08 10:34
 **/
@Data
public class CourseFocusResult  extends ResultBase {
    /**
     * 关注数
     */
    private Long focusCount = 0l;

    /**
     * 是否已关注 0-未关注; 1-已关注
     */
    private Integer focusFlag;
}
