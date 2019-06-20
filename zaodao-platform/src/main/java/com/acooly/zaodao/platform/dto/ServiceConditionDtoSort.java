package com.acooly.zaodao.platform.dto;

import java.util.Comparator;

/**
 * 服务费条件排序(页面form传回的name值)
 *
 * @author xiaohong
 * @create 2018-05-30 15:58
 **/
public class ServiceConditionDtoSort implements Comparator<ServiceConditionDto> {
    @Override
    public int compare(ServiceConditionDto o1, ServiceConditionDto o2) {
        if(o1.getIndex().equals(o2.getIndex())){
            return o1.getName().compareTo(o2.getName());
        }else {
            return o1.getIndex().compareTo(o2.getIndex());
        }
    }
}
