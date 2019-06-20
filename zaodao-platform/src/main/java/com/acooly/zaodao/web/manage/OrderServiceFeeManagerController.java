/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-29
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.platform.dto.*;
import com.acooly.zaodao.platform.entity.OrderServiceCondition;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.service.manage.OrderServiceConditionService;
import com.acooly.zaodao.platform.service.manage.OrderServiceFeeService;
import com.google.common.collect.Lists;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.zaodao.platform.entity.OrderServiceFee;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * zd_order_service_fee 管理控制器
 * 
 * @author zhike
 * Date: 2018-05-29 10:00:52
 */
@Controller
@RequestMapping(value = "/manage/orderServiceFee/orderServiceFee")
public class OrderServiceFeeManagerController extends AbstractJQueryEntityController<OrderServiceFee, OrderServiceFeeService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private OrderServiceFeeService orderServiceFeeService;

	@Autowired
	private OrderServiceConditionService orderServiceConditionServicel;

	private static Map<String, String> allOrderStatuss = Maps.newLinkedHashMap();

	static {
		allOrderStatuss.put(OrderStatusEnum.pay.getCode(), OrderStatusEnum.pay.getMessage());
	}

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allServiceFeeNameTypes", ServiceFeeNameType.mapping());
		model.put("allServiceConditionNames", ServiceConditionName.mapping());
		model.put("allServiceConditionSymbols", ServiceConditionSymbol.mapping());
		model.put("allServiceFeeScaleTypes", ServiceFeeScaleType.mapping());
		model.put("allServiceFeeStatuss", ServiceFeeStatus.mapping());
	}

	/**
	 * 页面使用的枚举集合
	 */
	private Map<String, Object> getEnumsMap(){
		Map<String, Object> map = Maps.newHashMap();
		map.put("allFeeNames", ServiceFeeNameType.mapping());
		map.put("allConditionNames", ServiceConditionName.mapping());
		map.put("allConditionSymbols", ServiceConditionSymbol.mapping());
		map.put("allFeeScales", ServiceFeeScaleType.mapping());
		map.put("allFeeStatuss", ServiceFeeStatus.mapping());
		map.put("allOrderStatuss", allOrderStatuss);
		return map;
	}

	/**
	 * 获取报名课程列表
	 */
	@RequestMapping(value = "/getServiceFeeList", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<OrderServiceFeeDto> getServiceFeeList(HttpServletRequest request, QueryServiceFeeDto queryServiceFeeDto){
		JsonListResult<OrderServiceFeeDto> result = new JsonListResult();
		try {
			PageInfo<OrderServiceFeeDto> pageInfo = orderServiceFeeService.getServiceFeeList(queryServiceFeeDto);
			result.setTotal(pageInfo.getTotalCount());
			result.setRows(pageInfo.getPageResults());

			Map<Object, Object> map = Maps.newHashMap();
			map.put("allFeeNames", ServiceFeeNameType.mapping());
			map.put("allConditionNames", ServiceConditionName.mapping());
			map.put("allConditionSymbols", ServiceConditionSymbol.mapping());
			map.put("allFeeScales", ServiceFeeScaleType.mapping());
			map.put("allFeeStatuss", ServiceFeeStatus.mapping());
			result.setData(map);
		} catch (Exception var5) {
			this.handleException(result, "分页查询", var5);
		}
		return result;
	}

	/**
	 * 添加服务费
	 */
	@RequestMapping(value = "/createOrderServiceFee", method = RequestMethod.GET)
	public String createOrderServiceFee(Model model){
		model.addAllAttributes(getEnumsMap());
		model.addAttribute("action", "create");
		model.addAttribute("unit", "元");

		return "manage/orderServiceFee/orderServiceFeeEdit";
	}

	/**
	 * 服务费条件编辑(列表行)
	 */
	@RequestMapping(value = "/editOrderServiceFee", method = RequestMethod.GET)
	public String editOrderServiceFee(Long id, Model model){
		OrderServiceFee orderServiceFee = orderServiceFeeService.get(id);
		List<OrderServiceCondition> conditions = orderServiceConditionServicel.getByFeeId(id);
		OrderServiceFeeDto dto = new OrderServiceFeeDto();
		BeanCopier.copy(orderServiceFee, dto);
		dto.setFeeAmount((double)orderServiceFee.getFeeAmount() / 100);

		model.addAllAttributes(getEnumsMap());
		model.addAttribute("action", "edit");
		model.addAttribute("orderServiceFee", dto);
		model.addAttribute("unit", dto.getFeeScale() == ServiceFeeScaleType.fixed_fee ? "元":"%");
		if(conditions != null && conditions.size() > 0) {
			model.addAttribute("conditions", conditions);
		}
		return "manage/orderServiceFee/orderServiceFeeEdit";
	}

	/**
	 * 添加服务费
	 */
	@ResponseBody
	@RequestMapping(value = "/saveServiceFee", method = RequestMethod.POST)
	public JsonEntityResult saveServiceFee(HttpServletRequest request, OrderServiceFeeDto orderServiceFee) {
		JsonEntityResult result = new JsonEntityResult();
		try {
			getOrderServiceFee(request, orderServiceFee);
			OrderServiceFee fee = orderServiceFeeService.saveOrderServiceFee(orderServiceFee, false);
			result.setEntity(fee);
			result.setMessage("添加服务费成功");
		} catch (Exception var5) {
			this.handleException(result, "添加服务费", var5);
		}
		return result;
	}

	/**
	 * 编辑后保存服务费条件
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyServiceFee", method = RequestMethod.POST)
	public JsonEntityResult modifyServiceFee(HttpServletRequest request, OrderServiceFeeDto orderServiceFee){
		JsonEntityResult result = new JsonEntityResult();
		try {
			getOrderServiceFee(request, orderServiceFee);
			OrderServiceFee fee = orderServiceFeeService.saveOrderServiceFee(orderServiceFee, true);
			result.setEntity(fee);
			result.setMessage("修改服务费成功");
		} catch (Exception var5) {
			this.handleException(result, "修改服务费", var5);
		}
		return result;
	}

	/**
	 * 页面：值为select标签选项
	 */
	private static final String CONDITION_VALUE_COM  = "conditionValueCom";

	/**
	 * 页面：值为text标签选项
	 */
	private static final String CONDITION_VALUE_TXT = "conditionValueTxt";

	/**
	 * 页面：条件名
	 */
	private static final String CONDITION_NAME = "conditionName";

	/**
	 * 页面：条件符号
	 */
	private static final String CONDITION_SYMBOL = "conditionSymbol";

	/**
	 * 页面：条件值
	 */
	private static final String CONDITION_VALUE = "conditionValue";
	/**
	 * 获取条件数据
	 */
	private void getOrderServiceFee(HttpServletRequest request, OrderServiceFeeDto orderServiceFee){
		if(null == orderServiceFee.getFeeAmount()){
			throw new BusinessException("收费标准不能为空");
		}
		Map<String, String[]> map = request.getParameterMap();
		List<ServiceConditionDto> list = Lists.newArrayList();
		map.forEach((k, v)-> {
			addSplitCondition(k, v[0], list);
		});

		//按照index排序
		list.sort(new ServiceConditionDtoSort());

		//如果存在cancel_days，则移除conditionValueCom选项条件,保留conditionValueTxt文本条件
		for(int i = list.size() - 1; i >= 0; i--){
			ServiceConditionDto dto = list.get(i);
			if(dto.getName().equals(CONDITION_VALUE_COM)
					&& list.stream().anyMatch(p -> p.getIndex().equals(dto.getIndex())
					&& p.getValue().equals(ServiceConditionName.cancel_days.getCode()))){
				list.remove(i);
			}else if(dto.getName().equals(CONDITION_VALUE_TXT)
					&& list.stream().anyMatch(p -> p.getIndex().equals(dto.getIndex())
					&& p.getValue().equals(ServiceConditionName.order_status.getCode()))){
				list.remove(i);
			}
		}
		//获取条件实体
		for(int i = 0; i < list.size(); i = i + 3){
			OrderServiceConditionDto conditionDto = new OrderServiceConditionDto();
			conditionDto.setConditionName(ServiceConditionName.find(list.get(i).getValue()));
			conditionDto.setConditionSymbol(ServiceConditionSymbol.find(list.get(i + 1).getValue()));
			if(list.size() % 3 > 0 || !Strings.isNotBlank(list.get(i + 2).getValue())){
				throw new BusinessException("收费条件值不能为空值");
			}
			conditionDto.setConditionValue(list.get(i + 2).getValue());
			orderServiceFee.getOrderServiceConditionDtoList().add(conditionDto);
		}
		//验证订单状态=已支付
		//提前取消天数最多两个条件

	}

//	private void checkCondition(List<OrderServiceConditionDto> conditionDtos){
//		conditionDtos
//	}

	/**
	 * 拆分条件
	 */
	private void addSplitCondition(String k, String v, List<ServiceConditionDto> list){
		if(Strings.isNotBlank(k) && Strings.isNotBlank(v)) {
			if(k.indexOf(CONDITION_NAME) > -1
					|| k.indexOf(CONDITION_SYMBOL) > -1
					|| k.indexOf(CONDITION_VALUE) > -1) {
				ServiceConditionDto dto = new ServiceConditionDto();
				dto.setSource(k);
				String[] s = k.split("_");
				if (s != null && s.length == 2) {
					dto.setName(s[0]);
					dto.setIndex(s[1]);
				}else return;

				dto.setValue(v);
				list.add(dto);
			}
		}
	}
}
