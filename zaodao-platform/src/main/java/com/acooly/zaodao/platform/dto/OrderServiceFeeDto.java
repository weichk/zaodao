package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.ServiceFeeNameType;
import com.acooly.zaodao.platform.enums.ServiceFeeScaleType;
import com.acooly.zaodao.platform.enums.ServiceFeeStatus;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-30 13:45
 **/
@Data
public class OrderServiceFeeDto implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 收费项目{约导服务费,用户取消约导}
     */
    @Enumerated(EnumType.STRING)
    private ServiceFeeNameType feeName;

    /**
     * 收费标准(固定收费,百分比收费)
     */
    @Enumerated(EnumType.STRING)
    private ServiceFeeScaleType feeScale;

    /**
     * 收费额度(百分比收费:30表示30%,固定收费30表示30分)
     */
    private Double feeAmount;
    /**
     * 收费额度
     */
    private Long feeAmountLong;
    /**
     * 状态{开启,关闭}
     */
    @Enumerated(EnumType.STRING)
    private ServiceFeeStatus feeStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 费用条件
     */
    private List<OrderServiceConditionDto> orderServiceConditionDtoList = Lists.newArrayList();

    /**
     * 组合收费条件(订单状态等于已支付、取消订单提前天数大于等于7天)
     */
    private String conditionTxt;

    /**
     * 排序
     */
    private Long feeSort;
}
