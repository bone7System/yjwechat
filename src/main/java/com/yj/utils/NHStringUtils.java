package com.yj.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class NHStringUtils {
	private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");  
	public enum TranslateType{
		/** 将大写下划线形式的字符串转换成首字母大写的驼峰形式(TABLE_NAME→TableName)*/
		upper_to_Hump {
			@Override
			String translate(String srcStr) {
				String[] nameItems = srcStr.split("_");
				StringBuffer sb=new StringBuffer();
				for(String str:nameItems){
					if(str==null || str.equals("")){
						continue;
					}
					sb.append(str.substring(0, 1).toUpperCase());
					sb.append(str.substring(1).toLowerCase());
				}
				return sb.toString();
			}
		},
		/** 将大写下划线形式的字符串转换成首字母小写的驼峰形式(TABLE_NAME→tableName)*/
		upper_to_hump{
			@Override
			String translate(String srcStr){
				String[] nameItems = srcStr.split("_");
				StringBuffer sb=new StringBuffer();
				sb.append(nameItems[0].toLowerCase());
				for(int i=1;i<nameItems.length;i++){
					String str=nameItems[i];
					if(str==null || str.equals("")){
						break;
					}
					sb.append(str.substring(0, 1).toUpperCase());
					sb.append(str.substring(1).toLowerCase());
				}
				return sb.toString();
			}
		},
		/**
		 * 将驼峰命名法修改为大写下划线格式
		 */
		hump_to_upper{
			@Override
			String translate(String srcStr) {
				//如果长度为1，则不需要进行转换
				if(srcStr==null || srcStr.length()==1) {
					return srcStr.toUpperCase();
				}
				//如果字符全部都是大写，此时也不需要进行转换
				boolean isUpperFlag = true;
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<srcStr.length();i++){
					char c=srcStr.charAt(i);
					if(c<='Z' && c>='A'){
						sb.append("_");
					}else {
						isUpperFlag=false;
					}
					sb.append(c);
				}
				if(isUpperFlag) {
					return srcStr;
				}
				return sb.toString().toUpperCase();
			}
		};
		abstract String translate(String srcStr);
	}
	/**
	 * 字符串转化为目标格式
	 * @param sourceStr（要转化的字符串）
	 * @param type（目标格式TranslateType的upper_to_Hump,upper_to_hump,hump_to_upper）
	 * @return
	 */
	public static String translateString(String sourceStr,TranslateType type){
		return type.translate(sourceStr);
	}
	/**
	 * 首字母大写
	 * @return
	 */
	public static String makeFirstCharToUpperCase(String str){
		if(str==null || "".equals(str)){
			return str;
		}else{
			return str.substring(0,1).toUpperCase()+str.substring(1);
		}
	}
	/**
	 * 首字母小写
	 * @return
	 */
	public static String makeFirstCharToLowerCase(String str){
		if(str==null || "".equals(str)){
			return str;
		}else{
			return str.substring(0,1).toLowerCase()+str.substring(1);
		}
	}

	/**
	 * 判断字符串是否为空
	 * 返回true表示为空，false表示不为空
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(String str){
		if(str!=null && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 判断字符串是否为非空
	 * 返回true表示为非空，false表示为空
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
			"W", "X", "Y", "Z" };  


	/**
	 * 获取一个长度为8的UUID
	 * @return
	 */
	public static String getShortUuid() {  
		StringBuffer shortBuffer = new StringBuffer();  
		String uuid = UUID.randomUUID().toString().replace("-", "");  
		for (int i = 0; i < 8; i++) {  
			String str = uuid.substring(i * 4, i * 4 + 4);  
			int x = Integer.parseInt(str, 16);  
			shortBuffer.append(chars[x % 0x3E]);  
		}
		return shortBuffer.toString();  
	}  

	/**
	 * 获得一个字符串在UTF-8下的字符串长度
	 * @param str
	 * @return
	 */
	public static int getUTF8Length(String str){
		try {
			return str.getBytes("utf-8").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static String unSplit(String[] strings, String rex) {
		if(strings==null || strings.length==0){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(String str:strings){
			sb.append(rex+str);
		}
		return sb.substring(1);
	}
	
	public static String unSplit(List<String> strings, String rex) {
		if(strings==null || strings.size()==0){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(String str:strings){
			sb.append(rex+str);
		}
		return sb.substring(1);
	}
	
	/**
	 * 从一个Object类型字段获取字符串
	 * @param value
	 * @return
	 */
	public static String getStringFrom(Object value) {
		String valStr=null;
		if(value instanceof String){
			valStr=(String)value;
		}else if(value instanceof Double){
			valStr=decimalFormat.format((double)value);  
		}else if(value instanceof Integer){
			valStr=""+value;
		}else if(value instanceof Date){
			valStr=NHDateUtils.toFullTimeString((Date)value);
		}
		return valStr;
	}

	public static boolean isBlankOrNull(String str){  
		if(null==str)return true;  
		//return str.length()==0?true:false;  
		return str.length()==0;  
	}  
	/**
	 * 对字符串进行批量替换
	 * @param str
	 * @param pattern 如果为null则去除空格，换行，制表符
	 * @param replace
	 * @return
	 */
	public static String replaceSpecialtyStr(String str,String pattern,String replace){  
		if(isBlankOrNull(pattern))  
			pattern="\\s*|\t|\r|\n";//去除字符串中空格、换行、制表  
		if(isBlankOrNull(replace))  
			replace="";  
		return Pattern.compile(pattern).matcher(str).replaceAll(replace);  

	}

}
