package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.openapi.message.enums.ReservationType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
@OpenApiMessage(service = "platOrderAdd", type = ApiMessageType.Request)
public class PlatOrderAddRequest extends ApiRequest {

    @OpenApiField(desc = "订单号", constraint = "订单号(有则修改,无则添加)")
    private String platOrderNo;

    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "开始时间", constraint = "开始时间", demo = "2017-11-23 11:23:11")
    private Date startTime;

    @NotNull
    @OpenApiField(desc = "结束时间", constraint = "结束时间", demo = "2017-11-23 11:23:11")
    private Date endTime;

    @NotBlank
    @OpenApiField(desc = "小孩数量", constraint = "小孩数量")
    private String childrenNum;

    @NotBlank
    @OpenApiField(desc = "成人数量", constraint = "成人数量")
    private String adultNum;

    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID")
    private String guideUserId;

    @NotBlank
    @OpenApiField(desc = "联系人姓名", constraint = "联系人姓名")
    private String contactName;

    @NotBlank
    @OpenApiField(desc = "联系人手机", constraint = "联系人手机")
    private String contactPhone;

    @OpenApiField(desc = "联系人备注", constraint = "联系人备注")
    private String contactMemo;

    @OpenApiField(desc = "旅行社ID", constraint = "旅行社ID")
    private Long travelAgencyId;

    @OpenApiField(desc = "预约类型", constraint = "预约类型")
    private ReservationType reservationType;
}
