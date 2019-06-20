package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.ServiceFeeNameType;
import com.acooly.zaodao.platform.enums.ServiceFeeStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 服务费查询
 *
 * @author xiaohong
 * @create 2018-05-29 11:49
 **/
@Getter
@Setter
public class QueryServiceFeeDto extends QueryBase {
    /**
     * 收费项目
     */
    private ServiceFeeNameType feeName;

    /**
     * 状态
     */
    private ServiceFeeStatus feeStatus;
}
