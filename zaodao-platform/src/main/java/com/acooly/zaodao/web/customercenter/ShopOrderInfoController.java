/*
 * 修订记录:
 * zhike@yiji.com 2017-06-25 14:13 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.service.manage.ShopOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/services/shopOrderInfo")
@Slf4j
public class ShopOrderInfoController extends AbstractPortalController<ShopOrderInfo, ShopOrderInfoService> {

    /**
     * 获取用户积分订单列表
     *
     * @param currentPageNo
     * @param countOfCurrentPage
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orderInfoList", method = RequestMethod.POST)
    public JsonListResult<ShopOrderInfo> orderInfoList(Integer currentPageNo, Integer countOfCurrentPage, HttpServletRequest request) {
        JsonListResult<ShopOrderInfo> result = new JsonListResult<>();
        // 获取session中用户信息
        Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (sessionCustomer == null) {
            throw new BusinessException("请先登录");
        }
        try {
            PageInfo<ShopOrderInfo> pageInfo = getEntityService().queryPageByUserName(currentPageNo,
                    countOfCurrentPage, sessionCustomer.getUserId());
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
            result.setMessage("获取积分订单列表成功");
            log.info("获取用户{}积分订单列表成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("获取积分订单列表失败");
            log.error("获取用户{}积分订单列表失败", sessionCustomer.getMobileNo());
        }
        return result;
    }

}
