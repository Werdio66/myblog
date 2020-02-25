/*
package com._520.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity  // 启用 webSecurity 注解
//@Configuration
public class MyWebSercureryConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 对这些资源放行
                .antMatchers("/static/**", "/", "/login.html").permitAll()
                // 其他的资源必须验证
                .anyRequest().authenticated();

        http.formLogin()
                // 去登录页面
                .loginPage("/")
                // 登录表单提交路径
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功后跳转页面
                .successForwardUrl("/main");

    }
}
*/
