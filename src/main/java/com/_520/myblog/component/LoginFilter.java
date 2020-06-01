package com._520.myblog.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *  登录请求拦截
 */
@Slf4j
public class LoginFilter implements Filter {

    private String noAuthUrl = "/admin";

    private Set<String> exclusionUrlSet = new ConcurrentSkipListSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String url = filterConfig.getInitParameter("loginUrl");

        exclusionUrlSet.add(noAuthUrl);
        exclusionUrlSet.add("/login");
        exclusionUrlSet.add("redirect:/main.html");
        exclusionUrlSet.add("redirect:/admin");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        log.info("【LongFilter】当前请求 uri = {}", uri);

        // 只对 /admin/** 下的资源拦截
        if (!uri.startsWith(noAuthUrl)){
            chain.doFilter(request, response);
            return;
        }

        if (exclusionUrlSet.contains(uri)){
            chain.doFilter(request, response);
            return;
        }

        String username = (String) req.getSession().getAttribute("loginUser");
        log.info("session 中的用户：{}", username);

        if (StringUtils.isEmpty(username)){
            log.error("您没有访问权限，请先登录");
            resp.sendRedirect("/admin");
            return;
        }

        // 登录成功
        chain.doFilter(request, response);
    }
}
