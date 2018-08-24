package com.yj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NHDateUtils {
	public final static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public final static SimpleDateFormat fullTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获得日期类型的时间戳
	 * @param date
	 * @return
	 */
	public static String buildTimeSign(Date date){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}
	/**
	 * @param dateStr yyyy-MM-dd
	 * @return
	 */
	public static Date parseDate(String dateStr) throws Exception {
		if(dateStr==null || "".equals(dateStr)){
			return null;
		}
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new Exception("日期格式有误，只接受yyyy-MM-dd格式的日期");
		}
	}
	/**
	 * 时间格式化，将String类型转化为Date类型，格式为：yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public static Date superParseDate(String dateStr){
		Date result=null;
		if(dateStr==null || "".equals(dateStr)){
			return null;
		}
		dateStr=dateStr.replace("年", "-").replace("月", "-").replace("日","-").replace("/","-");
		try{
			result=dateFormat.parse(dateStr);
		}catch(ParseException e){
			result=null;
		}
		return result;
	}
	/**
	 * @param timeStr yyyy-MM-dd HH:mm
	 * @return
	 */
	public static Date parseTime(String timeStr) throws Exception {
		if(timeStr==null || "".equals(timeStr)){
			return null;
		}
		try {
			return dateTimeFormat.parse(timeStr);
		} catch (ParseException e) {
			throw new Exception("时间格式，只接受yyyy-MM-dd HH:mm格式的时间");
		}
	}
	
	/**
	 * @param timeStr yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseFullTime(String timeStr) throws Exception {
		if(timeStr==null || "".equals(timeStr)){
			return null;
		}
		try {
			return fullTimeFormat.parse(timeStr);
		} catch (ParseException e) {
			throw new Exception("时间格式，只接受yyyy-MM-dd HH:mm:ss格式的时间");
		}
	}
	/**
	 * Date类型转化为String，格式为：yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date){
		return dateFormat.format(date);
	}
	/**
	 * Date类型转化为String，格式为：yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String toTimeString(Date date){
		return dateTimeFormat.format(date);
	}

	/**
	 * Date类型转化为String，格式为：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String toFullTimeString(Date date){
		return fullTimeFormat.format(date);
	}
}
