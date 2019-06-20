/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * xiyang@yiji.com 2017-05-24 10:20 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.boot.Apps;
import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Images;
import com.acooly.core.utils.Strings;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.enums.ArticleCodeTypeEnum;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.platform.dto.ArticleCountDto;
import com.acooly.zaodao.platform.dto.EnshrineDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.Action;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.platform.service.platform.PlatformTourGuideService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xiyang@yiji.com on 2017-05-24 10:20.
 */
@Slf4j
@Controller
@RequestMapping("/portal/services/guideCenter")
public class GuideCenterController extends AbstractPortalController {

    @Autowired
    private PlatformTourGuideService platformTourGuideService;

    @Autowired
    private GuideCountInfoService guideCountInfoService;

    @Autowired
    private TourGuideService tourGuideService;

    @Autowired
    private CustomerVideoService customerVideoService;

    @Autowired
    private CustomerAlbumService customerAlbumService;

    @Autowired
    private CustomerImgService customerImgService;

    @Autowired
    private OfilePortalController ofilePortalController;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ArticleOperationLogService articleOperationLogService;

    @Autowired
    private PointAccountService pointAccountService;

    /**
     * 账户信息
     */
    @RequestMapping("/accountInfo")
    public String accountConfigue(HttpServletRequest request, HttpServletResponse response, Model model) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        GuideCountInfo countInfo = guideCountInfoService.findByUserId(customer.getUserId());
        List<String> orderDays = tourGuideService.getOrderLockDays(customer.getUserId());
        List<String> restDays = tourGuideService.getGuideRestDays(customer.getUserId());
        model.addAttribute("customer", customer);
        model.addAttribute("countInfo", countInfo);
        model.addAttribute("orderDays", JSON.toJSONString(orderDays));
        model.addAttribute("restDays", JSON.toJSONString(restDays));
        return "/portal/customercenter/guide/guideAccountInfo";
    }

    /**
     * 会员中心首页
     */
    @ResponseBody
    @RequestMapping("/saveLockDays")
    public JsonResult saveLockDays(HttpServletRequest request, HttpServletResponse response, Model model,
                                   String restDays) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonResult result = new JsonResult();
        try {
            TourGuide guide = tourGuideService.getEntityByUserId(customer.getUserId());
            guide.setRestDays(restDays);
            tourGuideService.update(guide);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 会员中心(我的文章)
     */
    @ResponseBody
    @RequestMapping("/articleList")
    public JsonListResult<Article> articleList(String articleStatus, HttpServletRequest request,
                                               HttpServletResponse response, Model model, String restDays, String title, String pageNo, String pageSize) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonListResult<Article> result = new JsonListResult<Article>();
        try {
            Map<String, Object> searchMap = getSearchParams(request);
            searchMap.put("EQ_userId", customer.getUserId());
            searchMap.put("EQ_articleStatus", articleStatus);
            searchMap.put("LIKE_title", title);
            PageInfo<Article> pageInfo = new PageInfo<>();
            if (Strings.isNotBlank(pageSize)) {
                pageInfo.setCountOfCurrentPage(Integer.parseInt(pageSize));
            } else {
                pageInfo.setCountOfCurrentPage(getDefaultPageSize());
            }
            if (StringUtils.isNotBlank(pageNo)) {
                pageInfo.setCurrentPage(Integer.parseInt(pageNo));
            }
            pageInfo = articleService.query(pageInfo, searchMap, getSortMap(request));
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
            result.appendData("articleTypes", ArticleTypeEnum.mapping());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 会员中心(我的文章)
     */
    @ResponseBody
    @RequestMapping("/enshrineList")
    public JsonListResult<EnshrineDto> enshrineList(HttpServletRequest request, HttpServletResponse response,
                                                    Model model, String title, String pageNo, String pageSize) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonListResult<EnshrineDto> result = new JsonListResult<EnshrineDto>();
        try {
            PageInfo<EnshrineDto> pageInfo = new PageInfo<>();
            if (Strings.isNotBlank(pageSize)) {
                pageInfo.setCountOfCurrentPage(Integer.parseInt(pageSize));
            } else {
                pageInfo.setCountOfCurrentPage(getDefaultPageSize());
            }
            if (StringUtils.isNotBlank(pageNo)) {
                pageInfo.setCurrentPage(Integer.parseInt(pageNo));
            }
            pageInfo = platformTourGuideService.queryEnshrineList(pageInfo, customer.getUserId(), title);
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
            result.appendData("articleTypes", ArticleTypeEnum.mapping());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 会员中心(文章数量统计)
     */
    @ResponseBody
    @RequestMapping("/articleCount")
    public JsonListResult<ArticleCountDto> articleCount(HttpServletRequest request, HttpServletResponse response,
                                                        Model model) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonListResult<ArticleCountDto> result = new JsonListResult<ArticleCountDto>();
        try {
            List<ArticleCountDto> countDtos = Lists.newArrayList();
            List<ArticleTypeEnum> articleTypeEnums = ArticleTypeEnum.getAll();
            int totalCount = 0;
            for (ArticleTypeEnum articleTypeEnum : articleTypeEnums) {
                int count = articleService.countByTypeAndStatus(customer.getUserId(), articleTypeEnum.getCode(),
                        ArticleStatusEnum.published.getCode());
                if (count > 0) {
                    ArticleCountDto dto = new ArticleCountDto();
                    dto.setArticleTypeCode(articleTypeEnum.getCode());
                    dto.setArticleTypeMsg(articleTypeEnum.getMessage());
                    dto.setCount(count);
                    countDtos.add(dto);
                    totalCount = totalCount + count;
                }
            }
            result.setTotal((long) countDtos.size());
            result.appendData("totalCount", totalCount);
            result.setRows(countDtos);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 视频列表
     */
    @ResponseBody
    @RequestMapping("/myVideoList")
    public JsonListResult<CustomerVideo> myVideoList(HttpServletRequest request, HttpServletResponse response,
                                                     Model model, String pageNo, String pageSize) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonListResult<CustomerVideo> result = new JsonListResult<CustomerVideo>();
        try {
            Map<String, Object> searchMap = getSearchParams(request);
            searchMap.put("EQ_userId", customer.getUserId());

            PageInfo<CustomerVideo> pageInfo = new PageInfo<>();
            if (Strings.isNotBlank(pageSize)) {
                pageInfo.setCountOfCurrentPage(Integer.parseInt(pageSize));
            } else {
                pageInfo.setCountOfCurrentPage(getDefaultPageSize());
            }
            if (StringUtils.isNotBlank(pageNo)) {
                pageInfo.setCurrentPage(Integer.parseInt(pageNo));
            }
            pageInfo = customerVideoService.query(pageInfo, searchMap, getSortMap(request));
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 相册列表
     */
    @ResponseBody
    @RequestMapping("/albumList")
    public JsonListResult<CustomerAlbum> albumList(HttpServletRequest request, HttpServletResponse response,
                                                   Model model, String pageNo, String pageSize) {
        Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (customer == null) {
            throw new BusinessException("请先登录");
        }
        JsonListResult<CustomerAlbum> result = new JsonListResult<CustomerAlbum>();
        try {
            Map<String, Object> searchMap = getSearchParams(request);
            searchMap.put("EQ_userId", customer.getUserId());

            PageInfo<CustomerAlbum> pageInfo = new PageInfo<>();
            if (Strings.isNotBlank(pageSize)) {
                pageInfo.setCountOfCurrentPage(Integer.parseInt(pageSize));
            } else {
                pageInfo.setCountOfCurrentPage(getDefaultPageSize());
            }
            if (StringUtils.isNotBlank(pageNo)) {
                pageInfo.setCurrentPage(Integer.parseInt(pageNo));
            }
            pageInfo = customerAlbumService.query(pageInfo, searchMap, getSortMap(request));
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 相片列表
     */
    @ResponseBody
    @RequestMapping("/photoList")
    public JsonListResult<CustomerImg> photoList(HttpServletRequest request, HttpServletResponse response, Model model,
                                                 String pageNo, String pageSize, String albumId) {
        JsonListResult<CustomerImg> result = new JsonListResult<CustomerImg>();
        try {
            Map<String, Object> searchMap = getSearchParams(request);
            searchMap.put("EQ_albumId", albumId);

            CustomerAlbum customerAlbum = customerAlbumService.get(Long.parseLong(albumId));
            PageInfo<CustomerImg> pageInfo = new PageInfo<>();
            if (Strings.isNotBlank(pageSize)) {
                pageInfo.setCountOfCurrentPage(Integer.parseInt(pageSize));
            } else {
                pageInfo.setCountOfCurrentPage(getDefaultPageSize());
            }
            if (StringUtils.isNotBlank(pageNo)) {
                pageInfo.setCurrentPage(Integer.parseInt(pageNo));
            }
            pageInfo = customerImgService.query(pageInfo, searchMap, getSortMap(request));
            result.setTotal(pageInfo.getTotalCount());
            result.setRows(pageInfo.getPageResults());
            result.appendData("customerAlbum", customerAlbum);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 保存视频
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveVedio(String name, String coverImgPath, String vedioPath, HttpServletRequest request,
                                HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            if (StringUtils.isBlank(name)) {
                throw new BusinessException("视频名称不能为空");
            }
            if (StringUtils.isBlank(coverImgPath)) {
                throw new BusinessException("视频封面不能为空");
            }

            if (StringUtils.isBlank(vedioPath)) {
                throw new BusinessException("视频不能为空");
            }
            List<CustomerVideo> customerVideoList = customerVideoService.getEntityByUserId(sessionCustomer.getUserId());
            if (customerVideoList.size() >= 10) {
                throw new BusinessException("上传视频数量不能超过10个");
            }
            String userId = sessionCustomer.getUserId();
            CustomerVideo customerVideo = new CustomerVideo();
            customerVideo.setCover(coverImgPath);
            customerVideo.setUserId(userId);
            customerVideo.setVideoName(name);
            customerVideo.setCusVideo(vedioPath);
            customerVideoService.save(customerVideo);
            result.setMessage("上传视频成功");
            log.info("用户{}上传视频成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("上传失败");
            }
            result.setSuccess(false);
            log.info("上传失败");
        }
        return result;
    }

    /**
     * 修改视频信息
     *
     * @return
     */
    @RequestMapping(value = "/updateVideo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateVideo(String videoId, String videoName, String coverImgPath, HttpServletRequest request,
                                  HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            if (StringUtils.isBlank(videoName)) {
                throw new BusinessException("视频名称不能为空");
            }
            CustomerVideo customerVideo = customerVideoService.get(Long.parseLong(videoId));
            if (customerVideo == null) {
                throw new BusinessException("视频不存在");
            }
            customerVideo.setVideoName(videoName);
            if (Strings.isNotBlank(coverImgPath)) {
                customerVideo.setCover(coverImgPath);
            }
            customerVideoService.update(customerVideo);
            result.setMessage("修改视频成功");
            log.info("用户{}修改视频成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("上传失败");
            }
            result.setSuccess(false);
            log.info("上传失败");
        }
        return result;
    }

    /**
     * 用户上传图片后，保存
     *
     * @return
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadImg(String albumId, HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            JsonListResult<OnlineFile> uploadResult = ofilePortalController.upload(request, response);
            for (OnlineFile onlineFile : uploadResult.getRows()) {
                CustomerImg customerImg = new CustomerImg();
                customerImg.setAlbumId(Long.parseLong(albumId));
                customerImg.setCusPicture("/ofile/image/" + onlineFile.getId());
                customerImg.setThumbPic("/ofile/thumb/" + onlineFile.getId());
                customerImgService.save(customerImg);
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("上传失败");
            }
            result.setSuccess(false);
            log.info("上传失败");
        }
        return result;
    }

    /**
     * 创建相册
     *
     * @return
     */
    @RequestMapping(value = "/createAlbum", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult createAlbum(String albumName, HttpServletRequest request, HttpServletResponse response,
                                  Model model) {
        JsonResult result = new JsonResult();

        try {
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            CustomerAlbum customerAlbum = new CustomerAlbum();
            customerAlbum.setAlbumName(albumName);
            customerAlbum.setType(0);
            customerAlbum.setUserId(sessionCustomer.getUserId());
            customerAlbumService.save(customerAlbum);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("创建失败");
            }
            result.setSuccess(false);
            log.info("创建失败");
        }
        return result;
    }

    /**
     * 创建相册
     *
     * @return
     */
    @RequestMapping(value = "/deleteImgs", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteImgs(HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();

        try {
            Serializable[] ids = getRequestIds(request);
            customerImgService.removes(ids);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("删除失败");
            }
            result.setSuccess(false);
            log.info("删除失败");
        }
        return result;
    }

    /**
     * 修改相册名称
     *
     * @return
     */
    @RequestMapping(value = "/renameAlbum", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult renameAlbum(String albumId, String albumName, HttpServletRequest request,
                                  HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();

        try {
            CustomerAlbum customerAlbum = customerAlbumService.get(Long.parseLong(albumId));
            customerAlbum.setAlbumName(albumName);
            customerAlbumService.update(customerAlbum);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("修改失败");
            }
            result.setSuccess(false);
            log.info("修改失败");
        }
        return result;
    }

    /**
     * 删除相册
     *
     * @return
     */
    @RequestMapping(value = "/deleteAlbum", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteAlbum(String albumId, HttpServletRequest request, HttpServletResponse response,
                                  Model model) {
        JsonResult result = new JsonResult();

        try {
            customerAlbumService.removeById(Long.parseLong(albumId));
            customerImgService.removeByAlbumId(Long.parseLong(albumId));
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("删除相册");
            }
            result.setSuccess(false);
            log.info("删除相册");
        }
        return result;
    }

    /**
     * 修改相册封面
     *
     * @return
     */
    @RequestMapping(value = "/setAlbumCoverImg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setAlbumCoverImg(String albumId, String coverImgId, HttpServletRequest request,
                                       HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            CustomerAlbum customerAlbum = customerAlbumService.get(Long.parseLong(albumId));
            CustomerImg customerImg = customerImgService.get(Long.parseLong(coverImgId));
            customerAlbum.setCoverImg(customerImg.getCusPicture());
            customerAlbumService.update(customerAlbum);

            TourGuide tourGuide = tourGuideService.getEntityByUserId(sessionCustomer.getUserId());
            tourGuide.setGuideCoverImg(customerImg.getCusPicture());
            tourGuideService.update(tourGuide);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("修改失败");
            }
            result.setSuccess(false);
            log.info("修改失败");
        }
        return result;
    }

    /**
     * 写文章
     */
    @RequestMapping("/writeBlog")
    public String writeBlog(String type, String articleId, HttpServletRequest request, HttpServletResponse response,
                            Model model) {
        model.addAttribute("allArticleTypeEnums", ArticleTypeEnum.mapping());
        model.addAttribute("allArticleCodeTypeEnums", ArticleCodeTypeEnum.getAll());
        model.addAttribute("areaList", areaService.getAll());
        Customer sessionCustomer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        if (sessionCustomer == null) {
            throw new BusinessException("请先登录");
        }
        if (sessionCustomer.getIsTourGuide() != 1) {
            throw new BusinessException("非导游用户不能发帖");
        }
        PointAccount pointAccount = pointAccountService.findByUserName(sessionCustomer.getUserId());
        model.addAttribute("pointAccount", pointAccount);
        if (Strings.isNotBlank(articleId)) {
            Article article = articleService.get(Long.parseLong(articleId));
            model.addAttribute("article", article);
        }
        return "/portal/customercenter/guide/writeBlog";
    }

    /**
     * 保存文章
     */
    @ResponseBody
    @RequestMapping("/saveWriteBlog")
    public JSONObject saveWriteBlog(HttpServletRequest request, HttpServletResponse response, Long articleId,
                                    Model model, String articleStatus, String content, String area, String articleType, String label,
                                    String cover, String coverThumb, String title, String hasRedBag, Long score, Integer redBagNum) {
        JSONObject result = new JSONObject();
        try {
            Customer customer = (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (customer == null) {
                throw new BusinessException("请先登录");
            }

            customer = customerService.findEntityByUserId(customer.getUserId());
            if (1 == customer.getIsShutup()) {
                throw new BusinessException("您已被禁言，请联系管理员！");
            }
            Article article = new Article();
            if (articleId != null) {
                article = articleService.get(articleId);
            }
            article.setArea(area);
            article.setArticleStatus(ArticleStatusEnum.find(articleStatus));
            article.setArticleType(ArticleTypeEnum.find(articleType));
            article.setContent(HtmlUtils.htmlUnescape(content));
            article.setCover(cover);
            article.setCoverThumb(coverThumb);
            article.setPraiseCount(0L);
            article.setReadCount(0L);
            article.setEnshrineCount(0L);
            article.setRewardCount(0L);
            article.setRewardTotalAmount(0L);
            article.setTitle(HtmlUtils.htmlUnescape(title));
            article.setUserId(customer.getUserId());
            article.setMobileNo(customer.getMobileNo());
            article.setLabel(ArticleCodeTypeEnum.find(label));
            article.setScenic(area);
            articleService.saveWithRedBag(article, hasRedBag, score, redBagNum, customer.getUserId());
            result.put("status", "y");
            result.put("id", article.getId());
        } catch (BusinessException e) {
            result.put("status", "n");
            result.put("info", e.getMessage());
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
    public JsonResult deleteArticle(Long articleId, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        try {
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
            articleOperationLog.setReason("用户自行操作");
            articleOperationLogService.save(articleOperationLog);
            result.setMessage("删除成功");
            log.info("帖子删除成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("帖子删除失败");
            log.error("帖子删除失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/umUploadImg")
    public JSONObject umUploadImg(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        try {
            JsonListResult<OnlineFile> onlineFiles = ofilePortalController.upload(request, response);
            OnlineFile onlineFile = Collections3.getFirst(onlineFiles.getRows());
            BufferedImage bi = ImageIO.read(new FileInputStream(onlineFile.getAbsolutePath()));
            if (bi.getWidth() > 200 && bi.getHeight() > 200) {
                // 200px以上的图片添加水印
                Resource resource = Apps.getApplicationContext().getResource("classpath:static/images/shuiyin.png");
                Images.pressImage(resource.getInputStream(), onlineFile.getThumbnailAbsolutePath(), 0, 0);
            }
            result.put("name", onlineFile.getFileName());
            result.put("originalName", onlineFile.getOriginalName());
            result.put("size", onlineFile.getFileSize());
            result.put("state", "SUCCESS");
            result.put("url", "/ofile/thumb/" + onlineFile.getId());
        } catch (Exception e) {
            result.put("state", "FAIL");
            result.put("msg", "文件上传失败:" + e.getMessage());
        }
        return result;
    }
}
