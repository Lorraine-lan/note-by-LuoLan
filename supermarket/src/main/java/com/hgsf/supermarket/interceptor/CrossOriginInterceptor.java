package com.hgsf.supermarket.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wanjianhong
 * @Version: 1.0
 * @Description:
 * 配置跨域请求处理类
 */
@Component
public class CrossOriginInterceptor implements HandlerInterceptor {

    public CrossOriginInterceptor(){
        System.out.println("*********init CrossOriginInterceptor");
    }
    //请求前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*****CrossOriginInterceptor prehandle 111****");

        //设置响应数据格式及编码
        response.setContentType("application/json;charset=UTF-8");
        //跨域处理
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "content-Type,x-requested-with,Authorization,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        //放行请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
