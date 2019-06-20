/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-16
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Encodes;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.point.dao.PointGradeDao;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.domain.PointGrade;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.enums.PointTradeType;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointGradeService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.module.security.utils.Digests;
import com.acooly.openapi.tool.AcoolyGateway;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.account.util.AccountConstants;
import com.acooly.zaodao.gateway.gsy.GsyApiConstant;
import com.acooly.zaodao.gateway.gsy.message.CustomerRegisterRequest;
import com.acooly.zaodao.gateway.gsy.message.CustomerRegisterResponse;
import com.acooly.zaodao.gateway.gsy.message.WxAccessTokenResponse;
import com.acooly.zaodao.gateway.gsy.message.WxUserResponse;
import com.acooly.zaodao.gateway.gsy.message.enums.CustomerTypeEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.gateway.gsy.service.WeixinService;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.dao.manage.CustomerFocusDao;
import com.acooly.zaodao.platform.dao.manage.CustomerLanguageDao;
import com.acooly.zaodao.platform.dao.manage.TourGuideDao;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.LectorDto;
import com.acooly.zaodao.platform.dto.OpenWxUserDto;
import com.acooly.zaodao.platform.dto.PointTradeInfDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerLanguage;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.*;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.ZdSmsService;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 用户表 Service实现
 * <p>
 * <p>Date: 2017-05-16 20:08:56
 *
 * @author zhike
 */
@Service("customerService")
@Slf4j
public class CustomerServiceImpl extends EntityServiceImpl<Customer, CustomerDao>
        implements CustomerService {

    @Autowired
    private TourGuideDao tourGuideDao;

    @Autowired
    private CustomerLanguageDao customerLanguageDao;

    @Autowired
    private PointGradeService pointGradeService;

    @Resource
    private PointGradeDao pointGradeDao;

    @Autowired
    private PointAccountService pointAccountService;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private GsyBusinessService gsyBusinessService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private PointTradeService pointTradeService;

    @Autowired
    private ZdSmsService zdSmsService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerCardService customerCardService;

    @Autowired
    private CustomerFocusDao customerFocusDao;

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private AccountService accountService;

    @Value("${zaodao.wx.appId}")
    private String wxAppId;

    @Value("${zaodao.wx.secretKey}")
    private String wxSecretKey;

    @Value("${gsyPay.gateway.partnerId}")
    private String partnerId;

    @Value("${gsyPay.gateway.zdUserId}")
    private String zdUserId;

    public static final int SALT_SIZE = 8;
    public static final int HASH_INTERATIONS = 512;

    @Override
    public Customer login(String mobileNo, String password) {
        Customer userInfo = findEntityByMobileNo(mobileNo);
        log.info("查询用户成功，用户信息{}", userInfo);
        if (userInfo == null) {
            throw new BusinessException("电话号码不存在", "userNameError");
        }
        String dbSalt = userInfo.getLoginSail();
        String enPassword = entryptPassword(password, dbSalt);
        String dbPassword = userInfo.getLoginPassword();
        if (!enPassword.equals(dbPassword)) {
            throw new BusinessException("密码错误", "passwordError");
        }
        log.info("用户[" + userInfo.getMobileNo() + "]登录完成");
        return userInfo;
    }

    /**
     * 注册用户
     *
     * @param customer
     * @param mobileCaptcha
     * @return
     */
    @Override
    @Transactional
    public CustomerRegisterResult register(Customer customer, String mobileCaptcha) {
        CustomerRegisterResult result = new CustomerRegisterResult();

        try {
            ResultBase resultBase = zdSmsService.checkMobileCaptcha(customer.getMobileNo(), mobileCaptcha);
            resultBase.throwExceptionIfNotSuccess();
            checkUnique(customer);
            // 创建用户信息
            if (StringUtils.isNotBlank(customer.getLoginPassword())) {
                entryptPassword(customer);
            }
            // 保存用户注册信息
            saveCustomerInfo(customer);

            //2018-04-10 xh modify,账务模块修改，注册创建用户在早导网中的账务
            // 注册观世宇账户
            //registerGsy(customer);
            //创建账务账户
            accountService.createAccount(partnerId, customer.getUserId(), customer.getUserName(), AccountConstants.ACCOUNT_TYPE_DEFAULT, partnerId, "用户注册");

            result.setCustomer(customer);
            log.info(String.format("用户[%s]注册完成", customer.getMobileNo()));
        } catch (BusinessException bx) {
            log.info("用户注册失败{}", bx.getMessage());
            result.setCode(ResultCode.FAILURE.getCode());
            result.setStatus(ResultStatus.failure);
            result.setDetail(bx.getMessage());
        } catch (Exception e) {
            log.info("用户注册失败{}", e.getMessage());
            result.setCode(ResultCode.FAILURE.getCode());
            result.setStatus(ResultStatus.failure);
            result.setDetail("注册失败，系统异常");
        }
        return result;
    }

    /**
     * 保存用户信息
     *
     * @param customer
     */
    @Override
    @Transactional
    public void saveCustomerInfo(Customer customer) {
        try {
            this.getEntityDao().save(customer);
            // 创建积分账户
            createPointAccount(customer.getUserId());
            // 注册送积分
            createPoint(customer.getUserId());
            // 发送系统消息
            createMessage(customer.getUserId());
        } catch (Exception e) {
            log.info(String.format("保存用户注册信息失败,%s", e.getMessage()));
            throw new BusinessException("保存用户注册信息失败");
        }
    }

    /**
     * 创建积分账户
     *
     * @param userId
     */
    private void createPointAccount(String userId) {
        PointAccount pointAccount = new PointAccount();
        pointAccount.setUserName(userId);
        PointGrade pointGrade = pointGradeDao.findUniqu("EQ_num", 1);
        pointAccount.setGradeId(pointGrade.getId());
        pointAccountService.save(pointAccount);
    }

    /**
     * 注册送积分
     *
     * @param userId
     */
    private void createPoint(String userId) {
        Random rd = new Random();
        int point = rd.nextInt(3) + 1;
        PointTradeDto pointTradeDto = new PointTradeDto();
        pointTradeDto.setBusiId(userId);
        pointTradeDto.setBusiType("register");
        pointTradeDto.setBusiTypeText("注册");
        pointTradeService.pointProduce(userId, point, pointTradeDto);
    }

    /**
     * 添加注册消息
     *
     * @param userId
     */
    private void createMessage(String userId) {
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setUserId(userId);
        customerMessage.setMessageTitle("注册消息");
        customerMessage.setMessage("恭喜你注册成为早导网会员");
        customerMessage.setMessageType(CustomerMessageType.other);
        customerMessageService.save(customerMessage);
        redisClientService.addRedis(SysConstant.CUSTOMER_MSG + userId);
    }

    /**
     * 注册观世宇账户
     *
     * @param customer
     */
    private void registerGsy(Customer customer) {
        CustomerRegisterRequest registerRequest = new CustomerRegisterRequest();
        try {
            log.info(String.format("观世宇平台注册账户[%s]开始...", customer.getMobileNo()));
            registerRequest.setOutUserId(customer.getUserId());
            registerRequest.setType(CustomerTypeEnum.PERSON);
            registerRequest.setPassword(
                    AcoolyGateway.getOpenApiClientService()
                            .AESEncrypt("11111111", GsyApiConstant.GATEWAY_SECRETKEY));
            registerRequest.setMobileNo(customer.getMobileNo());
            // 接观世宇注册
            CustomerRegisterResponse customerRegisterResponse =
                    gsyBusinessService.customerRegister(registerRequest);
            if (customerRegisterResponse.getStatus() != ResultStatus.success) {
                log.info(
                        String.format(
                                "观世宇平台注册账户[%s]失败, %s",
                                customer.getMobileNo(), customerRegisterResponse.getResultMessage()));
            }
        } catch (Exception ex) {
            log.info(String.format("观世宇平台注册账户[%s]出现错误, %s", customer.getMobileNo(), ex.getMessage()));
        } finally {
            log.info(String.format("观世宇平台注册账户[%s]结束...", customer.getMobileNo()));
        }
    }

    @Override
    public Customer findEntityByMobileNo(String mobileNo) {
        return this.getEntityDao().findEntityByMobileNo(mobileNo);
    }

    @Override
    public Customer findPassword(Customer customer, String newPassword) {
        String loginSail = customer.getLoginSail();
        String password = entryptPassword(newPassword, loginSail);
        customer.setLoginPassword(password);
        this.getEntityDao().update(customer);
        return customer;
    }

    @Override
    public boolean checkPassword(Customer customer, String oldPassword) {
        boolean result = false;
        String dbSalt = customer.getLoginSail();
        String enPassword = entryptPassword(oldPassword, dbSalt);
        String dbPassword = customer.getLoginPassword();
        if (enPassword.equals(dbPassword)) {
            result = true;
        }
        return result;
    }

    @Transactional
    @Override
    public void setApplyGuideInfo(
            Customer customer, TourGuide tourGuide, List<CustomerLanguage> customerLanguages) {
        if (customer.getIsTourGuide() == 0) {
            tourGuideDao.deleteEntityByUserId(customer.getUserId());
            customerLanguageDao.deleteEntityByUserId(customer.getUserId());
        }
        this.getEntityDao().update(customer);
        tourGuideDao.save(tourGuide);
        for (CustomerLanguage customerLanguage : customerLanguages) {
            customerLanguageDao.save(customerLanguage);
        }
    }

    @Override
    public void uploadHearPortrait(Long id, String headPortrait) {
        this.getEntityDao().uploadHearPortrait(id, headPortrait);
    }

    @Override
    public Customer findEntityByUserId(String userId) {
        return getEntityDao().findEntityByUserId(userId);
    }

    @Override
    public void auditPass(Long id) {
        Customer customer = getEntityDao().get(id);
        customer.setIsTourGuide(1);
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setUserId(customer.getUserId());
        customerMessage.setMessageTitle("导游申请审核结果");
        customerMessage.setMessage("恭喜导游申请审核通过");
        customerMessageService.save(customerMessage);
        redisClientService.addRedis(SysConstant.CUSTOMER_MSG + customer.getUserId());

        // 注册关世宇会员
        // 用户注册就会到观世宇调用注册账号，导游审核时不再需要创建账号  by xiyang 2017-11-30
//    if (Strings.isBlank(customer.getOutUserId())) {
//      CustomerRegisterRequest registerRequest = new CustomerRegisterRequest();
//      registerRequest.setOutUserId(customer.getUserId());
//      registerRequest.setType(CustomerTypeEnum.PERSON);
//      registerRequest.setPassword(
//          YijifuGateway.getOpenApiClientService()
//              .AESEncrypt("11111111", GsyApiConstant.GATEWAY_SECRETKEY));
//      registerRequest.setMobileNo(customer.getMobileNo());
//      CustomerRegisterResponse customerRegisterResponse =
//          gsyBusinessService.customerRegister(registerRequest);
//      if (customerRegisterResponse.getStatus() == ResultStatus.success) {
//        customer.setOutUserId(customerRegisterResponse.getUserId());
//      } else {
//        throw new BusinessException("注册关世宇失败:" + customerRegisterResponse.getResultMessage());
//      }
//    }
//    getEntityDao().update(customer);
        // 发送短信给用户提示用户注册的关世宇平台帐号和密码
        // TODO
    }

    /**
     * 申请讲师
     */
    @Override
    public ResultBase applyTeach(String userId, String realName) {
        ResultBase result = new ResultBase();
        try {
            Customer customer = this.getUser(userId);
            //如果没有填写姓名，且app未传姓名
            if(!Strings.isNotBlank(customer.getRealName())){
                if(!Strings.isNotBlank(realName)) {
                    throw new BusinessException("姓名不能为空");
                }else{
                    customer.setRealName(realName);
                }
            }
            customer.setIsLector(-1);
            this.getEntityDao().update(customer);
        } catch (BusinessException e) {
            log.info("申请讲师错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        }catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    @Override
    public PageResult<PointTradeInfDto> getPointTradeList(TradeCaListOrder order) {
        PageResult<PointTradeInfDto> pageResult = new PageResult<>();

        try {
            order.check();
            pageResult = PageResult.from(this.getEntityDao().getPagePointTradeList(order));
            for (PointTradeInfDto p : pageResult.getDto().getPageResults()) {
                if (p.getTradeType() == PointTradeType.produce || p.getTradeType() == PointTradeType.unfreeze) {
                    p.setInOutType(InOutType.in);
                } else {
                    p.setInOutType(InOutType.out);
                }
            }
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;

    }

    /**
     * 开放平台登录
     *
     * @param order
     * @return
     */
    @Override
    public OpenPlatformLoginResult openPlaformLogin(OpenPlatformLoginOrder order) {
        OpenPlatformLoginResult result = new OpenPlatformLoginResult();
        try {
            order.check();
            Customer customer = getCustomerByOpenId(order.getOpenLoginMethod(), order.getOpenUserId());
            if (customer == null) {
                result.setStatus(ResultStatus.failure);
                result.setCode(OpenLoginResultCode.user_openid_unbind_mobile.getCode());
                result.setDetail(OpenLoginResultCode.user_openid_unbind_mobile.getMessage());
            } else {
                result.setStatus(ResultStatus.success);
                result.setAccessKey(customer.getUserName());
                result.setCustomerId(customer.getUserId());
            }
        } catch (BusinessException e) {
            log.info("开放平台登录失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

    /**
     * 获取开放平台用户访问信息
     *
     * @param order
     * @return
     */
    @Override
    public OpenPlatformUserResult getOpenPlatformUser(OpenPlatformUserOrder order) {
        OpenPlatformUserResult result = new OpenPlatformUserResult();
        try {
            order.check();
            if (order.getOpenLoginMethod() == OpenPlatformType.weixin) {
                WxAccessTokenResponse response = weixinService.getAccessToken(wxAppId, wxSecretKey, order.getWxCode());
                if (response == null) {
                    throw new BusinessException("微信服务器返回结果为空");
                } else if (Strings.isNotBlank(response.getErrcode())) {
                    throw new BusinessException(String.format("微信服务器返回错误,%s", response.getErrmsg()));
                }
                //请求微信获取用户访问信息
                WxUserResponse userResponse = weixinService.getUserInfo(wxAppId, wxSecretKey, response.getAccessToken(), response.getRefreshToken(), response.getOpenId());
                if (userResponse == null) {
                    throw new BusinessException("微信服务器返回用户信息结果为空");
                } else if (Strings.isNotBlank(response.getErrcode())) {
                    throw new BusinessException(String.format("微信服务器返回用户信息错误,%s", response.getErrmsg()));
                } else {
                    OpenWxUserDto openWxUserDto = new OpenWxUserDto();
                    openWxUserDto.setHeadImgUrl(userResponse.getHeadimgurl());
                    openWxUserDto.setNickName(userResponse.getNickname());
                    result.setOpenWxUserDto(openWxUserDto);
                }
            }
        } catch (BusinessException e) {
            log.info("开放平台获取用户失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 获取粉丝数和关注数
     *
     * @param userId
     * @return
     */
    @Override
    public CustomerFocusCountDto getCustomerFocusCount(String userId) {
        return customerFocusDao.getCustomerFocusCount(userId);
    }

    /**
     * 开放平台登录检查手机号
     *
     * @param order
     * @return
     */
    @Override
    public CustomerMobileCheckResult checkCustomerMobile(CustomerMobileCheckOrder order) {
        CustomerMobileCheckResult result = new CustomerMobileCheckResult();
        try {
            order.check();
            //手机号是否注册
            Customer customer = this.getEntityDao().findEntityByMobileNo(order.getMobileNo());
            if (customer == null) {
                result.setExist(false);
                result.setBinding(false);
            } else {
                result.setExist(true);
                result.setBinding(getMobileBindstate(order.getOpenLoginMethod(), customer));
            }
        } catch (BusinessException e) {
            log.info("开放平台检查手机号失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(e.getMessage());
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 根据开放平台登录方式设置openUserId
     *
     * @param openUserId
     * @param openPlatformType
     * @param customer
     */
    @Override
    public void setOpenUserId(String openUserId, OpenPlatformType openPlatformType, Customer customer) {
        if (openPlatformType == OpenPlatformType.weixin) {
            if (Strings.isBlank(openUserId)) {
                throw new BusinessException("微信用户ID不能为空");
            }
            customer.setOpenId(openUserId);
        } else if (openPlatformType == OpenPlatformType.qq) {
            if (Strings.isBlank(openUserId)) {
                throw new BusinessException("QQ用户ID不能为空");
            }
            customer.setQqId(openUserId);
        } else if (openPlatformType == OpenPlatformType.weibo) {
            if (Strings.isBlank(openUserId)) {
                throw new BusinessException("微博用户ID不能为空");
            }
            customer.setWeiboId(openUserId);
        } else {
            throw new BusinessException("未知开放平台");
        }
    }

    /**
     * 根据是否是讲师字段获取用户列表
     */
    @Override
    public List<LectorDto> getByIsLector(int isLector) {
        List<Customer> list =  this.getEntityDao().getListByIsLector(isLector);
        List<LectorDto> dtos = Lists.newArrayList();
        list.forEach(p ->{
            dtos.add(new LectorDto(p.getId(), p.getUserId(), p.getRealName()));
        });
        return dtos;
    }

    /**
     * 根据课程Id获取购买人员头像，最多6名
     * @param courseId
     * @return
     */
    @Override
    public List<Customer> getByCourseId(Long courseId) {
        return this.getEntityDao().getByCourseId(courseId);
    }

    /**
     * 根据openUserId获取用户
     *
     * @param openPlatformType
     * @param openUserId
     * @return
     */
    private Customer getCustomerByOpenId(OpenPlatformType openPlatformType, String openUserId) {
        Customer customer = null;
        if (openPlatformType == OpenPlatformType.weixin) {
            customer = this.getEntityDao().findEntityByOpenId(openUserId);
        } else if (openPlatformType == OpenPlatformType.qq) {
            customer = this.getEntityDao().findEntityByQqId(openUserId);
        } else if (openPlatformType == OpenPlatformType.weibo) {
            customer = this.getEntityDao().findEntityByWeiboId(openUserId);
        } else {
            throw new BusinessException("未知开放平台");
        }
        return customer;
    }

    /**
     * 绑定openUserId
     *
     * @param openPlatformType
     * @param openUserId
     * @param customer
     */
    private void bindOpenUserId(OpenPlatformType openPlatformType, String openUserId, Customer customer) {
        if (openPlatformType == OpenPlatformType.weixin) {
            customer.setOpenId(openUserId);
        } else if (openPlatformType == OpenPlatformType.qq) {
            customer.setQqId(openUserId);
        } else if (openPlatformType == OpenPlatformType.weibo) {
            customer.setWeiboId(openUserId);
        } else {
            throw new BusinessException("未知开放平台");
        }
    }

    /**
     * 检查手机号是否绑定开放平台账号
     *
     * @param openPlatformType
     * @param customer
     * @return
     */
    private boolean getMobileBindstate(OpenPlatformType openPlatformType, Customer customer) {
        if (openPlatformType == OpenPlatformType.weixin) {
            return Strings.isNotBlank(customer.getOpenId());
        } else if (openPlatformType == OpenPlatformType.qq) {
            return Strings.isNotBlank(customer.getQqId());
        } else if (openPlatformType == OpenPlatformType.weibo) {
            return Strings.isNotBlank(customer.getWeiboId());
        } else {
            throw new BusinessException("未知开放平台");
        }
    }


    @Override
    public UserInfoResult findUserInfo(String userId) {
        UserInfoResult result = new UserInfoResult();
        try {
            Customer customer = this.getEntityDao().findEntityByUserId(userId);
            BeanCopier.copy(customer, result);
            // 获取积分等级信息
            PointAccount pointAccount = pointAccountService.findByUserName(customer.getUserId());
            PointGrade pointGrade = pointGradeService.getSectionPoint(pointAccount);
            result.setPoint(pointAccount.getBalance());
            result.setPointName(pointGrade.getTitle());
            result.setPointLevel(pointGrade.getNum());

            int articleNums = articleService.countUserArticles(userId, ArticleStatusEnum.published.getCode());
            result.setArticleNums(articleNums);

            Account account = accountService.findByUserId(userId);

            if (account != null) {
                result.setBalance(Money.cent(account.getAvailable()));
            }

            CustomerFocusCountDto customerFocusCountDto = customerFocusDao.getCustomerFocusCount(userId);
            result.setCount(customerFocusCountDto.getCount());
            result.setFocusCount(customerFocusCountDto.getFocusCount());
            result.setBindCardCount(customerCardService.getBindCardCountByUserId(userId));
        } catch (BusinessException e) {
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }
        return result;
    }

    /**
     * 检查账户是否重复
     *
     * @param customer
     */
    private void checkUnique(Customer customer) {
        Customer exsit = getEntityDao().findEntityByMobileNo(customer.getMobileNo());
        if (exsit != null && (customer.getId() == null || !customer.getId().equals(exsit.getId()))) {
            throw new BusinessException("用户电话号码:[" + customer.getMobileNo() + "]已经存在");
        }
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     *
     * @param customer
     */
    @Override
    public void entryptPassword(Customer customer) {
        String salt = Encodes.encodeHex(Digests.generateSalt(SALT_SIZE));
        customer.setLoginSail(salt);
        customer.setLoginPassword(entryptPassword(customer.getLoginPassword(), salt));
    }

    private String entryptPassword(String plainPassword, String salt) {
        return Encodes.encodeHex(
                Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS));
    }

    /**
     * 检查用户
     *
     * @param userId
     * @return
     */
    public boolean checkUser(String userId) {
        if (Strings.isBlank(userId)) {
            throw new BusinessException(
                    ResultCode.FAILURE, String.format("%s,用户ID不能为空", ResultCode.FAILURE.getMessage()));
        }
        return true;
    }

    /**
     * 获取用户
     *
     * @param userId
     * @return
     */
    public Customer getUser(String userId) {
        Customer customer = customerDao.findEntityByUserId(userId);
        if (null == customer) {
            throw new BusinessException(ResultCode.FAILURE, String.format("%s,未找到用户", ResultCode.FAILURE.getMessage()));
        }
        return customer;
    }

    /**
     * 修改用户头像
     *
     * @param userHeadModifyOrder
     * @return
     */
    @Override
    @Transactional
    public ResultBase modifyUserHead(UserHeadModifyOrder userHeadModifyOrder) {
        ResultBase resultBase = new ResultBase();

        try {
            userHeadModifyOrder.check();
            Customer customer = getUser(userHeadModifyOrder.getUserId());
            customer.setHeadImg(userHeadModifyOrder.getHeadImg());
            this.getEntityDao().save(customer);
        } catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("系统错误！");
        }

        return resultBase;
    }
}
