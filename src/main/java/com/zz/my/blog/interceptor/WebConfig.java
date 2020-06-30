package com.zz.my.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类
 * ClassName: WebConfig
 * Description: <br/>
 * date: 2020/6/24 23:47
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截"/admin/**"下的所有访问路径，除"/admin"和"/admin/login"外
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
