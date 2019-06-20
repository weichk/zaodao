/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-04
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.exception.AppConfigException;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.MappingMethod;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.enums.MedalEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户表 管理控制器
 *
 * @author zhike Date: 2017-07-04 10:18:25
 */
@Slf4j
@Controller
@RequestMapping(value = "/manage/customer/customer")
public class CustomerManagerController extends AbstractJQueryEntityController<Customer, CustomerService> {

	@Autowired
	private CustomerMessageService customerMessageService;

	@Autowired
	private GsyBusinessService gsyBusinessService;

	@SuppressWarnings("unused")
	@Autowired
	private CustomerService customerService;

	@Autowired
	private TourGuideService tourGuideService;

	@Autowired
	private RedisClientService redisClientService;

	private static Map<Integer, String> allIsCertifications = Maps.newLinkedHashMap();

	static {
		allIsCertifications.put(1, "已认证");
		allIsCertifications.put(0, "未认证");
	}

	private static Map<Integer, String> allIsTourGuides = Maps.newLinkedHashMap();

	static {
		allIsTourGuides.putAll(IsTourGuide.getMaps());
	}

	private static Map<Integer, String> allIsLectors = Maps.newLinkedHashMap();

	static {
		allIsLectors.put(1, "是");
		allIsLectors.put(0, "否");
		allIsLectors.put(-1, "待审核");
	}

	private static Map<Integer, String> allIsShutup = Maps.newLinkedHashMap();

	static {
		allIsShutup.put(1, "是");
		allIsShutup.put(0, "否");
	}

	private static Map<Integer, String> allIsModerator = Maps.newLinkedHashMap();

	static {
		allIsModerator.put(1, "是");
		allIsModerator.put(0, "否");
	}

	private static Map<Integer, String> allSexs = Maps.newLinkedHashMap();

	static {
		allSexs.put(1, "男");
		allSexs.put(0, "女");
	}

	private static Map<Integer, String> allIsOpenables = Maps.newLinkedHashMap();

	static {
		allIsOpenables.putAll(IsOpenable.getMaps());
	}

	{
		allowMapping = "*";
	}

	@Override
	protected Customer doSave(HttpServletRequest request, HttpServletResponse response, Model model, boolean isCreate) throws Exception {
		//获取数据库实体entity
		Customer entity = this.loadEntity(request);
		Integer isTourGuide = entity.getIsTourGuide();
		Integer isShutup = entity.getIsShutup();

		if (entity == null) {
			this.allow(request, response, MappingMethod.create);
			entity = this.getEntityClass().newInstance();
		} else {
			this.allow(request, response, MappingMethod.update);
		}

        //表单请求修改entity值
		this.doDataBinding(request, entity);
		//发送审核消息
		sendGuideApproveMessage(isTourGuide, entity);

		// 禁言发送系统消息
		if (isShutup == 0 && entity.getIsShutup() == 1) {
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setUserId(entity.getUserId());
			customerMessage.setMessageTitle("禁言通知");
			customerMessage.setMessage("你已被管理员禁言，请联系管理员");
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + entity.getUserId());
		}

		if (isShutup == 1 && entity.getIsShutup() == 0) {
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setUserId(entity.getUserId());
			customerMessage.setMessageTitle("解除禁言通知");
			customerMessage.setMessage("恭喜你已解除禁言");
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + entity.getUserId());
		}

		String[] medalCodes = request.getParameterValues("medalCodes");

		if (medalCodes != null && medalCodes.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String code : medalCodes) {
				sb.append(code + ",");
			}
			entity.setMedalCode(sb.substring(0, sb.length() - 1));
		} else {
			entity.setMedalCode(null);
		}
		this.onSave(request, response, model, entity, isCreate);
		if (isCreate) {
			this.getEntityService().save(entity);
		} else {
			this.getEntityService().update(entity);
		}

		return entity;
	}

	/**
	 * 根据修改是否是导游字段发送审核消息
	 */
	private void sendGuideApproveMessage(Integer isTourGuide, Customer guide){
		log.info("导游审核[{}]: 开始状态[{}],申请状态[{}]", guide.getUserId(), IsTourGuide.getMessage(isTourGuide), IsTourGuide.getMessage(guide.getIsTourGuide()));

		//审核选择验证
		if(isTourGuide == IsTourGuide.WAIT_APPROVE.getKey()
				&& (guide.getIsTourGuide() == IsTourGuide.TWO_LEVEL.getKey()
				|| guide.getIsTourGuide() == IsTourGuide.TWO_APPROVE.getKey())){
			throw new BusinessException("审核选项错误,这是一级导游申请");
		}else if(isTourGuide == IsTourGuide.TWO_APPROVE.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.TWO_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.ONE_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.NOT_PASS.getKey()){
			throw new BusinessException("审核选项错误,这是二级导游申请");
		}else if(isTourGuide == IsTourGuide.TWO_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.ONE_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.NOT_PASS.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.TWO_LEVEL.getKey()){
			throw new BusinessException(String.format("二级导游不能修改为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
		}else if(isTourGuide == IsTourGuide.ONE_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.TWO_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.NOT_PASS.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.ONE_LEVEL.getKey()){
			throw new BusinessException(String.format("一级导游不能修改为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
		}else if(isTourGuide == IsTourGuide.NOT_PASS.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.ONE_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.TWO_LEVEL.getKey()
				&& guide.getIsTourGuide() != IsTourGuide.NOT_PASS.getKey()){
			throw new BusinessException(String.format("未认证不能修改为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
		}

		CustomerMessage customerMessage = new CustomerMessage();
		customerMessage.setUserId(guide.getUserId());
		customerMessage.setMessageType(CustomerMessageType.other);
		if (isTourGuide == IsTourGuide.WAIT_APPROVE.getKey()) {
			//如果是一级导游申请
			customerMessage.setMessageTitle("一级导游申请审核结果");
			if(guide.getIsTourGuide() == IsTourGuide.ONE_LEVEL.getKey()){
				customerMessage.setMessage("恭喜申请一级导游审核通过");
				guide.setAuditResult(GuideAuditResult.one_level_success);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}else if(guide.getIsTourGuide() == IsTourGuide.NOT_PASS.getKey()){
				customerMessage.setMessage("对不起申请一级导游审核未通过");
				guide.setAuditResult(GuideAuditResult.one_level_failed);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}
		}else if(isTourGuide == IsTourGuide.TWO_APPROVE.getKey()){
			//如果是二级导游申请
			customerMessage.setMessageTitle("二级导游申请审核结果");
			if(guide.getIsTourGuide() == IsTourGuide.TWO_LEVEL.getKey()){
				customerMessage.setMessage("恭喜申请二级导游审核通过");
				guide.setAuditResult(GuideAuditResult.two_level_success);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}else if(guide.getIsTourGuide() == IsTourGuide.ONE_LEVEL.getKey()){
				customerMessage.setMessage("对不起申请二级导游审核未通过");
				guide.setAuditResult(GuideAuditResult.two_level_failed);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}else if(guide.getIsTourGuide() == IsTourGuide.NOT_PASS.getKey()){
				customerMessage.setMessage("对不起申请一级导游审核未通过");
				guide.setAuditResult(GuideAuditResult.one_level_failed);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}
		}else if(isTourGuide == IsTourGuide.TWO_LEVEL.getKey()){
			//如果是二级修改为一级，或者否
			customerMessage.setMessageTitle(String.format("二级导游调整为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
			if(guide.getIsTourGuide() == IsTourGuide.ONE_LEVEL.getKey()){
				customerMessage.setMessage("对不起申请二级导游审核未通过");
				guide.setAuditResult(GuideAuditResult.two_level_failed);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}else if (guide.getIsTourGuide() == IsTourGuide.NOT_PASS.getKey()){
				customerMessage.setMessage("对不起导游未认证");
				guide.setAuditResult(null);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}
		}else if(isTourGuide == IsTourGuide.ONE_LEVEL.getKey()){
			//如果是一级修改为二级，或者否
			customerMessage.setMessageTitle(String.format("一级导游调整为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
			if(guide.getIsTourGuide() == IsTourGuide.TWO_LEVEL.getKey()){
				customerMessage.setMessage("恭喜申请二级导游审核通过");
				guide.setAuditResult(GuideAuditResult.two_level_success);
				customerMessageService.save(customerMessage);
			}else if(guide.getIsTourGuide() == IsTourGuide.NOT_PASS.getKey()){
				customerMessage.setMessage("对不起导游未认证");
				guide.setAuditResult(null);
				customerMessageService.save(customerMessage);
			}
		}else if(isTourGuide == IsTourGuide.NOT_PASS.getKey()){
			//如果是否修改为一级或者二级
			customerMessage.setMessageTitle(String.format("未认证状态调整为%s", IsTourGuide.getMessage(guide.getIsTourGuide())));
			if(guide.getIsTourGuide() == IsTourGuide.TWO_LEVEL.getKey()){
				customerMessage.setMessage("恭喜申请二级导游审核通过");
				guide.setAuditResult(GuideAuditResult.two_level_success);
				customerMessageService.save(customerMessage);
			}else if(guide.getIsTourGuide() == IsTourGuide.ONE_LEVEL.getKey()){
				customerMessage.setMessage("恭喜申请一级导游审核通过");
				guide.setAuditResult(GuideAuditResult.one_level_success);
				customerMessageService.save(customerMessage);
				redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guide.getUserId());
			}
		}
	}

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allIsCertifications", allIsCertifications);
		model.put("allIsTourGuides", allIsTourGuides);
		model.put("allIsLectors", allIsLectors);
		model.put("allSexs", allSexs);
		model.put("allIsModerator", allIsModerator);
		model.put("allIsShutup", allIsShutup);
		model.put("allModeratorPermissions", ModeratorPermissionEnum.mapping());
		model.put("allMedalEnums", MedalEnum.mapping());
		model.put("allIsOpenables", allIsOpenables);
	}

	@RequestMapping(value = { "/auditPage" })
	public String auditPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		allow(request, response, MappingMethod.show);
		try {
			model.addAllAttributes(referenceData(request));
			Customer entity = loadEntity(request);
			if (entity == null) {
				throw new AppConfigException("LoadEntity failure.");
			}
			onShow(request, response, model, entity);
			model.addAttribute(getEntityName(), entity);

			model.addAttribute("tourGuide", tourGuideService.getEntityByUserId(entity.getUserId()));
		} catch (Exception e) {
			handleException("审核", e, request);
		}
		return getRequestMapperValue() + "AuditPage";
	}

	/**
	 * 审核通过
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auditPass")
	@ResponseBody
	public JsonResult auditPass(HttpServletRequest request, HttpServletResponse response, Long id,
	        String auditOpinion) {
		JsonResult result = new JsonResult();
		try {
			getEntityService().auditPass(id);
			result.setMessage("审核通过操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			handleException(result, "审核通过失败", e);
		}
		return result;
	}

	/**
	 * 审核驳回
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auditReject")
	@ResponseBody
	public JsonResult auditReject(HttpServletRequest request, HttpServletResponse response, Long id,
	        String auditOpinion) {
		JsonResult result = new JsonResult();
		try {
			Customer customer = customerService.get(id);
			customer.setIsTourGuide(0);
			customerService.update(customer);
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setUserId(customer.getUserId());
			customerMessage.setMessageTitle("导游申请审核结果");
			customerMessage.setMessage("导游审核未通过，原因:" + auditOpinion);
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + customer.getUserId());
			result.setMessage("审核驳回操作成功");
		} catch (Exception e) {
			handleException(result, "审核驳回失败", e);
		}
		return result;
	}

	@RequestMapping(value = "/prohibition")
	public String prohibition(@ModelAttribute("loadEntity") Customer entity, Model model, HttpServletRequest request,
	        HttpServletResponse response) {
		try {
			model.addAttribute(getEntityName(), loadEntity(request));
		} catch (Exception e) {
			handleException("禁言界面", e, request);
		}

		return "/manage/customer/prohibition";
	}

	@ResponseBody
	@RequestMapping(value = "/prohibitionMsg")
	public JsonResult prohibitionMsg(String prohibitionMsg, Model model, HttpServletRequest request,
	        HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			Customer entity = loadEntity(request);
			entity.setIsShutup(1);
			customerService.update(entity);
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setUserId(entity.getUserId());
			customerMessage.setMessageTitle("禁言通知");
			customerMessage.setMessage(prohibitionMsg);
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + entity.getUserId());
			result.setMessage("禁言成功");
		} catch (Exception e) {
			handleException(result, "禁言成功", e);
		}
		return result;
	}

	protected void onShow(HttpServletRequest request, HttpServletResponse response, Model model, Customer entity)
	        throws Exception {
		if (Strings.isNotBlank(entity.getMedalCode())) {
			String[] codes = entity.getMedalCode().split(",");
			String medalName = "";
			for (String code : codes) {
				medalName += MedalEnum.find(code).getMessage() + ",";
			}
			entity.setMedalName(medalName.substring(0, medalName.length() - 1));
		}
	}
}
