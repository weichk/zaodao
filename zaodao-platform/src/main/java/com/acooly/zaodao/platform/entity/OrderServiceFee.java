package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-28 17:32
 **/
@Getter
@Setter
@Entity
@Table(name = "zd_order_service_fee")
public class OrderServiceFee  extends AbstractEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
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
    private Long feeAmount;

    /**
     * 状态{开启,关闭}
     */
    @Enumerated(EnumType.STRING)
    private ServiceFeeStatus feeStatus;

    /**
     * 排序(1,2,3)
     */
    private Long feeSort;
}
