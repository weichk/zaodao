package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Money;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.enums.ReservationType;
import lombok.Data;

import java.util.Date;

/**
 * 订单详情
 *
 * @author xiaohong
 * @create 2017-11-22 18:15
 **/
@Data
public class PlatOrderDetailResult extends ResultBase {
    /**
     * 订单ID
     */
    private Long orderInfoId;

    /**
     * 游玩会员头像
     */
    private String headImg;

    /**
     * 游玩会员用户名
     */
    private String userName;
    /**
     * 姓名(约我的订单返回导游姓名)
     */
    private String realName;
    /**
     * 导游每日价格(我约的订单有效)
     */
    private Long pricePerday = 0L;
    /**
     * 目的地
     */
    private String destination;

    /**
     * 游玩开始时间
     */
    private Date startPlayTime;

    /**
     * 游玩结束时间
     */
    private Date endPlayTime;

    /**
     * 成人数
     */
    private Integer adultCount;

    /**
     * 小孩数
     */
    private Integer childCount;

    /**
     * 订单金额
     */
    private Long orderAmount = 0L;

    /**
     * 订单状态
     */
    private PlatOrderInfoOrderStatus orderStatus;

    /**
     * 订单号
     */
    private String platOrderNo;

    /**
     * 下单时间
     */
    private Date createTime;
    /**
     * 约导天数
     */
    private Integer dayCount;
    /**
     * 联系人备注
     */
    private String contactMemo;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 出行用户id
     */
    private String userId;
    /**
     * 详情使用
     */
    private String guideUserId;
    /**
     * 导游名称
     */
    private String guideName;

    /**
     * 订单评价
     */
    private PlatOrderEvalDto platOrderEvalDto;

    /**
     * 预约类型
     */
    private ReservationType reservationType;

    /**
     * 旅行社ID
     */
    private Long travelAgencyId;

    /**
     * 旅行社名称
     */
    private String agencyName;

    /**
     * 旅行社许可证
     */
    private String licenseNo;

    /**
     * 取消原因
     */
    private CloseReasonType closeReasonType;

    /**
     * 取消订单原因描述
     */
    private String closeReasonDesc;

    /**
     * 服务费(取消订单)
     */
    private Money servceAmount;

    /**
     * 导游电话
     */
    private String guideMobileNo;

    /**
     * 订单是否可以取消{true,false}
     */
    private boolean orderCloseble;
}
