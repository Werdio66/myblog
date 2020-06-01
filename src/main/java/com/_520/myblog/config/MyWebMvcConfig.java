package com._520.myblog.config;

import com._520.myblog.component.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    // 视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 登录
        registry.addViewController("/admin").setViewName("login");
        // 登录成功后的跳转页面
        registry.addViewController("/main.html").setViewName("redirect:/admin/blog/index");
        // 前端页面
        registry.addViewController("/index").setViewName("index");

    }

    /**
     *  登录过滤器
     */
    @Bean
    public FilterRegistrationBean<LoginFilter> registrationBean(){
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.addInitParameter("loginUrl", "/admin");
        return filterRegistrationBean;
    }
}
