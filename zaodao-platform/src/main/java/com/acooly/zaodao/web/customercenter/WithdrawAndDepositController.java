/*
 * 修订记录:
 * zhike@yiji.com 2017-06-29 14:53 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/services/withdrawAndDeposit")
@Slf4j
public class WithdrawAndDepositController {

    /**
     * 获取导游秘籍
     */
    @RequestMapping("/intoDeductDepost")
    public String guideSecret(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/portal/order/orderPay";
    }

//    public String deductDeposit() {
//        try {
//            // 获取session中用户信息
//            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
//            if (sessionCustomer == null) {
//                throw new BusinessException("请先登录");
//            }
//            PlatOrderInfo platOrderInfo = new PlatOrderInfo();
//            platOrderInfo.setOrderNo(Ids.oid());
//            platOrderInfo.setStartTime(dateStart);
//            platOrderInfo.setEndTime(dateEnd);
//            platOrderInfo.setStartPlayTime(Dates.parse(dateStart));
//            platOrderInfo.setEndPlayTime(Dates.parse(dateEnd));
//            platOrderInfo.setAdultCount(Integer.parseInt(adultNum));
//            platOrderInfo.setChildCount(Integer.parseInt(childrenNum));
//            platOrderInfo.setTourGuideId(guideUserId);
//            platOrderInfo.setTouristId(sessionCustomer.getUserId());
//            platOrderInfo.setOrderStatus(PlatOrderInfoOrderStatus.noPay);
//            platOrderInfo.setContactName(sessionCustomer.getRealName());
//            platOrderInfo.setContactPhone(sessionCustomer.getMobileNo());
//            platOrderInfo = platOrderInfoService.createPlatOrder(platOrderInfo);
//            result.appendData("orderNo", platOrderInfo.getOrderNo());
//        } catch (Exception e) {
//            result.setMessage(e.getMessage());
//            result.setSuccess(false);
//        }
//    }
}
