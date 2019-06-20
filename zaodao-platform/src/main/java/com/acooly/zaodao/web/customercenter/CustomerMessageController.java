/*
 * 修订记录:
 * zhike@yiji.com 2017-06-01 14:47 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;
import com.acooly.zaodao.platform.dto.ReplyMessageDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.entity.LeaveMessage;
import com.acooly.zaodao.platform.entity.LeaveMessageReply;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.manage.ArticleReviewService;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.acooly.zaodao.platform.service.manage.LeaveMessageReplyService;
import com.acooly.zaodao.platform.service.manage.LeaveMessageService;
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
@RequestMapping(value = "/portal/services/customerMessage")
@Slf4j
public class CustomerMessageController extends AbstractPortalController<CustomerMessage, CustomerMessageService> {

	@Autowired
	private ArticleReviewService articleReviewService;

	@Autowired
	private LeaveMessageService leaveMessageService;

	@Autowired
	private LeaveMessageReplyService leaveMessageReplyService;

	@Autowired
	private CustomerMessageService customerMessageService;

	/**
	 * 获取我的消息列表
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getSysMessages", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<CustomerMessage> getSysMessages(Integer currentPageNo, Integer countOfCurrentPage,
	        HttpServletRequest request, HttpServletResponse response) {
		JsonListResult<CustomerMessage> result = new JsonListResult<>();
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		try {
			// 获取所有消息列表
			PageInfo<CustomerMessage> customerMessagePageInfo = getEntityService()
			        .getCustomerPageEntityInfo(currentPageNo, countOfCurrentPage, customer.getUserId());
			result.setTotal(customerMessagePageInfo.getTotalCount());
			result.setRows(customerMessagePageInfo.getPageResults());

			// 未读系统消息条数
			List<CustomerMessage> customerMessageList = customerMessageService
					.getNotReadEntitysByUserId(customer.getUserId());

			// 更新状态为已读
			getEntityService().updateEntityReadStatus(customer.getUserId());

			// 减少系统消息条数
			redisClientService.subRedis(SysConstant.CUSTOMER_MSG + customer.getUserId(), customerMessageList.size());
			result.setMessage("获取系统消息列表成功");
			log.info("获取用户{}获取系统消息列表成功", customer.getMobileNo());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("获取获取系统消息列表失败");
			log.error("获取用户{}获取系统消息列表失败", customer.getMobileNo());
		}
		return result;
	}

	/**
	 * 获取评论回复消息列表
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getReplyMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<ReplyMessageDto> getReplyMessage(Integer currentPageNo, Integer countOfCurrentPage,
	        HttpServletRequest request, HttpServletResponse response) {
		JsonListResult<ReplyMessageDto> result = new JsonListResult<>();
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		try {
			// 获取所有消息列表
			PageInfo<ReplyMessageDto> customerMessagePageInfo = articleReviewService
			        .getReplyMessagePageEntityInfo(currentPageNo, countOfCurrentPage, customer.getUserId());
			result.setTotal(customerMessagePageInfo.getTotalCount());
			result.setRows(customerMessagePageInfo.getPageResults());

			// 未读回复条数
			int notReadArticleReviewCount = articleReviewService.getNotReadEntitysByUserId(customer.getUserId());

			// 更新状态为已读
			articleReviewService.updateEntityReadStatus(customer.getUserId());

			// 减少系统消息条数
			redisClientService.subRedis(SysConstant.CUSTOMER_MSG + customer.getUserId(), notReadArticleReviewCount);

			result.setMessage("获取回复消息列表成功");
			log.info("获取用户{}获取回复消息列表成功", customer.getMobileNo());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("获取获取回复消息列表失败");
			log.error("获取用户{}获取回复消息列表失败", customer.getMobileNo());
		}
		return result;
	}

	/**
	 * 删除消息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteMessage(String type, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonResult result = new JsonResult();
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		try {
			if (StringUtils.equals(type, "sysMessage")) {
				this.getEntityService().removeById(id);
			} else if (StringUtils.equals(type, "replyMessage")) {
				articleReviewService.removeById(id);
			} else {
				throw new BusinessException("不用需删除类型为【{}】的消息");
			}
			result.setMessage("删除消息成功");
			log.info("用户{}删除消息成功", customer.getMobileNo());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("删除消息失败");
			log.error("用户{}删除消息失败:{}", customer.getMobileNo(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取留言消息列表
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getLeaveMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<LeaveMessageDto> getLeaveMessage(Integer currentPageNo, Integer countOfCurrentPage,
	        HttpServletRequest request, HttpServletResponse response) {
		JsonListResult<LeaveMessageDto> result = new JsonListResult<>();
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		try {
			// 获取所有消息列表
			PageInfo<LeaveMessageDto> leaveMessagePageInfo = leaveMessageService.getPageLeaveMessages(currentPageNo,
			        countOfCurrentPage, customer.getUserId());
			result.setTotal(leaveMessagePageInfo.getTotalCount());
			result.setRows(leaveMessagePageInfo.getPageResults());

			// 未读留言条数
			List<LeaveMessage> leaveMessages = leaveMessageService.getNotReadEntitysByUserId(customer.getUserId());

			// 更新状态为已读
			leaveMessageService.updateEntityReadStatus(customer.getUserId());

			// 减少系统消息条数
			redisClientService.subRedis(SysConstant.CUSTOMER_MSG + customer.getUserId(), leaveMessages.size());

			result.setMessage("获取留言消息列表成功");
			log.info("获取用户{}获取留言消息列表成功", customer.getMobileNo());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("获取获取留言消息列表失败");
			log.error("获取用户{}获取留言消息列表失败", customer.getMobileNo());
		}
		return result;
	}

	/**
	 * 回复游客留言
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "replyLeaveMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult replyLeaveMessage(Long messageId, String content, String leaveCusUserId,
	        HttpServletRequest request, HttpServletResponse response) {
		JsonResult result = new JsonResult();
		Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
		try {
			LeaveMessageReply leaveMessageReply = new LeaveMessageReply();
			leaveMessageReply.setMessageId(messageId);
			leaveMessageReply.setReplyContent(content);
			leaveMessageReply.setReplyUserId(customer.getUserId());
			leaveMessageReplyService.save(leaveMessageReply);
			// 发送系统提示
			CustomerMessage customerMessage = new CustomerMessage();
			customerMessage.setMessageTitle("留言回复");
			customerMessage.setMessage("导游<a class='link' href='/portal/guide/detail/" + leaveCusUserId + "'>【"
			        + customer.getRealName() + "】</a>回复了你的留言");
			customerMessage.setUserId(leaveCusUserId);
			customerMessageService.save(customerMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + leaveCusUserId);
			result.setMessage("回复留言成功");
			log.info("导游:{},回复留言成功", customer.getRealName());
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("回复留言失败");
			log.error("导游:{},回复留言失败", customer.getRealName());
		}
		return result;
	}

	/**
	 * 获取分页对象
	 *
	 * @param currentPage
	 * @return
	 */
	private PageInfo<LeaveMessage> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
		PageInfo<LeaveMessage> pageinfo = new PageInfo<>();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setCountOfCurrentPage(countOfCurrentPage);
		return pageinfo;
	}
}
