/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 22:13 创建
 *
 */
package com.acooly.zaodao.web.gsynotify;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Servlets;
import com.acooly.openapi.tool.AcoolyGateway;
import com.acooly.zaodao.common.utils.GatewayUtil;
import com.acooly.zaodao.gateway.gsy.GsyApiConstant;
import com.acooly.zaodao.gateway.gsy.message.TradeCreateNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawCardNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawNotify;
import com.acooly.zaodao.platform.enums.TradeMethod;
import com.acooly.zaodao.platform.service.platform.GsyNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping("/gsy/notify")
@Slf4j
public class GsyNotifyController {

    @Autowired
    private GsyNotifyService GsyNotifyService;

//    /**
//     * 关世宇订单交易异步处理
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/orderCreate", method = RequestMethod.POST)
//    public void tradeCreateNotify(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            //异步通知报文转map
//            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
//            if (notifyParams == null) {
//                throw new BusinessException("观世宇交易充值异步通知参数为空");
//            }
//            log.info("观世宇交易充值异步通知参数：notifyParams={}", notifyParams);
//            //异步通知报文验签
//            boolean isPass = YijifuGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
//            if(!isPass) {
//                throw new BusinessException("观世宇交易充值异步通知报文验签失败");
//            }
//            TradeCreateNotify notify = new TradeCreateNotify();
//            BeanUtils.populate(notify, notifyParams);
//            log.info("观世宇交易充值异步通知参数：notify={}", notify);
//            GsyNotifyService.tradeCreate(notify);
//            log.info("观世宇交易充值订单orderNo={},处理成功", notify.getMerchOrderNo());
//            Servlets.writeResponse(response, "success", null);
//        } catch (Exception e) {
//            log.info("观世宇交易充值异步处理失败，异常：{}", e.getStackTrace());
//            Servlets.writeResponse(response, "failure", null);
//        }
//    }

    /**
     * 观世宇提现异步处理
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public void withdraw(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
            if (notifyParams == null) {
                throw new BusinessException("观世宇提现异步通知参数为空");
            }
            log.info("提现异步通知参数：notifyParams={}", notifyParams);
            //异步通知报文验签
            boolean isPass = AcoolyGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
            if(!isPass) {
                throw new BusinessException("观世宇提现异步通知报文验签失败");
            }
            WithdrawNotify notify = new WithdrawNotify();
            BeanUtils.populate(notify, notifyParams);
            log.info("提现异步通知参数：notify={}", notify);
            GsyNotifyService.withdraw(notify);
            log.info("观世宇提现订单orderNo={},处理成功", notify.getMerchOrderNo());
            Servlets.writeResponse(response, "success", null);
        } catch (Exception e) {
            log.info("观世宇提现异步处理失败，异常：{}", e.getMessage());
            Servlets.writeResponse(response, "failure", null);
        }
    }

    /**
     * 观世宇绑卡回调处理
     */
    @RequestMapping(value = "/bankCardBind", method = RequestMethod.POST)
    public void bankCardBind(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
            if (notifyParams == null) {
                throw new BusinessException("观世宇绑卡异步通知参数为空");
            }
            log.info("观世宇绑卡异步通知参数：notifyParams={}", notifyParams);
            //异步通知报文验签
            boolean isPass = AcoolyGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
            if(!isPass) {
                throw new BusinessException("观世宇绑卡异步通知报文验签失败");
            }
            //回调逻辑处理
            Servlets.writeResponse(response, "success", null);
        } catch (Exception e) {
            log.info("观世宇绑卡异步处理失败，异常：{}", e.getMessage());
            Servlets.writeResponse(response, "failure", null);
        }
    }

    /**
     * 观世宇支付宝支付回调处理
     */
    @RequestMapping(value = "/aliScanQRCodePay", method = RequestMethod.POST)
    public void aliScanQRCodePay(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
            if (notifyParams == null) {
                throw new BusinessException("观世宇支付宝支付异步通知参数为空");
            }
            log.info("观世宇支付宝支付异步通知参数：notifyParams={}", notifyParams);
            boolean isPass = AcoolyGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
            if(!isPass) {
                throw new BusinessException("观世宇支付宝支付异步通知报文验签失败");
            }
            TradeCreateNotify notify = new TradeCreateNotify();
            BeanUtils.populate(notify, notifyParams);
            log.info("观世宇支付宝支付异步通知参数：notify={}", notify);

            GsyNotifyService.payNotify(notify, TradeMethod.alipay);

            log.info("观世宇支付宝支付订单orderNo={},处理成功", notify.getMerchOrderNo());
            Servlets.writeResponse(response, "success", null);
        } catch (Exception e) {
            log.info("观世宇支付宝支付异步处理失败，异常：{}", e.getMessage());
            e.printStackTrace();
            Servlets.writeResponse(response, "failure", null);
        }
    }

    /**
     * 观世宇微信支付回调处理
     */
    @RequestMapping(value = "/wechatScanQRCodePay", method = RequestMethod.POST)
    public void wechatScanQRCodePay(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
            if (notifyParams == null) {
                throw new BusinessException("观世宇微信支付异步通知参数为空");
            }
            log.info("观世宇微信支付异步通知参数：notifyParams={}", notifyParams);
            boolean isPass = AcoolyGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
            if(!isPass) {
                throw new BusinessException("观世宇微信支付异步通知报文验签失败");
            }
            TradeCreateNotify notify = new TradeCreateNotify();
            BeanUtils.populate(notify, notifyParams);
            log.info("观世宇微信支付异步通知参数：notify={}", notify);

            GsyNotifyService.payNotify(notify, TradeMethod.weixin);

            log.info("观世宇微信支付订单orderNo={},处理成功", notify.getMerchOrderNo());
            Servlets.writeResponse(response, "success", null);
        } catch (Exception e) {
            log.info("观世宇微信支付异步处理失败，异常：{}", e.getStackTrace());
            Servlets.writeResponse(response, "failure", null);
        }
    }

    /**
     * 提现到卡异步通知
     */
    @RequestMapping(value = "/withdrawCard", method = RequestMethod.POST)
    public void withdrawCardNotify(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> notifyParams = GatewayUtil.getNotifyParameters(request);
            if (notifyParams == null) {
                throw new BusinessException("观世宇提现到卡异步通知参数为空");
            }
            log.info("观世宇提现到卡异步通知参数：notifyParams={}", notifyParams);
            boolean isPass = AcoolyGateway.getOpenApiClientService().verificationSign(notifyParams, GsyApiConstant.GATEWAY_SECRETKEY);
            if(!isPass) {
                throw new BusinessException("观世宇提现到卡异步通知报文验签失败");
            }
            WithdrawCardNotify notify = new WithdrawCardNotify();
            BeanUtils.populate(notify, notifyParams);
            log.info("观世宇提现到卡异步通知参数：notify={}", notify);

            GsyNotifyService.withdrawCard(notify);

            log.info("观世宇提现到卡交易订单tradeOrderNo={},处理成功", notify.getMerchOrderNo());
            Servlets.writeResponse(response, "success", null);
        } catch (Exception e) {
            log.info("观世宇提现到卡异步处理失败，异常：{}", e.getMessage());
            e.printStackTrace();
            Servlets.writeResponse(response, "failure", null);
        }
    }
}
