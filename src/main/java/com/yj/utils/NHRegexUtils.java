package com.yj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NHRegexUtils {
	
	/**
	 * 验证数据的正确性
	 * 排除掉可能会存在导致代码注入和跨站点访问的特殊字符
	 * 只有字符串做判断
	 * @param str
	 */
	public static void regexNoSpecialChar(Object str) throws Exception {
		String aimStr=null;
		if(str instanceof String){
			aimStr=(String)str;
		}else{
			return;
		}
		if(aimStr==null || "".equals(aimStr)){
			return;
		}
		String regex="[^%$&<>'\"=;:]{1,}";
	    Pattern p=Pattern.compile(regex);
	    Matcher m=p.matcher(aimStr);
		if(!m.matches()){//包含了上述字符
			throw new Exception("数据有误，存在可引起系统故障的特殊字符，请联系管理员！");
		}
	}
	
	/**
	 * 验证数据是否是整数
	 * @param str
	 */
	public static void regexInteger(Object str) throws Exception {
		String aimStr=null;
		if(str instanceof String){
			aimStr=(String)str;
		}else{
			return;
		}
		if(aimStr==null || "".equals(aimStr)){
			return;
		}
		String regex="^-?\\d+$";
	    Pattern p=Pattern.compile(regex);
	    Matcher m=p.matcher(aimStr);
		if(!m.matches()){//包含了上述字符
			throw new Exception("数据有误，不是整型格式，请联系管理员！");
		}
	}
	
}
