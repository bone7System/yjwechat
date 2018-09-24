package com.yj.config;

import com.yj.pojo.ReSult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wenhao.zhu on 2017/7/19.
 */
@Configuration
public class YcWebAppConfigurer   implements WebMvcConfigurer {
    private List<Integer> errorCodeList = Arrays.asList(404, 403);

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (errorCodeList.contains(response.getStatus())) {
                    response.setContentType("application/json; charset=utf-8");
                    //捕获异常后进行重定向，controller对应的requestMapping为/error/{code}
                    if(response.getStatus()==403){
                        response.getWriter().print(
                                JSONObject.fromObject(ReSult.error(403L,"没有权限访问"))
                        );
                    }

                    else if(response.getStatus()==404){
                        response.getWriter().print(
                                JSONObject.fromObject(ReSult.error(404L,"请求错误")));
                    };
                    return false;
                }
                return super.preHandle(request, response, handler);
            }
        });
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
