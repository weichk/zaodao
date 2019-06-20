/*
 * 修订记录:
 * zhike@yiji.com 2017-05-22 16:56 创建
 *
 */
package com.acooly.zaodao.web.guidehome;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.module.cms.domain.Content;
import com.acooly.module.cms.enums.ContentTypeEnum;
import com.acooly.module.cms.service.ContentService;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.CommonUtils;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.dto.BlogArticleInfoDto;
import com.acooly.zaodao.platform.dto.NewsDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.entity.RedBagLog;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.acooly.zaodao.platform.service.manage.RedBagLogService;
import com.acooly.zaodao.platform.service.manage.RedBagService;
import com.acooly.zaodao.platform.service.platform.NewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/portal/guideHome")
@Slf4j
public class GuideHomeController extends AbstractPortalController<Article, ArticleService> {

	@Autowired
	private ContentService contentService;

	@Autowired
	private OFileProperties oFileProperties;

	@Autowired
	private NewsInfoService newsInfoService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private RedBagService redBagService;

	@Autowired
	private PointTradeService pointTradeService;

	@Autowired
	private RedBagLogService redBagLogService;

	/**
	 * 导游之家
	 */
	@RequestMapping("/guideHomeIndex")
	public String guideHome(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			int count = getTopCount(request);
			// 获取首页横幅
			List<Content> contents = contentService.topByTypeCode(ContentTypeEnum.indexBanner.code(), count);
			// 获取公告列表
			List<NewsDto> noticeList = newsInfoService.getNewsByType("notice", 10);
			//获取活动列表
			List<NewsDto> activityList = newsInfoService.getNewsByType("activityList", 10);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("activityList", activityList);
			model.addAttribute("indexBanners", contents);
			model.addAttribute("count", count);
			model.addAttribute("mediaRoot", getFileServerRoot());
		} catch (Exception e) {
			log.info("进入导游之家页面异常:{}", e.getMessage());
		}
		return "/portal/guidehome/guide_home";
	}

	/**
	 * 获取导游秘籍
	 */
	@RequestMapping("/guideSecret")
	public String guideSecret(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// 获取导游词
			List<Article> guideWordList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.guideSecret.getCode(), "guideWord");
			// 获取带团技巧
			List<Article> skillsTourList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.guideSecret.getCode(), "skillsTour");
			// 获取导游培训
			List<Article> guideTrainingList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.guideSecret.getCode(), "guideTraining");
			model.addAttribute("guideWordList", guideWordList);
			model.addAttribute("skillsTourList", skillsTourList);
			model.addAttribute("guideTrainingList", guideTrainingList);
		} catch (Exception e) {
			log.info("获取导游秘籍异常:{}", e.getMessage());
		}
		return "/portal/guidehome/guide_secret";
	}

	/**
	 * 带团日志
	 */
	@RequestMapping("/experienceTour")
	public String experienceTour(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// 获取国内路线
			List<Article> domesticRoutesList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.experienceTour.getCode(), "domesticRoutes");
			// 获取出境路线
			List<Article> exitRoutesList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.experienceTour.getCode(), "exitRoutes");
			// 获取订制路线
			List<Article> customRouteList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.experienceTour.getCode(), "customRoute");
			model.addAttribute("domesticRoutesList", domesticRoutesList);
			model.addAttribute("exitRoutesList", exitRoutesList);
			model.addAttribute("customRouteList", customRouteList);
		} catch (Exception e) {
			log.info("获取带团日志异常:{}", e.getMessage());
		}
		return "/portal/guidehome/experience_tour";
	}

	/**
	 * 获取导游树洞
	 */
	@RequestMapping("/guideTreeHole")
	public String guideTreeHole(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// 获取导游词
			List<Article> guideTreeHoleList = this.getEntityService()
			        .getEntityByType(ArticleTypeEnum.guideTreeHole.getCode());
			model.addAttribute("guideTreeHoleList", guideTreeHoleList);
		} catch (Exception e) {
			log.info("获取导游树洞异常:{}", e.getMessage());
		}
		return "/portal/guidehome/guide_tree_hole";
	}

	/**
	 * 获取案例分析
	 */
	@RequestMapping("/caseAnalysis")
	public String caseAnalysis(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// 获取带团案例
			List<Article> caseTourList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.caseAnalysis.getCode(), "caseTour");
			// 获取投诉案例
			List<Article> complaintCaseList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.caseAnalysis.getCode(), "complaintCase");
			// 获取问题处理
			List<Article> problemSolvingList = this.getEntityService()
			        .getEntityByTypeAndLabel(ArticleTypeEnum.caseAnalysis.getCode(), "problemSolving");
			model.addAttribute("caseTourList", caseTourList);
			model.addAttribute("complaintCaseList", complaintCaseList);
			model.addAttribute("problemSolvingList", problemSolvingList);
		} catch (Exception e) {
			log.info("获取案例分析异常:{}", e.getMessage());
		}
		return "/portal/guidehome/case_analysis";
	}

	/**
	 * 获取时事热点列表
	 */
	@RequestMapping("/lighthouseList")
	public String lighthouseList(Integer currentPageNo, HttpServletRequest request, HttpServletResponse response,
	        Model model) {
		String type = request.getParameter("type");
		// 获取公告列表
		List<NewsDto> lighthouseList = newsInfoService.getNewsByType("lighthouse", 10);
		model.addAttribute("type", type);
		model.addAttribute("lighthouseList", lighthouseList);

		return "/portal/guidehome/guide_lighthouse";
	}

	/**
	 * 获取日周月排行帖子列表
	 */
	@RequestMapping("getDateArticleList")
	public String getDateNewsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取日新闻点击排行列表
		List<Article> dayArticleList = articleService.getArticleByDateType("day");
		// 获取周新闻点击排行列表
		List<Article> weekArticleList = articleService.getArticleByDateType("week");
		// 获取月新闻点击排行列表
		List<Article> monthArticleList = articleService.getArticleByDateType("month");
		model.addAttribute("dayArticleList", dayArticleList);
		model.addAttribute("weekArticleList", weekArticleList);
		model.addAttribute("monthArticleList", monthArticleList);
		return "/portal/guidehome/guide_date_ranking_article_list";
	}

	/**
	 * 获取帖子详情
	 */
	@RequestMapping("getArticleInfo_{id}")
	public String getArticleInfo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response,
	        Model model) {
		int isLogin = 0;
		Integer isShutup = 0;
		model.addAttribute("id", id);
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		if (customer != null) {
			isLogin = 1;
			isShutup = customer.getIsShutup();
		}
		model.addAttribute("isLogin", isLogin);
		model.addAttribute("isShutup", isShutup);

		Article article = articleService.get(id);
		if (article == null || !ArticleStatusEnum.published.equals(article.getArticleStatus())) {
			throw new BusinessException("文章不存在");
		}
		// 帖子红包
		int havaRedBag = 0;
		int getPoint = 0;
		if (customer != null) {
			RedBag redBag = redBagService.getEntityByUserIdAndRefId(article.getUserId(), article.getId());
			// 查询当前用户是否领取过当前文章的红包
			RedBagLog getRedBagLog = redBagLogService.getEntityByUserIdAndRefId(customer.getUserId(), article.getId());
			if (redBag != null && redBag.getSurplusNum() > 0 && getRedBagLog == null
			        && !StringUtils.equals(customer.getUserId(), article.getUserId())) {
				havaRedBag = 1;
				getPoint = CommonUtils.calculatingIntegral(redBag.getSurplusNum(), redBag.getSurplusAmount());
				redBag.setSurplusNum(redBag.getSurplusNum() - 1);
				redBag.setSurplusAmount(redBag.getSurplusAmount() - getPoint);
				// 产生积分
				PointTradeDto pointTradeDto = new PointTradeDto();
				pointTradeDto.setBusiId(article.getId() + "");
				pointTradeDto.setBusiType(redBag.getRedBagType());
				pointTradeDto.setBusiTypeText("阅读帖子红包");
				pointTradeService.pointProduce(customer.getUserId(), getPoint, pointTradeDto);
				// 保存红包领取记录
				RedBagLog redBagLog = new RedBagLog();
				redBagLog.setOrderNo(redBag.getOrderNo());
				redBagLog.setUserId(customer.getUserId());
				redBagLog.setRedBagType(redBag.getRedBagType());
				redBagLog.setRefId(article.getId());
				redBagLog.setTotalAmount(Long.parseLong(String.valueOf(getPoint)));
				redBagLogService.save(redBagLog);
			}
		}
		model.addAttribute("article", article);
		model.addAttribute("havaRedBag", havaRedBag);
		model.addAttribute("getPoint", getPoint);
		return "/portal/guidehome/article_info";
	}

	/**
	 * 获取导游攻略列表
	 */
	@RequestMapping("/blogList")
	public String blogList(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取公告列表
		List<NewsDto> noticeList = newsInfoService.getNewsByType("notice", 10);
		model.addAttribute("noticeList", noticeList);
		String type = request.getParameter("type");
		String lable = request.getParameter("lable");
		model.addAttribute("type", type);
		model.addAttribute("lable", lable);
		return "/portal/guidehome/blog_list";
	}

	/**
	 * 获取导游之家帖子列表
	 *
	 * @param currentPageNo
	 * @param articleType
	 * @param lable
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getBlogArticleList", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<BlogArticleInfoDto> getBlogArticleList(Integer currentPageNo, String articleType,
	        String lable, HttpServletRequest request, HttpServletResponse response) {
		JsonListResult<BlogArticleInfoDto> result = new JsonListResult<>();
		try {
			PageInfo<BlogArticleInfoDto> blogArticleInfoDtoPageInfo = articleService
			        .getPageEntitysByTypeAndLable(currentPageNo, articleType, lable, null);
			result.setTotal(blogArticleInfoDtoPageInfo.getTotalCount());
			result.setRows(blogArticleInfoDtoPageInfo.getPageResults());
			result.setMessage("获取帖子列表成功");
			log.info("获取帖子列表成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("获取帖子列表失败");
			log.error("获取帖子列表失败");
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
