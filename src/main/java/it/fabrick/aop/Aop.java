package it.fabrick.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Log4j2
@Configuration
public class Aop {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController))")
    public void restControllerMonitoring() {
        // AOP empty method
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.ControllerAdvice))")
    public void controllerAdviceMonitoring() {
        // AOP empty method
    }

    @Before("restControllerMonitoring()")
    public void logControllerMethodEntering(JoinPoint joinPoint) {
        log.info("START {}",
            String.join(
                ".",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName()));
    }

    @AfterReturning(pointcut =
        "restControllerMonitoring() || controllerAdviceMonitoring()")
    public void logControllerMethodExiting(JoinPoint joinPoint) {
        log.info("END {}",
            String.join(
                ".",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName()));
    }

}
