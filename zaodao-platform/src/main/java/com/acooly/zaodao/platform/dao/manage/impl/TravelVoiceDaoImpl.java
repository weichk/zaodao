package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.TravelVoiceCusDao;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.enums.TravelVoiceType;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * 旅声
 *
 * @author xiaohong
 * @create 2017-10-27 17:41
 **/
@Slf4j
public class TravelVoiceDaoImpl  extends AbstractJdbcTemplateDao implements TravelVoiceCusDao {
    /**
     * 获取旅声列表
     * @param travelVoiceListOrder
     * @return
     */
    @Override
    public PageInfo<TravelVoiceDto> getPageTravelVoiceList(TravelVoiceListOrder travelVoiceListOrder) {
        StringBuilder sbSql = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();
        String appUserId = Strings.isBlank(travelVoiceListOrder.getAppUserId()) ? "" : travelVoiceListOrder.getAppUserId();
        String userId = Strings.isBlank(travelVoiceListOrder.getUserId()) ? "" : travelVoiceListOrder.getUserId();

        sbWhere.append(" WHERE 1=1 ");
        if(Strings.isNotBlank(userId)) {
            sbWhere.append(String.format(" AND a.user_id='%s'", userId));
        }
        sbSql.append("SELECT TA.*,");
        sbSql.append("(CASE WHEN TB.travelVoiceId IS NULL THEN 0 ELSE 1 END) AS praiseFlag, ");
        sbSql.append("(CASE WHEN TC.travelVoiceId IS NULL THEN 0 ELSE 1 END) AS focusAuthorFlag ");
        sbSql.append(" FROM (SELECT ");
        sbSql.append(" a.id AS travelVoiceId,a.user_id AS userId,b.user_name AS realName,b.head_img AS headImg,");
        sbSql.append(" a.title,a.content,a.praise_count AS praiseCount,a.review_count AS reviewCount,a.position_name AS positionName,");
        sbSql.append(" a.position_lat AS positionLat,a.position_lng AS positionLng,a.create_time AS createTime");
        sbSql.append(" FROM zd_travel_voice a");
        sbSql.append(" JOIN zd_customer b ON (a.user_id = b.user_id)");
        sbSql.append(sbWhere);
        TravelVoiceType travelVoiceType = travelVoiceListOrder.getTravelVoiceType();
        if(travelVoiceType != null && travelVoiceType == TravelVoiceType.focus && Strings.isNotBlank(appUserId)){
            sbSql.append(String.format(" AND a.user_id IN(SELECT focus_user_id FROM zd_customer_focus WHERE user_id='%s') ",appUserId));
        }
        sbSql.append(" ) TA LEFT JOIN (");
        sbSql.append(" SELECT a.id AS travelVoiceId FROM zd_travel_voice a");
        sbSql.append(" JOIN zd_travel_praise_log c ON(a.id = c.travel_voice_id) ");
        sbSql.append(sbWhere);
        if(Strings.isNotBlank(appUserId)){
            sbSql.append(String.format(" AND c.user_id='%s' ", appUserId));
        }
        sbSql.append(") TB ON (TA.travelVoiceId=TB.travelVoiceId) LEFT JOIN (");
        sbSql.append(" SELECT DISTINCT a.id AS travelVoiceId FROM zd_travel_voice a");
        sbSql.append(" JOIN zd_customer_focus d ON(a.user_id = d.focus_user_id) ");
        sbSql.append(sbWhere);
        if(Strings.isNotBlank(appUserId)){
            sbSql.append(String.format(" AND d.user_id='%s' ", appUserId));
        }
        sbSql.append(" ) TC ON (TA.travelVoiceId=TC.travelVoiceId) ");

        StringBuilder sbNewSql = new StringBuilder();
        //排除忽略的旅声作者
        if(Strings.isNotBlank(appUserId)){
            sbNewSql.append("SELECT * FROM ( ");
            sbNewSql.append(sbSql.toString());
            sbNewSql.append(") TX WHERE NOT EXISTS(");
            sbNewSql.append(" SELECT 1 FROM zd_customer_ingore a ");
            sbNewSql.append(" WHERE a.ingore_user_id=TX.userId ");
            sbNewSql.append(String.format(" AND a.user_id='%s') ", appUserId));
            sbNewSql.append(" ORDER BY TX.createTime DESC");
        }else{
            sbSql.append(" ORDER BY TA.createTime DESC");
            sbNewSql.append(sbSql.toString());
        }

        log.info(sbNewSql.toString());

        PageInfo<TravelVoiceDto> pageInfo = query(travelVoiceListOrder.getPageInfo(), sbNewSql.toString(), TravelVoiceDto.class);
        //资源列表
        for (TravelVoiceDto travelVoiceDto: pageInfo.getPageResults()) {
            if(null != travelVoiceDto.getTravelVoiceId()) {
                StringBuilder sbResourceSql = new StringBuilder();
                sbResourceSql.append("SELECT c.travel_voice_id AS travelVoiceId,c.ofile_id AS ofileId,c.ofile_type AS ofileType,");
                sbResourceSql.append("c.cover_url AS coverUrl,c.resource_url AS resourceUrl");
                sbResourceSql.append(" FROM zd_travel_resource c");
                sbResourceSql.append(" JOIN zd_travel_voice a ON(c.travel_voice_id=a.id)");
                sbResourceSql.append(String.format(" WHERE c.travel_voice_id=%s", travelVoiceDto.getTravelVoiceId()));

                List<TravelResourceDto> travelResourceDtoList = queryForList(sbResourceSql.toString(), TravelResourceDto.class);
                travelVoiceDto.setTravelResourceList(travelResourceDtoList);
            }
        }

        return pageInfo;
    }

    /**
     * 获取旅声详情
     * @param travelVoiceDetailOrder
     * @return
     */
    @Override
    public TravelVoiceDto getTravelVoiceDetail(TravelVoiceDetailOrder travelVoiceDetailOrder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id as travelVoiceId,a.user_id as userId,b.real_name as realName,b.head_img as headImg,a.title,a.content,");
        sql.append("a.praise_count as praiseCount,a.review_count as reviewCount,a.position_name as positionName,");
        sql.append("a.position_lat as positionLat,a.position_lng as positionLng,a.create_time as createTime ");
        sql.append(" FROM zd_travel_voice a JOIN zd_customer b ON(a.user_id=b.user_id) ");
        sql.append(String.format(" WHERE a.id=%s", travelVoiceDetailOrder.getTravelVoiceId()));

        //List<TravelVoiceDto> list = jdbcTemplate.query(sql.toString(), new Object[] {}, new BeanPropertyRowMapper<>(TravelVoiceDto.class));
        List<TravelVoiceDto> list = queryForList(sql.toString(), TravelVoiceDto.class);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
