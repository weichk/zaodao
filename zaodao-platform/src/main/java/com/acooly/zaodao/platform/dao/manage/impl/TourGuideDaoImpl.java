package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.TourGuideCusDao;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.enums.IsTourGuide;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.order.QueryGuideListOrder;
import com.acooly.zaodao.platform.order.QueryGuideSearchOrder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/** Created by xiyang on 2017/9/26. */
public class TourGuideDaoImpl extends AbstractJdbcTemplateDao implements TourGuideCusDao {

  /**
   * 2017-10-11 xh modify 1.在zd_tour_guide中分两种情况进行联合查询. #1.1
   * rest_days有值:转换为纵表,筛选被转换的时间字段datetimestr不在输入时间范围内集合a. #1.2 rest_days没有值 union a 得到新的集合a.
   * 2.在zd_plat_order_info中按时间排除三种情况(可用坐标方式画线描述),得到集合b. #2.1 starttime < inputStarttime < endtime
   * #2.2 starttime < inputEndtime < endtime #2.3 inputStarttime < starttime < endtime <
   * inputEndtime 3.a left join b 得到导游数据
   */
  @Override
  public PageInfo<GuideInfoDto> queryGuideList(QueryGuideListOrder order) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String startTime = format.format(order.getStartTime());
    String endTime = format.format(order.getEndTime());
    StringBuilder sbSql = new StringBuilder();

    sbSql.append("SELECT a.*, c.real_name as realName FROM (");
    sbSql.append( " SELECT a.*,SUBSTRING_INDEX(SUBSTRING_INDEX(a.rest_days,',',b.help_topic_id + 1),',',-1) as datetimestr");
    sbSql.append(" FROM zd_tour_guide a");
    sbSql.append(" JOIN mysql.help_topic b ON b.help_topic_id < (length(a.rest_days) - length(replace(a.rest_days,',',''))+1)");
    sbSql.append(" WHERE LENGTH(IFNULL(a.rest_days,''))>0");
    sbSql.append(" UNION SELECT a.*, '' AS datetimestr");
    sbSql.append(" FROM zd_tour_guide a ");
    sbSql.append(" WHERE LENGTH(IFNULL(a.rest_days,''))=0");
    sbSql.append("  ) a LEFT JOIN (");
    sbSql.append(" SELECT DISTINCT tour_guide_id FROM zd_plat_order_info x");
    sbSql.append(String.format(" WHERE (x.order_status='%s' OR x.order_status='%s') AND NOT EXISTS(", OrderStatusEnum.pay.getCode(), OrderStatusEnum.noPay.getCode()));
    sbSql.append(" SELECT 1 FROM zd_plat_order_info y");
    sbSql.append(" WHERE x.tour_guide_id = y.tour_guide_id ");
    sbSql.append(String.format(" AND (('%s' BETWEEN y.start_play_time AND y.end_play_time)", startTime));
    sbSql.append(String.format(" OR ('%s' BETWEEN y.start_play_time AND y.end_play_time)", endTime));
    sbSql.append(String.format(" OR (y.start_play_time BETWEEN '%s' AND y.end_play_time ", startTime));
    sbSql.append(String.format(" AND y.end_play_time BETWEEN '%s' AND '%s'))) ", startTime, endTime));
    sbSql.append(String.format(" ) b ON a.user_id = b.tour_guide_id"));
    sbSql.append(String.format(" JOIN zd_customer c ON a.user_id = c.user_id"));
    sbSql.append(String.format(" WHERE a.datetimestr NOT BETWEEN '%s' AND '%s' AND c.is_tour_guide=%s", startTime, endTime, IsTourGuide.TWO_LEVEL.getKey()));

    if (order.getSex() != null) {
      sbSql.append(String.format(" AND c.sex=%s", order.getSex()));
    }
    if (Strings.isNotBlank(order.getRealName())) {
      sbSql.append(String.format(" AND c.real_name LIKE '%s%s%s'", "%", order.getRealName(), "%"));
    }
    if (Strings.isNotBlank(order.getCity())) {
      sbSql.append(String.format(" AND a.permanent_city LIKE '%s%s%s'", "%", order.getCity(), "%"));
    }
    if (Strings.isNotBlank(order.getLanguage())) {
      sbSql.append(String.format(" AND a.language LIKE '%s%s%s'", "%", order.getLanguage(), "%"));
    }
    if (order.getHotGuide() != null) {
      sbSql.append(String.format(" AND a.hot_guide=%s", order.getHotGuide()));
    }
    if (order.getPriceMin() != null && order.getPriceMin().greaterThan(new Money(0))) {
      sbSql.append(String.format(" AND a.price_per_day >=%s", order.getPriceMin().getCent()));
    }
    if (order.getPriceMax() != null && order.getPriceMax().greaterThan(new Money(0))) {
      sbSql.append(String.format(" AND a.price_per_day <=%s", order.getPriceMax().getCent()));
    }
    if (Strings.isNotBlank(order.getKeywords())) {
      sbSql.append(String.format(" AND (c.real_name LIKE '%s%s%s'", "%", order.getKeywords(), "%"));
      sbSql.append(String.format(" OR a.language LIKE '%s%s%s'", "%", order.getKeywords(), "%"));
      sbSql.append(String.format(" OR a.good_route LIKE '%s%s%s')", "%", order.getKeywords(), "%"));
    }

    PageInfo<GuideInfoDto> pageInfo = query(order.getPageInfo(), sbSql.toString(), GuideInfoDto.class);

    return pageInfo;
  }

  @Override
  public PageInfo<GuideInfoDto> queryGuideSearchList(QueryGuideSearchOrder order) {
    StringBuilder sbSql = new StringBuilder();

    sbSql.append("SELECT a.*, c.real_name AS realName,c.head_img AS headImg FROM zd_tour_guide a");
    sbSql.append(" JOIN zd_customer c ON a.user_id = c.user_id");
    //二级导游才能被约导
    sbSql.append(String.format(" WHERE c.is_tour_guide=%s", IsTourGuide.TWO_LEVEL.getKey()));
    // 注释使用hotguide查询，与pc端热门导游概念一致
    //    if (order.getHotGuide() != null) {
    //      sbSql.append(String.format(" AND a.hot_guide=%s", order.getHotGuide()));
    //    }

    if (order.getGuideLevel() != null) {
      sbSql.append(" AND a.guide_level='" + order.getGuideLevel().getCode() + "'");
    }

    if (Strings.isNotBlank(order.getKeywords())) {
      sbSql.append(String.format(" AND (c.real_name LIKE '%s%s%s'", "%", order.getKeywords(), "%"));
      sbSql.append(String.format(" OR a.language LIKE '%s%s%s'", "%", order.getKeywords(), "%"));
      sbSql.append(String.format(" OR a.good_route LIKE '%s%s%s')", "%", order.getKeywords(), "%"));
    }

    PageInfo<GuideInfoDto> pageInfo = query(order.getPageInfo(), sbSql.toString(), GuideInfoDto.class);

    return pageInfo;
  }
}
