/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-28 23:23 创建
 */
package com.acooly.zaodao.common;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.domain.Entityable;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.service.EntityService;
import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.validate.Validators;
import com.acooly.module.security.captche.Captchas;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.ZdSmsService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端网站抽象控制器
 *
 * @author acooly
 */
@Slf4j
public abstract class AbstractPortalController<T extends Entityable, M extends EntityService<T>>
        extends AbstractJQueryEntityController<T, M> {

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected RedisClientService redisClientService;

	@Autowired
	protected ZdSmsService zdSmsService;

	{
		allowMapping = "";
	}

	private static final int CONTENT_TOP_COUNT = 5;

	protected int getTopCount(HttpServletRequest request) {
		int topCount = CONTENT_TOP_COUNT;
		String requCount = request.getParameter("count");
		if (Strings.isNotBlank(requCount) && Strings.isNumeric(requCount)) {
			topCount = Integer.valueOf(requCount);
		}
		return topCount;
	}

	protected PageInfo<T> initPageInfo(Integer pageNo, Integer pageSize) {
		PageInfo<T> pageInfo = new PageInfo<>();
		pageInfo.setCurrentPage(pageNo);
		pageInfo.setCountOfCurrentPage(pageSize);
		return pageInfo;
	}

	/**
	 * 校验入参
	 *
	 * @param requestInfoBase
	 */
	protected void checkRequestInfo(RequestInfoBase requestInfoBase) {
		if (null == requestInfoBase) {
			throw new BusinessException("请求参数不能为空");
		}
		try {
			Validators.assertJSR303(requestInfoBase);
		} catch (IllegalArgumentException iae) {
			log.error("请求参数不完整：" + iae.getMessage());
			throw new BusinessException("请求参数不完整：" + iae.getMessage());
		} catch (Exception e) {
			log.error("请求参数不完整：" + e.getMessage());
			throw new BusinessException("请求参数不完整：" + e.getMessage());
		}
	}

	protected void handleAjaxException(String action, Exception e, JsonResult result) {
		String message = null;
		if (StringUtils.isBlank(message)) {
			message = e.getMessage();
		}
		if (StringUtils.isNotBlank(action)) {
			message = action + ":" + message;
		}
		if (e instanceof BusinessException) {
			result.setCode(((BusinessException) e).getCode());
		}
		result.setSuccess(false);
		result.setMessage(message);
		log.warn(message, e.getMessage());
	}

	protected Customer loadCustomer(HttpServletRequest request) {
		Customer customer = (Customer) (request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO));
		return customerService.get(customer.getId());
	}

	/**
	 * 验证验证码有效性
	 */
	protected void validateCaptcha(HttpServletRequest request) {
		String capchaName = request.getParameter("captcha");
		// 校验验证码
		boolean captchaPassed = Captchas.verify(request, capchaName);
		if (!captchaPassed) {
			throw new RuntimeException("验证码错误或过期");
		}
	}

	/**
	 * 验证密码
	 *
	 * @param request
	 */
	protected void validatePassword(HttpServletRequest request) {
		String password = request.getParameter("password");
		Customer customer = loadCustomer(request);
		boolean pwdPassed = customerService.checkPassword(customer, password);
		if (!pwdPassed) {
			throw new RuntimeException("登录密码错误");
		}
	}

	/**
	 * 插件验证密码
	 *
	 * @param request
	 */
	protected void plugValidatePassword(HttpServletRequest request) {
		String password = request.getParameter("param");
		Customer customer = loadCustomer(request);
		boolean pwdPassed = customerService.checkPassword(customer, password);
		if (!pwdPassed) {
			throw new RuntimeException("登录密码错误");
		}
	}

	@ExceptionHandler
	protected String exception(HttpServletRequest request, BusinessException ex) {
		log.info(ex.getMessage());
		return "/portal/404";
	}
}
