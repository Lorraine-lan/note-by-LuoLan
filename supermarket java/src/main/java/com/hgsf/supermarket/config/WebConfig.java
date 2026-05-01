package com.hgsf.supermarket.config;

import com.hgsf.supermarket.interceptor.CrossOriginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wanjianhong
 * @Version: 1.0
 * @Description:
 * WEB配置类，注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CrossOriginInterceptor crossOriginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册跨域请求拦截器
        registry.addInterceptor(crossOriginInterceptor);
    }
}
