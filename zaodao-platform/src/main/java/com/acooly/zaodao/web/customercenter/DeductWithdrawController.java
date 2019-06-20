/*
 * 修订记录:
 * zhike@yiji.com 2017-08-09 13:51 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.gateway.gsy.message.SendCaptchaSmsRequest;
import com.acooly.zaodao.gateway.gsy.message.SendCaptchaSmsResponse;
import com.acooly.zaodao.gateway.gsy.message.enums.SmsTemplateEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.manage.TradeOrderInfoService;
import com.acooly.zaodao.platform.service.platform.DeductWithdrawService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/services/deductWithdraw")
@Slf4j
public class DeductWithdrawController extends AbstractPortalController {

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    @Autowired
    private DeductWithdrawService deductWithdrawService;

    @Autowired
    private TradeOrderInfoService tradeOrderInfoService;

    private static final String WITHDRAW = "withdraw";
    private static final String SIGNCARD = "signCard";

    /**
     * 提现绑卡发送短息验证码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "sendCaptchaSms", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult sendCaptchaSms(String type, String amount,String cardNo, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        try {
            SendCaptchaSmsRequest sendCaptchaSmsRequest = new SendCaptchaSmsRequest();
            sendCaptchaSmsRequest.setMerchOrderNo(Ids.oid());
            sendCaptchaSmsRequest.setMobileNo(customer.getMobileNo());
            Map<String, Object> map = Maps.newHashMap();
            if (StringUtils.equals(type, WITHDRAW)) {
                sendCaptchaSmsRequest.setSmsTemplateEnum(SmsTemplateEnum.WITHDRAW);
                map.put("amount", amount);
            }else if(StringUtils.equals(type, SIGNCARD)){
                sendCaptchaSmsRequest.setSmsTemplateEnum(SmsTemplateEnum.SIGNCARD);
                map.put("cardNo", cardNo);
            }
            sendCaptchaSmsRequest.setMap(map);
            SendCaptchaSmsResponse sendCaptchaSmsResponse = gsyTradePayService.sendCaptchaSms(sendCaptchaSmsRequest);
            if (sendCaptchaSmsResponse.getStatus() == ResultStatus.success) {
                result.setMessage("发送短息成功");
                log.info("用户{}发送短息成功", customer.getMobileNo());
            } else {
                result.setSuccess(false);
                result.setMessage("发送短息失败");
                log.error("用户{}发送短息失败：{}", customer.getMobileNo(), sendCaptchaSmsResponse.getResultMessage());
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("发送短息失败");
            log.error("用户{}发送短息失败", customer.getMobileNo());
        }
        return result;
    }

    /**
     * 创建交易
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "tradeCreate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult tradeCreate(String amount, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        Customer customer = loadCustomer(request);
        try {
            String orderNo = deductWithdrawService.tradeCreate(customer, amount);
            result.appendData("orderNo", orderNo);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("创建交易失败");
            log.error("用户{}创建交易失败", customer.getMobileNo());
        }
        return result;
    }

//    /**
//     * 充值
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "deposit_{orderNo}")
//    public String deposit(@PathVariable("orderNo") String orderNo, HttpServletRequest request, HttpServletResponse response, Model model) {
//        JsonResult result = new JsonResult();
//        Customer customer = loadCustomer(request);
//        try {
//            DepositResult depositResult = deductWithdrawService.deposit(orderNo);
//            //获取订单信息
//            TradeOrderInfo tradeOrderInfo = tradeOrderInfoService.findByOrderNo(orderNo);
//            tradeOrderInfo.setAliScanUrl(depositResult.getAliScanUrl());
//            tradeOrderInfo.setWeiScanUrl(depositResult.getWeiScanUrl());
//            model.addAttribute("orderInfo",tradeOrderInfo);
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setMessage("充值失败");
//            log.error("用户{}充值失败", customer.getMobileNo());
//        }
//        return "/portal/order/orderPay";
//    }

    /**
     * 提现
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "withdraw", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult withdraw(String amount, String captchaCode, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        try {
            deductWithdrawService.withdraw(customer, amount, captchaCode);
            log.error("用户{}提现申请成功", customer.getMobileNo());
        } catch (Exception e) {
            result.setSuccess(false);
            if(e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            }else {
                result.setMessage("提现失败");
            }
            log.error("用户{}提现申请失败", customer.getMobileNo());
        }
        return result;
    }
}
