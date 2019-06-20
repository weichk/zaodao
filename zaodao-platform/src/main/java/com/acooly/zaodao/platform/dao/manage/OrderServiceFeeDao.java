package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.QueryServiceFeeDto;
import com.acooly.zaodao.platform.entity.OrderServiceFee;
import com.acooly.zaodao.platform.enums.ServiceFeeNameType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 9:44
 **/
public interface OrderServiceFeeDao extends EntityJpaDao<OrderServiceFee, Long>,OrderServiceFeeCusDao {

    /**
     *  根据feeNameType获取约导服务费
     */
    List<OrderServiceFee> getByFeeName(ServiceFeeNameType feeName);
}
