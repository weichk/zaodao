package com.acooly.zaodao.platform.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
@Data
public class CityHotGuideDto {

	private String city;

	private String cityCode;

	private List<TourGuideDto> guides;
}
