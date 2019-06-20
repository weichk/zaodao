/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:57 创建
 *
 */
package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.utils.Dates;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.exception.ApiServiceException;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "createPlatOrder", type = ApiMessageType.Request)
public class CreateOrderRequest extends ApiRequest {

    /**
     * 用户id
     */
    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id", demo = "O00117052610240611600000")
    private String userId;
    /**
     * 用户名称
     */
    @NotBlank
    @OpenApiField(desc = "用户姓名", constraint = "用户姓名", demo = "张三")
    private String realName;
    /**
     * 电话
     */
    @NotBlank
    @OpenApiField(desc = "电话号码", constraint = "电话号码", demo = "18695265221")
    private String mobileNo;
    /**
     * 开始时间
     */
    @NotBlank
    @OpenApiField(desc = "开始时间", constraint = "开始时间", demo = "2017-10-09")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank
    @OpenApiField(desc = "结束时间", constraint = "结束时间", demo = "2017-10-10")
    private String endTime;
    /**
     * 小孩数量
     */
    @OpenApiField(desc = "小孩数量", constraint = "小孩数量", demo = "1")
    private int childrenNum = 0;
    /**
     * 成人数量
     */
    @OpenApiField(desc = "成人数量", constraint = "成人数量", demo = "1")
    private int adultNum = 0;
    /**
     * 导游ID
     */
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID", demo = "O00117052610240611600000")
    private String guideUserId;

    @Override
    public void check() throws RuntimeException {
        super.check();
        try {
            Dates.parse(startTime);
        } catch (UnsupportedOperationException e) {
            throw new ApiServiceException(ResultCode.PARAMETER_ERROR, "开始时间格式错误yyyy-MM-dd");
        }

        try {
            Dates.parse(endTime);
        } catch (UnsupportedOperationException e) {
            throw new ApiServiceException(ResultCode.PARAMETER_ERROR, "结束时间格式错误yyyy-MM-dd");
        }

        if (childrenNum == 0 || adultNum == 0) {
            throw new ApiServiceException(ResultCode.PARAMETER_ERROR, "小孩数量和成人数量不能同时为空");
        }

        if (childrenNum < 0 || adultNum < 0) {
            throw new ApiServiceException(ResultCode.PARAMETER_ERROR, "小孩数量或成人数量不能为小于0");
        }
    }
}
