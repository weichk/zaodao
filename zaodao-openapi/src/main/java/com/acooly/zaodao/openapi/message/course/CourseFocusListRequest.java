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
 * 课程关注列表请求
 *
 * @author xiaohong
 * @create 2017-10-31 15:24
 **/
@Data
@OpenApiMessage(service = "courseFocusList", type = ApiMessageType.Request)
public class CourseFocusListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户Id", constraint = "用户Id")
    private String userId;
}
