package com._520.myblog.config;

import com._520.myblog.component.MylocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    // 视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if (registry != null){
            // 登录
            registry.addViewController("/admin").setViewName("login");
            // 登录成功后的跳转页面
            registry.addViewController("/main.html").setViewName("redirect:/admin/blog/index");
            // 前端页面
//            registry.addViewController("/index").setViewName("index");
        }
    }

    // 语言解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MylocaleResolver();
    }
}
