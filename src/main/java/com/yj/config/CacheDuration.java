package com.yj.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CacheDuration {
    //Sets the expire time (in seconds).
//    public long duration() default 24 * 60 * 60;        // 默认为一天

    public long duration() default 10 * 60;        // 默认为10分钟
}

