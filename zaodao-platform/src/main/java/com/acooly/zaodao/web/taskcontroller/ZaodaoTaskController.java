/*
 * 修订记录:
 * zhike@yiji.com 2017-08-15 14:49 创建
 *
 */
package com.acooly.zaodao.web.taskcontroller;

import com.acooly.zaodao.platform.service.platform.ZaodaoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/zaodao/task")
@Slf4j
public class ZaodaoTaskController {

    @Autowired
    private ZaodaoTaskService zaodaoTaskService;

    /**
     * 交易清分定时任务
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping("/tradeProfit")
    public void tradeProfit(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            zaodaoTaskService.tradeProfitTask();
        }catch (Exception e) {
            log.info("订单清分定时任务执行异常：{}",e.getMessage());
            e.getStackTrace();
        }
    }

}
