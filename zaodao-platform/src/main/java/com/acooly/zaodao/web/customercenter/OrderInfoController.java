/*
 * 修订记录:
 * zhike@yiji.com 2017-06-11 23:37 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import com.acooly.zaodao.platform.service.platform.DeductWithdrawService;
import com.acooly.zaodao.platform.service.platform.PlatformTourGuideService;
import com.acooly.zaodao.platform.service.platform.base.DepositResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/services/orderInfo")
@Slf4j
public class OrderInfoController extends AbstractPortalController<PlatOrderInfo, PlatOrderInfoService> {

    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Autowired
    private PlatformTourGuideService platformTourGuideService;

    @Autowired
    private DeductWithdrawService deductWithdrawService;

    /**
     * 获取用户订单列表
     *
     * @param currentPageNo
     * @param countOfCurrentPage
     * @param orderStatus
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orderInfoList", method = RequestMethod.POST)
    public JsonListResult<OrderInfoDto> orderInfoList(Integer currentPageNo, Integer countOfCurrentPage,
                                                      String orderStatus, HttpServletRequest request) {
        JsonListResult<OrderInfoDto> result = new JsonListResult<>();
        // 获取session中用户信息
        Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (sessionCustomer == null) {
            throw new BusinessException("请先登录");
        }
        try {
            PageInfo<OrderInfoDto> pageInfo = getEntityService().getPageOrderInfoListByUserId(currentPageNo,
                    countOfCurrentPage, sessionCustomer.getUserId(), orderStatus, sessionCustomer.getIsTourGuide());
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
            result.setMessage("获取订单列表成功");
            log.info("获取用户{}订单列表成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("获取订单列表失败");
            log.error("获取用户{}订单列表失败", sessionCustomer.getMobileNo());
        }
        return result;
    }

    /**
     * 创建订单
     */
    @ResponseBody
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public JsonResult createOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                                  String dateStart, String dateEnd, String childrenNum, String adultNum, String guideUserId) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            PlatOrderInfo platOrderInfo = new PlatOrderInfo();
            platOrderInfo.setOrderNo(Ids.oid());
            platOrderInfo.setStartTime(dateStart);
            platOrderInfo.setEndTime(dateEnd);
            platOrderInfo.setStartPlayTime(Dates.parse(dateStart));
            platOrderInfo.setEndPlayTime(Dates.parse(dateEnd));
            platOrderInfo.setAdultCount(Integer.parseInt(adultNum));
            platOrderInfo.setChildCount(Integer.parseInt(childrenNum));
            platOrderInfo.setTourGuideId(guideUserId);
            platOrderInfo.setTouristId(sessionCustomer.getUserId());
            platOrderInfo.setOrderStatus(PlatOrderInfoOrderStatus.noPay);
            platOrderInfo.setContactName(sessionCustomer.getRealName());
            platOrderInfo.setContactPhone(sessionCustomer.getMobileNo());
            platOrderInfo = platOrderInfoService.createOrder(platOrderInfo);
            result.appendData("orderNo", platOrderInfo.getOrderNo());
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 订单详情
     */
    @RequestMapping("/orderDetail/{orderNo}")
    public String orderDetail(HttpServletRequest request, HttpServletResponse response, Model model,
                              @PathVariable String orderNo) {
        // 获取session中用户信息
        Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (sessionCustomer == null) {
            return "redirect:/portal/portlet/login_register.html";
        }

        PlatOrderInfo orderInfo = platOrderInfoService.findByOrderNo(orderNo);
        if (!orderInfo.getTouristId().equals(sessionCustomer.getUserId()) && !orderInfo.getTourGuideId().equals(sessionCustomer.getUserId())) {
            throw new BusinessException("订单信息有误!");
        }

        TourGuideDto dto = platformTourGuideService.findByUserId(orderInfo.getTourGuideId());
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("guide", dto);
        return "/portal/order/orderDetail";
    }


    /**
     * 修改并支付订单
     */
    @RequestMapping("/checkOrderInfo")
    @ResponseBody
    public JsonResult checkOrderInfo(HttpServletRequest request, HttpServletResponse response, Model model, String contactName,
                                     String contactPhone, String contactMemo, String orderNo) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                result.setSuccess(false);
                result.setMessage("请先登录");
            }

            PlatOrderInfo orderInfo = platOrderInfoService.findByOrderNo(orderNo);
            if (!orderInfo.getTouristId().equals(sessionCustomer.getUserId())) {
                throw new BusinessException("订单信息有误!");
            }
            if (Strings.isNotBlank(contactName)) {
                orderInfo.setContactName(contactName);
            }
            if (Strings.isNotBlank(contactPhone)) {
                orderInfo.setContactPhone(contactPhone);
            }
            if (Strings.isNotBlank(contactMemo)) {
                orderInfo.setContactMemo(contactMemo);
            }
            orderInfo.setOrderStatus(PlatOrderInfoOrderStatus.noPay);
            platOrderInfoService.update(orderInfo);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("支付订单错误");
            log.info("校验支付订单orderNo={}失败:{}", orderNo, e.getMessage());
        }
        return result;
    }

    /**
     * 修改并支付订单
     */
    @RequestMapping("/orderPay/{payType}/{orderNo}")
    public String orderPay(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("orderNo") String orderNo, @PathVariable("payType") String payType) {
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                return "redirect:/portal/portlet/login_register.html";
            }

            PlatOrderInfo orderInfo = platOrderInfoService.findByOrderNo(orderNo);
            orderInfo.setOrderStatus(PlatOrderInfoOrderStatus.processing);
            platOrderInfoService.update(orderInfo);
            //调用观世宇接口生成支付二维码
            DepositResult depositResult = deductWithdrawService.deposit(orderNo, payType);
            orderInfo.setScanUrl(depositResult.getScanUrl());
            model.addAttribute("orderInfo", orderInfo);
            model.addAttribute("payType", "aboutGuide");
            model.addAttribute("orderPayType", payType);
            return "/portal/order/orderPay";
        } catch (Exception e) {
            log.info("支付订单orderNo={}失败:{}", orderNo, e.getMessage());
        }
        return "/portal/order/orderPay";
    }


    /**
     * 查询订单信息如果已支付就跳转到订单列表页面
     */
    @RequestMapping("/setTimeRefreshOrder")
    @ResponseBody
    public JsonResult setTimeRefreshOrder(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                result.setSuccess(false);
                result.setMessage("请先登录");
            }

            PlatOrderInfo orderInfo = platOrderInfoService.findByOrderNo(orderNo);
            if (orderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.pay || orderInfo.getOrderStatus() == PlatOrderInfoOrderStatus.confirm) {
                result.appendData("orderStatus","success");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("查询支付订单错误");
            log.info("查询支付订单orderNo={}失败:{}", orderNo, e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cancleOrder", method = RequestMethod.POST)
    public JsonResult cancleOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                                  String orderStatus, String orderNo) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonResult result = new JsonResult();
        try {
            PlatOrderInfo orderInfo = platOrderInfoService.findByOrderNo(orderNo);
            if (!orderInfo.getTouristId().equals(customer.getUserId())) {
                throw new BusinessException("订单信息有误!");
            } else {
                orderInfo.setOrderStatus(PlatOrderInfoOrderStatus.close);
            }
            // 根据订单状态处理取消订单时的逻辑
            // TODO
            platOrderInfoService.update(orderInfo);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 订单确认
     *
     * @param request
     * @param response
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public JsonResult confirm(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonResult result = new JsonResult();
        try {
            //2018-05-09 xh modify, 取消清分
            //platOrderInfoService.confirm(customer, orderNo);
            platOrderInfoService.confirmOrder(customer, orderNo);
            log.info("订单orderNo={}确认成功", orderNo);
        } catch (Exception e) {
            log.info("订单orderNo={}确认失败", orderNo);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public JsonResult deleteOrder(String orderNo, HttpServletRequest request) {
        JsonListResult<OrderInfoDto> result = new JsonListResult<>();
        // 获取session中用户信息
        Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (sessionCustomer == null) {
            throw new BusinessException("请先登录");
        }
        try {
            PlatOrderInfo orderInfo = getEntityService().findByOrderNo(orderNo);
            orderInfo.setOrderStatus(PlatOrderInfoOrderStatus.delete);
            getEntityService().update(orderInfo);
            result.setMessage("删除订单成功");
            log.info("用户{}删除订单成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除订单失败");
            log.error("用户{}删除订单失败", sessionCustomer.getMobileNo());
        }
        return result;
    }
}
