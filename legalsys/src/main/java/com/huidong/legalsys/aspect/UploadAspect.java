package com.huidong.legalsys.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UploadAspect {

    @Pointcut("execution(public * com.huidong.legalsys.controller..*(..))")
    public void upload() {

    }

    @AfterReturning("upload()") {

    }
}
