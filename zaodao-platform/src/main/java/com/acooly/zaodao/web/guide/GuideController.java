/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * xiyang@yiji.com 2017-05-24 10:20 创建
 *
 */
package com.acooly.zaodao.web.guide;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Dates;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.platform.dto.CityHotGuideDto;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.platform.service.platform.PlatformTourGuideService;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.portal.dto.TourGuideSearchDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by xiyang@yiji.com on 2017-05-24 10:20.
 */

@Controller
@RequestMapping("/portal/guide")
public class GuideController extends AbstractPortalController {

	@Autowired
	private PlatformTourGuideService platformTourGuideService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private TourGuideService tourGuideService;

	@Autowired
	private CustomerImgService customerImgService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private LeaveMessageService leaveMessageService;

	@Autowired
	private TourGradeService tourGradeService;

	@Autowired
	private OfilePortalController ofilePortalController;

	@Autowired
	private CustomerVideoService customerVideoService;

	/**
	 * 导游搜索结果
	 */
	@ResponseBody
	@RequestMapping("/resultList")
	public JsonResult resultList(HttpServletRequest request, HttpServletResponse response, Model model, String keywords,
	        String province, String city, String tripTime, String level, String gender, String language, Integer pageNo,
	        Integer pageSize) {
		JsonResult result = new JsonResult();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		TourGuideSearchDto guideDto = new TourGuideSearchDto();
		guideDto.setKeywords(keywords);
		guideDto.setProvince(province);
		guideDto.setCity(city);
		guideDto.setTripTime(Dates.format(tripTime));
		guideDto.setLevel(level);
		guideDto.setGender(gender);
		guideDto.setLanguage(language);
		PageInfo<TourGuideDto> pageInfo = initPageInfo(pageNo, pageSize);
		pageInfo = platformTourGuideService.queryByConditions(pageInfo, guideDto);
		result.appendData("pageList", pageInfo);
		return result;
	}

	/**
	 * 导游详情
	 */
	@RequestMapping("/detail/{userId}")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
	        @PathVariable String userId) {
		TourGuideDto dto = platformTourGuideService.findByUserId(userId);
		List<CustomerImg> imgList = customerImgService.getCusDefaultImgListByUserId(userId);
		List<CustomerVideo> customerVideos = customerVideoService.getEntityByUserId(userId);
		List<TourGrade> tourGrades = tourGradeService.findByGuideUserId(userId);
		List<String> orderDays = tourGuideService.getOrderLockDays(userId);
		List<String> restDays = tourGuideService.getGuideRestDays(userId);
		List<String> lockedDays = Lists.newArrayList();
		lockedDays.addAll(orderDays);
		lockedDays.addAll(restDays);
		model.addAttribute("guide", dto);
		model.addAttribute("imgList", imgList);
		model.addAttribute("tourGrades", tourGrades);
		model.addAttribute("orderDays", JSON.toJSONString(orderDays));
		model.addAttribute("restDays", JSON.toJSONString(restDays));
		model.addAttribute("lockedDays", JSON.toJSONString(lockedDays));
		model.addAttribute("video", Collections3.getFirst(customerVideos));
		return "/portal/guide/guideDetail";
	}

	/**
	 * 留言列表
	 */
	@ResponseBody
	@RequestMapping("/guideArticleList")
	public JsonListResult<Article> guideArticleList(HttpServletRequest request, HttpServletResponse response,
	        Model model, String guideUserId, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		JsonListResult<Article> result = new JsonListResult<>();
		PageInfo<Article> pageInfo = initPageInfo(pageNo, pageSize);
		Map<String, Object> searchMap = getSearchParams(request);
		searchMap.put("EQ_userId", guideUserId);
		pageInfo = articleService.query(pageInfo, searchMap, null);
		result.setTotal(pageInfo.getTotalCount());
		result.setRows(pageInfo.getPageResults());
		result.appendData("hasNext", pageInfo.hasNext());
		return result;
	}

	/**
	 * 留言列表
	 */
	@ResponseBody
	@RequestMapping("/leaveMsgList")
	public JsonListResult<LeaveMessageDto> leaveMsgList(HttpServletRequest request, HttpServletResponse response,
	        Model model, String guideUserId, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		JsonListResult<LeaveMessageDto> result = new JsonListResult<>();
		PageInfo<LeaveMessageDto> pageInfo = leaveMessageService.getPageLeaveMessages(pageNo, pageSize, guideUserId);
		result.setTotal(pageInfo.getTotalCount());
		result.setRows(pageInfo.getPageResults());
		result.appendData("hasNext", pageInfo.hasNext());
		return result;
	}

	/**
	 * 人气导游页面
	 */
	@RequestMapping("/hotGuide/{city}")
	public String hotGuide(HttpServletRequest request, HttpServletResponse response, Model model,
	        @PathVariable String city) {
		model.addAttribute("city", city);
		return "/portal/guide/hotGuide";
	}

	/**
	 * 导游搜索结果
	 */
	@ResponseBody
	@RequestMapping("/hotGuideList")
	public JsonResult hotGuideList(HttpServletRequest request, HttpServletResponse response, Model model, String city,
	        Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 4;
		}
		JsonResult result = new JsonResult();
		boolean hasNext = false;
		List<CityHotGuideDto> dataList = Lists.newArrayList();
		Area area = areaService.findByCode(city);

		if (area == null) {
			// 查询城市分类导游
			pageNo = 1;
			pageSize = 4;
			List<Area> areaList = areaService.getAll();
			for (Area ar : areaList) {
				CityHotGuideDto dto = new CityHotGuideDto();
				dto.setCity(ar.getName());
				dto.setCityCode(ar.getCode());
				List<TourGuideDto> list = getCityHotGuides(ar.getName(), pageNo, pageSize).getPageResults();
				dto.setGuides(list);
				if (list != null && list.size() > 0) {
					dataList.add(dto);
				}
			}
			result.appendData("dataList", dataList);
			result.appendData("hasNext", hasNext);
		} else {

			// 查询单独城市导游
			PageInfo<TourGuideDto> pageInfo = getCityHotGuides(area.getName(), pageNo, pageSize);
			if (pageInfo != null) {
				CityHotGuideDto dto = new CityHotGuideDto();
				dto.setCity(area.getName());
				if (pageInfo.getCurrentPage() < pageInfo.getTotalPage()) {
					hasNext = true;
				}
				dto.setCityCode(area.getCode());
				dto.setGuides(pageInfo.getPageResults());
				dataList.add(dto);
			}
			result.appendData("dataList", dataList);
			result.appendData("hasNext", hasNext);
		}
		return result;
	}

	/**
	 * 人气导游（首页）
	 */
	@RequestMapping("/popularTours")
	public String popularTours(HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer pageNo = 1;
		Integer pageSize = 8;
		PageInfo<TourGuideDto> pageInfo = getCityHotGuides(null, pageNo, pageSize);
		model.addAttribute("dataList", pageInfo.getPageResults());
		return "/portal/portlet/popular_tours";
	}

	private PageInfo<TourGuideDto> getCityHotGuides(String city, Integer pageNo, Integer pageSize) {
		TourGuideSearchDto guideDto = new TourGuideSearchDto();
		guideDto.setHotGuide(1);
		guideDto.setCity(city);
		PageInfo<TourGuideDto> pageInfo = initPageInfo(pageNo, pageSize);
		pageInfo = platformTourGuideService.queryByConditions(pageInfo, guideDto);
		if (pageInfo != null) {
			return pageInfo;
		} else {
			return null;
		}
	}

	/**
	 * 游客给导游留言
	 */
	@ResponseBody
	@RequestMapping("/leaveMsg")
	public JSONObject leaveMsg(HttpServletRequest request, HttpServletResponse response, Model model,
	        String guideUserId, String content) {
		JSONObject result = new JSONObject();
		try {
			Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
			if (customer == null) {
				throw new BusinessException("请先登录");
			}

			TourGuide guide = tourGuideService.getEntityByUserId(guideUserId);
			if (guide == null) {
				throw new BusinessException("导游信息不存在");
			}
			LeaveMessage leaveMessage = new LeaveMessage();
			leaveMessage.setContent(content);
			leaveMessage.setLeaveId(customer.getUserId());
			leaveMessage.setUserId(guideUserId);
			leaveMessage.setReadStatus(0);
			leaveMessageService.save(leaveMessage);
			redisClientService.addRedis(SysConstant.CUSTOMER_MSG + guideUserId);
			result.put("status", "y");
		} catch (Exception e) {
			result.put("status", "n");
			result.put("info", e.getMessage());
		}
		return result;
	}

	@RequestMapping("layedit")
	@ResponseBody
	public JSONObject layedit(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			JsonListResult<OnlineFile> onlineFiles = ofilePortalController.upload(request, response);
			OnlineFile onlineFile = Collections3.getFirst(onlineFiles.getRows());
			JSONObject data = new JSONObject();
			data.put("src", "/ofile/thumb/" + onlineFile.getId());
			data.put("title", "");
			result.put("code", 0);
			result.put("data", data);
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "文件上传失败:" + e.getMessage());
		}
		return result;
	}
}
