package com.icu.mybatis.filters;

import com.icu.mybatis.utils.TokenHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
// 拦截所有请求
@WebFilter(urlPatterns = "/*")
@Service // 注册Spring Bean对象，方便依赖注入
public class TokenFilter implements Filter { // 实现 Servlet 包下的 Filter 接口
    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 获取请求uri “/login”、“/users/list”
        String uri = req.getRequestURI();
        log.info("LoginFilter过滤器执行：{}", uri);

        // 如果是登录请求，放行
        if("/login".equals(uri)){
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求头中的token内容
        String token = req.getHeader("token");

        try {
            // 解析token
            Claims claims = tokenHelper.parseToken(token);
            // 解析成功，放行
            if(claims != null){
                log.info("token有效：{}", claims);
                filterChain.doFilter(request, response);
                return;
            }
        }catch (Exception ex){
            log.warn("token失效：{}", ex);
        }

        // 解析失败，设置401响应头
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(401);
        resp.setHeader("token", null);
        resp.setHeader("Content-Type", "application/json;charset=UTF-8");
        resp.getWriter().write("{\"code\":401,\"msg\":\"token无效\"}");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("LoginFilter初始化");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("LoginFilter销毁");
    }
}