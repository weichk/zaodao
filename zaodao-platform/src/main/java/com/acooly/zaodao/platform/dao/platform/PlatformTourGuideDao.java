package com.acooly.zaodao.platform.dao.platform;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.EnshrineDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.portal.dto.TourGuideSearchDto;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface PlatformTourGuideDao {

    PageInfo<TourGuideDto> queryByConditions(PageInfo<TourGuideDto> pageInfo, TourGuideSearchDto guideDto);

    TourGuideDto findByUserId(String userId);

    PageInfo<EnshrineDto> queryEnshrineList(PageInfo<EnshrineDto> pageInfo, String userId, String title);
}
