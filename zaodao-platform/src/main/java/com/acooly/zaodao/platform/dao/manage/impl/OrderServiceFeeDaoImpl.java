package com.acooly.zaodao.platform.dao.manage.impl;


import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.OrderServiceFeeCusDao;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.OrderServiceFeeDto;
import com.acooly.zaodao.platform.dto.QueryServiceFeeDto;
import com.acooly.zaodao.platform.enums.ServiceFeeNameType;
import com.acooly.zaodao.platform.enums.ServiceFeeStatus;

import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 13:55
 **/
public class OrderServiceFeeDaoImpl extends AbstractJdbcTemplateDao implements OrderServiceFeeCusDao {

    /**
     * 获取服务费分页列表
     */
    @Override
    public PageInfo<OrderServiceFeeDto> getPageOrderServiceFee(QueryServiceFeeDto query){
        PageInfo<OrderServiceFeeDto> pageInfo = new PageInfo<OrderServiceFeeDto>();
        pageInfo.setCountOfCurrentPage(query.getRows());
        pageInfo.setCurrentPage(query.getPage());

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("SELECT * FROM zd_order_service_fee a WHERE 1=1 ");

        if(null != query.getFeeName()){
            sbSql.append(String.format(" AND a.fee_name='%s'", query.getFeeName().getCode()));
        }
        if(null != query.getFeeStatus()){
            sbSql.append(String.format(" AND a.fee_status='%s'", query.getFeeStatus().getCode()));
        }
        sbSql.append(" ORDER BY a.fee_sort ");
        pageInfo = query(pageInfo, sbSql.toString(), OrderServiceFeeDto.class);

        for(OrderServiceFeeDto feeDto : pageInfo.getPageResults()){
            //获取条件
            StringBuilder sbConSql = new StringBuilder();
            sbConSql.append(String.format("SELECT * FROM zd_order_service_condition WHERE fee_id='%s'", feeDto.getId()));
            List<OrderServiceConditionDto> conditionDtos = queryForList(sbConSql.toString(), OrderServiceConditionDto.class);
            feeDto.setOrderServiceConditionDtoList(conditionDtos);
        }
        return pageInfo;
    }

    /**
     * 根据feeName获取服务费列表
     */
    @Override
    public List<OrderServiceFeeDto> getOrderServiceFeeListByFeeName(ServiceFeeNameType feeName) {
        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT a.fee_amount AS feeAmountLong, a.* FROM zd_order_service_fee a");
        sbSql.append(String.format(" WHERE a.fee_status='%s'", ServiceFeeStatus.enable.getCode()));
        if(null != feeName) {
            sbSql.append(String.format(" AND a.fee_name='%s'", feeName.getCode()));
        }
        sbSql.append(" ORDER BY a.fee_sort ");
        List<OrderServiceFeeDto> orderServiceFeeDtoList = queryForList(sbSql.toString(), OrderServiceFeeDto.class);
        orderServiceFeeDtoList.forEach(p -> {
            StringBuilder sbConSql = new StringBuilder();
            sbConSql.append(String.format("SELECT * FROM zd_order_service_condition WHERE fee_id='%s'", p.getId()));
            sbConSql.append(String.format(" ORDER BY condition_name ASC,condition_symbol ASC"));
            List<OrderServiceConditionDto> conditionDtos = queryForList(sbConSql.toString(), OrderServiceConditionDto.class);
            p.setOrderServiceConditionDtoList(conditionDtos);
        });
        return orderServiceFeeDtoList;
    }
}
