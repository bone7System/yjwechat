package com.yj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by wenhao.zhu on 2017/7/19.
 */
@Configuration
public class YcWebAppConfigurer  implements WebMvcConfigurer {




    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){


    }
}
