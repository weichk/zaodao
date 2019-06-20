/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 15:06 创建
 *
 */
package com.acooly.zaodao.web.travelstrategy;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.zaodao.platform.dto.AtricleAreaDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修订记录：
 * 旅游攻略模块
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/travelStrategy")
@Slf4j
public class TravelStrategyController extends AbstractJQueryEntityController<Article, ArticleService> {

    /**
     * 旅游攻略
     */
    @RequestMapping("/travelStrategyIndex")
    public String travelStrategy(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Article> articleList = getEntityService().getCurrPageArticles(1);
        List<AtricleAreaDto> areaList = getEntityService().getAreaList(1);
        model.addAttribute("articleList", articleList);
        model.addAttribute("areaList", areaList);
        return "/portal/travelstrategy/travel_strategy";
    }

    public void replaceContent(List<Article> articleList) {
        for (Article article : articleList) {
            String htmlStr = article.getContent(); // 含html标签的字符串
            Pattern p_html1;
            Matcher m_html1;
            String IMG_html1 = "<img\\s[^>]+/>";
            p_html1 = Pattern.compile(IMG_html1);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤IMG标签
            // <p>段落替换为换行
            htmlStr = htmlStr.replaceAll("<p .*?>", "\r\n");
            // <br><br/>替换为换行
            htmlStr = htmlStr.replaceAll("<br\\s*/?>", "\r\n");
            // 去掉其它的<>之间的东西
            htmlStr = htmlStr.replaceAll("\\<.*?>", "");
            if(htmlStr.length() >70) {
                article.setContent(StringUtils.substring(htmlStr,0,70)+"...");
            }else {
                article.setContent(htmlStr);
            }

        }
    }

    /**
     * 获取更多旅游攻略
     */
    @RequestMapping("getMoreArticles")
    @ResponseBody
    public JsonResult getMoreArticles(Integer currentPageNo, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        List<Article> articleList = getEntityService().getCurrPageArticles(currentPageNo);
        List<AtricleAreaDto> areaList = getEntityService().getAreaList(currentPageNo);
        replaceContent(articleList);
        result.appendData("articleList", articleList);
        result.appendData("areaList", areaList);
        result.appendData("total", areaList.size());
        return result;
    }

    /**
     * 旅游攻略列表
     */
    @RequestMapping("/travelStrategyList")
    public String travelStrategyList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String searchContent = request.getParameter("searchContent");
        String area = request.getParameter("area");
        List<Article> articleList = this.getEntityService().getArticlesByArea(area,searchContent);
        replaceContent(articleList);
        model.addAttribute("area", area);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("articleList", articleList);
        return "/portal/travelstrategy/travel_strategy_list";
    }

    /**
     * 获取当前地区更多旅游攻略
     */
    @RequestMapping("getMoreArticlesList")
    @ResponseBody
    public JsonResult getMoreArticlesList(Integer currentPageNo, String area,String searchContent, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        PageInfo<Article> articlePageInfo = getEntityService().getEntityListByArea(currentPageNo, area,searchContent);
        result.appendData("articleList", articlePageInfo.getPageResults());
        result.appendData("total", articlePageInfo.getTotalCount());
        return result;
    }
}
