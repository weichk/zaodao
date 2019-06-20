/*
 * 修订记录:
 * zhike@yiji.com 2017-05-25 11:56 创建
 *
 */
package com.acooly.zaodao.web.customercenter;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.domain.PointGrade;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointGradeService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.common.AbstractPortalController;
import com.acooly.zaodao.common.CommonUtils;
import com.acooly.zaodao.gateway.gsy.message.*;
import com.acooly.zaodao.gateway.gsy.message.enums.PublicTagEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.Purpose;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.platform.dto.SessionGuideInfoDto;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.GuideLevel;
import com.acooly.zaodao.platform.enums.LanguageTypeEnum;
import com.acooly.zaodao.platform.enums.TourRank;
import com.acooly.zaodao.platform.service.manage.*;
import com.acooly.zaodao.web.customercenter.request.ApplyGuideRequest;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 修订记录： 会员中心
 *
 * @author zhike@yiji.com
 */
@Controller
@RequestMapping(value = "/portal/services/customer")
@Slf4j
public class CustomerCenterController extends AbstractPortalController<Customer, CustomerService> {

    @Autowired
    private CustomerCardService customerCardService;

    @Autowired
    private OfilePortalController ofilePortalController;

    @Autowired
    private CustomerAlbumService customerAlbumService;

    @Autowired
    private CustomerImgService customerImgService;

    @Autowired
    private TourGuideService tourGuideService;

    @Autowired
    private PointAccountService pointAccountService;

    @Autowired
    private CreditSigninService creditSigninService;

    @Autowired
    private OFileProperties oFileProperties;

    @Autowired
    private CustomerVideoService customerVideoService;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private PointGradeService pointGradeService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private ArticleReviewService articleReviewService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private AccountService accountService;

    /**
     * 会员中心首页
     */
    @RequestMapping("/customeIndex")
    public String travelStrategy(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        Customer customer =
                (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        // 更新用户信息
        customer = customerService.findEntityByUserId(customer.getUserId());
        request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, customer);
        // 默认加载账户信息
        model.addAttribute("childIndex", "accountConfigue");
        SessionGuideInfoDto sessionGuideInfoDto = new SessionGuideInfoDto();
        sessionGuideInfoDto.setIsGuide(customer.getIsTourGuide());
        // 是否为导游
        model.addAttribute("isGuide", customer.getIsTourGuide());
        if (customer.getIsTourGuide() == 1) {
            TourGuide tourGuide = tourGuideService.getEntityByUserId(customer.getUserId());
            sessionGuideInfoDto.setTourRank(tourGuide.getTourRank().getMessage());
            request.getSession().setAttribute(SysConstant.SESSION_KET_GUIDEINFO, sessionGuideInfoDto);
        }
        // 获取签到信息
        CreditSignin creditSignin = creditSigninService.isCerditSignin(customer);
        model.addAttribute("creditSignin", creditSignin);

        // 获取积分等级信息
        PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
        PointGrade pointGrade = pointGradeService.getSectionPoint(pointAccount);
        model.addAttribute("pointAccount", pointAccount);
        model.addAttribute("pointGrade", pointGrade);
        if (customer.getIsTourGuide() != 1) {
            return "redirect:/services/customer/customeIndex.html?childIndex=accountConfigue";
        }
        return "/portal/customercenter/index";
    }

    @RequestMapping("/childpage")
    public String childPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        Customer customer =
                (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        // 更新用户信息
        customer = customerService.findEntityByUserId(customer.getUserId());
        request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, customer);
        String childIndex = request.getParameter("childIndex");
        // 是否为导游
        model.addAttribute("isGuide", customer.getIsTourGuide());
        if (StringUtils.isEmpty(childIndex)) {
            model.addAttribute("childIndex", "accountInfo");
        } else {
            model.addAttribute("childIndex", childIndex);
        }
        // 获取签到信息
        if (StringUtils.equals(childIndex, "accountConfigue")
                || StringUtils.equals(childIndex, "accountInfo")) {
            CreditSignin creditSignin = creditSigninService.isCerditSignin(customer);
            model.addAttribute("creditSignin", creditSignin);
        }
        // 获取积分等级信息
        PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
        PointGrade pointGrade = pointGradeService.getSectionPoint(pointAccount);
        model.addAttribute("pointAccount", pointAccount);
        model.addAttribute("pointGrade", pointGrade);
        // 获取未读消息条数
        if (StringUtils.equals(childIndex, "myGuideMessage")) {
            // 未读留言条数
            List<LeaveMessage> leaveMessages =
                    leaveMessageService.getNotReadEntitysByUserId(customer.getUserId());
            model.addAttribute("notReadLeaveMesCount", leaveMessages.size());
            // 未读系统消息条数
            List<CustomerMessage> customerMessageList =
                    customerMessageService.getNotReadEntitysByUserId(customer.getUserId());
            model.addAttribute("notReadCusMesCount", customerMessageList.size());
            // 未读帖子留言条数
            int notReadArticleReviewCount =
                    articleReviewService.getNotReadEntitysByUserId(customer.getUserId());
            model.addAttribute("notReadArticleReviewCount", notReadArticleReviewCount);
        }
        // 获取会员余额和绑卡信息
        if (StringUtils.equals(childIndex, "deductWithdraw")
                || StringUtils.equals(childIndex, "accountInfo")) {
            // 获取绑卡信息
            CustomerCard customerCard = customerCardService.getEntityByUserId(customer.getUserId());
            if (customerCard != null) {
                String tailNum =
                        customerCard
                                .getCardNo()
                                .substring(
                                        customerCard.getCardNo().length() - 4, customerCard.getCardNo().length());
                customerCard.setShowCardNo(tailNum);
                model.addAttribute("customerCard", customerCard);
            }
            // 获取账户余额
            String balance = "0.00";

            Account account = accountService.findByUserId(customer.getUserId());

            if (account != null) {
                balance = Money.cent(account.getBalance()).toString();
            }
//      CustomerQueryRequest customerQueryRequest = new CustomerQueryRequest();
//      customerQueryRequest.setMerchOrderNo(Ids.getDid());
//      customerQueryRequest.setUserId(customer.getUserId());
//      customerQueryRequest.setIsAccountInfo(true);
//      CustomerQueryResponse customerQueryResponse =
//          gsyBusinessService.customerQuery(customerQueryRequest);
//      if (customerQueryResponse.getStatus() == ResultStatus.success) {
//        balance = customerQueryResponse.getAccountInfo().getEffectiveBalance().toStandardString();
//      }
            model.addAttribute("balance", balance);
        }
        return "/portal/customercenter/index";
    }

    @RequestMapping("/applyGuideStepOne")
    public String applyGuideStepOne(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Area> areaList = areaService.getAll();
        model.addAttribute("areaList", areaList);
        return "/portal/customercenter/tourist/apply_guide_step_one";
    }

    /**
     * 保存申请导游信息第一步
     *
     * @return
     */
    @RequestMapping(value = "/setApplyGuideInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setApplyGuideInfo(
            ApplyGuideRequest applyGuideRequest, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            // 校验参数
            checkRequestInfo(applyGuideRequest);
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer.getIsTourGuide() != 0) {
                throw new BusinessException("已经申请为导游不能重复申请");
            }
            // 更新用户信息
            sessionCustomer.setAge(applyGuideRequest.getAge());
            sessionCustomer.setSex(applyGuideRequest.getSex());
            sessionCustomer.setRealName(applyGuideRequest.getRealName());
            // 保存导游信息
            TourGuide tourGuide = new TourGuide();
            tourGuide.setGoodRoute(applyGuideRequest.getFamiliarLine());
            tourGuide.setGuideNo(applyGuideRequest.getGuideNo());
            tourGuide.setPermanentCity(applyGuideRequest.getCity());
            tourGuide.setIntroduceMyself(applyGuideRequest.getIntroduce());
            tourGuide.setUserId(sessionCustomer.getUserId());
            tourGuide.setTourTime(applyGuideRequest.getWorkAge());
            tourGuide.setGuideCertificateNo(applyGuideRequest.getGuideCertificateNo());
            tourGuide.setGuideCertificateImg(applyGuideRequest.getGuideCertificateImg());
            tourGuide.setTourRank(TourRank.copperMedal);
            tourGuide.setSpeciality(applyGuideRequest.getSpeciality());
            tourGuide.setStarLevel(1);
            tourGuide.setHotGuide(0);
            tourGuide.setGuideLevel(GuideLevel.general);
            tourGuide.setPricePerDay(applyGuideRequest.getPricePerDay() * 100);
            // 保存导游语言
            List<CustomerLanguage> customerLanguageList = Lists.newArrayList();
            String guideLanguage = "";
            for (String language : applyGuideRequest.getLanguage()) {

                guideLanguage += LanguageTypeEnum.find(language).getMessage() + "、";
            }
            if (guideLanguage.length() > 1) {
                guideLanguage = guideLanguage.substring(0, guideLanguage.length() - 1);
            }
            tourGuide.setLanguage(guideLanguage);
            this.getEntityService().setApplyGuideInfo(sessionCustomer, tourGuide, customerLanguageList);
            request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, sessionCustomer);
            request.getSession().setAttribute("apply_guide_step_one", "yes");
            log.info("用户{}申请导游第一步成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            log.warn("申请导游失败");
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("申请导游失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/applyGuideStepTwo")
    public String applyGuideStrpTwo(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        String applyGuideStepOneIndex =
                (String) request.getSession().getAttribute("apply_guide_step_one");
        if (!StringUtils.equals(applyGuideStepOneIndex, "yes")) {
            return "redirect:/portal/services/customer/applyGuideStepOne";
        }
        return "/portal/customercenter/tourist/apply_guide_step_two";
    }

    /**
     * 上传导游封面图
     *
     * @return
     */
    @RequestMapping(value = "/uploadGuideConverImg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadGuideConverImg(
            String coverImg, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }

            String image = request.getParameter("coverImg");
            String reviewPath = image;

            if (image.contains("data:image/jpeg;base64,")) {
                // 去除base64前缀
                image = image.replace("data:image/jpeg;base64,", "");
                String fileName = Ids.getDid();
                // 实际保存路径
                String imagePath =
                        oFileProperties.getStorageRoot()
                                + sessionCustomer.getUserId()
                                + File.separator
                                + fileName
                                + ".png";
                // 访问路径
                reviewPath =
                        oFileProperties.getServerRoot()
                                + File.separator
                                + sessionCustomer.getUserId()
                                + File.separator
                                + fileName
                                + ".png";
                File pathFloder = new File(oFileProperties.getStorageRoot() + sessionCustomer.getUserId());
                if (!pathFloder.exists()) {
                    pathFloder.mkdirs();
                }
                GenerateImage(image, imagePath);
            }
            String userId = sessionCustomer.getUserId();
            TourGuide tourGuide = tourGuideService.getEntityByUserId(userId);
            tourGuide.setGuideCoverImg(reviewPath);
            tourGuideService.update(tourGuide);
            result.setMessage("上传封面图成功");
            log.info("用户{}上传封面图成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            log.info("上传封面图失败");
            result.setMessage("上传封面图失败");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 上传照片
     *
     * @return
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadPicture(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            String userId = sessionCustomer.getUserId();
            JsonListResult<OnlineFile> uploadResult = ofilePortalController.upload(request, response);
            for (OnlineFile onlineFile : uploadResult.getRows()) {
                customerAlbumService.uploadDefaulAlbum(
                        userId, onlineFile.getAccessUrl(), onlineFile.getThumbnailAccessUrl());
                result.setMessage("上传照片成功");
                log.info("文件访问路径路径：{}", onlineFile.getAccessUrl());
                model.addAttribute(
                        "pictureList",
                        customerImgService.getCusDefaultImgListByUserId(sessionCustomer.getUserId()));
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("上传失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 保存视频
     *
     * @return
     */
    @RequestMapping(value = "/saveVedio", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveVedio(
            String name,
            String coverImgPath,
            String vedioPath,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
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
            List<CustomerVideo> customerVideoList =
                    customerVideoService.getEntityByUserId(sessionCustomer.getUserId());
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
     * 上传视频
     *
     * @return
     */
    @RequestMapping(value = "/uploadVedioFile", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadVedioFile(
            String type, HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            if (type.startsWith("video")) {
                oFileProperties.setAllowExtentions("mp4,flv,txt,zip,csv,xls,word,jpg,gif,png");
                oFileProperties.setMaxSize(5242880L * 4);
            }
            JsonListResult<OnlineFile> uploadResult = ofilePortalController.upload(request, response);
            for (OnlineFile onlineFile : uploadResult.getRows()) {
                result.appendData("url", onlineFile.getAccessUrl());
                result.setMessage("上传文件成功");
                log.info("文件访问路径路径：{}", onlineFile.getAccessUrl());
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("上传失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除视频
     *
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteVideo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteVideo(
            Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            customerVideoService.removeById(id);
            result.setMessage("删除成功");
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("删除失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 获取默认相册图片列表
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultPictureList", method = RequestMethod.POST)
    @ResponseBody
    public JsonListResult<CustomerImg> getDefaultPictureList(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonListResult<CustomerImg> result = new JsonListResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            List<CustomerImg> customerImgList =
                    customerImgService.getCusDefaultImgListByUserId(sessionCustomer.getUserId());
            result.setRows(customerImgList);
            result.setMessage("获取默认照片列表成功");
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("获取默认照片列表失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 获取用户视频列表
     *
     * @return
     */
    @RequestMapping(value = "/getCusVideoList", method = RequestMethod.POST)
    @ResponseBody
    public JsonListResult<CustomerVideo> getCusVideoList(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonListResult<CustomerVideo> result = new JsonListResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            List<CustomerVideo> customerVideos =
                    customerVideoService.getEntityByUserId(sessionCustomer.getUserId());
            result.setRows(customerVideos);
            result.setMessage("获取用户视频列表成功");
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("获取用户视频列表失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除照片
     *
     * @return
     */
    @RequestMapping(value = "/deletePicture", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deletePicture(
            Long id, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        try {
            customerImgService.removeById(id);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setMessage("删除失败");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 校验申请第二步资料是否完整
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/verifyStepTwoInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult verifyStepTwoInfo(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            Customer customer = loadCustomer(request);
            // //校验封面图是否上传
            // TourGuide tourGuide =
            // tourGuideService.getEntityByUserId(customer.getUserId());
            // if (StringUtils.isBlank(tourGuide.getGuideCoverImg())) {
            // throw new BusinessException("请上传封面照");
            // }
            // //校验风采照是否上传
            // List<CustomerImg> customerImgList =
            // customerImgService.getCusDefaultImgListByUserId(customer.getUserId());
            // if (customerImgList == null || customerImgList.size() == 0) {
            // throw new BusinessException("请上传封风彩照");
            // }
            // //校验视频是否上传
            // List<CustomerVideo> customerVideos =
            // customerVideoService.getEntityByUserId(customer.getUserId());
            // if (customerVideos == null || customerVideos.size() == 0) {
            // throw new BusinessException("请上传视频");
            // }
            result.setMessage("校验成功");
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("校验失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 申请导游第三步
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/applyGuideStrpThree")
    public String applyGuideStrpThree(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        String applyGuideStepOneIndex =
                (String) request.getSession().getAttribute("apply_guide_step_one");
        if (!StringUtils.equals(applyGuideStepOneIndex, "yes")) {
            return "redirect:/portal/customercenter/tourist/apply_guide_step_one";
        }
        Customer sessionCustomer =
                (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        sessionCustomer.setIsTourGuide(-1);
        customerService.update(sessionCustomer);
        request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, sessionCustomer);
        // 发送系统消息
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setMessageTitle("申请导游");
        customerMessage.setMessage("你的导游申请正在处理中，请耐心等待！");
        customerMessage.setUserId(sessionCustomer.getUserId());
        customerMessageService.save(customerMessage);
        redisClientService.addRedis(SysConstant.CUSTOMER_MSG + sessionCustomer.getUserId());
        return "/portal/customercenter/tourist/apply_guide_step_three";
    }

    /**
     * 校验手机号码和手机验证码
     *
     * @return
     */
    @RequestMapping(value = "/checkMobileNoAndMobileCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkMobileNoAndMobileCaptcha(HttpServletRequest request) {
        String mobileCaptcha = request.getParameter("mobileCaptcha");
        JsonResult result = new JsonResult();
        try {
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            // 校验用户是否存在
            Customer customer =
                    this.getEntityService().findEntityByMobileNo(sessionCustomer.getMobileNo());
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }
            // 校验手机验证码
            ResultBase resultBase =
                    zdSmsService.checkMobileCaptcha(sessionCustomer.getMobileNo(), mobileCaptcha);
            resultBase.throwExceptionIfNotSuccess();
            result.setMessage("校验成功");
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("系统异常");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 修改手机号码
     *
     * @return
     */
    @RequestMapping(value = "/resetMobileNo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult resetMobileNo(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            String newMobileNo = request.getParameter("mobileNo");
            String mobileCaptcha = request.getParameter("mobileCaptcha");
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            // 校验用户是否存在
            Customer customer =
                    this.getEntityService().findEntityByMobileNo(sessionCustomer.getMobileNo());
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }
            ResultBase resultBase = zdSmsService.checkMobileCaptcha(newMobileNo, mobileCaptcha);
            resultBase.throwExceptionIfNotSuccess();
            sessionCustomer.setMobileNo(newMobileNo);
            this.getEntityService().update(sessionCustomer);
            log.info("用户{}修改手机号码为{}成功", sessionCustomer.getMobileNo(), newMobileNo);
        } catch (Exception e) {
            log.warn("修改手机号码失败");
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("系统异常");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult resetPassword(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            // 校验用户是否存在
            Customer customer =
                    this.getEntityService().findEntityByMobileNo(sessionCustomer.getMobileNo());
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }
            // 校验原密码是否正确
            boolean checkResult = getEntityService().checkPassword(sessionCustomer, oldPassword);
            if (!checkResult) {
                throw new BusinessException("原密码错误！");
            }
            // 修改密码为最新密码
            this.getEntityService().findPassword(sessionCustomer, newPassword);
            log.info("用户{}修改密码成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            log.warn("修改密码失败");
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("系统异常");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 实名认证
     *
     * @return
     */
    @RequestMapping(value = "/certification", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult certification(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            String realName = request.getParameter("realName");
            String idNumber = request.getParameter("idNumber");
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            // 校验用户是否存在
            Customer customer =
                    this.getEntityService().findEntityByMobileNo(sessionCustomer.getMobileNo());
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }
            // 实名认证
            // TODO
            sessionCustomer.setRealName(realName);
            sessionCustomer.setIdNumber(idNumber);
            sessionCustomer.setIsCertification(1);
            this.getEntityService().update(sessionCustomer);
            request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, sessionCustomer);
            log.info("用户{}实名认证成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            log.warn("实名认证失败");
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("系统异常");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 绑定银行卡
     *
     * @return
     */
    @RequestMapping(value = "/bindingCard", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult bindingCard(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            String bankId = request.getParameter("bankId");
            String bankcardNum = request.getParameter("bankcardNum");
            // String mobileNo = request.getParameter("mobileNo");
            String realName = request.getParameter("realName");
            String idNumber = request.getParameter("idNumber");
            String captcha = request.getParameter("captcha");
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            // 校验用户是否存在
            Customer customer = loadCustomer(request);
            if (customer == null) {
                throw new BusinessException("用户不存在！");
            }
            // 如果已绑定解绑原来的银行卡
            CustomerCard oldCustomerCard = customerCardService.getEntityByUserId(customer.getUserId());
            if (oldCustomerCard != null) {
                UnSignCardRequest unSignCardRequest = new UnSignCardRequest();
                unSignCardRequest.setBindId(oldCustomerCard.getBindId());
                unSignCardRequest.setUserId(customer.getUserId());
                UnSignCardResponse unSignCardResponse = gsyBusinessService.unSignCard(unSignCardRequest);
                if (unSignCardResponse.getStatus() == ResultStatus.success
                        || unSignCardResponse.getStatus() == ResultStatus.processing) {
                    log.info("解绑银行卡[{}]成功", oldCustomerCard.getCardNo());
                } else {
                    throw new BusinessException("解绑失败:" + unSignCardResponse.getResultMessage());
                }
            }
            // 银行卡实名签约验卡
            SignCardRequest signCardRequest = new SignCardRequest();
            signCardRequest.setCaptcha(captcha);
            signCardRequest.setUserId(customer.getUserId());
            signCardRequest.setMobile(customer.getMobileNo());
            signCardRequest.setBankCardNo(bankcardNum);
            signCardRequest.setRealName(realName);
            signCardRequest.setCertNo(idNumber);
            signCardRequest.setPurpose(Purpose.WITHDRAW);
            signCardRequest.setPublicTag(PublicTagEnum.N);
            signCardRequest.setBankCode(bankId);
            SignCardResponse signCardResponse = gsyBusinessService.signCard(signCardRequest);
            if (signCardResponse.getStatus() != ResultStatus.success
                    && signCardResponse.getStatus() != ResultStatus.processing) {
                throw new BusinessException("绑卡失败:" + signCardResponse.getResultMessage());
            }
            // 删除原来银行卡
            customerCardService.deleteEntityByUserId(sessionCustomer.getUserId());
            // 保存银行卡信息
            CustomerCard customerCard = new CustomerCard();
            customerCard.setCardName(signCardResponse.getBankName());
            customerCard.setCardNo(bankcardNum);
            customerCard.setMobileNo(customer.getMobileNo());
            customerCard.setUserId(sessionCustomer.getUserId());
            customerCard.setBindId(signCardResponse.getBindId());
            customerCardService.save(customerCard);
            // 更新实名信息
            sessionCustomer.setRealName(realName);
            sessionCustomer.setIdNumber(idNumber);
            sessionCustomer.setIsCertification(1);
            this.getEntityService().update(sessionCustomer);
            // 格式化显示信息格式
            String cardNo = customerCard.getCardNo();
            String subCardNo = cardNo.substring(cardNo.length() - 4, cardNo.length());
            customerCard.setShowCardNo("**** **** **** " + subCardNo);
            request.getSession().setAttribute(SysConstant.SESSION_KEY_CARD, customerCard);
            log.info("用户{}实名绑卡成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            log.warn("实名绑卡失败");
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("实名绑卡失败");
            }
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 修改用户头像
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadHeadPortrait", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadHeadPortrait(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Customer customer =
                (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
        try {

            String image = request.getParameter("image");
            String reviewPath = image;

            if (image.contains("data:image/png;base64,")) {
                // 去除base64前缀
                image = image.replace("data:image/png;base64,", "");
                String fileName = Ids.getDid();
                // 实际保存路径
                String imagePath =
                        oFileProperties.getStorageRoot()
                                + customer.getUserId()
                                + File.separator
                                + fileName
                                + ".png";
                // 访问路径
                reviewPath =
                        oFileProperties.getServerRoot()
                                + File.separator
                                + customer.getUserId()
                                + File.separator
                                + fileName
                                + ".png";
                File pathFloder = new File(oFileProperties.getStorageRoot() + customer.getUserId());
                if (!pathFloder.exists()) {
                    pathFloder.mkdirs();
                }
                GenerateImage(image, imagePath);
            }
            getEntityService().uploadHearPortrait(customer.getId(), reviewPath);
            log.info("用户{}上传头像成功", customer.getMobileNo());
            customer.setHeadImg(reviewPath);
            request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, customer);
            result.setMessage("用户" + customer.getMobileNo() + "上传头像成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("用户" + customer.getMobileNo() + "上传头像失败");
            log.info("用户{}上传头像失败：{}", customer.getMobileNo(), e.getMessage());
        }
        return result;
    }

    /**
     * 签到
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "creditSignin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult creditSignin(
            HttpServletRequest request, HttpServletResponse response, Model model) {
        JsonResult result = new JsonResult();
        try {
            Customer customer = loadCustomer(request);
            CreditSignin creditSignin = creditSigninService.CerditSignin(customer);
            result.setMessage("签到成功");
            // 可用积分余额
            PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
            request
                    .getSession()
                    .setAttribute(SysConstant.CUSTOMER_POINT_BALANCE, pointAccount.getBalance());
            result.appendData("times", creditSignin.getTimes());
            log.info("用户{}签到成功", customer.getMobileNo());
        } catch (Exception e) {
            log.info("签到失败:{}", e.getMessage());
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @return
     */
    @RequestMapping(value = "/verifyUserPassword")
    @ResponseBody
    public JSONObject verifyUserPassword(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            plugValidatePassword(request);
            result.put("status", "y");
        } catch (Exception e) {
            result.put("status", "n");
            result.put("info", "密码错误");
        }
        return result;
    }

    /**
     * 修改用户用户名
     *
     * @return
     */
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateUserName(
            String userName, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        try {
            // 获取session中用户信息
            Customer sessionCustomer =
                    (Customer) request.getSession().getAttribute(SysConstant.SESSION_KEY_USERINFO);
            if (sessionCustomer == null) {
                throw new BusinessException("请先登录");
            }
            if (CommonUtils.getStrBytesLength(userName) > 12) {
                throw new BusinessException("昵称长度过长");
            }
            if (StringUtils.equals(userName, sessionCustomer.getUserName())) {
                throw new BusinessException("此用户名已存在");
            }
            sessionCustomer.setUserName(userName);
            customerService.update(sessionCustomer);
            result.setMessage("修改用户名成功");
            request.getSession().setAttribute(SysConstant.SESSION_KEY_USERINFO, sessionCustomer);
            log.info("用户{}修改用户名成功", sessionCustomer.getMobileNo());
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("修改用户名失败");
            }
            result.setSuccess(false);
            log.info("修改用户名失败：", e.getMessage());
        }
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
