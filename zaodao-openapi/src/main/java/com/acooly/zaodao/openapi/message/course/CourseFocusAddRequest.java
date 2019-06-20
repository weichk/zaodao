package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.esotericsoftware.kryo.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 课程关注请求
 *
 * @author xiaohong
 * @create 2017-10-31 15:01
 **/
@Data
@OpenApiMessage(service = "courseFocusAdd", type = ApiMessageType.Request)
public class CourseFocusAddRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户Id", constraint = "用户Id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    private Long courseId;

    @NotNull
    @OpenApiField(desc = "关注标识", constraint = "关注标识", demo = "0-取消关注;1-关注")
    private Integer focusFlag;

}
