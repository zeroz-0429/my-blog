package com.zz.my.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * ClassName: LoginInterceptor
 * Description: 未登录的用户无法进入后台管理页面
 * date: 2020/6/24 23:42
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session中是否有user参数，为空则拦截并重定向到登录界面，否则放行
        if (request.getSession().getAttribute("user") ==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
