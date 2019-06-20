package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.dto.PointTradeDto;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.SysConstant;
import com.acooly.zaodao.platform.dao.manage.ArticleDao;
import com.acooly.zaodao.platform.dao.manage.ArticleReviewDao;
import com.acooly.zaodao.platform.dao.manage.CourseDao;
import com.acooly.zaodao.platform.dao.manage.CustomerDao;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.CustomerMessageType;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;
import com.acooly.zaodao.platform.order.RewardOrder;
import com.acooly.zaodao.platform.result.RewardResult;
import com.acooly.zaodao.platform.service.RedisClientService;
import com.acooly.zaodao.platform.service.manage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiaohong on 2017/9/27.
 */
@Slf4j
@Service("rewardService")
public class RewardServiceImpl implements RewardService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleReviewDao articleReviewDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private PointAccountService pointAccountService;

    @Autowired
    private PointTradeService pointTradeService;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private ArticleRewardLogService articleRewardLogService;

    @Autowired
    protected RedisClientService redisClientService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected ArticleReviewService articleReviewService;

    /**
     * 获取打赏结果
     * @param rewardOrder
     * @return
     */
    @Override
    @Transactional
    public RewardResult getReward(RewardOrder rewardOrder) {
        RewardResult rewardResult = new RewardResult();

        try {
            rewardOrder.check();
            //判断用户
            Customer customer = getUserIdByReward(rewardOrder.getBusinessId(),rewardOrder.getType());
            if(null == customer) {
                throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,找不到打赏目标用户", ResultCode.PARAMETER_ERROR.getMessage()));
            }
            else if(customer.getUserId().equals(rewardOrder.getUserId())){
                throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,不能给自己打赏", ResultCode.PARAMETER_ERROR.getMessage()));
            }
            // 判断积分余额
            PointAccount pointAccount = getPointByUserId(rewardOrder.getUserId());
            if (pointAccount.getBalance() < rewardOrder.getPoint()) {
                throw new BusinessException(ResultCode.FAILURE, String.format("%s,积分余额不足",ResultCode.FAILURE.getMessage()));
            }

            //处理积分
            executePointTrade(rewardOrder, customer);
            //发送系统消息
            sendCustomerMessage(rewardOrder, customer);
            //保存打赏记录
            execueReward(rewardOrder, customer);

            redisClientService.addRedis(SysConstant.CUSTOMER_MSG + customer.getUserId());
            rewardResult.getJsonResult().setMessage("打赏成功");
        }catch (OrderCheckException ex){
            log.info("参数错误！{}", ex.getMessage());
            rewardResult.setStatus(ResultStatus.failure);
            rewardResult.setCode(ResultCode.PARAMETER_ERROR.getCode());
            rewardResult.setDetail(ex.getMessage());
        }catch (BusinessException ex){
            log.info("执行失败！{}", ex.getMessage());
            rewardResult.setStatus(ResultStatus.failure);
            rewardResult.setCode(ResultCode.FAILURE.getCode());
            rewardResult.setDetail(ex.getMessage());
        }catch (Exception e){
            log.info("执行失败！{}", e.getMessage());
            rewardResult.setStatus(ResultStatus.failure);
            rewardResult.setCode(ResultCode.FAILURE.getCode());
            rewardResult.setDetail(ResultCode.FAILURE.getMessage());
        }

        return rewardResult;
    }

    /**
     * 给据打赏的对象获取用户ID
     * @param
     * @return
     */
    private Customer getUserIdByReward(String businessId, RewardTypeEnum rewardTypeEnum){
        String userId = "";
        if(rewardTypeEnum == RewardTypeEnum.article){
            //查询文章表
            Article article = articleDao.get(Long.valueOf(businessId));
            userId = article != null ? article.getUserId() : "";
        }
        else if(rewardTypeEnum == RewardTypeEnum.review){
            //查询评论表
            ArticleReview articleReview = articleReviewDao.get(Long.valueOf(businessId));
            userId = articleReview != null ? articleReview.getReviewUserId() : "";
        }
        else if(rewardTypeEnum == RewardTypeEnum.course){
            //查询课程表
            Course course = courseDao.get(Long.valueOf(businessId));
            userId = course != null ? course.getUserId() : "";
        } else{
            throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,打赏类型参数RewardTypeEnum错误", ResultCode.PARAMETER_ERROR.getMessage()));
        }
        if(Strings.isBlank(userId)){
            throw new BusinessException(ResultCode.PARAMETER_ERROR, String.format("%s,未找到打赏目标用户", ResultCode.PARAMETER_ERROR.getMessage()));
        }
        Customer customer = customerDao.findEntityByUserId(userId);

        return customer;
    }

    /**
     * 获取用户账户
     * @param userId
     * @return
     */
    private PointAccount getPointByUserId(String userId){
        return  pointAccountService.findByUserName(userId);
    }

    /**
     * 处理积分
     * @param rewardOrder 当前用户
     * @param customer 打赏目标用户
     */
    public void executePointTrade(RewardOrder rewardOrder, Customer customer){
        PointTradeDto pointTradeDto = new PointTradeDto();

        pointTradeDto.setBusiId(customer.getUserId());
        pointTradeDto.setBusiType("onlyReward");
        pointTradeDto.setBusiTypeText("打赏");
        //打赏
        pointTradeService.pointProduce(customer.getUserId(), rewardOrder.getPoint(), pointTradeDto);
        //消费
        pointTradeService.pointExpense(rewardOrder.getUserId(), rewardOrder.getPoint(), false, pointTradeDto);
    }

    /**
     * 把积分从一个人分配给另一个人
     * @param rewardUserId 被分配者
     * @param customerId 分配者
     * @param point 积分数量
     * @param busiType 分配类型
     * @param busiTypeText 分配类型说明
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executePointTrade(String rewardUserId, String customerId, long point, String busiType, String busiTypeText){
        if(Strings.isBlank(rewardUserId)){
            throw new BusinessException(ResultCode.FAILURE, String.format("%s,积分接收者ID不能为空",ResultCode.FAILURE.getMessage()));
        }
        if(Strings.isBlank(customerId)){
            throw new BusinessException(ResultCode.FAILURE, String.format("%s,积分提供者ID不能为空",ResultCode.FAILURE.getMessage()));
        }
        PointTradeDto pointTradeDto = new PointTradeDto();

        pointTradeDto.setBusiId(customerId);
        pointTradeDto.setBusiType(busiType);
        pointTradeDto.setBusiTypeText(busiTypeText);

        try {
            //分配积分
            pointTradeService.pointProduce(rewardUserId, point, pointTradeDto);
            //消费消费
            pointTradeService.pointExpense(customerId, point, false, pointTradeDto);
        }catch (Exception e) {
            throw e;
        }
    }

    /**
     * 发送系统消息
     * @param rewardOrder
     * @param customer
     */
    private void sendCustomerMessage(RewardOrder rewardOrder, Customer customer){
        CustomerMessage customerMessage = new CustomerMessage();

        customerMessage.setMessageTitle("打赏");
        customerMessage.setMessage(String.format("用户(%s)打赏你%s积分", customer.getUserName(), rewardOrder.getPoint()));
        customerMessage.setUserId(customer.getUserId());
        customerMessage.setMessageType(CustomerMessageType.other);

        customerMessageService.save(customerMessage);
    }

    /**
     * 保存打赏记录
     * @param rewardOrder
     * @param customer
     */
    private void execueReward(RewardOrder rewardOrder, Customer customer){
        ArticleRewardLog articleRewardLog = new ArticleRewardLog();
        articleRewardLog.setBusinessId(Long.valueOf(rewardOrder.getBusinessId()));
        articleRewardLog.setPointAmount(rewardOrder.getPoint());
        articleRewardLog.setUserId(customer.getUserId());

        if (rewardOrder.getType() == RewardTypeEnum.article) {
            Article article = articleDao.get(Long.valueOf(rewardOrder.getBusinessId()));
            article.setRewardCount(article.getRewardCount() + 1);
            article.setRewardTotalAmount(article.getRewardTotalAmount() + Long.valueOf(rewardOrder.getPoint()));
            articleDao.update(article);
            articleRewardLog.setRewardType(RewardTypeEnum.article);
        } else if (rewardOrder.getType() == RewardTypeEnum.review) {
            articleRewardLog.setRewardType(RewardTypeEnum.review);
        }else if(rewardOrder.getType() == RewardTypeEnum.course){
            articleRewardLog.setRewardType(RewardTypeEnum.course);
        }
        articleRewardLogService.save(articleRewardLog);
    }
}
