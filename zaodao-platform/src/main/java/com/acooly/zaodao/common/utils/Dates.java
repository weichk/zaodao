package com.acooly.zaodao.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Dates extends com.acooly.core.utils.Dates {

	/**
	 * 判断日期是否在最近三个月当中
	 *
	 * @param day
	 * @return
	 */
	public static boolean checkLatest(String day) {
		Date dStart = new Date(); // 当前时间
		Date dEnd = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dStart);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, 3); // 设置为后3月
		dEnd = calendar.getTime(); // 得到前3月的时间
		String start = com.acooly.core.utils.Dates.format(dStart, com.acooly.core.utils.Dates.CHINESE_DATE_FORMAT_LINE);
		String end = com.acooly.core.utils.Dates.format(dEnd, com.acooly.core.utils.Dates.CHINESE_DATE_FORMAT_LINE);
		if (com.acooly.core.utils.Dates.parse(day).compareTo(com.acooly.core.utils.Dates.parse(start)) >= 0 && com.acooly.core.utils.Dates.parse(day).compareTo(com.acooly.core.utils.Dates.parse(end)) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static List<String> getBetweenDates(Date start, Date end) {
		List<String> result = Lists.newArrayList();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.compareTo(tempEnd) <= 0) {
			result.add(com.acooly.core.utils.Dates.format(tempStart.getTime(), com.acooly.core.utils.Dates.CHINESE_DATE_FORMAT_LINE));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	public static String generateTransactionNo() {
		return com.acooly.core.utils.Dates.format(new Date(), com.acooly.core.utils.Dates.DATETIME_NOT_SEPARATOR)
				+ RandomStringUtils.randomNumeric(3);
	}

	/**
	 * 取得指定日期的当月号数
	 * @param date
	 * @return
	 */
	public static int getDateOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 生成日期（传入日期的年月+传入的号数）</p>
	 * 		如果传入号数大于传入日期当月最大号数，取传入日期当月最后一天
	 * @param date
	 * @return
	 */
	public static Date setDate(Date date,int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDaysForMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (day > maxDaysForMonth){
			day = maxDaysForMonth;
		}
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * 生成日期（传入日期的年月+传入的号数）</p>
	 * 		如果传入号数大于传入日期当月最大号数，取传入日期当月最后一天
	 * @param date
	 * @return
	 */
	public static Date addMinute(Date date,int value){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, value);
		return cal.getTime();
	}

	public static int getCountNaturalDay(Date startDate,Date endDate){
		return Long.valueOf(DateUtil.countDays(com.acooly.core.utils.Dates.format(startDate, "yyyy-MM-dd"), com.acooly.core.utils.Dates.format(endDate, "yyyy-MM-dd"))).intValue();
	}
}
