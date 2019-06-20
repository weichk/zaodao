/*
 * 修订记录:
 * zhike@yiji.com 2017-08-10 16:17 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.RequestBase;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class UnSignCardRequest extends RequestBase {
    @Length(max = 32)
    @NotEmpty(message = "用户ID不能为空")
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "2016070809542815088")
    private String userId;

    @Length(max = 64)
    @NotEmpty(message = "绑卡ID不能为空")
    @OpenApiField(desc = "绑卡ID", constraint = "绑卡ID", demo = "2159485615616")
    private String bindId;
}
