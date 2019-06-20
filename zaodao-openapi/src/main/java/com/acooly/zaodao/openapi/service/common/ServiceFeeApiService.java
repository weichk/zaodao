package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ServiceFeeRequest;
import com.acooly.zaodao.openapi.message.common.ServiceFeeResponse;
import com.acooly.zaodao.openapi.message.dto.OrderServiceConditionInfoDto;
import com.acooly.zaodao.openapi.message.dto.OrderServiceFeeInfoDto;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDtoSort;
import com.acooly.zaodao.platform.dto.OrderServiceFeeDto;
import com.acooly.zaodao.platform.enums.ServiceConditionName;
import com.acooly.zaodao.platform.enums.ServiceConditionSymbol;
import com.acooly.zaodao.platform.enums.ServiceFeeScaleType;
import com.acooly.zaodao.platform.result.ServiceFeeListResult;
import com.acooly.zaodao.platform.service.manage.OrderServiceFeeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务费规则
 *
 * @author xiaohong
 * @create 2018-06-01 17:33
 **/
@OpenApiService(name = "serviceFee", desc = "服务费规则", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ServiceFeeApiService extends BaseApiService<ServiceFeeRequest, ServiceFeeResponse> {
    @Autowired
    private OrderServiceFeeService orderServiceFeeService;

    @Override
    protected void doService(ServiceFeeRequest request, ServiceFeeResponse response) {
        ServiceFeeListResult result =  orderServiceFeeService.getOrderServiceFeeList();
        result.throwExceptionIfNotSuccess();
        List<OrderServiceFeeInfoDto> feeInfoDtos = getOrderServiceFeeText(result.getDto());
        response.setRows(feeInfoDtos);
    }

    private List<OrderServiceFeeInfoDto> getOrderServiceFeeText(List<OrderServiceFeeDto> list){
        List<OrderServiceFeeInfoDto> feeInfoDtos = Lists.newArrayList();
        list.forEach(p -> {
            StringBuilder sbDaysText = new StringBuilder();
            StringBuilder sbFeeText = new StringBuilder();

            if(p.getOrderServiceConditionDtoList().size() > 0) {
                List<OrderServiceConditionDto> dtos = p.getOrderServiceConditionDtoList().stream().filter(x -> x.getConditionName().equals(ServiceConditionName.cancel_days)).collect(Collectors.toList());
                dtos.sort(new OrderServiceConditionDtoSort());
                if(dtos.size() > 0) {
                    if (dtos.size() == 1) {
                        sbDaysText.append(getDaysText(dtos.get(0)));
                    } else if (dtos.size() > 1) {
                        sbDaysText.append(getDaysText(dtos.get(0), dtos.get(1)));
                    }
                    sbFeeText.append(getFeeText(p));

                    OrderServiceFeeInfoDto feeInfoDto = new OrderServiceFeeInfoDto();
                    feeInfoDto.setDaysText(sbDaysText.toString());
                    feeInfoDto.setFeeText(sbFeeText.toString());
                    feeInfoDtos.add(feeInfoDto);
                }
            }
        });
        return feeInfoDtos;
    }

    /**
     * 天数差值
     */
    private String getDaysText(OrderServiceConditionDto dto){
        ServiceConditionSymbol symbol = dto.getConditionSymbol();
        String value = dto.getConditionValue();
        if(symbol.equals(ServiceConditionSymbol.more_equal_than)){
            return String.format("x≥%s天", value);
        }else if(symbol.equals(ServiceConditionSymbol.more_than)){
            return String.format("x>%s天", value);
        }else if(symbol.equals(ServiceConditionSymbol.equal_to)){
            return String.format("x=%s天", value);
        }else if(symbol.equals(ServiceConditionSymbol.less_equal_than)){
            return String.format("x≤%s天", value);
        }else if(symbol.equals(ServiceConditionSymbol.less_than)){
            return String.format("x<%s天", value);
        }
        return "";
    }

    /**
     * 天数差值
     */
    private String getDaysText(OrderServiceConditionDto dto1, OrderServiceConditionDto dto2){
        ServiceConditionSymbol symbol1 = dto1.getConditionSymbol();
        String value1 = dto1.getConditionValue();
        ServiceConditionSymbol symbol2 = dto2.getConditionSymbol();
        String value2 = dto2.getConditionValue();

        if(symbol1.equals(ServiceConditionSymbol.more_equal_than) && symbol2.equals(ServiceConditionSymbol.less_than)){
            return String.format("%s天≤x<%s天", value1, value2);
        }
        return "";
    }

    /**
     * 服务费收取标准
     */
    private String getFeeText(OrderServiceFeeDto dto){
        if(dto.getFeeScale().equals(ServiceFeeScaleType.fixed_fee)){
            return dto.getFeeAmountLong() == 0 ? "免服务费" : String.format("固定收费%s元", dto.getFeeAmount() / 10000);
        }else if(dto.getFeeScale().equals(ServiceFeeScaleType.percent_fee)){
            return dto.getFeeAmountLong() == 0 ? "免服务费" : String.format("订单费用的%s", dto.getFeeAmount() / 100) + "%";
        }
        return "";
    }
}
