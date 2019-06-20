/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-26
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.service.OnlineFileService;
import com.acooly.openapi.tool.fastjson.JSON;
import com.acooly.zaodao.platform.dao.manage.TravelVoiceDao;
import com.acooly.zaodao.platform.dto.ResourceOfileDto;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.entity.TravelPraiseLog;
import com.acooly.zaodao.platform.entity.TravelResource;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.enums.OfileType;
import com.acooly.zaodao.platform.enums.TravelVoiceType;
import com.acooly.zaodao.platform.order.TravelVoiceAddOrder;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;
import com.acooly.zaodao.platform.order.TravelVoicePraiseOrder;
import com.acooly.zaodao.platform.result.TravelVoiceDetailResult;
import com.acooly.zaodao.platform.result.TravelVoicePraiseResult;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import com.acooly.zaodao.platform.service.manage.TravelPraiseLogService;
import com.acooly.zaodao.platform.service.manage.TravelResourceService;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.TravelVoice;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * zd_travel_voice Service实现
 *
 * Date: 2017-10-26 18:05:37
 *
 * @author zhike
 *
 */
@Slf4j
@Service("travelVoiceService")
public class TravelVoiceServiceImpl extends EntityServiceImpl<TravelVoice, TravelVoiceDao> implements TravelVoiceService {
    @Value("${image.fileType.extentions}")
    private String imageExtentions;

    @Value("${video.fileType.extentions}")
    private String videoExtentions;

    @Autowired
    private OnlineFileService onlineFileService;

    @Autowired
    private TravelResourceService travelResourceService;

    @Autowired
    private TravelPraiseLogService travelPraiseLogService;

    @Autowired
    private GuideMessageService guideMessageService;
    /**
     * 添加旅声
     * @param travelVoiceAddOrder
     * @return
     */
    @Override
    @Transactional
    public ResultBase addTravelVoice(TravelVoiceAddOrder travelVoiceAddOrder) {
        ResultBase resultBase = new ResultBase();
        try{
            travelVoiceAddOrder.check();
            TravelVoice travelVoice = new TravelVoice();
            //获取旅声内容
            travelVoice.setUserId(travelVoiceAddOrder.getUserId());
            travelVoice.setTitle(travelVoiceAddOrder.getTitle());
            travelVoice.setContent(travelVoiceAddOrder.getContent());
            travelVoice.setPositionName(travelVoiceAddOrder.getPositionName());
            travelVoice.setPositionLat(travelVoiceAddOrder.getPositionLat());
            travelVoice.setPositionLng(travelVoiceAddOrder.getPositionLng());

            this.getEntityDao().save(travelVoice);
            log.info(travelVoiceAddOrder.getTravelResourceListJson());
            if(Strings.isNotBlank(travelVoiceAddOrder.getTravelResourceListJson())){
                List<ResourceOfileDto> travelResourceList = JSON.parseArray(travelVoiceAddOrder.getTravelResourceListJson(), ResourceOfileDto.class);
                if(travelResourceList != null && travelResourceList.size() > 0){
                    addTravelResources(travelResourceList, travelVoice);
                }
            }
        }catch (Exception e){
            log.info("保存失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("保存失败！");
        }

        return resultBase;
    }
    /**
     * 添加旅声资源
     * @param resourceOfileDtoList
     */
    private void addTravelResources(List<ResourceOfileDto> resourceOfileDtoList, TravelVoice travelVoice) throws Exception {
        for (ResourceOfileDto resourceOfileDto : resourceOfileDtoList){
            OnlineFile onlineFile = onlineFileService.get(resourceOfileDto.getResourceOfileId());
            if(onlineFile == null){
                throw new Exception(String.format("未找到ofile资源[id=%s]", resourceOfileDto.getResourceOfileId()));
            }

            TravelResource travelResource = new TravelResource();
            OfileType ofileType = getOfileType(onlineFile.getFileExt());

            travelResource.setOfileType(ofileType);
            travelResource.setTravelVoiceId(travelVoice.getId());
            travelResource.setUserId(travelVoice.getUserId());
            travelResource.setOfileId(onlineFile.getId());
            travelResource.setResourceUrl(onlineFile.getAccessUrl());

            if(ofileType == OfileType.video){
                //视频需要封面图
                if(Strings.isBlank(resourceOfileDto.getCoverOfileUrl()) || resourceOfileDto.getCoverOfileId() == null){
                    throw new Exception("视频文件请上传封面图");
                }
                OnlineFile onlineFileCover = onlineFileService.get(resourceOfileDto.getCoverOfileId());
                if(onlineFileCover == null){
                    throw new Exception(String.format("未找到封面图ofile资源[id=%s]", resourceOfileDto.getResourceOfileId()));
                }
                travelResource.setCoverUrl(onlineFileCover.getAccessUrl());
            }else if(ofileType == OfileType.image){
                travelResource.setCoverUrl(onlineFile.getAccessUrl());
            }

            travelResourceService.save(travelResource);
        }
    }
    /**
     * 添加旅声资源
     * @param resourceIds
     */
    private void addTravelResoures(String[] resourceIds, TravelVoice travelVoice) throws Exception {
        //通过ids查询ofile
        for(String id : resourceIds){
            Long onlineId = Long.MIN_VALUE;
            try {
                onlineId = Long.parseLong(id);
            }catch (NumberFormatException e){
                throw new Exception(String.format("资源ID[%s]格式转换错误,%s", id, e.getMessage()));
            }
            OnlineFile onlineFile = onlineFileService.get(onlineId);
            if(null == onlineFile){
                throw new Exception(String.format("未找到ofile资源[id=%s]", onlineId));
            }

            TravelResource travelResource = new TravelResource();
            travelResource.setOfileId(onlineFile.getId());
            travelResource.setResourceUrl(onlineFile.getAccessUrl());
            travelResource.setCoverUrl(""); //待定
            travelResource.setOfileType(getOfileType(onlineFile.getFileExt()));
            travelResource.setTravelVoiceId(travelVoice.getId());
            travelResource.setUserId(travelVoice.getUserId());

            travelResourceService.save(travelResource);
        }
    }
    /**
     * 获取旅声列表
     * @param order
     * @return
     */
    @Override
    public PageResult<TravelVoiceDto> getTravelVoiceList(TravelVoiceListOrder order) {
        PageResult<TravelVoiceDto> pageResult = new PageResult<>();

        try{
            if(order.getTravelVoiceType() != null){
                if(order.getTravelVoiceType() == TravelVoiceType.focus && Strings.isBlank(order.getAppUserId())){
                    throw new BusinessException("App用户不能为空");
                }
            }
            pageResult = PageResult.from(this.getEntityDao().getPageTravelVoiceList(order));
        }catch (BusinessException e){
            log.info("获取旅声列表出现错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail(e.getMessage());
        } catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }

    /**
     * 获取旅声详情
     * @param travelVoiceDetailOrder
     * @return
     */
    @Override
    public TravelVoiceDetailResult getTravelVoiceDetail(TravelVoiceDetailOrder travelVoiceDetailOrder) {
        TravelVoiceDetailResult travelVoiceDetailResult = new TravelVoiceDetailResult();

        try{
            travelVoiceDetailOrder.check();
            TravelVoiceDto travelVoiceDto = this.getEntityDao().getTravelVoiceDetail(travelVoiceDetailOrder);
            if(travelVoiceDto == null){
                throw new BusinessException(String.format("未找到旅声信息"));
            }
            List<TravelResource> travelResourceList = travelResourceService.getListByTravelVoiceId(travelVoiceDetailOrder.getTravelVoiceId());
            for (TravelResource travelResource : travelResourceList){
                TravelResourceDto travelResourceDto = new TravelResourceDto();
                BeanCopier.copy(travelResource, travelResourceDto);
                travelVoiceDto.getTravelResourceList().add(travelResourceDto);
            }
            BeanCopier.copy(travelVoiceDto, travelVoiceDetailResult);
            travelVoiceDetailResult.setTravelResourceDtoList(travelVoiceDto.getTravelResourceList());
        }catch (BusinessException e){
            log.info("查找旅声详情错误！{}", e.getMessage());
            travelVoiceDetailResult.setStatus(ResultStatus.failure);
            travelVoiceDetailResult.setCode(ResultStatus.failure.getCode());
            travelVoiceDetailResult.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            travelVoiceDetailResult.setStatus(ResultStatus.failure);
            travelVoiceDetailResult.setCode(ResultStatus.failure.getCode());
            travelVoiceDetailResult.setDetail("系统错误！");
        }

        return travelVoiceDetailResult;
    }

    /**
     * 点赞/取消点赞
     * @param travelVoicePraiseOrder
     * @return
     */
    @Override
    public TravelVoicePraiseResult praiseTravelVoice(TravelVoicePraiseOrder travelVoicePraiseOrder) {
        TravelVoicePraiseResult result = new TravelVoicePraiseResult();
        try{
            travelVoicePraiseOrder.check();
            TravelVoice travelVoice = this.getEntityDao().get(travelVoicePraiseOrder.getTravelVoiceId());
            if(null == travelVoice){
                throw new BusinessException(String.format("未找到旅声信息,id=%s", travelVoicePraiseOrder.getTravelVoiceId()));
            }

            TravelPraiseLog travelPraiseLog = travelPraiseLogService.getTravelPraiseLog(travelVoicePraiseOrder);
            result.setPraiseFlag(travelVoicePraiseOrder.getPraiseFlag());
            //如果是取消点赞请求
            if(travelVoicePraiseOrder.getPraiseFlag() == 0 && travelPraiseLog != null) {
                //点赞数减一
                travelVoice.setPraiseCount(travelVoice.getPraiseCount() - 1 >= 0 ? travelVoice.getPraiseCount() - 1 : 0);
                //删除点赞记录
                travelPraiseLogService.removeTravelVoicePraise(travelVoicePraiseOrder.getUserId(), travelVoicePraiseOrder.getTravelVoiceId());
                result.setPraiseFlag(0);
            }else if(travelVoicePraiseOrder.getPraiseFlag() == 1) {
                if(travelPraiseLog == null) {
                    //查询是否有该用户针对该旅声的点赞记录, 没有点赞记录则添加
                    travelPraiseLog = new TravelPraiseLog();
                    //添加点赞记录
                    travelPraiseLog.setTravelVoiceId(travelVoicePraiseOrder.getTravelVoiceId());
                    travelPraiseLog.setUserId(travelVoicePraiseOrder.getUserId());
                    travelPraiseLogService.save(travelPraiseLog);
                    //加入点赞数
                    travelVoice.setPraiseCount(travelVoice.getPraiseCount() + 1);
                }
                //点赞人不是旅声作者则添加动态
                if (!travelVoice.getUserId().equals(travelVoicePraiseOrder.getUserId())) {
                    guideMessageService.addGuideMessage(travelVoicePraiseOrder.getUserId(), GuideMessageType.praiseTravelVoice, travelVoice.getContent(), "", travelVoice.getId().toString(), travelVoice.getUserId());
                }
                result.setPraiseFlag(1);
            }
            this.getEntityDao().save(travelVoice);
            //获取点赞数
            result.setPraiseCount(travelVoice.getPraiseCount());
        }
        catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

    /**
     * 获取文件类型
     * @param extension
     * @return
     */
    private OfileType getOfileType(String extension) throws Exception {
        if(Strings.isBlank(extension)){
            throw new Exception("后缀名不能为空");
        }

        if (imageExtentions.indexOf(extension) >= 0) {
            return OfileType.image;
        } else if (videoExtentions.indexOf(extension) >= 0) {
            return OfileType.video;
        } else {
            return OfileType.other;
        }
    }
}
