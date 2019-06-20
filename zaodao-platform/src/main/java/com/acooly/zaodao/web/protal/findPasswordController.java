/*
 * 修订记录:
 * zhike@yiji.com 2017-05-17 23:37 创建
 *
 */
package com.acooly.zaodao.web.protal;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.module.security.captche.Captchas;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.Secret;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.SecretAnswerService;
import com.acooly.zaodao.platform.service.manage.SecretService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/findPassword")
@Slf4j
public class findPasswordController extends AbstractPortalController<Customer, CustomerService> {

    @Autowired
    private SecretService secretService;

    @Autowired
    private SecretAnswerService secretAnswerService;

    /**
     * 进入密码修改页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoFindPassword")
    public String intoFindPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/portal/findpassword/find_pwd";
    }

    /**
     * 进入密码密保修改页面一
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoFindPasswordProblemOne")
    public String intoFindPasswordProblemOne(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/portal/findpassword/find_pwd_problem_one";
    }

    /**
     * 校验验证码和手机号
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/checkCaptcha")
    @ResponseBody
    public JSONObject checkCaptcha(HttpServletRequest request, HttpServletResponse response, Model model) {
        String capchaName = request.getParameter("param");
        JSONObject result = new JSONObject();
        //校验验证码
        boolean captchaPassed = Captchas.verify(request, capchaName);
        String status = "y";
        if (!captchaPassed) {
            status = "n";

            result.put("info", "验证码校验错误");
        }
        result.put("status", status);
        if (StringUtils.equals("y", status)) {
            request.getSession().setAttribute("findPasswordProblemStepOne", "yes");
        }
        return result;
    }

    /**
     * 进入校验问题页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoCheckProblems")
    public String intoCheckProblems(HttpServletRequest request, HttpServletResponse response, Model model) {
        String setpOneIndex = (String) request.getSession().getAttribute("findPasswordProblemStepOne");
        if (!StringUtils.equals("yes", setpOneIndex)) {
            return "redirect:/portal/findPassword/intoFindPasswordProblemOne";
        }
        String mobileNo = request.getParameter("mobileNo");
        if (StringUtils.isBlank(mobileNo)) {
            return "redirect:/portal/index";
        }
        model.addAttribute("mobileNo", mobileNo);
        //获取所有密保问题
        List<Secret> secrets = secretService.getAll();
        model.addAttribute("secrets", secrets);
        return "/portal/findpassword/find_pwd_problem_two";
    }

    /**
     * 校验密保
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkSecret", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkSecret(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        String mobileNo = request.getParameter("mobileNo");
        try {
            Customer customer = this.getEntityService().findEntityByMobileNo(mobileNo);
            List<Secret> secrets = secretService.getAll();
            for (Secret secret : secrets) {
                String secretAnswer = request.getParameter("secret_" + secret.getId());
                SecretAnswer secretAnswerEntity = secretAnswerService.getEntityBySerretIdAndCusId(secret.getId(), customer.getUserId());
                if (secretAnswerEntity != null) {
                    if (!StringUtils.equals(secretAnswer, secretAnswerEntity.getContent())) {
                        result.setSuccess(false);
                        result.setMessage("回答错误");
                        break;
                    }
                } else {
                    result.setSuccess(false);
                    result.setMessage("回答错误");
                }
            }
            if (result.isSuccess()) {
                request.getSession().setAttribute("findPasswordProblemStepTwo", "yes");
            }
            result.appendData("mobileNo", mobileNo);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("回答错误");
            log.warn("问题校验失败：{}", e.getMessage());
        }
        return result;
    }

    /**
     * 进入修改密码页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoModifyPwd")
    public String intoModifyPwd(HttpServletRequest request, HttpServletResponse response, Model model) {
        String setpOneIndex = (String) request.getSession().getAttribute("findPasswordProblemStepTwo");
        if (!StringUtils.equals("yes", setpOneIndex)) {
            return "redirect:/portal/findPassword/intoFindPasswordProblemOne";
        }
        String mobileNo = request.getParameter("mobileNo");
        if (StringUtils.isBlank(mobileNo)) {
            return "redirect:/portal/findPassword/intoFindPassword";
        }
        model.addAttribute("mobileNo", mobileNo);
        return "/portal/findpassword/find_pwd_problem_three";
    }

    /**
     * 更新密码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public JsonResult updatePassword(HttpServletRequest request, HttpServletResponse response) {
        String mobileNo = request.getParameter("mobileNo");
        String password = request.getParameter("password");
        JsonResult result = new JsonResult();
        try {
            //校验用户是否存在
            Customer customer = this.getEntityService().findEntityByMobileNo(mobileNo);
            if (customer == null) {
                log.info("用户{}不存在", mobileNo);
                throw new BusinessException("用户不存在！");
            }
            //保存更新用户密码
            this.getEntityService().findPassword(customer, password);
            log.info("用户{}找回密码成功", mobileNo);
        } catch (Exception e) {
            log.info("用户{}找回密码失败：{}", mobileNo, e.getMessage());
            result.setSuccess(false);
            result.setMessage("修改密码失败");
        }
        result.appendData("mobileNo", mobileNo);
        return result;
    }

    /**
     * 进入修改密码页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoModifyPwdFinish")
    public String intoModifyPwdFinish(HttpServletRequest request, HttpServletResponse response, Model model) {
        String setpOneIndex = (String) request.getSession().getAttribute("findPasswordProblemStepTwo");
        if (!StringUtils.equals("yes", setpOneIndex)) {
            return "redirect:/portal/findPassword/intoFindPasswordProblemOne";
        }
        String mobileNo = request.getParameter("mobileNo");
        if (StringUtils.isBlank(mobileNo)) {
            return "redirect:/portal/index";
        }
        request.getSession().removeAttribute("findPasswordProblemStepOne");
        request.getSession().removeAttribute("findPasswordProblemStepTwo");
        return "/portal/findpassword/find_pwd_problem_four";
    }

    /**
     * 进入通过手机号码修改密码页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/intoFindPasswordByPhone")
    public String intoFindPasswordByPhone(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/portal/findpassword/find_pwd_phone";
    }

    /**
     * 通过手机号码找回密码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "portlet/findPasswordByPhone", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult findPasswordByPhone(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        String mobileNo = request.getParameter("mobileNo");
        String password = request.getParameter("password");
        String mobileCaptcha = request.getParameter("mobileCaptcha");
        try {
            //校验用户是否存在
            Customer customer = this.getEntityService().findEntityByMobileNo(mobileNo);
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }

            ResultBase resultBase = zdSmsService.checkMobileCaptcha(mobileNo, mobileCaptcha);
            resultBase.throwExceptionIfNotSuccess();
            //保存更新用户密码
            this.getEntityService().findPassword(customer, password);
            log.info("用户{}找回密码成功", mobileNo);
        } catch (Exception e) {
            result.setSuccess(false);
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("修改失败");
            }
            log.info("用户{}找回密码失败：{}", mobileNo, e.getMessage());
        }
        return result;
    }

    /**
     * 校验手机号码是否存在
     *
     * @return
     */
    @RequestMapping(value = "/verifyMobileRepeatHave")
    @ResponseBody
    public JSONObject verifyMobileRepeat(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String mobileNo = request.getParameter("param");
        Customer userInfo = this.getEntityService().findEntityByMobileNo(mobileNo);
        result.put("status", "y");
        if (userInfo == null) {
            result.put("status", "n");
            result.put("info", "此手机号码不存在");
        }
        return result;
    }
}
