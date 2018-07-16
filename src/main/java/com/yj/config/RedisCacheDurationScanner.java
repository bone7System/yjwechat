package com.yj.config;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

public class RedisCacheDurationScanner {
    private ApplicationContext applicationContext;
    private RedisCacheConfiguration redisCacheConfiguration;

    public RedisCacheDurationScanner(ApplicationContext applicationContext, RedisCacheConfiguration redisCacheConfiguration) {
        this.applicationContext = applicationContext;
        this.redisCacheConfiguration = redisCacheConfiguration;
    }

    public Map<String, RedisCacheConfiguration> scanCacheConfigurations() {
        return parseCacheDuration(applicationContext);
    }
    private Map<String, RedisCacheConfiguration> parseCacheDuration(ApplicationContext applicationContext) {
        final Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            Service service = findAnnotation(clazz, Service.class);
//            Service service = findAnnotation(clazz, Service.class);
            if (null == service) {
                continue;
            }
            addcacheConfigurations(clazz, cacheConfigurations);
        }
        return cacheConfigurations;
    }
    private void addcacheConfigurations(final Class clazz, final Map<String, RedisCacheConfiguration> cacheConfigurations) {
        ReflectionUtils.doWithMethods(clazz, method -> {
            ReflectionUtils.makeAccessible(method);
            CacheDuration cacheDuration = findCacheDuration(clazz, method);
            if (cacheDuration != null) {
                Cacheable cacheable = findAnnotation(method, Cacheable.class);
                CacheConfig cacheConfig = findAnnotation(clazz, CacheConfig.class);
                Set<String> cacheNames = findCacheNames(cacheConfig, cacheable);
                for (String cacheName : cacheNames) {
                    if (cacheDuration.duration() > 0) {
                        cacheConfigurations.put(cacheName, redisCacheConfiguration.entryTtl(Duration.ofSeconds(cacheDuration.duration())));
                    }
                }
            }
        }, method -> null != findAnnotation(method, Cacheable.class));
    }
    /**
     * CacheDuration标注的有效期，优先使用方法上标注的有效期
     * @param clazz
     * @param method
     * @return
     */
    private CacheDuration findCacheDuration(Class clazz, Method method) {
        CacheDuration methodCacheDuration = findAnnotation(method, CacheDuration.class);
        if (null != methodCacheDuration) {
            return methodCacheDuration;
        }
        CacheDuration classCacheDuration = findAnnotation(clazz, CacheDuration.class);
        if (null != classCacheDuration) {
            return classCacheDuration;
        }
        return null;
    }
    private Set<String> findCacheNames(CacheConfig cacheConfig, Cacheable cacheable) {
        return StringUtils.isEmpty(cacheable.value()) ?
                array2Set(cacheConfig.cacheNames()) : array2Set(cacheable.value());
    }

    private static Set<String> array2Set(String[] param) {
        //数组-->Set
        Set<String> set = new HashSet<String>(Arrays.asList(param));
        return set;
    }
}


