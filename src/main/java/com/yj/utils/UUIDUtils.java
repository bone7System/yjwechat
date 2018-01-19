package com.yj.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by zhiyuan.huang on 2017/6/13.
 */
@Component
public class UUIDUtils {
    // TODO: 2017/6/13 生成的ID存入数据库，作惟一性验证
    // 创建8位的唯一ID
    public String get() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
