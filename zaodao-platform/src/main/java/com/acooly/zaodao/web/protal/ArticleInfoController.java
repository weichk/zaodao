/*
 * 修订记录:
 * zhike@yiji.com 2017-06-05 10:30 创建
 *
 */
package com.acooly.zaodao.web.protal;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.domain.PointGrade;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointGradeService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.CommonUtils;
import com.acooly.zaodao.common.enums.StampEnum;
import com.acooly.zaodao.common.utils.HtmlTagUtils;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.dto.ArticleInfoDto;
import com.acooly.zaodao.platform.dto.ArticleReviewChildDto;
import com.acooly.zaodao.platform.dto.ArticleReviewInfoDto;
import com.acooly.zaodao.platform.dto.StampCodeDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import com.acooly.zaodao.platform.enums.IsTourGuide;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.manage.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/articleInfo")
@Slf4j
public class ArticleInfoController extends AbstractPortalController<Article, ArticleService> {

	@Autowired
	private ArticleReviewService articleReviewService;

	@Autowired
	private PointTradeService pointTradeService;

	@Autowired
	private PointAccountService pointAccountService;

	@Autowired
	private PointGradeService pointGradeService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CustomerMessageService customerMessageService;

	@Autowired
	private ArticlePraiseLogService articlePraiseLogService;

	@Autowired
	private ArticleEnshrineLogService articleEnshrineLogService;

	@Autowired
	private ArticleRewardLogService articleRewardLogService;

	@Autowired
	private RedBagService redBagService;

	@Autowired
	private RedBagLogService redBagLogService;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RedisClientService redisClientService;
	/**
	 * 获取帖子详细信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/getArticleDetails", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getArticleDetails(Long id, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			// 增加帖子访问数量
			Article article = getEntityService().get(id);
			article.setReadCount(article.getReadCount() + 1);
			log.info("获取帖子成功");
			ArticleInfoDto articleDetails = this.getEntityService().getArticleDetailsById(id);
			if (customer != null) {
				articleDetails.setIsModerator(customer.getIsModerator());
				articleDetails.setModeratorPermission(customer.getModeratorPermission().getCode());

				// 是否点赞
				ArticlePraiseLog articlePraiseLog = articlePraiseLogService
				        .findByUserIdAndArticleId(customer.getUserId(), article.getId());
				if (articlePraiseLog != null) {
					articleDetails.setHavaPraise(1);
				}
				// 是否收藏
				ArticleEnshrineLog articleEnshrineLog = articleEnshrineLogService
				        .findByUserIdAndArticleId(customer.getUserId(), article.getId());
				if (articleEnshrineLog != null) {
					articleDetails.setHavaEnshrine(1);
				}
			}
			log.info("获取帖子详情成功");
			// 获取勋章
			List<StampCodeDto> stampCodes = Lists.newArrayList();
			for (StampEnum stampEnum : StampEnum.getAll()) {
				StampCodeDto stampCodeDto = new StampCodeDto();
				stampCodeDto.setCode(stampEnum.getCode());
				stampCodeDto.setMessage(stampEnum.getMessage());
				stampCodes.add(stampCodeDto);
			}
			articleDetails.setStampCodes(stampCodes);
			// 获取文章打赏记录
			articleDetails.setArticleRewardLogDtoList(getEntityService().getArticleRewardLogList(id));
			// 更新打赏人数，去掉同一个人重复打赏
			if (articleDetails.getArticleRewardLogDtoList() != null) {
				int rewardCount = articleDetails.getArticleRewardLogDtoList().size();
				articleDetails.getArticle().setRewardCount(Long.parseLong(rewardCount + ""));
			}
			// 获取积分等级信息
			PointAccount pointAccount = pointAccountService.findByUserName(articleDetails.getArticle().getUserId());
			log.info("获取积分账户成功");
			PointGrade pointGrade = pointGradeService.getSectionPoint(pointAccount);
			articleDetails.setPointGradeTitle(pointGrade.getTitle());
			log.info("获取积分账户等级成功");
			result.appendData("articleDetails", articleDetails);
		} catch (Exception e) {
			log.warn("获取帖子详情失败:{}", e.getMessage());
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("获取帖子详情失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 评论帖子
	 *
	 * @return
	 */
	@RequestMapping(value = "/articleReview", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult articleReview(Long articleId, String reviewContent, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		int haveRedPacket = 0;
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			if (customer == null) {
				throw new BusinessException("请先登录");
			}
			if (StringUtils.isBlank(reviewContent)) {
				throw new BusinessException("回复内容不能为空");
			}

			Article article = articleService.get(articleId);

			// 首次回复获得积分
			List<ArticleReview> articleReviews = articleReviewService.getCusArticleReviews(articleId,
			        customer.getUserId());
			if (articleReviews.size() == 0) {
				// 回复获取积分
				PointTradeDto pointTradeDto = new PointTradeDto();
				pointTradeDto.setBusiId(customer.getUserId());
				pointTradeDto.setBusiType("articleReview");
				pointTradeDto.setBusiTypeText("评论帖子");
				pointTradeService.pointProduce(customer.getUserId(), 1, pointTradeDto);
				haveRedPacket = 1;
			}
			ArticleReview articleReview = new ArticleReview();
			articleReview.setReviewUserId(customer.getUserId());
			articleReview.setContent(HtmlUtils.htmlUnescape(reviewContent));
			articleReview.setArticleId(articleId);
			articleReviewService.save(articleReview);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + article.getUserId());
			result.appendData("haveRedPacket", haveRedPacket);
			result.setMessage("获取回复消息成功");
			log.info("回复成功");
		} catch (Exception e) {
			log.warn("回复成功");
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("回复失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 获取帖子回复列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/getArticleReviews", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<ArticleReviewInfoDto> getArticleReviews(Integer currentPageNo, Integer countOfCurrentPage,
	        Long id, HttpServletRequest request) {
		JsonListResult<ArticleReviewInfoDto> result = new JsonListResult<>();
		try {
			PageInfo<ArticleReviewInfoDto> articleReviewInfoDtoPageInfo = articleReviewService
			        .getArticleReviewsByArticleId(currentPageNo, countOfCurrentPage, id);
			if (articleReviewInfoDtoPageInfo.getPageResults() != null) {
				for (ArticleReviewInfoDto articleReviewInfoDto : articleReviewInfoDtoPageInfo.getPageResults()) {
					ArticleReview articleReview = articleReviewInfoDto.getArticleReview();
					Long articleReviewId = articleReview.getId();
					List<ArticleReviewChildDto> articleReviewList = articleReviewService
					        .getArticleReviewChildInfo(articleReviewId, id);
					articleReview.setArticleReviewChildDtoList(articleReviewList);
					// 获取积分等级信息
					PointAccount pointAccount = pointAccountService.findByUserName(articleReview.getReviewUserId());
					PointGrade pointGrade = pointGradeService.getSectionPoint(pointAccount);
					articleReviewInfoDto.setPointGradeTitle(pointGrade.getTitle());
				}
			}
			result.setTotal(articleReviewInfoDtoPageInfo.getTotalCount());
			result.setRows(articleReviewInfoDtoPageInfo.getPageResults());
			result.setMessage("获取回复消息列表成功");
			log.info("获取回复消息列表成功");
		} catch (Exception e) {
			log.warn("获取回复消息列表失败：{}",e.getStackTrace());
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("获取回复消息列表失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 点赞
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/likeHeart", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult likeHeart(Long id, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			Article article = this.getEntityService().get(id);
			article.setPraiseCount(article.getPraiseCount() + 1);
			getEntityService().update(article);
			// 记录点赞
			if (customer != null) {
				ArticlePraiseLog getAPLog = articlePraiseLogService.findByUserIdAndArticleId(customer.getUserId(),
				        article.getId());
				if (getAPLog == null) {
					ArticlePraiseLog articlePraiseLog = new ArticlePraiseLog();
					articlePraiseLog.setArticleId(article.getId());
					articlePraiseLog.setUserId(customer.getUserId());
					articlePraiseLogService.save(articlePraiseLog);
				}
			}
			result.setMessage("点赞成功");
			log.info("点赞成功");
		} catch (Exception e) {
			log.warn("点赞失败");
			result.setSuccess(false);
			result.setMessage("点赞失败");
		}
		return result;
	}

	/**
	 * 收藏
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enshrine", method = RequestMethod.POST)
	@ResponseBody
	public synchronized JsonResult enshrine(Long id, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			Article article = this.getEntityService().get(id);
			Long enshrineCount = article.getEnshrineCount() + 1;
			article.setEnshrineCount(enshrineCount);
			getEntityService().update(article);
			// 记录收藏
			if (customer != null) {
				ArticleEnshrineLog getAELog = articleEnshrineLogService.findByUserIdAndArticleId(customer.getUserId(),
				        article.getId());
				if (getAELog == null) {
					ArticleEnshrineLog articleEnshrineLog = new ArticleEnshrineLog();
					articleEnshrineLog.setArticleId(article.getId());
					articleEnshrineLog.setUserId(customer.getUserId());
					articleEnshrineLogService.save(articleEnshrineLog);
				}
			}
			result.setMessage("收藏成功");
			log.info("收藏成功");
		} catch (Exception e) {
			log.warn("收藏失败");
			result.setSuccess(false);
			result.setMessage("收藏失败");
		}
		return result;
	}

	/**
	 * 取消
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancelEnshrine", method = RequestMethod.POST)
	@ResponseBody
	public synchronized JsonResult cancelEnshrine(Long id, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			Article article = this.getEntityService().get(id);
			Long enshrineCount = article.getEnshrineCount() - 1;
			article.setEnshrineCount(enshrineCount);
			getEntityService().update(article);
			// 取消收藏
			if (customer != null) {
				ArticleEnshrineLog getAELog = articleEnshrineLogService.findByUserIdAndArticleId(customer.getUserId(),
				        article.getId());
				articleEnshrineLogService.removeById(getAELog.getId());
			}
			result.setMessage("取消收藏成功");
			log.info("取消收藏成功");
		} catch (Exception e) {
			log.warn("取消收藏失败");
			result.setSuccess(false);
			result.setMessage("取消收藏失败");
		}
		return result;
	}

	/**
	 * 回复评论
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/replyReview", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult replyReview(Long articleId, String content, Long reviewId, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			if (customer == null) {
				throw new BusinessException("请先登录");
			}
			if (StringUtils.isBlank(content)) {
				throw new BusinessException("回复内容不能为空");
			}
			ArticleReview articleReview = new ArticleReview();
			articleReview.setArticleId(articleId);
			articleReview.setParentId(reviewId);
			articleReview.setContent(content);
			articleReview.setReviewUserId(customer.getUserId());
			articleReviewService.save(articleReview);
			ArticleReview parentAticleReview = articleReviewService.get(reviewId);
			parentAticleReview.setReplyCount(parentAticleReview.getReplyCount() + 1);
			articleReviewService.update(parentAticleReview);
			result.setMessage("回复成功");
			log.info("回复成功");
		} catch (Exception e) {
			log.warn("回复失败");
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("回复失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 屏蔽回复
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setShield", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult setShield(Long reviewId, Integer status, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			if (customer == null) {
				throw new BusinessException("请先登录");
			} else if (customer.getIsModerator() == 0) {
				throw new BusinessException("没有此权限");
			}
			ArticleReview articleReview = articleReviewService.get(reviewId);
			articleReview.setShieldStatus(status);
			articleReviewService.update(articleReview);

			// 发送系统消息
			Article article = articleService.get(articleReview.getArticleId());
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setMessageTitle("评论屏蔽");
			customerMessage.setMessage("你评论的文章<a class='link' href='/portal/guideHome/getArticleInfo_" + article.getId()
			        + ".html'>【" + article.getTitle() + "】</a>信息已被屏蔽");
			customerMessage.setUserId(articleReview.getReviewUserId());
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + articleReview.getReviewUserId());
			result.setMessage("屏蔽成功");
			log.info("屏蔽成功");
		} catch (Exception e) {
			log.warn("屏蔽失败");
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("屏蔽失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 打赏
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/onlyReward", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult onlyReward(String userId, Long id, String type, Integer point, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			// 获取session中用户信息
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			if (customer == null) {
				throw new BusinessException("请先登录");
			}

			if (StringUtils.equals(userId, customer.getUserId())) {
				throw new BusinessException("不能给自己打赏");
			}
			// 判断积分余额
			PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
			if (pointAccount.getBalance() < point) {
				throw new BusinessException("积分余额不足");
			}

			PointTradeDto pointTradeDto = new PointTradeDto();
			pointTradeDto.setBusiId(customer.getUserId());
			pointTradeDto.setBusiType("onlyReward");
			pointTradeDto.setBusiTypeText("打赏");
			pointTradeService.pointProduce(userId, point, pointTradeDto);
			// 消费积分
			pointTradeService.pointExpense(customer.getUserId(), point, false, pointTradeDto);
			// 发送系统消息
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setMessageTitle("打赏");
			customerMessage.setMessage("用户(" + customer.getUserName() + ")打赏你" + point + "积分");
			customerMessage.setUserId(userId);
			customerMessageService.save(customerMessage);
			// 保存打赏记录
			ArticleRewardLog articleRewardLog = new ArticleRewardLog();
			articleRewardLog.setBusinessId(id);
			articleRewardLog.setPointAmount(Long.valueOf(point));
			articleRewardLog.setUserId(customer.getUserId());
			if (StringUtils.equals(type, RewardTypeEnum.article.getCode())) {
				Article article = getEntityService().get(id);
				article.setRewardCount(article.getRewardCount() + 1);
				article.setRewardTotalAmount(article.getRewardTotalAmount() + Long.valueOf(point));
				getEntityService().update(article);
				articleRewardLog.setRewardType(RewardTypeEnum.article);
			} else if (StringUtils.equals(type, RewardTypeEnum.review.getCode())) {
				articleRewardLog.setRewardType(RewardTypeEnum.review);
			}
			articleRewardLogService.save(articleRewardLog);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + userId);
			result.setMessage("打赏成功");
			log.info("打赏成功");
		} catch (Exception e) {
			log.warn("打赏失败");
			if (e instanceof BusinessException) {
				result.setMessage(e.getMessage());
			} else {
				result.setMessage("打赏失败");
			}
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 获取帖子详情(h5页面)
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/article_{id}", method = RequestMethod.GET)
	public String article(@PathVariable("id") Long id, String token, HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("id={},token={}", id, token);
		int point = 0;
		int hasRedBag = 0;
		int articleCount = 0;
		String headImg = "";
		String userName = "";
		//用户
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		if(customer == null){
			log.info("用户信息为空");
			if(Strings.isNotBlank(token)) {
				log.warn("token不为空，根据token={}，查询redis获取用户信息",token);
				String appUserId = (String) redisClientService.getRedis(token);
				log.info("appUserId={}", appUserId);
				if (Strings.isNotBlank(appUserId)) {
					log.info("根据token获取userId={}",appUserId);
					//找到customer设置Session
					customer = customerDao.findEntityByUserId(appUserId);
					if (customer == null) {
						log.info("根据token获取用户信息为空");
						//throw new BusinessException(ResultCode.FAILURE, String.format("%s,未找到用户", ResultCode.FAILURE.getMessage()));
					} else {
						log.info("customer设置Session={}", customer);
						//设置session,清除token
						log.info("根据token获取用户信息不为空，清空redis");
						request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, customer);
						redisClientService.delRedis(token);
					}
				} else {
					//throw new BusinessException(ResultCode.FAILURE, String.format("%s,用户未登录", ResultCode.FAILURE.getMessage()));
				}
			}
		}else{
			log.info("customer={}", customer);
			customer = customerDao.findEntityByUserId(customer.getUserId());
		}

		model.addAttribute("id", id);
		model.addAttribute("isLogin", customer == null ? 0 : 1);
		model.addAttribute("isShutup", customer == null ? 0 : customer.getIsShutup());
		Integer isTourGuide = customer == null ? 0 : customer.getIsTourGuide();
		log.info("isTourGuide={}", isTourGuide);
		isTourGuide = (isTourGuide == IsTourGuide.ONE_LEVEL.getKey() || isTourGuide == IsTourGuide.TWO_LEVEL.getKey()) ? 1 : 0;
		log.info("是否是导游={}", isTourGuide);
		model.addAttribute("isTourGuide", isTourGuide);
		//文章
		Article article = articleService.get(id);
		if (null == article || !ArticleStatusEnum.published.equals(article.getArticleStatus())) {
			log.info("文章不存在，或已删除！");
			throw new BusinessException(ResultCode.FAILURE, String.format("%s,文章不存在", ResultCode.FAILURE.getMessage()));
		}
		//文章作者
		if(Strings.isNotBlank(article.getUserId())){
			Customer articleCustomer = customerDao.findEntityByUserId(article.getUserId());
			if(null != articleCustomer) {
				headImg = articleCustomer == null ? "" : articleCustomer.getHeadImg();
				userName = articleCustomer == null ? "" : Strings.isBlank(articleCustomer.getUserName()) ? articleCustomer.getMobileNo() : articleCustomer.getUserName();
			}
			ArticleInfoDto articleDetails = this.getEntityService().getArticleDetailsById(id);
			articleCount = articleDetails == null ? 0 : articleDetails.getArticleCount();
		}

		if(null != customer && !StringUtils.equals(customer.getUserId(), article.getUserId())){
			RedBag redBag = redBagService.getEntityByUserIdAndRefId(article.getUserId(), article.getId());
			// 查询当前用户是否领取过当前文章的红包
			RedBagLog redBagLog = redBagLogService.getEntityByUserIdAndRefId(customer.getUserId(), article.getId());

			if ( null != redBag && redBag.getSurplusNum() > 0 &&  null == redBagLog) {
				hasRedBag = 1;
				point = CommonUtils.calculatingIntegral(redBag.getSurplusNum(), redBag.getSurplusAmount());
				redBag.setSurplusNum(redBag.getSurplusNum() - 1);
				redBag.setSurplusAmount(redBag.getSurplusAmount() - point);
				//处理积分
				executePointTrade(customer, article, redBag, point);
				//处理红包领取记录
				executeRedBagLog(customer, article, redBag, point);
			}
		}
		String html = HtmlTagUtils.replaceHtmlTag(article.getContent(), "img", "src", "nsrc=\"", "\"");
		//log.info(html);
		article.setContent(html);

		model.addAttribute("article", article);
		model.addAttribute("hasRedBag", hasRedBag);
		model.addAttribute("point", point);
		model.addAttribute("headImg", headImg);
		model.addAttribute("userName", userName);
		model.addAttribute("articleCount", articleCount);

		return "/portal/h5/articleInfo";
	}



	/**
	 * 处理积分
	 * @param customer
	 * @param article
	 * @param redBag
	 * @param point
	 */
	private void executePointTrade(Customer customer, Article article, RedBag redBag, int point){
		PointTradeDto pointTradeDto = new PointTradeDto();

		pointTradeDto.setBusiId(article.getId() + "");
		pointTradeDto.setBusiType(redBag.getRedBagType());
		pointTradeDto.setBusiTypeText("阅读帖子红包");

		pointTradeService.pointProduce(customer.getUserId(), point, pointTradeDto);
	}

	/**
	 * 处理红包领取记录
	 * @param customer
	 * @param redBag
	 * @param point
	 */
	private void executeRedBagLog(Customer customer, Article article, RedBag redBag, int point){
		RedBagLog redBagLog = new RedBagLog();

		redBagLog.setOrderNo(redBag.getOrderNo());
		redBagLog.setUserId(customer.getUserId());
		redBagLog.setRedBagType(redBag.getRedBagType());
		redBagLog.setRefId(article.getId());
		redBagLog.setTotalAmount(Long.parseLong(String.valueOf(point)));

		redBagLogService.save(redBagLog);
	}
}
