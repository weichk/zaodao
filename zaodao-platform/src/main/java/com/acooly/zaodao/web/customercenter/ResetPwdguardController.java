/*
 * 修订记录:
 * zhike@yiji.com 2017-07-05 14:35 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.Secret;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import com.acooly.zaodao.platform.service.manage.SecretAnswerService;
import com.acooly.zaodao.platform.service.manage.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 修订记录：
 * 设置密保
 * @author zhike@yiji.com
 */
@Slf4j
@Controller
@RequestMapping("/portal/services/resetPwdguard")
public class ResetPwdguardController extends AbstractPortalController {

    @Autowired
    private SecretService secretService;

    @Autowired
    private SecretAnswerService secretAnswerService;
    /**
     * 设置密保第一步
     */
    @RequestMapping("/stepOne")
    public String stepOne(HttpServletRequest request, HttpServletResponse response, Model model) {
        request.getSession().setAttribute("pwdguardOne","yes");
        return "/portal/customercenter/reset_pwdguard_step_one";
    }


    /**
     * 设置密保第二步
     */
    @RequestMapping("/stepTwo")
    public String stepTwo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String pwdguardOne = (String)request.getSession().getAttribute("pwdguardOne");
        if(!StringUtils.equals(pwdguardOne,"yes")) {
            return "/portal/customercenter/reset_pwdguard_step_one";
        }
        //获取所有密保问题
        List<Secret> secrets = secretService.getAll();
        model.addAttribute("secrets", secrets);
        request.getSession().setAttribute("pwdguardTwo","yes");
        return "/portal/customercenter/reset_pwdguard_step_two";
    }

    /**
     * 设置密保第三步
     */
    @RequestMapping("/stepThree")
    public String stepThree(HttpServletRequest request, HttpServletResponse response, Model model) {
        String pwdguardOne = (String)request.getSession().getAttribute("pwdguardTwo");
        if(!StringUtils.equals(pwdguardOne,"yes")) {
            return "/portal/customercenter/reset_pwdguard_step_one";
        }
        try {
            Customer customer = loadCustomer(request);
            //删除用户原有答案
            secretAnswerService.deleteByUserId(customer.getUserId());
            List<Secret> secrets = secretService.getAll();
            for (Secret secret : secrets) {
                String answer = request.getParameter("secret_" + secret.getId());
                SecretAnswer secretAnswer = new SecretAnswer();
                secretAnswer.setUserId(customer.getUserId());
                secretAnswer.setSecretId(secret.getId());
                secretAnswer.setContent(answer);
                secretAnswerService.save(secretAnswer);
            }
            log.info("保存密保答案成功");
            request.getSession().removeAttribute("pwdguardOne");
            request.getSession().removeAttribute("pwdguardTwo");
        } catch (Exception e) {
            log.warn("保存密保答案失败：{}", e.getMessage());
        }
        return "/portal/customercenter/reset_pwdguard_step_three";
    }
}
