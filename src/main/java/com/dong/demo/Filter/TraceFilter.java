package com.dong.demo.Filter;

import com.dong.demo.interceptor.TraceInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-20
 **/
//@WebFilter(urlPatterns = "/*",filterName = "myFilter")
public class TraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("Filter初始化中");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("开始进行过滤处理");
        //调用该方法后，表示过滤器经过原来的url请求处理方法
//        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
//        log.info("Filter销毁中");
    }
}
