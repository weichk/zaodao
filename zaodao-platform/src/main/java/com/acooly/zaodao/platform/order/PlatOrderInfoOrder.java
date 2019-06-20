package com.acooly.zaodao.platform.order;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.utils.Dates;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.exception.ApiServiceException;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderSubTradeTypeEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.acooly.zaodao.platform.enums.ReservationType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
public class PlatOrderInfoOrder extends OrderBase {
    @OpenApiField(desc = "订单号", constraint = "订单号(有则修改,无则添加)")
    private String platOrderNo;

    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "开始时间", constraint = "开始时间")
    private Date startTime;

    @NotNull
    @OpenApiField(desc = "结束时间", constraint = "结束时间")
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

    @OpenApiField(desc = "旅行社ID(预约类型为旅行社预约时有值)", constraint = "旅行社ID")
    private Long travelAgencyId;

    @OpenApiField(desc = "预约类型", constraint = "预约类型")
    private ReservationType reservationType;

    @Override
    public void check() throws BusinessException {
        super.check();
        if(startTime.getTime() > endTime.getTime()) {
            throw new BusinessException(ResultCode.PARAMETER_ERROR, "开始时间应小于结束时间");
        }
        if(userId.equals(guideUserId)){
            throw new BusinessException(ResultCode.PARAMETER_ERROR, "导游不能给自己下单");
        }
        if(reservationType == ReservationType.agency && travelAgencyId == null){
            throw new BusinessException(ResultCode.PARAMETER_ERROR, "旅行社不能为空");
        }
    }
}
