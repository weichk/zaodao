package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.EvalInfoDto;
import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.ReservationType;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * 订单详情响应
 *
 * @author xiaohong
 * @create 2017-11-22 18:13
 **/
@Data
@OpenApiMessage(service = "platOrderDetail", type = ApiMessageType.Response)
public class PlatOrderDetailResponse extends ApiResponse {
    @OpenApiField(desc = "订单ID", constraint = "订单ID")
    private Long orderInfoId;

    @OpenApiField(desc = "用户头像", constraint = "用户头像")
    private String headImg;

    @OpenApiField(desc = "用户名", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "姓名", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "目的地", constraint = "目的地")
    private String destination;

    @OpenApiField(desc = "导游每日价格", constraint = "导游每日价格")
    private Money pricePerday;

    @OpenApiField(desc = "游玩开始时间", constraint = "游玩开始时间")
    private Date startPlayTime;

    @OpenApiField(desc = "游玩结束时间", constraint = "游玩结束时间")
    private Date endPlayTime;

    @OpenApiField(desc = "成人数", constraint = "成人数")
    private Integer adultCount;

    @OpenApiField(desc = "小孩数", constraint = "小孩数")
    private Integer childCount;

    @OpenApiField(desc = "订单金额", constraint = "订单金额")
    private Money orderAmount;

    @OpenApiField(desc = "订单状态枚举", constraint = "订单状态枚举")
    private PlatOrderInfoOrderStatus orderStatus;

    @OpenApiField(desc = "订单状态", constraint = "订单状态")
    private String orderStatusText;

    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @OpenApiField(desc = "下单时间", constraint = "下单时间")
    private Date createTime;

    @OpenApiField(desc = "约导天数", constraint = "约导天数")
    private Integer dayCount;

    @OpenApiField(desc = "联系人备注", constraint = "联系人备注")
    private String contactMemo;

    @OpenApiField(desc = "联系人名称", constraint = "联系人名称")
    private String contactName;

    @OpenApiField(desc = "联系人电话", constraint = "联系人电话")
    private String contactPhone;

    @OpenApiField(desc = "导游ID", constraint = "导游ID")
    private String guideUserId;

    @OpenApiField(desc = "导游名称", constraint = "导游名称")
    private String guideName;

    @OpenApiField(desc = "是否可以重新支付", constraint = "true：是，false：否")
    private boolean payAgain;

    @OpenApiField(desc = "是否可以取消订单", constraint = "true：是，false：否")
    private boolean cancel;

    @OpenApiField(desc = "是否本人发出的订单", constraint = "true：是，false：否")
    private boolean myself;

    @OpenApiField(desc = "订单评价", constraint = "订单评价(为空则可以评价)")
    private EvalInfoDto evalInfoDto;

    @OpenApiField(desc = "预约类型", constraint = "预约类型")
    private ReservationType reservationType;

    @OpenApiField(desc = "旅行社ID", constraint = "旅行社ID")
    private Long travelAgencyId;

    @OpenApiField(desc = "旅行社名称", constraint = "旅行社名称")
    private String agencyName;

    @OpenApiField(desc = "旅行社许可证", constraint = "旅行社许可证")
    private String licenseNo;

    @OpenApiField(desc = "取消原因", constraint = "取消原因")
    private CloseReasonType closeReasonType;

    @OpenApiField(desc = "取消订单原因描述", constraint = "取消订单原因描述")
    private String closeReasonDesc;

    @OpenApiField(desc = "服务费", constraint = "服务费(取消订单使用)")
    private Money servceAmount;

    @OpenApiField(desc = "导游电话", constraint = "导游电话")
    private String guideMobileNo;

    @OpenApiField(desc = "订单是否可以取消", constraint = "订单是否可以取消{true,false}")
    private boolean orderCloseble;
}
