package com.yj.utils;

import java.math.BigDecimal;

/**
 * Created by tao.huang on 2017/5/15.
 */
public class ObjectUtils {


    public static boolean isEmpty(Object obj){
        if(obj == null){
            return true;
        }
        return false;
    }

    public static BigDecimal getBigDecimal(BigDecimal b){
        if(b==null){
            return new BigDecimal(0);
        }

        return b;
    }
}
