package org.example.apps.aop.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author rival
 * @since 2023-10-24
 */
@Aspect
@Component
public class InternalOnlyAspect {

    @Around("@annotation(org.example.apps.aop.annotation.InternalOnly)")
    public Object checkInternalRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String remoteAddr = request.getRemoteAddr();

        if (!"127.0.0.1".equals(remoteAddr) && !"0:0:0:0:0:0:0:1".equals(remoteAddr)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return joinPoint.proceed();
    }
}
