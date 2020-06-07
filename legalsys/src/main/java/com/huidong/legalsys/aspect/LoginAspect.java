package com.huidong.legalsys.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 权限拦截切面，为登录用户只能进行注册，登录及查看首页推送等操作
 */
@Aspect
@Component
public class LoginAspect {

    @Pointcut("execution(public * com.huidong.legalsys.controller..*(..)) && " +
            "!execution(public * com.huidong.legalsys.controller.LoginController.*(..)) && " +
            "!execution(public * com.huidong.legalsys.controller.StatisticsController.*(..))")
    public void login(){

    }

    @Around("login()")
    public Object isLogin(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes=(ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        return joinPoint.proceed();
    }
}
