/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 00:37 创建
 *
 */
package com.acooly.zaodao.web.protal;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.module.cms.domain.Content;
import com.acooly.module.cms.service.ContentService;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.dto.NewsDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import com.acooly.zaodao.platform.service.platform.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Slf4j
@Controller
@RequestMapping("/portal/news")
public class NewsInfoController extends AbstractPortalController {

  @Autowired private ContentService contentService;

  @Autowired private NewsInfoService newsInfoService;

  /** 新闻详情页面 */
  @RequestMapping("/newsInfo")
  public String newsinfo(HttpServletRequest request, HttpServletResponse response, Model model) {
    Long newsId = Long.parseLong(request.getParameter("id"));
    String type = request.getParameter("type");
    // 获取新闻详情列表
    Content content = contentService.get(newsId);
    if (null == content || 1 != content.getStatus()) {
      throw new BusinessException("新闻不存在");
    }
    // 获取上一篇新闻
    NewsDto previousNews = newsInfoService.findNesInfoPreviousOrNext(type, newsId, false);
    // 获取下一篇新闻
    NewsDto nextNews = newsInfoService.findNesInfoPreviousOrNext(type, newsId, true);
    // 热点导读
    List<NewsDto> hotNewsList = newsInfoService.getNewsByType(type, 10);
    content.setHits(content.getHits() + 1);
    contentService.update(content);
    model.addAttribute("newsInfo", content);
    model.addAttribute("previousNews", previousNews);
    model.addAttribute("nextNews", nextNews);
    model.addAttribute("type", type);
    model.addAttribute("hotNewsList", hotNewsList);

    return "/portal/news/news_info";
  }

  /** 新闻详情页面 */
  @RequestMapping("/newsinfoList")
  public String newsinfoList(
      Integer currentPageNo,
      HttpServletRequest request,
      HttpServletResponse response,
      Model model) {
    String type = request.getParameter("type");
    // 获取公告列表
    List<NewsDto> noticeList = newsInfoService.getNewsByType("notice", 10);
    model.addAttribute("type", type);
    model.addAttribute("noticeList", noticeList);

    return "/portal/news/news_list";
  }

  /** 分页获取新闻列表 */
  @RequestMapping("getMoreNews")
  @ResponseBody
  public JsonResult getMoreNews(
      Integer currentPageNo,
      Integer countOfCurrentPage,
      String type,
      HttpServletResponse response) {
    JsonResult result = new JsonResult();
    PageInfo<NewsDto> newsDtoPageInfo =
        newsInfoService.getPageNewsListByType(currentPageNo, countOfCurrentPage, type);
    result.appendData("newsList", newsDtoPageInfo.getPageResults());
    result.appendData("total", newsDtoPageInfo.getTotalCount());
    result.appendData("type", type);
    return result;
  }

  public static boolean GenerateImage(String imgStr, String path) { // 对字节数组字符串进行Base64解码并生成图片
    if (imgStr == null) // 图像数据为空
    return false;
    BASE64Decoder decoder = new BASE64Decoder();
    try {
      // Base64解码
      byte[] bytes = decoder.decodeBuffer(imgStr);
      for (int i = 0; i < bytes.length; ++i) {
        if (bytes[i] < 0) { // 调整异常数据
          bytes[i] += 256;
        }
      }
      // 生成jpeg图片
      OutputStream out = new FileOutputStream(path);
      out.write(bytes);
      out.flush();
      out.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
