package com.yj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wenhao.zhu on 2017/7/19.
 */
@Configuration
public class YcWebAppConfigurer  extends WebMvcConfigurerAdapter{

    @Value("${wx.image}")
    String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:"+path);

        super.addResourceHandlers(registry);
    }
}
