package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.order.QueryGuideListOrder;
import com.acooly.zaodao.platform.order.QueryGuideSearchOrder;

/** Created by xiyang on 2017/9/26. */
public interface TourGuideCusDao {

  /**
   * 分页获取导游列表
   *
   * @param order
   * @return
   */
  PageInfo<GuideInfoDto> queryGuideList(QueryGuideListOrder order);

  /**
   * 查询导游列表
   * @param order
   * @return
   */
  PageInfo<GuideInfoDto> queryGuideSearchList(QueryGuideSearchOrder order);
}
