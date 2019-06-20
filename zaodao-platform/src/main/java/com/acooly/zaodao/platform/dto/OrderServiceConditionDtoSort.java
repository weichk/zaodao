package com.acooly.zaodao.platform.dto;

import java.util.Comparator;

/**
 * 服务费条件排序(数据库端)
 *
 * @author xiaohong
 * @create 2018-05-31 9:46
 **/
public class OrderServiceConditionDtoSort implements Comparator<OrderServiceConditionDto> {
    @Override
    public int compare(OrderServiceConditionDto o1, OrderServiceConditionDto o2) {
        if(o1.getConditionName().equals(o2.getConditionName())){
            return o1.getConditionSymbol().compareTo(o2.getConditionSymbol());
        }else {
            return o1.getConditionName().compareTo(o2.getConditionName());
        }
    }
}
