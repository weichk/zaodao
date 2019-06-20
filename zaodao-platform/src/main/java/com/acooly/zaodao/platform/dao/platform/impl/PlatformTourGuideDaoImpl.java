package com.acooly.zaodao.platform.dao.platform.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.platform.PlatformTourGuideDao;
import com.acooly.zaodao.platform.dto.EnshrineDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.portal.dto.TourGuideSearchDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
@Service
public class PlatformTourGuideDaoImpl extends AbstractJdbcTemplateDao implements PlatformTourGuideDao {

	@Override
	public PageInfo<TourGuideDto> queryByConditions(PageInfo<TourGuideDto> pageInfo, TourGuideSearchDto guideDto) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.*,t.guide_no,t.guide_certificate_no,t.tour_time,t.tour_rank,t.permanent_city,t.province,"
		        + "t.good_route,t.introduce_myself,t.speciality,t.price_per_day,t.language,t.star_level,t.hot_guide,t"
		        + ".guide_cover_img" + " FROM zd_customer as c,zd_tour_guide as t WHERE c.user_id = t.user_id and c"
		        + ".is_tour_guide=1");
		if (Strings.isNotBlank(guideDto.getGender())) {
			sql.append(" and c.sex = '" + guideDto.getGender() + "'");
		}
		if (Strings.isNotBlank(guideDto.getCity())) {
			sql.append(" and t.permanent_city like '%" + guideDto.getCity() + "%'");
		}
		if (Strings.isNotBlank(guideDto.getLevel())) {
			sql.append(" and t.tour_rank = '" + guideDto.getLevel() + "'");
		}
		if (Strings.isNotBlank(guideDto.getProvince())) {
			sql.append(" and t.province like '%" + guideDto.getProvince() + "%'");
		}
		if (Strings.isNotBlank(guideDto.getKeywords())) {
			sql.append(" and c.real_name like '%" + guideDto.getKeywords() + "%'");
		}
		if (Strings.isNotBlank(guideDto.getLanguage())) {
			sql.append(" and t.language like '%" + guideDto.getLanguage() + "%'");
		}
		if (guideDto.getHotGuide() > -1) {
			sql.append(" and t.hot_guide = " + guideDto.getHotGuide() + "");
		}
		if (Strings.isNotBlank(guideDto.getGuideLevel())) {
			sql.append(" and t.guide_level = '" + guideDto.getGuideLevel() + "'");
		}
		// 注释时间查询条件
		// if (Strings.isNotBlank(guideDto.getTripTime())) {
		// sql.append(" and t.user_id not in" + "(SELECT tour_guide_id FROM
		// zd_plat_order_info "
		// + " WHERE order_status = 'pay' or order_status = 'nopay'" + " and '"
		// + guideDto.getTripTime()
		// + "' between start_play_time and end_play_time)");
		// }
		// System.out.println(sql.toString());
		PageInfo<TourGuideDto> result = query(pageInfo, sql.toString(), TourGuideDto.class);
		return result;
	}

	@Override
	public TourGuideDto findByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.*,t.guide_no,t.guide_certificate_no,t.tour_time,t.tour_rank,t.permanent_city,t.province,"
		        + "t.good_route,t.introduce_myself,t.speciality,t.price_per_day,t.language,t.star_level,t.hot_guide,t"
		        + ".guide_cover_img"
		        + " FROM zd_customer as c,zd_tour_guide as t WHERE c.user_id = t.user_id and t.user_id ='" + userId
		        + "' limit 1");
		List<TourGuideDto> list = jdbcTemplate.query(sql.toString(), new Object[] {},
		        new BeanPropertyRowMapper<TourGuideDto>(TourGuideDto.class));
		if (null != list && list.size() > 0) {
			TourGuideDto dto = list.get(0);
			return dto;
		}
		return null;
	}

	@Override
	public PageInfo<EnshrineDto> queryEnshrineList(PageInfo<EnshrineDto> pageInfo, String userId, String title) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT zae.user_id as userId, za.id as id,za.title,za.read_count readCount,za"
		        + ".praise_count praiseCount"
		        + ",za.create_time createTime from zd_article as za,zd_article_enshrine_log as zae "
		        + " where zae.article_id = za.id and zae.user_id='" + userId + "'");
		if (Strings.isNotBlank(title)) {
			sql.append(" and za.title like = '" + title + "'");
		}
		PageInfo<EnshrineDto> result = query(pageInfo, sql.toString(), EnshrineDto.class);
		return result;
	}
}
