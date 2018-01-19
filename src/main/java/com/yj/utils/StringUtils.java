package com.yj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tao.huang on 2017/5/15.
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }
        return false;
    }
    public static String getLikeString(String str){
        if(StringUtils.isEmpty(str)){
            str = "%%";
        }else{
            str = "%"+str+"%";
        }
        return str;
    }
    public static String get6NumberStringRandom(){
        int str = (int)((Math.random()*9+1)*100000);
        return String.valueOf(str);
    }
    public static boolean validMobile(String mobile) {
        String regExp = "(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
