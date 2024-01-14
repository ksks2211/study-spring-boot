package org.example.apps.mvc.config;

import org.example.apps.mvc.interceptor.PerformanceInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author rival
 * @since 2023-10-24
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    private final PerformanceInterceptor performanceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceInterceptor);
    }
}
