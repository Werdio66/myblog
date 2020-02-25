package com._520.myblog.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  自定义拦截器
 */
public class MyLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");

        // 没有登录转发到登录页面
        if (loginUser == null){
            request.setAttribute("msg", "你没有权限，请登录");
            request.getRequestDispatcher("/admin").forward(request, response);
            return false;
        }

        return true;
    }
}
