/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-28 23:24 创建
 */
package com.acooly.zaodao.web.protal;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.cms.domain.Content;
import com.acooly.module.cms.enums.ContentTypeEnum;
import com.acooly.module.cms.service.ContentService;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.portlet.entity.SiteConfig;
import com.acooly.module.portlet.enums.SiteConfigKeyEnum;
import com.acooly.module.portlet.service.SiteConfigService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.dto.NewsDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.GuideLevel;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.acooly.zaodao.platform.service.platform.NewsInfoService;
import com.acooly.zaodao.platform.service.platform.PlatformTourGuideService;
import com.acooly.zaodao.portal.dto.TourGuideSearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author acooly
 */
@Controller
@RequestMapping("/portal")
public class IndexPortalController extends AbstractPortalController {

	@Autowired
	private NewsInfoService newsInfoService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private OFileProperties oFileProperties;

	@Autowired
	private SiteConfigService siteConfigService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private PlatformTourGuideService platformTourGuideService;

	/**
	 * 首页
	 */
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mediaRoot", getFileServerRoot());
		return "/portal/index";
	}

	/**
	 * 导航
	 */
	@RequestMapping("portlet/nav")
	public String nav(HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		model.addAttribute("pageIndex", pageIndex);
		List<SiteConfig> siteConfigs = siteConfigService.getAll();
		// 客户热线
		for (SiteConfig siteConfig : siteConfigs) {
			if (StringUtils.equals(siteConfig.getName(), SiteConfigKeyEnum.serviceTel.getCode())) {
				model.addAttribute("serviceTel", siteConfig.getValue());
			}
		}
		// 当前会员是否有未读消息

		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		if (customer != null) {
			Integer msgNum = (Integer) redisClientService.getRedis(SysConstant.CUSTOMER_MSG + customer.getUserId());
			if (msgNum != null && msgNum > 0) {
				model.addAttribute("msgNum", msgNum.toString());
			}
		}
		siteConfigService.getAll();
		return "/portal/portlet/nav";
	}

	/**
	 * 导航
	 */
	@RequestMapping("portlet/navAbsolute")
	public String navAbsolute(HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		model.addAttribute("pageIndex", pageIndex);
		List<SiteConfig> siteConfigs = siteConfigService.getAll();
		// 客户热线
		for (SiteConfig siteConfig : siteConfigs) {
			if (StringUtils.equals(siteConfig.getName(), SiteConfigKeyEnum.serviceTel.getCode())) {
				model.addAttribute("serviceTel", siteConfig.getValue());
			}
		}
		// 当前会员是否有未读消息

		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		if (customer != null) {
			Integer msgNum = (Integer) redisClientService.getRedis(SysConstant.CUSTOMER_MSG + customer.getUserId());
			if (msgNum != null && msgNum > 0) {
				model.addAttribute("msgNum", msgNum.toString());
			}
		}
		siteConfigService.getAll();
		return "/portal/portlet/nav_absolute";
	}

	/**
	 * 横幅
	 */
	@RequestMapping("portlet/banner")
	public String banner(String type, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			int count = getTopCount(request);
			List<Content> contents = contentService.topByTypeCode(type, count);
			model.addAttribute("indexBanners", contents);
			model.addAttribute("count", count);
		} catch (Exception e) {
			handleException("首页横幅", e, request);
		}
		return "/portal/portlet/banner";
	}

	/**
	 * 搜索框
	 */
	@RequestMapping("portlet/search")
	public String search(String type, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/portlet/search";
	}

	/**
	 * 公共
	 */
	@RequestMapping("portlet/notice")
	public String notice(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/portlet/notice";
	}

	/**
	 * 广告
	 */
	@RequestMapping("portlet/adv")
	public String adv(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/portlet/adv";
	}

	/**
	 * 首页新闻
	 */
	@RequestMapping("portlet/firstNews")
	public String tender(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取精华帖子
		List<Article> articles = articleService.getHotEntitys(ArticleHotType.essence.getCode());
		replaceContent(articles);
		model.addAttribute("hotArticles", articles);
		// 获取行业新闻
		List<NewsDto> tradeNewsList = newsInfoService.getNewsByType("news", 5);
		model.addAttribute("tradeNewsList", tradeNewsList);
		TourGuideSearchDto guideDto = new TourGuideSearchDto();
		guideDto.setGuideLevel(GuideLevel.famous.getCode());
		PageInfo<TourGuideDto> pageInfo = initPageInfo(1, 8);
		pageInfo = platformTourGuideService.queryByConditions(pageInfo, guideDto);
		model.addAttribute("famousGuides", pageInfo.getPageResults());
		return "/portal/portlet/first_news";
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
			if (htmlStr.length() > 110) {
				article.setContent(StringUtils.substring(htmlStr, 0, 110) + "......");
			} else {
				article.setContent(htmlStr);
			}

		}
	}

	/**
	 * 合作伙伴
	 */
	@RequestMapping("portlet/partner")
	public String partner(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			int count = getTopCount(request);
			List<Content> contents = contentService.topByTypeCode(ContentTypeEnum.partner.code(), count);
			model.addAttribute("partners", contents);
			model.addAttribute("count", count);
		} catch (Exception e) {
			handleException("合作伙伴", e, request);
		}
		return "/portal/portlet/partner";
	}

	/**
	 * 友情链接
	 */
	@RequestMapping("portlet/friend")
	public String friend(HttpServletRequest request, HttpServletResponse response, Model model) {

		try {
			int count = getTopCount(request);
			List<Content> contents = contentService.topByTypeCode(ContentTypeEnum.friend.code(), count);
			model.addAttribute("friends", contents);
			model.addAttribute("count", count);
			model.addAttribute("mediaRoot", getFileServerRoot());
		} catch (Exception e) {
			handleException("友情链接", e, request);
		}
		return "/portal/portlet/friend";
	}

	/**
	 * 页脚
	 */
	@RequestMapping("portlet/footer")
	public String footer(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 关于我们
		Content aboutus = contentService.getLatestByTypeCode("aboutus");
		if (aboutus != null) {
			model.addAttribute("aboutusSubject", aboutus.getSubject());
		}
		// 服务保障
		Content serviceAssurance = contentService.getLatestByTypeCode("serviceAssurance");
		if (serviceAssurance != null) {
			model.addAttribute("serviceAssuranceSubject", serviceAssurance.getSubject());
		}
		// 支持机构
		Content supportingOrganizations = contentService.getLatestByTypeCode("supportingOrganizations");
		if (supportingOrganizations != null) {
			model.addAttribute("supportingOrganizationsSubject", supportingOrganizations.getSubject());
		}
		// 反馈中心
		Content siteProblem = contentService.getLatestByTypeCode("siteProblem");
		if (siteProblem != null) {
			model.addAttribute("siteProblemSubject", siteProblem.getSubject());
		}
		List<SiteConfig> siteConfigs = siteConfigService.getAll();
		for (SiteConfig siteConfig : siteConfigs) {
			// 网站版权
			if (StringUtils.equals(siteConfig.getName(), "serviceCopyright")) {
				model.addAttribute("serviceCopyright", siteConfig.getValue());
			}
		}
		return "/portal/portlet/footer";
	}

	/**
	 * 登录注册
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("portlet/login_register")
	public String loginRegister(HttpServletRequest request, HttpServletResponse response, Model model) {
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		if (customer != null) {
			return "redirect:/portal/services/customer/customeIndex.html?childIndex=myArticle";
		}
		return "/portal/login_register";
	}

	/**
	 * 修改密码
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("portlet/userResetPassword")
	public String userResetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/user_find_password";
	}

	@RequestMapping("protocolContent")
	public String protocolContent() {
		return "/portal/protocol";
	}

	/**
	 * 横幅(不带大图展示)
	 */
	@RequestMapping("portlet/innerBanner")
	public String innerBanner(String bannerType, HttpServletRequest request, HttpServletResponse response,
	        Model model) {
		List<Content> contents = contentService.topByTypeCode(bannerType, 1);
		if (contents != null && contents.size() > 0) {
			model.addAttribute("innerBanner", contents.get(0));
		}
		model.addAttribute("mediaRoot", getFileServerRoot());
		return "/portal/portlet/innerBanner";
	}

	/**
	 * 搜索结果
	 */
	@RequestMapping("portlet/searchResult")
	public String searchResult(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/portlet/searchResult";
	}

	/**
	 * 导游搜索
	 */
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response, Model model, String keywords,
	        String province, String city, String tripTime, String level, String gender, String language) {
		TourGuideSearchDto guideDto = new TourGuideSearchDto();
		guideDto.setKeywords(keywords);
		guideDto.setProvince(province);
		guideDto.setCity(city);
		guideDto.setTripTime(tripTime);
		guideDto.setLevel(level);
		guideDto.setGender(gender);
		guideDto.setLanguage(language);
		model.addAttribute("guideDto", guideDto);
		return "/portal/search";
	}

	/**
	 * 人气导游
	 */
	@RequestMapping("portlet/guideHome")
	public String guideHome(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/guidehome/guide_home";
	}

	/**
	 * 旅游攻略
	 */
	@RequestMapping("portlet/travelStrategy")
	public String travelStrategy(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/travelstrategy/travel_strategy";
	}

	/**
	 * 旅游攻略
	 */
	@RequestMapping("portlet/travelStrategyList")
	public String travelStrategyList(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/travelstrategy/travel_strategy_list";
	}

	@RequestMapping("/vedioPlay")
	public String vedioPaly(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/portal/vedio_play";
	}

	/**
	 * 关于我们
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/about")
	public String about(String childIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("childIndex", childIndex);
		// 获取内容
		Content content = contentService.getLatestByTypeCode(childIndex);
		if (content != null) {
			model.addAttribute("content", content.getContentBody());
		}
		return "/portal/about/index";
	}

	/**
	 * @return
	 */
	private Object getFileServerRoot() {

		return oFileProperties.getServerRoot();
	}
}
