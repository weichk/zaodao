/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-09-22
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.module.security.domain.User;
import com.acooly.zaodao.platform.dto.LectorDto;
import com.acooly.zaodao.platform.dto.OnlineFileDtoSort;
import com.acooly.zaodao.platform.dto.QueryCourseDto;
import com.acooly.zaodao.platform.dto.RecordOnlineFile;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.service.manage.CourseService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * zd_course 管理控制器
 * 
 * @author zhike
 * Date: 2017-09-22 13:55:30
 */
@Slf4j
@Controller
@RequestMapping(value = "/manage/customer/course")
public class CourseManagerController extends AbstractJQueryEntityController<Course, CourseService> {
	

	{
		allowMapping = "*";
	}

	@Autowired
	private OfilePortalController ofilePortalController;

	@Autowired
	private OFileProperties oFileProperties;

	@SuppressWarnings("unused")
	@Autowired
	private CourseService courseService;

	@Autowired
	private CustomerService customerService;

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allCourseStatuss", CourseStatusEnum.mapping());
	}

    /**
     * 导出标题
     * @return
     */
    protected List<String> getExportTitles() {
        return Lists.newArrayList(new String[]{"课程ID", "会员唯一标识","课程标题", "课程简介", "课程状态", "课程价格", "发布时间", "创建时间", "修改时间"});
    }
    /**
     * 导出功能(调整显示列顺序)
     * @param entity
     * @return
     */
    protected List<String> doExportEntity(Course entity) {
        List<String> row = Lists.newArrayList();
        row.add(entity.getId().toString());
        row.add(entity.getUserId());
        row.add(entity.getCourseTitle());
        row.add(entity.getCourseIntro());
        row.add(entity.getCourseStatus() == null ? "" : entity.getCourseStatus().getMessage());
        row.add(entity.getCoursePrice() == null ? "0" : entity.getCoursePrice().toString());
        row.add(entity.getPublishTime() == null ? "" : Dates.format(entity.getPublishTime()));
        row.add(entity.getCreateTime() == null ? "" : Dates.format(entity.getCreateTime()));
        row.add(entity.getUpdateTime() == null ? "" : Dates.format(entity.getUpdateTime()));

        return row;
    }

	@Override
	protected Course onSave(HttpServletRequest request, HttpServletResponse response, Model model, Course entity, boolean isCreate) throws Exception {
		entity.setCourseTitle(HtmlUtils.htmlUnescape(entity.getCourseTitle()));
		entity.setCourseIntro(HtmlUtils.htmlUnescape(entity.getCourseIntro()));

		if(request instanceof MultipartHttpServletRequest) {
			JsonListResult<OnlineFile> onlineFiles = ofilePortalController.upload(request, response);
			OnlineFile onlineFile = Collections3.getFirst(onlineFiles.getRows());
			if (onlineFile != null) {
				entity.setCourseImg("/ofile/image/" + onlineFile.getId());
				log.info("重新设置封面图成功");
			}
		}
		return super.onSave(request, response, model, entity, isCreate);
	}

	/**
	 * 报名课程列表
	 */
	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	public String courseList(){
		return "manage/customer/courseList";
	}

	/**
	 * 获取报名课程列表
	 */
	@RequestMapping(value = "/getCourseList", method = RequestMethod.POST)
	@ResponseBody
	public JsonListResult<Course> getCourseList(HttpServletRequest request, Model model, QueryCourseDto queryCourseDto){
		JsonListResult<Course> result = new JsonListResult();
		try {
			PageInfo<Course> pageInfo = courseService.queryPageCourseList(queryCourseDto);
			result.setTotal(pageInfo.getTotalCount());
			result.setRows(pageInfo.getPageResults());
			Map<Object, Object> map = Maps.newHashMap();
			map.put("allCourseStatuss", CourseStatusEnum.mapping());
			result.setData(map);
		} catch (Exception var5) {
			this.handleException(result, "分页查询", var5);
		}
		return result;
	}

	/**
	 * 新增报名课程
	 */
	@RequestMapping(value = "/courseCreate", method = RequestMethod.GET)
	public String courseCreate(Model model){
		List<LectorDto> lectors = customerService.getByIsLector(1);
		model.addAttribute("lectors", lectors);
		model.addAttribute("action", "create");

		return "manage/customer/courseCreate";
	}

	/**
	 * 编辑报名课程
	 */
	@RequestMapping(value = "/courseEdit", method = RequestMethod.GET)
	public String courseEdit(Long id, Model model){
		List<LectorDto> lectors = customerService.getByIsLector(1);
		model.addAttribute("lectors", lectors);
		model.addAttribute("action", "edit");

		Course course = courseService.get(id);
		model.addAttribute("course", course);

		return "manage/customer/courseCreate";
	}

	/**
	 * 报名课程详情
	 */
	@RequestMapping(value = "/courseDetail", method = RequestMethod.GET)
	public String courseDetail(Long id, Model model){
		Course course = courseService.get(id);
		Customer customer = customerService.getUser(course.getUserId());
		course.setRealName(customer.getRealName());
		model.addAttribute("course", course);

		return "manage/customer/courseDetail";
	}

	/**
	 * 制作课程
	 */
	@RequestMapping(value = "/courseMake", method = RequestMethod.GET)
	public String courseMake(Long id, HttpServletRequest request, Model model){
		List<LectorDto> lectors = customerService.getByIsLector(1);
		model.addAttribute("lectors", lectors);
		model.addAttribute("action", "edit");

		Course course = courseService.get(id);
		model.addAttribute("course", course);
		Map<String, Object> map = Maps.newHashMap();
		map.put("allCourseStatuss", CourseStatusEnum.mapping());

		Map<String, String> partMap = Maps.newLinkedHashMap();
		partMap.put(CourseStatusEnum.draft.getCode(), CourseStatusEnum.draft.getMessage());
		partMap.put(CourseStatusEnum.published.getCode(), CourseStatusEnum.published.getMessage());
		map.put("partCourseStatuss", partMap);
		model.addAllAttributes(map);

		return "manage/customer/courseMake";
	}

	/**
	 * 添加报名课程
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public JsonEntityResult<Course> saveCourse(HttpServletRequest request, Course course) {
		JsonEntityResult result = new JsonEntityResult();
		try {
			User user = (User)request.getSession().getAttribute("user");
			courseService.addCourse(course);
			result.setMessage("添加报名课程成功");
			log.info("[{}]添加报名课程[{}]成功", user.getUsername(),course.getId());
		} catch (Exception var5) {
			this.handleException(result, "添加报名课程", var5);
		}
		return result;
	}


	/**
	 * 修改报名课程
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
	public JsonEntityResult<Course> updateCourse(HttpServletRequest request, Course course) {
		JsonEntityResult result = new JsonEntityResult();
		try {
			User user = (User)request.getSession().getAttribute("user");
			courseService.updateCourse(course);
			log.info("[{}]修改报名课程[{}]成功", user.getUsername(), course.getId());
			result.setMessage("修改报名课程成功");
		} catch (Exception var5) {
			this.handleException(result, "修改报名课程", var5);
		}
		return result;
	}

	/**
	 *课程音频
	 */
	private static final String NAME_MAKE_RECORD_FILE  = "course_make_record_file";

	/**
	 * 课程封面
	 */
	private static final String NAME_MAKE_COURSE_IMG = "course_make_course_img";

	/**
	 * AMR音频格式
	 */
	private static final String AUDIO_AMR = "AMR";

	/**
	 * 保存制作报名课程
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCourseMake", method = RequestMethod.POST)
	public JsonEntityResult<Course> saveCourseMake(Course course, HttpServletRequest request, HttpServletResponse response) {
		JsonEntityResult result = new JsonEntityResult();
		try {
			User user = (User)request.getSession().getAttribute("user");
			log.info("[{}]保存制作报名课程[{}]", user.getUsername(),course.getId());
			List<RecordOnlineFile> listRecordOfile = null;
			OnlineFile courseImgOflie = null;

			if(request instanceof MultipartHttpServletRequest){
				oFileProperties.setMaxSize(5242880L * 20);	//100MB
				JsonListResult<OnlineFile> uploadResult = ofilePortalController.upload(request, response);
				listRecordOfile = getRecordOFileList(uploadResult.getRows(), NAME_MAKE_RECORD_FILE);
				courseImgOflie = getCourseImgOFile(uploadResult.getRows(), NAME_MAKE_COURSE_IMG);
			}
			if(listRecordOfile == null || listRecordOfile.size() <= 0){
				throw new BusinessException("请添加课程文件");
			}else if(listRecordOfile.size() > 5){
				throw new BusinessException("最多只能添加5个课程文件");
			}
			if(courseImgOflie == null){
				throw new BusinessException("请添加课程封面");
			}
			getAudioSeconds(listRecordOfile);
			courseService.updateMakeCourse(course, listRecordOfile, courseImgOflie);
			result.setMessage("制作报名课程成功");
		} catch (Exception var5) {
			this.handleException(result, "制作报名课程", var5);
		}
		return result;
	}

	@Value("${acooly.ofile.storageRoot}")
	private String storageRoot;

	private void getAudioSeconds(List<RecordOnlineFile> listRecordOfile) {
		if(listRecordOfile != null && listRecordOfile.size() > 0) {
			listRecordOfile.forEach(o -> {
				String pathname =  o.getAbsolutePath();
				log.info("pathname = {}", pathname);
				File file = new File(pathname);
				long duration = 0;
				try {
					if(Files.getFileExtension(pathname).toUpperCase().equals(AUDIO_AMR)){
						duration = getAmrDuration(file);
						log.info("读AMR音频文件： url={}, duration={}", o.getAbsolutePath(), duration);
					}else {
						duration = getAudioDuration(file);
						log.info("读一般音频文件： url={}, duration={}", o.getAbsolutePath(), duration);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				o.setRecordTime(duration);
			});
		}
	}

	/**
	 * 读取一般音频时长
	 */
	public long getAudioDuration(File file) throws EncoderException {
		Encoder encoder = new Encoder();
		MultimediaInfo m = encoder.getInfo(file);
		long ls = m.getDuration();
		long duration = ls / 1000;
		return duration;
	}

	/**
	 * 读取Amr音频时长
	 */
	public long getAmrDuration(File file) throws IOException {
		long duration = -1;
		int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0 };
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			long length = file.length();	//文件的长度
			int pos = 6;					//设置初始位置
			int frameCount = 0;				//初始帧数
			int packedPos = -1;

			byte[] datas = new byte[1];		//初始数据值
			while (pos <= length) {
				randomAccessFile.seek(pos);
				if (randomAccessFile.read(datas, 0, 1) != 1) {
					duration = length > 0 ? ((length - 6) / 650) : 0;
					break;
				}
				packedPos = (datas[0] >> 3) & 0x0F;
				pos += packedSize[packedPos] + 1;
				frameCount++;
			}
			duration += frameCount * 20;	//帧数 * 20
		} finally {
			if (randomAccessFile != null) {
				randomAccessFile.close();
			}
		}
		return duration;
	}

	/**
	 * 获取音频上传文件信息
	 */
	private List<RecordOnlineFile> getRecordOFileList(List<OnlineFile> list, String inputName){
		List<RecordOnlineFile> recordOnlineFiles = Lists.newArrayList();
		//排序
		list.sort(new OnlineFileDtoSort());
		for (OnlineFile onlineFile : list) {
			if(onlineFile.getInputName().indexOf(inputName) > -1){
				RecordOnlineFile recordOnlineFile = new RecordOnlineFile();
				BeanCopier.copy(onlineFile, recordOnlineFile);
				Integer index = recordOnlineFiles.size() + 1;
				String title = String.format("第%s小节", index);
				recordOnlineFile.setRecordTitle(title);
				recordOnlineFiles.add(recordOnlineFile);
			}
		}
		return recordOnlineFiles;
	}

	/**
	 * 获取课程封面上传文件信息
	 */
	private OnlineFile getCourseImgOFile(List<OnlineFile> list, String inputName){
		for (OnlineFile onlineFile : list) {
			if(onlineFile.getInputName().equals(inputName)){
				return onlineFile;
			}
		}
		return null;
	}
}
