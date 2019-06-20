package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CustomerFocusCusDao;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.CustomerFocusDto;
import com.acooly.zaodao.platform.order.CustomerFocusCountOrder;
import com.acooly.zaodao.platform.order.CustomerFocusListOrder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * 关注数量和粉丝数
 *
 * @author xiaohong
 * @create 2017-10-30 18:19
 **/
public class CustomerFocusDaoImpl extends AbstractJdbcTemplateDao implements CustomerFocusCusDao {
    @Override
    public CustomerFocusCountDto getCustomerFocusCount(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM  ");
        sql.append(String.format("(SELECT COUNT(1) AS count FROM zd_customer_focus WHERE user_id='%s') a,", userId));
        sql.append(String.format("(SELECT COUNT(1) AS focusCount FROM zd_customer_focus WHERE focus_user_id='%s') b", userId));

        List<CustomerFocusCountDto> list = jdbcTemplate.query(sql.toString(), new Object[] {}, new BeanPropertyRowMapper<>(CustomerFocusCountDto.class));
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取关注/粉丝列表
     * @param customerFocusListOrder
     * @return
     */
    @Override
    public PageInfo<CustomerFocusDto> getCustomerFocusList(CustomerFocusListOrder customerFocusListOrder) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT b.user_id AS userId,b.user_name AS userName,b.real_name AS realName,b.head_img AS headImg");
        if(customerFocusListOrder.getFocusFlag() == 0){
            //粉丝
            sql.append(" FROM zd_customer_focus a JOIN zd_customer b ON(a.user_id=b.user_id)");
            sql.append(String.format("WHERE a.focus_user_id='%s'", customerFocusListOrder.getUserId()));
        }else if(customerFocusListOrder.getFocusFlag() == 1){
            //关注的人
            sql.append(" FROM zd_customer_focus a JOIN zd_customer b ON(a.focus_user_id=b.user_id)");
            sql.append(String.format(" WHERE a.user_id='%s'", customerFocusListOrder.getUserId()));
        }
        PageInfo<CustomerFocusDto> pageInfo = query(customerFocusListOrder.getPageInfo(), sql.toString(), CustomerFocusDto.class);

        return pageInfo;
    }

    /*
    StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id as travelVoiceId,a.user_id as userId,b.real_name as realName,b.head_img as headImg,a.title,a.content,");
        sql.append("a.praise_count as praiseCount,a.review_count as reviewCount,a.position_name as positionName,");
        sql.append("a.position_lat as positionLat,a.position_lng as positionLng,a.create_time as createTime ");
        sql.append(" FROM zd_travel_voice a JOIN zd_customer b ON(a.user_id=b.user_id) ");

        if(!Strings.isBlank(travelVoiceListOrder.getUserId())) {
            sql.append(String.format(" WHERE a.user_id='%s'", travelVoiceListOrder.getUserId()));
        }
        sql.append(" ORDER BY a.create_time desc");

        PageInfo<TravelVoiceDto> pageInfo = query(travelVoiceListOrder.getPageInfo(), sql.toString(), TravelVoiceDto.class);

        return pageInfo;
    * */
}
