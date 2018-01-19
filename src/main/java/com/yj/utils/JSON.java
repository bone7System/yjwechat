package com.yj.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zhiyuan.huang on 2017/7/7.
 */
public class JSON {
    private static ObjectMapper mapper = new ObjectMapper();
    public static String stringify(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
