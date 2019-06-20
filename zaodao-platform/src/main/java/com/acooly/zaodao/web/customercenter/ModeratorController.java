/*
 * 修订记录:
 * zhike@yiji.com 2017-07-06 11:17 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.enums.ArticleCodeTypeEnum;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.common.enums.StampEnum;
import com.acooly.zaodao.platform.dto.BlogArticleInfoDto;
import com.acooly.zaodao.platform.dto.StampCodeDto;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.entity.ArticleOperationLog;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.enums.Action;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import com.acooly.zaodao.platform.enums.ModeratorPermissionEnum;
import com.acooly.zaodao.platform.service.manage.ArticleOperationLogService;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/portal/services/moderator")
@Slf4j
public class ModeratorController extends AbstractPortalController<Article, ArticleService> {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleOperationLogService articleOperationLogService;

	@Autowired
	private CustomerMessageService customerMessageService;

	/**
	 * 获取导游之家帖子列表
	 *
	 * @param currentPageNo
	 * @param searchContent
	 * @param lable
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getModeratorArticleList", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<BlogArticleInfoDto> getBlogArticleList(Integer currentPageNo, String searchContent,
	        String lable, HttpServletRequest request, HttpServletResponse response) {
		JsonListResult<BlogArticleInfoDto> result = new JsonListResult<>();
		try {
			Customer customer = loadCustomer(request);
			String articleType = null;
			if (customer.getModeratorPermission() != ModeratorPermissionEnum.all) {
				articleType = customer.getModeratorPermission().getCode();
			}
			PageInfo<BlogArticleInfoDto> blogArticleInfoDtoPageInfo = articleService
			        .getPageEntitysByTypeAndLable(currentPageNo, articleType, lable, searchContent);
			result.setTotal(blogArticleInfoDtoPageInfo.getTotalCount());
			result.setRows(blogArticleInfoDtoPageInfo.getPageResults());
			result.setMessage("获取所有帖子列表成功");
			List<StampCodeDto> stamps = Lists.newArrayList();
			for (StampEnum stampEnum : StampEnum.getAll()) {
				StampCodeDto dto = new StampCodeDto();
				dto.setCode(stampEnum.getCode());
				dto.setMessage(stampEnum.getMessage());
				stamps.add(dto);
			}
			result.appendData("stamps", stamps);
			log.info("获取所有帖子列表成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("获取所有帖子列表失败");
			log.error("获取所有帖子列表失败");
		}
		return result;
	}

	/**
	 * 加精
	 *
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "essence", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult essence(Long articleId, Integer status, HttpServletRequest request,
	        HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			Customer customer = loadCustomer(request);
			Article article = articleService.get(articleId);
			article.setEssenceStatus(status);
			articleService.update(article);
			// 保存操作日志
			ArticleOperationLog articleOperationLog = new ArticleOperationLog();
			articleOperationLog.setAction(Action.essence);
			articleOperationLog.setArticleId(articleId);
			articleOperationLog.setOptMobileNo(customer.getMobileNo());
			articleOperationLog.setOptRealName(customer.getRealName());
			articleOperationLog.setOptUserId(customer.getUserId());
			articleOperationLog.setOptUserName(customer.getUserName());
			articleOperationLog.setTitle(article.getTitle());
			articleOperationLog.setReason(Action.essence.getMessage());
			articleOperationLogService.save(articleOperationLog);
			result.setMessage("帖子加精成功");
			log.info("帖子加精成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("帖子加精失败");
			log.error("帖子加精失败");
		}
		return result;
	}

	/**
	 * 加精
	 *
	 * @param stampCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "setStamp", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult setStamp(Long articleId, String stampCode, HttpServletRequest request,
	        HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			Customer customer = loadCustomer(request);
			Article article = articleService.get(articleId);
			article.setStampCode(stampCode);
			articleService.update(article);
			// 保存操作日志
			ArticleOperationLog articleOperationLog = new ArticleOperationLog();
			articleOperationLog.setAction(Action.setStamp);
			articleOperationLog.setArticleId(articleId);
			articleOperationLog.setOptMobileNo(customer.getMobileNo());
			articleOperationLog.setOptRealName(customer.getRealName());
			articleOperationLog.setOptUserId(customer.getUserId());
			articleOperationLog.setOptUserName(customer.getUserName());
			articleOperationLog.setTitle(article.getTitle());
			articleOperationLog.setReason(Action.setStamp.getMessage());
			articleOperationLogService.save(articleOperationLog);
			result.setMessage("帖子加精成功");
			log.info("帖子加精成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("帖子加精失败");
			log.error("帖子加精失败");
		}
		return result;
	}

	/**
	 * 置顶
	 *
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "up", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult up(Long articleId, Integer status, HttpServletRequest request, HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			Customer customer = loadCustomer(request);
			Article article = articleService.get(articleId);
			article.setUpStatus(status);
			articleService.update(article);
			// 保存操作日志
			ArticleOperationLog articleOperationLog = new ArticleOperationLog();
			articleOperationLog.setAction(Action.up);
			articleOperationLog.setArticleId(articleId);
			articleOperationLog.setOptMobileNo(customer.getMobileNo());
			articleOperationLog.setOptRealName(customer.getRealName());
			articleOperationLog.setOptUserId(customer.getUserId());
			articleOperationLog.setOptUserName(customer.getUserName());
			articleOperationLog.setTitle(article.getTitle());
			articleOperationLog.setReason(Action.up.getMessage());
			articleOperationLogService.save(articleOperationLog);
			result.setMessage("帖子置顶成功");
			log.info("帖子置顶成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("帖子置顶失败");
			log.error("帖子置顶失败");
		}
		return result;
	}

	/**
	 * 移动帖子
	 *
	 * @param label
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "moverArticle", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult moverArticle(Long articleId, String label, HttpServletRequest request,
	        HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			Customer customer = loadCustomer(request);
			Article article = articleService.get(articleId);
			article.setLabel(ArticleCodeTypeEnum.find(label));
			article.setArticleType(ArticleTypeEnum.find(article.getLabel().getType()));
			articleService.update(article);
			// 保存操作日志
			ArticleOperationLog articleOperationLog = new ArticleOperationLog();
			articleOperationLog.setAction(Action.move);
			articleOperationLog.setArticleId(articleId);
			articleOperationLog.setOptMobileNo(customer.getMobileNo());
			articleOperationLog.setOptRealName(customer.getRealName());
			articleOperationLog.setOptUserId(customer.getUserId());
			articleOperationLog.setOptUserName(customer.getUserName());
			articleOperationLog.setTitle(article.getTitle());
			articleOperationLog.setReason(Action.move.getMessage());
			articleOperationLogService.save(articleOperationLog);
			result.setMessage("帖子移动成功");
			log.info("帖子移动成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("帖子移动失败");
			log.error("帖子移动失败");
		}
		return result;
	}

	/**
	 * 删除帖子
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteArticle", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteArticle(Long articleId, String deleteReason, HttpServletRequest request,
	        HttpServletResponse response) {
		JsonResult result = new JsonResult();
		try {
			if (StringUtils.isBlank(deleteReason)) {
				throw new BusinessException("原因不能为空");
			}
			Customer customer = loadCustomer(request);
			Article article = articleService.get(articleId);
			article.setArticleStatus(ArticleStatusEnum.deleted);
			articleService.update(article);
			// 保存操作日志
			ArticleOperationLog articleOperationLog = new ArticleOperationLog();
			articleOperationLog.setAction(Action.delete);
			articleOperationLog.setArticleId(articleId);
			articleOperationLog.setOptMobileNo(customer.getMobileNo());
			articleOperationLog.setOptRealName(customer.getRealName());
			articleOperationLog.setOptUserId(customer.getUserId());
			articleOperationLog.setOptUserName(customer.getUserName());
			articleOperationLog.setTitle(article.getTitle());
			articleOperationLog.setReason(deleteReason);

			// 发送系统消息
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setMessageTitle("帖子删除");
			customerMessage.setMessage("你的帖子【" + article.getTitle() + "】因(" + deleteReason + ")已被管理员删除");
			customerMessage.setUserId(article.getUserId());
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + article.getUserId());
			result.setMessage("帖子删除成功");
			log.info("帖子删除成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("帖子删除失败");
			log.error("帖子删除失败");
		}
		return result;
	}
}
