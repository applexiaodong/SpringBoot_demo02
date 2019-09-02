package com.dong.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 注册拦截器
 * @author: wangxd
 * @create: 2019-06-20
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${uploadFile.location}")
    private String location;

    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(traceInterceptor())
//                //拦截所有请求
//                .addPathPatterns("/**")
//                //排除不需要拦截的请求
//                .excludePathPatterns("/","/login","/admin/*","/main","/loginOut")
//                //排除静态资源
//                .excludePathPatterns("/static/**");
//    }

//    @Bean
//    public TraceInterceptor traceInterceptor(){
//        return new TraceInterceptor();
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 配置静态资源映射
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //url中出现resourceHandler匹配时，映射到location中，location虚拟路径
        registry.addResourceHandler(resourceHandler)
                .addResourceLocations("file:///"+location);//file:///表示协议
    }
}
