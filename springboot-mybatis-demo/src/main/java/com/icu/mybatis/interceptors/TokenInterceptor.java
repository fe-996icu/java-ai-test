package com.icu.mybatis.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    // 目标资源方法执行前执行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        log.info("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.warn("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.error("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.debug("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.trace("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        return true;
    }

    @Override
    // 目标资源方法执行后执行
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("TokenInterceptor拦截器执行-[postHandle]：{}", req.getRequestURI());
    }

    @Override
    // 视图渲染完毕后执行，最后执行
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) throws Exception {
        log.info("TokenInterceptor拦截器执行-[afterCompletion]：{}", req.getRequestURI());
    }
}
