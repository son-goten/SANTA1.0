package com.example.santa.log.aop;

import com.example.santa.log.service.MongoLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final MongoLogService mongoLogService;

    public LoggingAspect(MongoLogService mongoLogService) {
        this.mongoLogService = mongoLogService;
    }
    // 예시 - 향후 개발시 추가해야함
    // 사용자 활동 로그
    @AfterReturning(pointcut = "execution(* com.example.santa.board.*.*(..))", returning = "result")
    public void logUserActivity(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        mongoLogService.saveLog(1L, "USER", methodName, "User activity logged");
    }

    // 관리자 활동 로그
    @AfterReturning(pointcut = "execution(* com.example.santa.product.*.*(..))", returning = "result")
    public void logAdminActivity(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        mongoLogService.saveLog(1L, "ADMIN", methodName, "Admin activity logged");
    }
}

