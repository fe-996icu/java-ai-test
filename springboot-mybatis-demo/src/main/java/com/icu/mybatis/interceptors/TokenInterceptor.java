package com.icu.mybatis.interceptors;

import com.icu.mybatis.exception.TokenInvalidException;
import com.icu.mybatis.utils.ThreadLocalHelper;
import com.icu.mybatis.utils.TokenHelper;
import com.icu.mybatis.vo.login.LoginResultVo;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service // 添加@Service注解，方便依赖注入
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenHelper tokenHelper;
    @Override
    // 目标资源方法执行前执行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        log.info("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.warn("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.error("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.debug("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        log.trace("TokenInterceptor拦截器执行-[preHandle]：{}", req.getRequestURI());
        String token = req.getHeader("token");
        if(token == null || token.isEmpty()){
            log.info("token为空");
            throw new TokenInvalidException("token为空");
        }

        try {
            LoginResultVo loginResultVo = tokenHelper.parseToken(token);
            ThreadLocalHelper.set(loginResultVo);
            log.info("token有效-[{}]： {}", req.getRequestURI(), loginResultVo);
            return true;
        }catch (Exception ex){
            log.info("token无效");
            throw new TokenInvalidException("token无效");
        }
    }

    @Override
    // 目标资源方法执行后执行
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("TokenInterceptor拦截器执行-[postHandle]：{}", req.getRequestURI());
        // 请求结束，清除ThreadLocal
        ThreadLocalHelper.remove();
    }

    @Override
    // 视图渲染完毕后执行，最后执行
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) throws Exception {
        log.info("TokenInterceptor拦截器执行-[afterCompletion]：{}", req.getRequestURI());
    }
}
