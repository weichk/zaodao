package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 课程关注响应
 *
 * @author xiaohong
 * @create 2017-10-31 15:01
 **/
@Data
@OpenApiMessage(service = "courseFocusAdd", type = ApiMessageType.Response)
public class CourseFocusAddResponse extends ApiResponse {
    @OpenApiField(desc = "关注数", constraint = "关注数")
    private Long focusCount = 0l;

    /**
     * 是否已关注 0-未关注; 1-已关注
     */
    @OpenApiField(desc = "是否已关注", constraint = "是否已关注")
    private Integer focusFlag;
}
