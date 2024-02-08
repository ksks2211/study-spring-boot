package org.example.apps.aop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author rival
 * @since 2023-10-24
 */
@Component
@Slf4j
public class PerformanceInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL: {} | Time taken: {} ms", request.getRequestURL(), System.currentTimeMillis() - startTime);
    }
}
