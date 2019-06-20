/*
 * 修订记录:
 * zhike@yiji.com 2017-03-07 14:32 创建
 *
 */
package com.acooly.zaodao.web.protal;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Ids;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.module.security.captche.Captchas;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.enums.SendSmsType;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.entity.LeaveMessage;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.platform.result.CustomerRegisterResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/portal/services")
public class SystemPortalController extends AbstractPortalController<Customer, CustomerService> {

    /**
     * SYSTEM LOG
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemPortalController.class);

    @Autowired
    private CustomerCardService customerCardService;

    @Autowired
    private OFileProperties oFileProperties;

    @Autowired
    private PointAccountService pointAccountService;

    @Autowired
    private PointTradeService pointTradeService;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private ArticleReviewService articleReviewService;

    private String loginFailureUrl = "/portal/portlet/login_register";

    /**
     * 发送短息验证码
     *
     * @param mobileNo
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public JsonResult sendSms(String mobileNo, HttpServletRequest request) {
        String sendSmsType = request.getParameter("sendSmsType");
        JsonResult result = new JsonResult();
        try {
            zdSmsService.zdSendSms(mobileNo, SendSmsType.find(sendSmsType));
            logger.info("用户{}发送短息成功", mobileNo);
        } catch (Exception e) {
            logger.error("用户{}发送短息失败:{}", mobileNo, e.getMessage());
            result.setSuccess(false);
            result.setMessage("发送短息失败");
        }
        return result;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @return
     */
    @RequestMapping(value = "/verifyMobileRepeat")
    @ResponseBody
    public JSONObject verifyMobileRepeat(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String mobileNo = request.getParameter("param");
        Customer userInfo = this.getEntityService().findEntityByMobileNo(mobileNo);
        result.put("status", "y");
        if (userInfo != null) {
            result.put("status", "n");
            result.put("info", "此手机号码已经注册");
        }
        return result;
    }

    @RequestMapping(value = "/checkCaptcha")
    @ResponseBody
    public JsonResult CheckCaptcha(String captcha, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        // 校验验证码
        boolean captchaPassed = Captchas.verify(request, captcha);
        if (!captchaPassed) {
            result.setSuccess(false);
            result.setMessage("请输入正确的图形验证码");
        }
        return result;
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonEntityResult<Customer> login(HttpServletRequest request, HttpServletResponse response) {
        JsonEntityResult<Customer> result = new JsonEntityResult<>();
        String password = request.getParameter("password");
        String mobileNo = request.getParameter("mobileNo");
        try {
            // 会员登录
            Customer customer = this.getEntityService().login(mobileNo, password);
            // 获取绑卡信息
            CustomerCard customerCard = customerCardService.getEntityByUserId(customer.getUserId());

            if (customerCard != null) {
                String cardNo = customerCard.getCardNo();
                String subCardNo = cardNo.substring(cardNo.length() - 4, cardNo.length());
                customerCard.setShowCardNo("**** **** **** " + subCardNo);
                request.getSession().setAttribute(SysConstant.SESSION_KEY_CARD, customerCard);
            }
            request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, customer);
            request.getSession().setAttribute(SysConstant.SESSION_SERVICE_ROOT, getFileServerRoot());
            result.setEntity(customer);
            // 可用积分余额
            PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
            request.getSession().setAttribute(SysConstant.CUSTOMER_POINT_BALANCE, pointAccount.getBalance());

            // 获取未读消息
            Integer msgNum = 0;
            int notArticleReviewCount = articleReviewService.getNotReadEntitysByUserId(customer.getUserId());
            List<CustomerMessage> notReadCustomerMessage = customerMessageService
                    .getNotReadEntitysByUserId(customer.getUserId());
            int notCustomerMessage = notReadCustomerMessage.size();
            List<LeaveMessage> notReadLeaveMessages = leaveMessageService
                    .getNotReadEntitysByUserId(customer.getUserId());
            int notLeaveMessages = notReadLeaveMessages.size();
            msgNum = notArticleReviewCount + notCustomerMessage + notLeaveMessages;
            if (msgNum > 0) {
                redisClientService.setRedis(SysConstant.CUSTOMER_MSG + customer.getUserId(), msgNum);
            }
            logger.info("获取用户未读消息条数为{}", msgNum);
            result.appendData("isGuide", customer.getIsTourGuide());
            result.setMessage("登录成功");
            logger.info("用户{}登录成功", mobileNo);
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            logger.error("用户{}登录失败:{}", mobileNo, be.getMessage());
        }
        return result;
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        doLogout(request, "web");
        return "redirect:" + loginFailureUrl + ".html";
    }

    protected void doLogout(HttpServletRequest request, String loginChannel) {
        request.getSession().removeAttribute(SysConstant.SESSION_KEY_USERINFO);
//        request.getSession().removeAttribute(SysConstant.MOBILE_CAPTCHA_SESSION);
        request.getSession().removeAttribute(SysConstant.SESSION_KEY_CARD);
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        String mobileNo = request.getParameter("mobileNo");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String mobileCaptcha = request.getParameter("mobileCaptcha");
        try {
            // 保存用户信息
            Customer customer = new Customer();
            customer.setMobileNo(mobileNo);
            customer.setLoginPassword(password);
            customer.setUserId(Ids.oid());
//			customer.setUserName(CreateRandomField.getRandomEnglishName());
            customer.setUserName(userName);
            CustomerRegisterResult registerResult = this.getEntityService().register(customer, mobileCaptcha);
            registerResult.throwExceptionIfNotSuccess();
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("注册失败");
            logger.error("用户{}注册失败:{}", mobileNo, e.getMessage());
        }
        return result;
    }

    /**
     * @return
     */
    private Object getFileServerRoot() {

        return oFileProperties.getServerRoot();
    }
}
