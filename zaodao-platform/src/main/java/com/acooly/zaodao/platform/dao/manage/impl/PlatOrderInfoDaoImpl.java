/*
 * 修订记录:
 * zhike@yiji.com 2017-06-11 23:58 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.PlatOrderInfoCusDao;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Slf4j
public class PlatOrderInfoDaoImpl extends AbstractJdbcTemplateDao implements PlatOrderInfoCusDao {

    @Override
    public PageInfo<OrderInfoDto> getPageOrderInfoListByUserId(PageInfo<OrderInfoDto> pageInfo, String userId, String orderStatus, Integer isTourGuide) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT zc.head_img headImg,zc.user_name userName,zpoi.id orderInfoId,");
        sql.append("zpoi.start_play_time startPlayTime,zpoi.end_play_time endPlayTime,");
        sql.append("zpoi.adult_count adultCount,zpoi.child_count childCount,");
        sql.append("IFNULL(zpoi.order_amount,0) orderAmount,zpoi.order_status orderStatus,");
        sql.append("zpoi.order_no orderNo,zpoi.order_no platOrderNo,zpoi.create_time createTime,");
        sql.append("ztg.permanent_city destination,zpoi.day_count dayCount,IFNULL(zpoi.price_per_day,0) pricePerday ");

        //如果是导游
        if(isTourGuide == 1) {
            sql.append(",zpoi.contact_name realName ");
        }else{
            sql.append(",zc.real_name realName ");
        }

        sql.append(" FROM zd_plat_order_info zpoi");
        if (isTourGuide == 1) {
            sql.append(" INNER JOIN zd_customer zc ON zpoi.tourist_id = zc.user_id");
        } else {
            sql.append(" INNER JOIN zd_customer zc ON zpoi.tour_guide_id = zc.user_id");
        }
        sql.append(" INNER JOIN zd_tour_guide ztg ON zpoi.tour_guide_id = ztg.user_id");
        sql.append(" WHERE 1=1");

        if(Strings.isNotBlank(orderStatus)) {
            if (!StringUtils.equals(orderStatus, "all")) {
                if (StringUtils.equals(orderStatus, "pay")) {
                    sql.append(" AND (zpoi.order_status='" + orderStatus + "' or zpoi.order_status='confirm')");
                } else {
                    sql.append(" AND zpoi.order_status='" + orderStatus + "'");
                }
            }
        }
        if(Strings.isNotBlank(userId)) {
            if (isTourGuide == 1) {
                sql.append(" AND zpoi.tour_guide_id = '" + userId + "'");
            } else {
                sql.append(" AND zpoi.tourist_id = '" + userId + "'");
            }
        }
        sql.append(" AND zpoi.order_status <> 'delete'");
        sql.append(" order by zpoi.create_time desc");

        //log.info(sql.toString());
        PageInfo<OrderInfoDto> result = query(pageInfo, sql.toString(), OrderInfoDto.class);
        return result;
    }

    @Override
    public PageInfo<PlatOrderInfo> getPageOrderInfoListByOrderStatus(PageInfo<PlatOrderInfo> pageInfo, String orderStatus) {
        String sql = "SELECT * FROM zd_plat_order_info where order_status = '" + orderStatus + "' and datediff(curdate(), create_time)>=3 order by create_time ASC";
        PageInfo<PlatOrderInfo> result = query(pageInfo, sql.toString(), PlatOrderInfo.class);
        return result;
    }

    /**
     * 通过订单号获取订单
     * @param orderNo
     * @return
     */
    @Override
    public OrderInfoDto getOrderDetail(String orderNo) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("SELECT b.head_img headImg,b.real_name realName,b.user_name userName,a.day_count dayCount,");
        sbSql.append("a.contact_memo contactMemo,a.contact_name contactName,a.contact_phone contactPhone,a.id orderInfoId,");
        sbSql.append("a.start_play_time startPlayTime,a.end_play_time endPlayTime,a.adult_count adultCount,");
        sbSql.append("a.order_amount orderAmount,a.order_status orderStatus,a.order_no platOrderNo,");
        sbSql.append("a.child_count childCount,a.create_time createTime,c.permanent_city destination,");
        sbSql.append("IFNULL(a.price_per_day,0) pricePerday,c.user_id guideUserId,d.real_name guideName,a.tourist_id userId,");
        sbSql.append("a.travel_agency_id AS travelAgencyId,a.reservation_type AS reservationType,");
        sbSql.append("a.close_reason_type AS closeReasonType,a.close_reason_desc AS closeReasonDesc,e.license_no AS licenseNo,");
        sbSql.append("d.mobile_no AS guideMobileNo");
        sbSql.append(" FROM zd_plat_order_info a");
        sbSql.append(" JOIN zd_customer b ON a.tourist_id = b.user_id ");
        sbSql.append(" JOIN zd_tour_guide c ON a.tour_guide_id = c.user_id ");
        sbSql.append(" JOIN zd_customer d ON c.user_id = d.user_id ");
        sbSql.append(" LEFT JOIN zd_travel_agency e ON a.travel_agency_id=e.id");
        sbSql.append(String.format(" WHERE a.order_no='%s'", orderNo));

        List<OrderInfoDto> list = queryForList(sbSql.toString(), OrderInfoDto.class);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;

    }
}
