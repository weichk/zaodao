package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dao.platform.PlatformTourGuideDao;
import com.acooly.zaodao.platform.dto.EnshrineDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.service.platform.PlatformTourGuideService;
import com.acooly.zaodao.portal.dto.TourGuideSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/24.
 */
@Service
public class PlatformTourGuideServiceImpl implements PlatformTourGuideService {

	@Autowired
	private PlatformTourGuideDao platformTourGuideDao;

	@Override
	public PageInfo<TourGuideDto> queryByConditions(PageInfo<TourGuideDto> pageInfo, TourGuideSearchDto guideDto) {
		return platformTourGuideDao.queryByConditions(pageInfo, guideDto);
	}

	@Override
	public TourGuideDto findByUserId(String userId) {
		return platformTourGuideDao.findByUserId(userId);
	}

	@Override
	public PageInfo<EnshrineDto> queryEnshrineList(PageInfo<EnshrineDto> pageInfo, String userId, String title) {
		return platformTourGuideDao.queryEnshrineList(pageInfo, userId, title);
	}
}
